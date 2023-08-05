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





    }
}
