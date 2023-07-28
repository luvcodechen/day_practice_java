package machineLearning.knn;

import weka.core.Instances;

import java.io.FileReader;
import java.util.Arrays;
import java.util.Random;

/**
 * ClassName: KnnClassification
 * Package: machineLearning.knn
 * Description:
 *
 * @Author: luv_x_c
 * @Create: 2023/6/7 17:54
 */
public class KnnClassification {

    /**
     * Manhattan distance.
     */
    public static final int MANHATTAN = 0;


    /**
     * Euclidean distance.
     */
    public static final int EUCLIDEAN = 1;

    /**
     * The distance measure.
     */
    private int distanceMeasure;

    /**
     * A random instance.
     */
    public static final Random RANDOM = new Random();

    /**
     * The number of neighbors.
     */
    private int numNeighbors;

    /**
     * The whole dataset.
     */
    Instances dataset;

    /**
     * The training set. Represented by the indices of the data.
     */
    int[] trainingSet;

    /**
     * The testing set. Represented by the indices of the data.
     */
    int[] testingSet;

    /**
     * The predictions.
     */
    int[] predictions;

    public void setDistanceMeasure(int paraDistanceMeasure) {
        this.distanceMeasure = paraDistanceMeasure;
    }

    public void setNumNeighbors(int paraNumNeighbors) {
        this.numNeighbors = paraNumNeighbors;
    }

    /**
     * The first constructor.
     *
     * @param paraFileName The arff filename.
     */
    public KnnClassification(String paraFileName) {
        try {
            FileReader fileReader = new FileReader(paraFileName);
            dataset = new Instances(fileReader);
            // The last attributes is the decision class.
            dataset.setClassIndex(dataset.numAttributes() - 1);
            fileReader.close();
        } catch (Exception e) {
            System.out.println("Error occurred while trying to read \'" + paraFileName
                    + "\' in KnnClassification constructor.\r\n" + e);
            System.exit(0);
        }// Of try
    }// Of the first constructor

    /**
     * Get a random indices for data randomization.
     *
     * @param paraLength The length of the sequence.
     * @return An array of indices.
     */
    public static int[] getRandomIndices(int paraLength) {
        int[] resultIndices = new int[paraLength];

        // Step1 . Initialize.
        for (int i = 0; i < paraLength; i++) {
            resultIndices[i] = i;
        }// Of for i

        // Step2 . Randomly swap.
        int tempFirst, tempSecond, tempValue;
        for (int i = 0; i < paraLength; i++) {
            // Generate two random indices.
            tempFirst = RANDOM.nextInt(paraLength);
            tempSecond = RANDOM.nextInt(paraLength);

            // Swap.
            tempValue = resultIndices[tempFirst];
            resultIndices[tempFirst] = resultIndices[tempSecond];
            resultIndices[tempSecond] = tempValue;
        }// Of for i

        return resultIndices;
    }//Of getRandomIndices

    /**
     * Split the data into training and testing parts.
     *
     * @param paraTrainingFraction The fraction of the training set.
     */
    public void splitTrainingTesting(double paraTrainingFraction) {
        int tempSize = dataset.numInstances();
        int[] tempIndices = getRandomIndices(tempSize);
        int tempTrainingSize = (int) (tempSize * paraTrainingFraction);

        trainingSet = new int[tempTrainingSize];
        testingSet = new int[tempSize - tempTrainingSize];

        for (int i = 0; i < tempTrainingSize; i++) {
            trainingSet[i] = tempIndices[i];
        }// Of for i

        for (int i = 0; i < tempSize - tempTrainingSize; i++) {
            testingSet[i] = tempIndices[i + tempTrainingSize];
        }// Of for i
    }// Of splitTrainingTesting

    /**
     * Predict for the whole testing set. The results are stored in predictions.
     */
    public void predict() {
        predictions = new int[testingSet.length];
        for (int i = 0; i < predictions.length; i++) {
            predictions[i] = predict(testingSet[i]);
        }// Of for i
    }// Of predict

    /**
     * Predict for given instance.
     *
     * @param paraIndex The given index.
     * @return The prediction.
     */
    private int predict(int paraIndex) {
        int[] tempNeighbors = computeNearest(paraIndex);

        return simpleVoting(tempNeighbors);
    }// Of  predict

