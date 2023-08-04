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
                for (int i = 0; i < dataset.numAttributes() - 1; i++) {
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
//    public void clustering() {
//        int[] tempOldClusterArray = new int[dataset.numInstances()];
//        tempOldClusterArray[0] = -1;
//        int[] tempClusterArray = new int[dataset.numInstances()];
//        Arrays.fill(tempClusterArray, 0);
//        double[][] tempCenters = new double[numClusters][dataset.numAttributes() - 1];
//
//        // Step1. Initialize centers.
//        int[] tempRandomOrders = getRandomIndices(dataset.numInstances());
//        for (int i = 0; i < numClusters; i++) {
//            for (int j = 0; j < tempCenters[0].length; j++) {
//                tempCenters[i][j] = dataset.instance(tempRandomOrders[i]).value(j);
//            }// OF for j
//        }// Of for i
//
//        int[] tempClusterLengths = null;
//        while (!Arrays.equals(tempOldClusterArray, tempClusterArray)) {
//            System.out.println("New loop ...");
//            tempOldClusterArray = tempClusterArray;
//            tempClusterArray = new int[dataset.numInstances()];
//
//            // Step2.1 Minimization. Assign cluster to each instance.
//            int tempNearestCenter;
//            double tempNearestDistance;
//            double tempDistance;
//
//            for (int i = 0; i < dataset.numInstances(); i++) {
//                tempNearestCenter = -1;
//                tempNearestDistance = Double.MAX_VALUE;
//
//                for (int j = 0; j < numClusters; j++) {
//                    tempDistance = distance(i, tempCenters[j]);
//                    if (tempNearestDistance > tempDistance) {
//                        tempNearestDistance = tempDistance;
//                        tempNearestCenter = j;
//                    }// Of if
//                }// OF  for j
//                tempClusterArray[i] = tempNearestCenter;
//            }// of for i
//
//            // Step2.2. Mean. Find new centers.
//            tempClusterLengths = new int[numClusters];
//            Arrays.fill(tempClusterLengths, 0);
//            double[][] tempNewCenters = new double[numClusters][dataset.numAttributes() - 1];
//            for (int i = 0; i < dataset.numInstances(); i++) {
//                for (int j = 0; j < tempNewCenters[0].length; j++) {
//                    tempNewCenters[tempClusterArray[i]][j] += dataset.instance(i).value(j);
//                }// OF for j
//                tempClusterLengths[tempClusterArray[i]]++;
//            }// Of for i
//
//            // Step2.3. Now average.
//            for (int i = 0; i < tempNewCenters.length; i++) {
//                for (int j = 0; j < tempNewCenters[0].length; j++) {
//                    tempNewCenters[i][j] /= tempClusterLengths[i];
//                }// Of for j
//            }// Of for i
//
//            System.out.println("Now the centers are: " + Arrays.deepToString(tempNewCenters));
//            tempCenters = tempNewCenters;
//        }// Of while
//
//        //Step3. Form clusters.
//        clusters = new int[numClusters][];
//        int[] tempCounters = new int[numClusters];
//        for (int i = 0; i < numClusters; i++) {
//            clusters[i] = new int[tempClusterLengths[i]];
//        }// Of for i
//
//        for (int i = 0; i < tempClusterArray.length; i++) {
//            clusters[tempClusterArray[i]][tempCounters[tempClusterArray[i]]] = i;
//            tempCounters[tempClusterArray[i]]++;
//        }//  of fot i
//
//        System.out.println("The clusters are:   " + Arrays.deepToString(clusters));
//    }// Of clustering
    public void clustering() {
        int[] tempOldClusterArray = new int[dataset.numInstances()];
        tempOldClusterArray[0] = -1;
        int[] tempClusterArray = new int[dataset.numInstances()];
        Arrays.fill(tempClusterArray, 0);
        double[][] tempCenters = new double[numClusters][dataset.numAttributes() - 1];

        // Step1. Initialize centers.
        int[] tempRandomOrders = getRandomIndices(dataset.numInstances());
        for (int i = 0; i < numClusters; i++) {
            for (int j = 0; j < tempCenters[0].length; j++) {
                tempCenters[i][j] = dataset.instance(tempRandomOrders[i]).value(j);
            }// OF for j
        }// Of for i

        int[] tempClusterLengths = null;
        while (!Arrays.equals(tempOldClusterArray, tempClusterArray)) {
            System.out.println("New loop ...");
            tempOldClusterArray = tempClusterArray;
            tempClusterArray = new int[dataset.numInstances()];

            // Step2.1 Minimization. Assign cluster to each instance.
            int tempNearestCenter;
            double tempNearestDistance;
            double tempDistance;

            for (int i = 0; i < dataset.numInstances(); i++) {
                tempNearestCenter = -1;
                tempNearestDistance = Double.MAX_VALUE;

                for (int j = 0; j < numClusters; j++) {
                    tempDistance = distance(i, tempCenters[j]);
                    if (tempNearestDistance > tempDistance) {
                        tempNearestDistance = tempDistance;
                        tempNearestCenter = j;
                    }// Of if
                }// OF  for j
                tempClusterArray[i] = tempNearestCenter;
            }// of for i

            // Step2.2. Mean. Find new centers.
            tempClusterLengths = new int[numClusters];
            Arrays.fill(tempClusterLengths, 0);
            double[][] tempNewCenters = new double[numClusters][dataset.numAttributes() - 1];
            for (int i = 0; i < dataset.numInstances(); i++) {
                for (int j = 0; j < tempNewCenters[0].length; j++) {
                    tempNewCenters[tempClusterArray[i]][j] += dataset.instance(i).value(j);
                }// OF for j
                tempClusterLengths[tempClusterArray[i]]++;
            }// Of for i

            // Step2.3. Now average.
            for (int i = 0; i < tempNewCenters.length; i++) {
                for (int j = 0; j < tempNewCenters[0].length; j++) {
                    tempNewCenters[i][j] /= tempClusterLengths[i];
                }// Of for j
            }// Of for i

//            System.out.println("Now the centers are: " + Arrays.deepToString(tempNewCenters));
            tempCenters = tempNewCenters;
            // 新增部分：虚拟中心替换为实际中心
            int[] nearestCenters = new int[numClusters]; // 用于存储每个虚拟中心最近的实际中心的索引
            for (int i = 0; i < numClusters; i++) {
                double minDistance = Double.MAX_VALUE;
                int nearestCenterIndex = -1;
                for (int j = 0; j < dataset.numInstances(); j++) {
                    double distanceToCenter = distance(j, tempCenters[i]);
                    if (distanceToCenter < minDistance) {
                        minDistance = distanceToCenter;
                        nearestCenterIndex = j;
                    }
                }
                nearestCenters[i] = nearestCenterIndex;
            }

             // 替换虚拟中心为实际中心
            for (int i = 0; i < numClusters; i++) {
                for (int j = 0; j < tempCenters[0].length; j++) {
                    tempCenters[i][j] = dataset.instance(nearestCenters[i]).value(j);
                }
            }
            System.out.println("Now the centers are: " + Arrays.deepToString(tempCenters));
        }// Of while

        //Step3. Form clusters.
        clusters = new int[numClusters][];
        int[] tempCounters = new int[numClusters];
        for (int i = 0; i < numClusters; i++) {
            clusters[i] = new int[tempClusterLengths[i]];
        }// Of for i

        for (int i = 0; i < tempClusterArray.length; i++) {
            clusters[tempClusterArray[i]][tempCounters[tempClusterArray[i]]] = i;
            tempCounters[tempClusterArray[i]]++;
        }//  of fot i

        System.out.println("The clusters are:   " + Arrays.deepToString(clusters));
    }

    /**
     * A test unit.
     */
    public static void testClustering() {
        KMeans tempKmeans = new KMeans("E:\\java_code\\data\\sampledata\\iris.arff");
        tempKmeans.setNumClusters(3);
        tempKmeans.clustering();
    }// Of testClustering

    /**
     * The entrance of the program.
     *
     * @param args NOt used now.
     */
    public static void main(String[] args) {
        testClustering();

    }// Of main
}// Of class KMeans
