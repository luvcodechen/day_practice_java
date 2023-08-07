package machineLearning.nb;

import weka.core.Instances;

import java.io.FileReader;
import java.util.Arrays;

/**
 * ClassName: NaiveBayes
 * Package: machineLearning.nb
 * Description:The Naive Bayes algorithm.
 *
 * @Author: luv_x_c
 * @Create: 2023/8/4 15:05
 */
public class NaiveBayes {

    /**
     * An inner class to store parameters.
     */
    private static class GaussianParameters {
        double mu;
        double sigma;

        public GaussianParameters(double paraMu, double paraSigma) {
            mu = paraMu;
            sigma = paraSigma;
        }// OF the constructor

        @Override
        public String toString() {
            return "GaussianParameters{}";
        }// Of toString
    }// OF class GaussianParameters

    /**
     * The data.
     */
    Instances dataset;

    /**
     * The number of classes. For binary classification it is 2.
     */
    int numClasses;


    /**
     * The number of instances.
     */
    int numInstances;

    /**
     * The number of conditional attributes.
     */
    int numConditions;

    /**
     * The prediction, including queried and predicted labels.
     */
    int[] predicts;

    /**
     * Class distribution.
     */
    double[] classDistribution;

    /**
     * Class distribution with Laplacian smooth.
     */
    double[] classDistributionLaplacian;

    /**
     * To calculate the conditional probabilities for all classes over all attributes on all values.
     */
    double[][][] conditionalCounts;

    /**
     * The conditional probabilities with Laplacian smooth.
     */
    double[][][] conditionalProbabilitiesLaplacian;

    /**
     * The Gaussian parameters.
     */
    GaussianParameters[][] gaussianParameters;

    /**
     * Data type.
     */
    int dataType;

    /**
     * Numerical.
     */
    public static final int NUMERICAL = 1;

    /**
     * The constructor.
     *
     * @param paraFileName The given file.
     */
    public NaiveBayes(String paraFileName) {
        dataset = null;

        try {
            FileReader fileReader = new FileReader(paraFileName);
            dataset = new Instances(fileReader);
            fileReader.close();
        } catch (Exception ee) {
            System.out.println("Cannot read the file: " + paraFileName + "\r\t" + ee);
            System.exit(0);
        }// Of try

        dataset.setClassIndex(dataset.numAttributes() - 1);
        numConditions = dataset.numAttributes() - 1;
        numInstances = dataset.numInstances();
        numClasses = dataset.attribute(numConditions).numValues();
    }// Of the  constructor

    /**
     * Set the data type.
     */
    public void setDataType(int paraDataType) {
        dataType = paraDataType;
    }// Of setDataType

    /**
     * Calculate the class distribution with Laplacian smooth.
     */
    public void calculateClassDistribution() {
        classDistribution = new double[numClasses];
        classDistributionLaplacian = new double[numClasses];

        double[] tempCounts = new double[numClasses];
        for (int i = 0; i < numInstances; i++) {
            int tempClassValue = (int) dataset.instance(i).classValue();
            tempCounts[tempClassValue]++;
        }// OF for i

        for (int i = 0; i < numClasses; i++) {
            classDistribution[i] = tempCounts[i] / numInstances;
            classDistributionLaplacian[i] = (tempCounts[i] + 1) / (numInstances + numClasses);
        }// OF for i

        System.out.println("Class distribution: " + Arrays.toString(classDistribution));
        System.out.println("Class distribution Laplacian: " + Arrays.toString(classDistributionLaplacian));
    }//Of calculateClassDistribution

    public void calculateConditionalProbabilities() {
        conditionalCounts = new double[numConditions][numConditions][];
        conditionalProbabilitiesLaplacian = new double[numClasses][numConditions][];

        //Allocate space.
        for (int i = 0; i < numClasses; i++) {
            for (int j = 0; j < numConditions; j++) {
                int tempNumValues = (int) dataset.attribute(j).numValues();
                conditionalCounts[i][j] = new double[tempNumValues];
                conditionalProbabilitiesLaplacian[i][j] = new double[tempNumValues];
            }// Of for j
        }// Of for i

        //Count the numbers
        int[] tempClassCounts = new int[numClasses];
        for (int i = 0; i < numInstances; i++) {
            int tempClass = (int) dataset.instance(i).classValue();
            tempClassCounts[tempClass]++;
            for (int j = 0; j < numConditions; j++) {
                int tempValue = (int) dataset.instance(i).value(j);
                conditionalCounts[tempClass][j][tempValue]++;
            }//Of for j
        }// OF for i

        // Now for the real probability with Laplacian
        for (int i = 0; i < numClasses; i++) {
            for (int j = 0; j < numConditions; j++) {
                int tempNumValue = (int) dataset.attribute(j).numValues();
                for (int k = 0; k < tempNumValue; k++) {
                    conditionalProbabilitiesLaplacian[i][j][k] =
                            (conditionalCounts[i][j][k] + 1) / (tempClassCounts[i] + tempNumValue);
                }// OF for k
            }// OF for j
        }// OF for i

        System.out.println("Conditional probabilities: " + Arrays.deepToString(conditionalCounts));
    }// OF calculateConditionalProbabilities

    /**
     * Calculate the conditional probabilities with Laplacian smooth.
     */
    public void calculateGaussianParameters() {

    }

    /**
     * @param tempDataset
     * @param paraTrainingFraction
     * @return
     */
    private static Instances[] splitTrainingTesting(Instances tempDataset, double paraTrainingFraction) {
        return new Instances[0];
    }

    /**
     * The entrance of the program.
     *
     * @param args Not used now.
     */
    public static void main(String[] args) {

    }

    public static void testNominal(double paraTrainingFraction) {
        System.out.println("Hello, Naive Bayes. I only want to  test the nominal data. ");
        String tempFileName = "E:\\java_code\\data\\sampledata\\mushroom.arff";

        Instances tempDataset = null;
        try {
            FileReader fileReader = new FileReader(tempFileName);
            tempDataset = new Instances(fileReader);
            fileReader.close();
        } catch (Exception ee) {
            System.out.println("Cannot read the file :" + tempFileName + "\r\n" + ee);
            System.exit(0);
        }// of try

        Instances[] tempDatasets = splitTrainingTesting(tempDataset, paraTrainingFraction);


    }
}
