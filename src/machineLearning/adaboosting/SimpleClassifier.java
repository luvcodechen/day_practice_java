package machineLearning.adaboosting;

import weka.core.Instance;

import java.util.Date;
import java.util.Random;

/**
 * ClassName: SimpleClassifier
 * Package: machineLearning.adaboosting
 * Description:The super class of any simple classifier.
 *
 * @Author: luv_x_c
 * @Create: 2023/8/14 13:43
 */
public abstract class SimpleClassifier {
    /**
     * The index of the current attribute.
     */
    int selectedAttribute;

    /**
     * Weighted data.
     */
    WeightedInstances weightedInstances;

    /**
     * The accuracy on the training set.
     */
    double trainingAccuracy;

    /**
     * The number of instances.
     */
    int numInstances;

    /**
     * The number of instances.
     */
    int numClasses;

    /**
     * The number of conditional attributes.
     */
    int numConditions;

    Random random = new Random();

    /**
     * The first constructor.
     *
     * @param paraWeightedInstances The given instances.
     */
    public SimpleClassifier(WeightedInstances paraWeightedInstances) {
        weightedInstances = paraWeightedInstances;

        numConditions = weightedInstances.numAttributes() - 1;
        numInstances=weightedInstances.numInstances();
        numClasses=weightedInstances.classAttribute().numValues();
    }// Of the first constructor

    /**
     * Train the classifier.
     */
    public abstract void train();

    /**
     * Classify an instance.
     * @param paraInstance The given instance.
     * @return Predicted label.
     */
    public abstract int classify(Instance paraInstance);

    /**
     * Which instance in the  training set are correctly classified.
     * @return The correctness array.
     */
    public boolean[]computeCorrectnessArray(){
        boolean[]resultCorrectnessArray=new boolean[]
    }
}//Of class
