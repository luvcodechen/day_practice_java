package machineLearning.nb;

import weka.core.Instances;

import java.io.FileReader;

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
     * The number of conditional attributes.
     */
    int numConditions;

    /**
     * The prediction, including queried and predicted labels.
     */
    int[]predicts;

    /**
     * Class distribution.
     */
    double[]classDistribution;

    double[]classDistributionLaplacian;


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
