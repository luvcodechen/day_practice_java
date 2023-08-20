package machineLearning.adaboosting;

import weka.core.Instance;
import weka.core.Instances;

import java.io.FileReader;
import java.io.IOException;

/**
 * ClassName: Booster
 * Package: machineLearning.adaboosting
 * Description: The booster which ensembles base classifiers.
 *
 * @Author: luv_x_c
 * @Create: 2023/8/18 14:34
 */
public class Booster {
    /**
     * Classifiers.
     */
    SimpleClassifier[] classifiers;

    /**
     * Number of classifiers.
     */
    int numClassifiers;

    /**
     * Whether stop after the training error is 0.
     */
    boolean stopAfterConverge = false;

    /**
     * The weights of classifier.
     */
    double[] classifierWeights;

    /**
     * The training data.
     */
    Instances trainingData;

    /**
     * The testing data.
     */
    Instances testingData;

    /**
     * The first constructor. The testing set is the same as the training data.
     *
     * @param paraTrainingFileName The data file name.
     */
    public Booster(String paraTrainingFileName) {
        // Step1. Read the training set.
        try {
            FileReader fileReader = new FileReader(paraTrainingFileName);
            trainingData = new Instances(fileReader);
            fileReader.close();
        } catch (IOException e) {
            System.out.println("Cannot read the file: " + paraTrainingFileName + "\r\n" + e);
            System.exit(0);
        }//Of try

        // Step2. Set the last attribute as the class index
        trainingData.setClassIndex(trainingData.numAttributes() - 1);

        // Step3. The testing data is the same as the training data
        testingData = trainingData;

        stopAfterConverge = true;

        System.out.println("****************Data**********\r\n" + trainingData);
    }//Of the first constructor

    /**
     * Set the number of base classifier, and allocate space for them.
     *
     * @param paraNumBaseClassifiers The number of base classifier.
     */
    public void setNumBaseClassifiers(int paraNumBaseClassifiers) {
        numClassifiers = paraNumBaseClassifiers;

        // Step1. Allocate space for classifiers
        classifiers = new SimpleClassifier[numClassifiers];

        // Step2. Initialize classifier weights
        classifierWeights = new double[numClassifiers];
    }//Of setNumBaseClassifiers

    /**
     * Train the booster.
     */
    public void train() {
        // Step1. Initialize.
        WeightedInstances tempWeightedInstances = null;
        double tempError;
        numClassifiers = 0;

        // Step2. Build other classifier.
        for (int i = 0; i < classifiers.length; i++) {
            // Step2.1 Key code: Construct or adjust the weightedInstances
            if (i == 0) {
                tempWeightedInstances = new WeightedInstances(trainingData);
            } else {
                // Adjust the weights of the data
                tempWeightedInstances.adjustWeights(classifiers[i - 1].computeCorrectnessArray(),
                        classifierWeights[i - 1]);
            }//Of if

            // Step 2.2 Train the next classifier.
            classifiers[i] = new StumpClassifier(tempWeightedInstances);
            classifiers[i].train();

            tempError = classifiers[i].computeWeightedError();

            // Key code: Set the classifier weight.
            classifierWeights[i] = 0.5 * Math.log(1 / tempError - 1);
            if (classifierWeights[i] < 1e-6) {
                classifierWeights[i] = 0;
            }//Of if

            System.out.println("Classifier #" + i + " , weighted error = " + tempError + " , " +
                    "weight = " + classifierWeights[i] + "\r\n");

            numClassifiers++;

            // The accuracy is enough.
            if (stopAfterConverge) {
                double tempTrainAccuracy = computeTrainingAccuracy();
                System.out.println("The accuracy of the booster is: " + tempTrainAccuracy + "\r\n");
                if (tempTrainAccuracy > 0.99999) {
                    System.out.println("Stop at the round： " + i + " due to converge.\r\n");
                    break;
                }//Of if
            }//of if
        }//Of for i
    }//Of for train

    /**
     * Classify an instance
     *
     * @param paraInstance The given instance
     * @return The predicted label
     */
    public int classify(Instance paraInstance) {
        double[] tempLabelCountArray = new double[testingData.classAttribute().numValues()];
        for (int i = 0; i < numClassifiers; i++) {
            int tempLabel = classifiers[i].classify(paraInstance);
            tempLabelCountArray[tempLabel] += classifierWeights[i];
        }//Of for i

        int resultLabel = -1;
        double tempMax = -1;
        for (int i = 0; i < tempLabelCountArray.length; i++) {
            if (tempMax < tempLabelCountArray[i]) {
                tempMax = tempLabelCountArray[i];
                resultLabel = i;
            }//Of if
        }//Of for i

        return resultLabel;
    }//Of classify

    /**
     * Test the booster on the training data.
     *
     * @return The classification accuracy.
     */
    public double test() {
        System.out.println("Testing on " + testingData.numInstances() + " instances\r\n");

        return test(testingData);
    }//Of test

    /**
     * Test the booster.
     *
     * @param paraInstances The testing set.
     * @return The classification accuracy.
     */
    public double test(Instances paraInstances) {
        double tempCorrect = 0;
        paraInstances.setClassIndex(paraInstances.numAttributes() - 1);

        for (int i = 0; i < paraInstances.numInstances(); i++) {
            Instance tempInstance = paraInstances.instance(i);
            if (classify(tempInstance) == (int) tempInstance.classValue()) {
                tempCorrect++;
            }//Of if
        }//Of for i

        double resultAccuracy = tempCorrect / paraInstances.numInstances();
        System.out.println("The accuracy is: " + resultAccuracy);

        return resultAccuracy;
    }//Of test

    /**
     * Compute the training accuracy of the booster. It is not weighted.
     *
     * @return The training accuracy.
     */
    public double computeTrainingAccuracy() {
        double tempCorrect = 0;

        for (int i = 0; i < trainingData.numInstances(); i++) {
            if (classify(trainingData.instance(i)) == (int) trainingData.instance(i).classValue()) {
                tempCorrect++;
            }//Of if
        }//Of for i

        double tempAccuracy = tempCorrect / trainingData.numInstances();
        return tempAccuracy;
    }//Of computeTrainingAccuracy

    /**
     * The entrance of the program.
     *
     * @param args Not used now.
     */
    public static void main(String[] args) {
        System.out.println("Starting Adaboosting ..");
        Booster tempBooster = new Booster("E:\\java_code\\data\\sampledata\\iris.arff");

        tempBooster.setNumBaseClassifiers(5);
        tempBooster.train();

        System.out.println("The training accuracy is: " + tempBooster.computeTrainingAccuracy());
        tempBooster.test();
    }//Of main
}//Of class Booster
