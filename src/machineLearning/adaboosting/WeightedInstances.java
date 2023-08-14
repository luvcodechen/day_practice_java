package machineLearning.adaboosting;

import weka.core.Instances;

import java.io.FileReader;
import java.util.Arrays;

/**
 * ClassName: WeightedInstances
 * Package: machineLearning.adaboosting
 * Description:Weighted instances.
 *
 * @Author: luv_x_c
 * @Create: 2023/8/13 15:12
 */
public class WeightedInstances extends Instances {
    /**
     * Just the requirement of some classes, any number is ok.
     */
    private static final long serialVersionUID = 11087456L;

    /**
     * Weights.
     */
    private double[] weights;

    /**
     * The first constructor.
     *
     * @param paraFileReader The given reader to read data from file.
     */
    public WeightedInstances(FileReader paraFileReader) throws Exception {
        super(paraFileReader);
        setClassIndex(numAttributes() - 1);

        //Initialize weights.
        weights = new double[numInstances()];
        double tempAverage = 1.0 / numInstances();
        Arrays.fill(weights, tempAverage);
        System.out.println("Instances weights are: " + Arrays.toString(weights));
    }//Of the first constructor

    /**
     * The second constructor.
     *
     * @param paraInstances The given instances.
     */
    public WeightedInstances(Instances paraInstances) {
        super(paraInstances);
        setClassIndex(numAttributes() - 1);

        // Initialize weights.
        weights = new double[numInstances()];
        double tempAverage = 1.0 / numInstances();
        Arrays.fill(weights, tempAverage);
        System.out.println("Instances weights are: " + Arrays.toString(weights));
    }//Of the second constructor

    /**
     * Getter.
     *
     * @param paraIndex The given index.
     * @return The weight of the given index.
     */
    public double getWeight(int paraIndex) {
        return weights[paraIndex];
    }//Of getWeight

    /**
     * Adjust the weights.
     *
     * @param paraCorrectArray Indicate which instance have been correctly classify.
     * @param paraAlpha        The weight of the last classifier.
     */
    public void adjustWeights(boolean[] paraCorrectArray, double paraAlpha) {
        //Step1. Calculate alpha.
        double tempIncrease = Math.exp(paraAlpha);

        // Step2. Adjust.
        double tempWeightsSum = 0;
        for (int i = 0; i < weights.length; i++) {
            if (paraCorrectArray[i]) {
                weights[i] /= tempIncrease;
            } else {
                weights[i] *= tempIncrease;
            }//Of if
            tempWeightsSum += weights[i];
        }//Of for i

        // Step3. Normalize.
        for (int i = 0; i < weights.length; i++) {
            weights[i] /= tempWeightsSum;
        }//Of for i

        System.out.println("After adjusting, instances weights are: " + Arrays.toString(weights));
    }//Of adjustWeights

    /**
     * Test the method.
     */
    public void adjustWeightsTest() {
        boolean[] tempCorrectArray = new boolean[numInstances()];
        Arrays.fill(tempCorrectArray, true);

        double tempWeightError = 0.3;

        adjustWeights(tempCorrectArray, tempWeightError);

        System.out.println("After adjusting: ");

        System.out.println(toString());
    }// Of adjustWeightsTest

    @Override
    public String toString() {
        String resultString =
                "I am a weighted Instances object.\r\n" + " I have " + numInstances() + " " +
                        "instances and " + (numAttributes() - 1) + " conditional attributes.\r\n" +
                        " My weights are: " + Arrays.toString(weights) + "\r\n" +
                        " My data are：\r\n" + super.toString();
        return resultString;
    }//Of toString

    /**
     * @param args Not provided.
     */
    public static void main(String[] args) {
        WeightedInstances tempWeightInstances = null;
        String tempFileName = "E:\\java_code\\data\\sampledata\\iris.arff";

        try {
            FileReader fileReader = new FileReader(tempFileName);
            tempWeightInstances = new WeightedInstances(fileReader);
            fileReader.close();
        } catch (Exception ee) {
            System.out.println("Cannot read the file: " + tempFileName + "\r\n" + ee);
            System.exit(0);
        }//Of try

        System.out.println(tempWeightInstances.toString());

        tempWeightInstances.adjustWeightsTest();
    }//Of main
}//OF class WeightedInstances
