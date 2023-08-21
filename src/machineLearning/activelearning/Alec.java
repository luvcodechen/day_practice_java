package machineLearning.activelearning;

import weka.core.Instances;

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
    double[]distanceToMaster;


}
