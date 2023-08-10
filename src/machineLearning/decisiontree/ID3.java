package machineLearning.decisiontree;

import weka.core.Instances;

/**
 * ClassName: ID3
 * Package: machineLearning.decisiontree
 * Description:  The ID3 decision tree inductive algorithm.
 *
 * @Author: luv_x_c
 * @Create: 2023/8/7 14:55
 */
public class ID3 {
    /**
     * The data.
     */
    Instances dataset;

    /**
     * Is the dataset pure(Only one label)?
     */
    boolean pure;

    /**
     * The number of classes. For binary classification it is 2.
     */
    int numClasses;

    /**
     * Available instances. Other instances do not belong this branch.
     */
    int[] availableInstances;

    /**
     * Available attributes. Other attributes have been selected int the path from the root.
     */
    int[] availableAttributes;

    /**
     * The selected attribute.
     */
    int splitAttributes;

    
}