    /**
     * Voting using the instances.
     *
     * @param paraNeighbors The indices of the neighbors.
     * @return The predicted label.
     */
    private int simpleVoting(int[] paraNeighbors) {
        int[] tempVotes = new int[dataset.numClasses()];
        for (int paraNeighbor : paraNeighbors) {
            tempVotes[(int) dataset.instance(paraNeighbor).classValue()]++;
        }// Of for i

        int tempMaximalVotingIndex = 0;
        int tempMaximalVoting = 0;
        for (int i = 0; i < dataset.numClasses(); i++) {
            if (tempVotes[i] > tempMaximalVoting) {
                tempMaximalVoting = tempVotes[i];
                tempMaximalVotingIndex = i;
            }// Of if
        }// Of for i

        return tempMaximalVotingIndex;

    }// Of simpleVoting

//    /**
//     * Compute the nearest k neighbors.
//     *
//     * @param paraCurrent current instance. We are comparing it with all others.
//     * @return The indices of the nearest instances.
//     */
//    private int[] computeNearest(int paraCurrent) {
//        int[] resultNearest = new int[numNeighbors];
//        boolean[] tempSelected = new boolean[trainingSet.length];
//        double tempMinimalDistance;
//        int tempMinimalIndex = 0;
//
//        // Compute all distances to avoid redundant computation.
//        double[] tempDistances = new double[trainingSet.length];
//        for (int i = 0; i < trainingSet.length; i++) {
//            tempDistances[i] = distance(paraCurrent, trainingSet[i]);
//        }// Of for i
//
//        // Select the nearest paraK indices.
//        for (int i = 0; i < numNeighbors; i++) {
//            tempMinimalDistance = Double.MAX_VALUE;
//
//            for (int j = 0; j < trainingSet.length; j++) {
//                if (tempSelected[j]) {
//                    continue;
//                }// Of if
//
//                if (tempDistances[j] < tempMinimalDistance) {
//                    tempMinimalDistance = tempDistances[j];
//                    tempMinimalIndex = j;
//                }// Of if
//            }// OF for j
//
//            resultNearest[i] = trainingSet[tempMinimalIndex];
//            tempSelected[tempMinimalIndex] = true;
//        }// Of for i
//
//        System.out.println("The nearest of " + paraCurrent + " are: " + Arrays.toString(resultNearest));
//        return resultNearest;
//    }// Of computeNearest

    /**
     * Compute the nearest k neighbors.
     *
     * @param paraCurrent current instance. We are comparing it with all others.
     * @return The indices of the nearest instances.
     */
    private int[] computeNearest(int paraCurrent) {
        int[] resultNearest = new int[numNeighbors];
        double[] tempDistance = new double[numNeighbors];

        double tempCurrentDistance;
        int tempCurrentIndex = 0;

        // Compute all distances and sort.
        for (int i = 0; i < trainingSet.length; i++) {
            tempCurrentDistance = distance(paraCurrent, trainingSet[i]);

            // Search and insert.
            tempCurrentIndex = i < numNeighbors ? i : numNeighbors - 1;
            boolean tempIsMove = false;
            while (tempCurrentIndex > 0 && tempDistance[tempCurrentIndex - 1] > tempCurrentDistance) {
                tempDistance[tempCurrentIndex] = tempDistance[tempCurrentIndex - 1];
                resultNearest[tempCurrentIndex] = resultNearest[tempCurrentIndex - 1];
                tempCurrentIndex--;
                tempIsMove = true;
            }// Of while
            if (tempIsMove || tempCurrentIndex < numNeighbors - 1) {
                tempDistance[tempCurrentIndex] = tempCurrentDistance;
                resultNearest[tempCurrentIndex] = trainingSet[i];
            }// Of if
        }// OF for i

        System.out.println("The nearest of " + paraCurrent + " are: " + Arrays.toString(resultNearest));
        return resultNearest;
    }// Of computeNearest

    /**
     * The distance between two instances.
     *
     * @param paraI The index of the first instance.
     * @param paraJ The index of the second instance.
     * @return The distance.
     */
    public double distance(int paraI, int paraJ) {
        double resultDistance = 0;
        double tempDifference;
        switch (distanceMeasure) {
            case MANHATTAN:
                for (int i = 0; i < dataset.numAttributes() - 1; i++) {
                    tempDifference = dataset.instance(paraI).value(i) - dataset.instance(paraJ).value(i);
                    if (tempDifference < 0) {
                        resultDistance -= tempDifference;
                    } else {
                        resultDistance += tempDifference;
                    }// Of if
                }// Of for i
                break;
            case EUCLIDEAN:
                for (int i = 0; i < dataset.numAttributes() - 1; i++) {
                    tempDifference = dataset.instance(paraI).value(i) - dataset.instance(paraJ).value(i);
                    resultDistance += tempDifference * tempDifference;
                }// OF for i
                break;
            default:
                System.out.println("Unsupported distance measure: " + distanceMeasure);
        }// Of switch

        return resultDistance;
    }// Of distance

    /**
     * Get the accuracy of the classifier.
     *
     * @return The accuracy.
     */
    public double getAccuracy() {
        // A double divides an int gets another double.
        double tempCorrect = 0;
        for (int i = 0; i < predictions.length; i++) {
            if (predictions[i] == dataset.instance(testingSet[i]).classValue()) {
                tempCorrect++;
            }// Of if
        }// Of for i

        return tempCorrect / testingSet.length;
    }// Of getAccuracy


    /**
     * The entrance of the program.
     *
     * @param args Not used now.
     */
    public static void main(String[] args) {
        KnnClassification tempClassifier = new KnnClassification("E:\\java_code\\data\\sampledata\\iris.arff");
        tempClassifier.setDistanceMeasure(EUCLIDEAN);
        tempClassifier.setNumNeighbors(8);
        tempClassifier.splitTrainingTesting(0.8);
        tempClassifier.predict();
        System.out.println("The accuracy of the classifier is: " + tempClassifier.getAccuracy());
    }// Of main
}// Of class KnnClassification
