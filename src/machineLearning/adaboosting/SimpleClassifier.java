package machineLearning.adaboosting;

import weka.core.Instance;

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
        numInstances = weightedInstances.numInstances();
        numClasses = weightedInstances.classAttribute().numValues();
    }// Of the first constructor

    /**
     * Train the classifier.
     */
    public abstract void train();

    /**
     * Classify an instance.
     *
     * @param paraInstance The given instance.
     * @return Predicted label.
     */
    public abstract int classify(Instance paraInstance);

    /**
     * Which instance in the  training set are correctly classified.
     *
     * @return The correctness array.
     */
    public boolean[] computeCorrectnessArray() {
        boolean[] resultCorrectnessArray = new boolean[weightedInstances.numInstances()];
        for (int i = 0; i < resultCorrectnessArray.length; i++) {
            Instance tempInstance = weightedInstances.instance(i);
            if ((int) (tempInstance.classValue()) == classify(tempInstance)) {
                resultCorrectnessArray[i] = true;
            }// OF if
        }//Of for i

        return resultCorrectnessArray;
    }//Of computeCorrectnessArray

    /**
     * Compute the accuracy on the training set.
     *
     * @return The training accuracy.
     */
    public double computeTrainingAccuracy() {
        double tempCorrect = 0;
        boolean[] tempCorrectnessArray = computeCorrectnessArray();
        for (int i = 0; i < tempCorrectnessArray.length; i++) {
            if (tempCorrectnessArray[i]) {
                tempCorrect++;
            }//Of if
        }//Of for i

        double resultAccuracy = tempCorrect / tempCorrectnessArray.length;

        return resultAccuracy;
    }//Of computeTrainingAccuracy

    public double computeWeightedError(){
        double resultError=0;
        boolean[]tempCorrectnessArray=computeCorrectnessArray();
        for (int i = 0; i < tempCorrectnessArray.length; i++) {
            if(!tempCorrectnessArray[i]){
                resultError+=weightedInstances.getWeight(i);
            }//Of if
        }//Of for i

        if(resultError<1e-6){
            resultError=1e-6;
        }//Of if

        return resultError;
    }//Of computeWeightedError
}//Of class SimpleClassifier
