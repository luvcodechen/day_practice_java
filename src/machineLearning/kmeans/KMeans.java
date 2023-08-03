package machineLearning.kmeans;

import weka.core.Instances;

import java.io.FileReader;
import java.util.Arrays;
import java.util.Random;

/**
 * ClassName: KMeans
 * Package: machineLearning.kmeans
 * Description: kMeans clustering.
 *
 * @Author: luv_x_c
 * @Create: 2023/7/31 12:29
 */
public class KMeans {
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
    public int distanceMeasure = EUCLIDEAN;

    /**
     * A random instance.
     */
    public static final Random RANDOM = new Random();

    /**
     * The data.
     */
    Instances dataset;

    /**
     * The number of clusters.
     */
    int numClusters = 2;

    /**
     * The clusters.
     */
    int[][] clusters;

    /**
     * The first constructor.
     *
     * @param paraFilename The data filename.
     */
    public KMeans(String paraFilename) {
        dataset = null;
        try {
            FileReader fileReader = new FileReader(paraFilename);
            dataset = new Instances(fileReader);
            fileReader.close();
        } catch (Exception ee) {
            System.out.println("Cannot read the file: " + paraFilename + "\r\n" + ee);
            System.exit(0);
        }// OF try
    }// OF the first constructor

    /**
     * A setter.
     */
    public void setNumClusters(int paraNumClusters) {
        numClusters = paraNumClusters;
    }// Of the setter

    /**
     * Get a random indices for data randomization.
     *
     * @param pratLength The length of the sequence.
     * @return AN array of indices, e.g.,{4,3,1,5,0,2} with length of 6.
     */
    public static int[] getRandomIndices(int pratLength) {
        int[] resultIndices = new int[pratLength];

        // Step1. Initialize.
        for (int i = 0; i < pratLength; i++) {
            resultIndices[i] = i;
        }// Of for i

        // Step2. Randomly swap.
        for (int i = 0; i < pratLength; i++) {
            // Generate two random indices.
            int tempFirst = RANDOM.nextInt(pratLength);
            int tempSecond = RANDOM.nextInt(pratLength);

            //Swap.
            int tempValue = resultIndices[tempFirst];
            resultIndices[tempFirst] = resultIndices[tempSecond];
            resultIndices[tempSecond] = tempValue;
        }// OF for i

        return resultIndices;
    }// OF getRandomIndices

    /**
     * The distance between two instances.
     *
     * @param paraI     The index of first instance.
     * @param paraArray The array representing a point in the space .
     * @return The distance.
     */
    public double distance(int paraI, double[] paraArray) {
        double resultDistance = 0;
        double tempDifference;
        switch (distanceMeasure) {
            case MANHATTAN:
                for (int i = 0; i < dataset.numAttributes() - 1; i++) {
                    tempDifference = dataset.instance(paraI).value(i) - paraArray[i];
                    if (tempDifference < 0) {
                        resultDistance -= tempDifference;
                    } else {

                        resultDistance += tempDifference;
                    }// Of if
                }// OF for i
                break;
            case EUCLIDEAN:
                for (int i = 0; i < dataset.numAttributes(); i++) {
                    tempDifference = dataset.instance(paraI).value(i) - paraArray[i];
                    resultDistance += tempDifference * tempDifference;
                }// OF for i
                break;
            default:
                System.out.println("Unsupported distance measure :" + distanceMeasure);
        }// Of switch

        return resultDistance;
    }// Of distance

    /**
     * Clustering.
     */
    public void clustering() {
        int[] tempOldClusterArray = new int[dataset.numInstances()];
        tempOldClusterArray[0] = -1;
        int[] tempClusterArray = new int[dataset.numInstances()];
        Arrays.fill(tempClusterArray, 0);
        double[][] tempCenters = new double[numClusters][dataset.numAttributes() - 1];

        // Step1.


    }
}
