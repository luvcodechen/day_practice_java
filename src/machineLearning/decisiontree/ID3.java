package machineLearning.decisiontree;

import weka.core.Instances;

import java.io.FileReader;

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

    /**
     * The children nodes.
     */
    ID3[] children;

    /**
     * My label. Inner nodes also have a label. For example, <outlook =sunny ,humidity = high > never appear it the
     * training data, but <humidity =high> is valid  in other cases.
     */
    int label;

    /**
     * The prediction, including queried and predicted labels.
     */
    int[] predicts;

    /**
     * Small block cannot be split further.
     */
    static int smallBlockThreshold = 3;

    /**
     * The constructor.
     *
     * @param paraFileName The given file.
     */
    public ID3(String paraFileName) {
        dataset = null;
        try {
            FileReader fileReader = new FileReader(paraFileName);
            dataset = new Instances(fileReader);
            fileReader.close();
        } catch (Exception ee) {
            System.out.println("Cannot read the file: " + paraFileName + "\r\n" + ee);
            System.exit(0);
        }// Of try

        dataset.setClassIndex(dataset.numAttributes() - 1);
        numClasses = dataset.classAttribute().numValues();

        availableInstances = new int[dataset.numInstances()];
        for (int i = 0; i < availableInstances.length; i++) {
            availableInstances[i] = i;
        }//Of for i
        availableAttributes = new int[dataset.numAttributes() - 1];
        for (int i = 0; i < availableInstances.length; i++) {
            availableAttributes[i] = i;
        }// OF for i

        //Initialize.
        children = null;
        label = getMajorityClass(availableInstances);
        pure = pureJudge(availableInstances);
    }// Of the first constructor

    /**
     * The constructor.
     */
    public ID3(Instances paraDataset, int[] parAvailableInstances, int[] paraAvailableAttributes) {
        //Copy its reference instead of clone the availableInstances.
        dataset = paraDataset;
        availableInstances = parAvailableInstances;
        availableAttributes = paraAvailableAttributes;

        //Initialize.
        children = null;
        label = getMajorityClass(availableInstances);
        pure = pureJudge(availableInstances);
    }// OF the second constructor

    /**
     * Is the given block pure?
     *
     * @param paraBlock The block.
     * @return True if pure.
     */
    public boolean pureJudge(int[] paraBlock) {
        pure = true;

        for (int i = 1; i < paraBlock.length; i++) {
            if (dataset.instance(paraBlock[i]).classValue() != dataset.instance(paraBlock[0]).classValue()) {
                pure = false;
                break;
            }//Of if
        }//Of for i

        return pure;
    }//Of pureJudge

    /**
     * Compute the majority class of the given block for voting.
     *
     * @param paraBlock The block.
     * @return The majority class.
     */
    public int getMajorityClass(int[] paraBlock) {
        int[]tempClassCounts=new int[dataset.numClasses()];
        for (int i = 0; i < paraBlock.length; i++) {
            tempClassCounts[(int)dataset.instance(paraBlock[i]).classValue()]++;
        }//OF for i

        int resultMajorityClass=-1;
        int tempMaxCount=-1;
        for (int i = 0; i < tempClassCounts.length; i++) {
            if(tempMaxCount<tempClassCounts[i]){
                resultMajorityClass=i;
                tempMaxCount=tempClassCounts[i];
            }//Of if
        }//Of for i

        return resultMajorityClass;
    }//Of getMajorityClass



}
