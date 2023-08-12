package machineLearning.decisiontree;

import weka.core.Instance;
import weka.core.Instances;

import java.io.FileReader;
import java.util.Arrays;

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
        for (int i = 0; i < availableAttributes.length; i++) {
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
        int[] tempClassCounts = new int[dataset.numClasses()];
        for (int i = 0; i < paraBlock.length; i++) {
            tempClassCounts[(int) dataset.instance(paraBlock[i]).classValue()]++;
        }//OF for i

        int resultMajorityClass = -1;
        int tempMaxCount = -1;
        for (int i = 0; i < tempClassCounts.length; i++) {
            if (tempMaxCount < tempClassCounts[i]) {
                resultMajorityClass = i;
                tempMaxCount = tempClassCounts[i];
            }//Of if
        }//Of for i

        return resultMajorityClass;
    }//Of getMajorityClass

    /**
     * Select the best attribute.
     *
     * @return The best attribute index.
     */
    public int selectBestAttribute() {
        splitAttributes = -1;
        double tempMinimalEntropy = 10000;
        double tempEntropy;
        for (int i = 0; i < availableAttributes.length; i++) {
            tempEntropy = conditionalEntropy(availableAttributes[i]);
            if (tempMinimalEntropy > tempEntropy) {
                tempMinimalEntropy = tempEntropy;
                splitAttributes = availableAttributes[i];
            }//Of if
        }//Of for i
        return splitAttributes;
    }//Of selectBestAttribute

    /**
     * Compute the conditional entropy of an attribute.
     *
     * @param paraAttribute The given attribute.
     * @return The entropy.
     */
    public double conditionalEntropy(int paraAttribute) {
        // Step1. Statistics.
        int tempNumClasses = dataset.numClasses();
        int tempNumValues = dataset.attribute(paraAttribute).numValues();
        int tempNumInstances = availableInstances.length;
        double[] tempValueCounts = new double[tempNumValues];
        double[][] tempCountMatrix = new double[tempNumValues][tempNumClasses];

        int tempClass, tempValue;
        for (int i = 0; i < tempNumInstances; i++) {
            tempClass = (int) dataset.instance(availableInstances[i]).classValue();
            tempValue = (int) dataset.instance(availableInstances[i]).value(paraAttribute);
            tempValueCounts[tempValue]++;
            tempCountMatrix[tempValue][tempClass]++;
        }//Of for i

        // Step2.
        double resultEntropy = 0;
        double tempEntropy, tempFraction;
        for (int i = 0; i < tempNumValues; i++) {
            if (tempValueCounts[i] == 0) {
                continue;
            }//Of if
            tempEntropy = 0;
            for (int j = 0; j < tempNumClasses; j++) {
                tempFraction = tempCountMatrix[i][j] / tempValueCounts[i];
                if (tempFraction == 0) {
                    continue;
                }//Of if
                tempEntropy += -tempFraction * Math.log(tempFraction);
            }//Of for j
            resultEntropy += tempValueCounts[i] / tempNumInstances * tempEntropy;
        }//Of for i

        return resultEntropy;
    }//Of conditionalEntropy

    /**
     * Split the data according to the given attribute.
     *
     * @param paraAttribute The given attribute.
     * @return The blocks.
     */
    public int[][] splitData(int paraAttribute) {
        int tempNumValues = dataset.attribute(paraAttribute).numValues();
        int[][] resultBlocks = new int[tempNumValues][];
        int[] tempSizes = new int[tempNumValues];

        // First scan to count the size of each block.
        int tempValue;
        for (int i = 0; i < availableInstances.length; i++) {
            tempValue = (int) dataset.instance(availableInstances[i]).value(paraAttribute);
            tempSizes[tempValue]++;
        }//Of for i

        // Allocate space.
        for (int i = 0; i < tempNumValues; i++) {
            resultBlocks[i] = new int[tempSizes[i]];
        }//Of for i

        // Second scan to fill.
        Arrays.fill(tempSizes, 0);
        for (int i = 0; i < availableInstances.length; i++) {
            tempValue = (int) dataset.instance(availableInstances[i]).value(paraAttribute);
            // Copy data
            resultBlocks[tempValue][tempSizes[tempValue]] = availableInstances[i];
             tempSizes[tempValue]++;
        }// OF for i

        return resultBlocks;
    }//Of splitData

    /**
     * Build the tree recursively.
     */
    public void buildTree() {
        if (pureJudge(availableInstances)) {
            return;
        }//OF if
        if (availableInstances.length <= smallBlockThreshold) {
            return;
        }//Of if

        selectBestAttribute();
        int[][] tempSubBlocks = splitData(splitAttributes);
        children = new ID3[tempSubBlocks.length];

        //Construct the remaining attribute set.
        int[] tempRemainingAttributes = new int[availableAttributes.length - 1];
        for (int i = 0; i < availableAttributes.length; i++) {
            if (availableAttributes[i] < splitAttributes) {
                tempRemainingAttributes[i] = availableAttributes[i];
            } else if (availableAttributes[i] > splitAttributes) {
                tempRemainingAttributes[i - 1] = availableAttributes[i];
            }//Of if
        }//Of for i

        //Construct children.
        for (int i = 0; i < children.length; i++) {
            if ((tempSubBlocks[i] == null) || (tempSubBlocks[i].length == 0)) {
                children[i] = null;
            } else {
                children[i] = new ID3(dataset, tempSubBlocks[i], tempRemainingAttributes);
                children[i].buildTree();
            }//Of if
        }//OF for i
    }//OF buildTree

    /**
     * Classify an instance,
     *
     * @param paraInstance The given instance.
     * @return The prediction.
     */
    public int classify(Instance paraInstance) {
        if (children == null) {
            return label;
        }//Of if

        ID3 tempChild = children[(int) paraInstance.value(splitAttributes)];
        if (tempChild == null) {
            return label;
        }//Of if

        return tempChild.classify(paraInstance);
    }//Of classify

    /**
     * Test on n testing set.
     *
     * @param paraDataset The given testing set.
     * @return The accuracy.
     */
    public double test(Instances paraDataset) {
        double tempCorrect = 0;
        for (int i = 0; i < paraDataset.numInstances(); i++) {
            if (classify(paraDataset.instance(i)) == (int) paraDataset.instance(i).classValue()) {
                tempCorrect++;
            }//Of if
        }//Of for i

        return tempCorrect / paraDataset.numInstances();
    }//Of test

    /**
     * Test on the training set.
     *
     * @return The accuracy.
     */
    public double selfTest() {
        return test(dataset);
    }//Of selfTest

    @Override
    public String toString() {
        String resultString = "";
        String tempAttributeName = dataset.attribute(splitAttributes).name();
        if (children == null) {
            resultString += "class = " + label;
        } else {
            for (int i = 0; i < children.length; i++) {
                if (children[i] == null) {
                    resultString += tempAttributeName + " = " + dataset.attribute(splitAttributes).value(i) + " : " + "class= " + label + "\r\n";
                } else {
                    resultString += tempAttributeName + " = " + dataset.attribute(splitAttributes).value(i) + " : " + children[i] + "\r\n";
                }//OF if
            }//Of for i
        }//Of if

        return resultString;
    }//OF toString

    /**
     * Test this class.
     */
    public static void id3Test() {
        ID3 tempID3 = new ID3("E:\\java_code\\data\\sampledata\\weather.arff");
        ID3.smallBlockThreshold = 3;
        tempID3.buildTree();

        System.out.println("The tree is: \r\n" + tempID3);

        double tempAccuracy = tempID3.selfTest();
        System.out.println("The accuracy is: " + tempAccuracy);
    }//Of id3Test

    /**
     * The entrance of the program.
     *
     * @param args Not used now.
     */
    public static void main(String[] args) {
        id3Test();
    }//OF main
}//Of class ID3
