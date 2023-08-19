package machineLearning.adaboosting;

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
     * @param paraNumBaseClassifiers The number of base classifier.
     */
    public void setNumBaseClassifiers(int paraNumBaseClassifiers){
        numClassifiers=paraNumBaseClassifiers;

        // Step1. Allocate space for classifiers
        classifiers=new SimpleClassifier[numClassifiers];

        // Step2. Initialize classifier weights
        classifierWeights=new double[numClassifiers];
    }//Of setNumBaseClassifiers



}
