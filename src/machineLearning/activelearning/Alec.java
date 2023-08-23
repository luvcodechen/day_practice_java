package machineLearning.activelearning;

import weka.core.Instances;

import java.io.FileReader;
import java.io.IOException;

/**
 * ClassName: Alec
 * Package: machineLearning.activelearning
 * Description: Active learning through density clustering.
 *
 * @Author: luv_x_c
 * @Create: 2023/8/21 14:26
 */
public class Alec {
    /**
     * The whole dataset.
     */
    Instances dataset;

    /**
     * The maximal number of queries that can be provided.
     */
    int maxNumQuery;

    /**
     * The radius, also dc in the paper. It is employed for density computation.
     */
    double radius;

    /**
     * The densities of instances.
     */
    double[] densities;

    /**
     * DistanceToMaster
     */
    double[] distanceToMaster;

    /**
     * Sorted indices, where the first element indicates the instance with the biggest density,
     */
    int[] descendantDensities;

    /**
     * Priority.
     */
    double[] priority;

    /**
     * The maximal distance between any pair of points.
     */
    double maximalDistance;

    /**
     * Who is my master?
     */
    int[] masters;

    /**
     * Predicted labels.
     */
    int[] predictedLabels;

    /**
     * Instance status. 0 for unprocessed, 1 for queried, 2 for classified.
     */
    int[] instanceStatusArray;

    /**
     * The descendant indices to show the representatives of instances in a descendant order.
     */
    int[] descendantRepresentatives;

    /**
     * Indicate the cluster of each instance. It is only used in clusterInTwo(int[]).
     */
    int[] clusterIndices;

    /**
     * Blocks with size more than this threshold should not be split further.
     */
    int smallBlockThreshold = 3;

    /**
     * The constructor.
     *
     * @param paraFileName The data filename.
     */
    public Alec(String paraFileName) {
        try {
            FileReader fileReader = new FileReader(paraFileName);
            dataset = new Instances(fileReader);
            dataset.setClassIndex(dataset.numAttributes() - 1);
            fileReader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }//Of try

        computeMaximalDistance();
        clusterIndices = new int[dataset.numInstances()];
    }//OF the constructor

    /**
     * Merge sort in descendant order to obtain an index array. The original array  is unchanged.
     * The method should be tested further.
     *
     * @param paraArray The original array.
     * @return The sorted indices.
     */
    public static int[] mergeSortToIndices(double[] paraArray) {
        int tempLength = paraArray.length;
        int[][] resultMatrix = new int[2][tempLength];

        // Initialize
        int tempIndex = 0;
        for (int i = 0; i < tempLength; i++) {
            resultMatrix[tempIndex][i] = i;
        }//Of for i

        // Merge
        int tempCurrentLength=1;
        // The indices for current merged groups.
        int tempFirstStart,tempSecondStart,tempSecondEnd;

        while (tempCurrentLength<tempLength){
            // Divide into a number of groups.
            // Here the boundary is adaptive to array length not equal to 2^k
            for (int i = 0; i < Math.ceil((tempLength+0.0)/tempCurrentLength/2); i++) {
                
            }
        }
    }
}
