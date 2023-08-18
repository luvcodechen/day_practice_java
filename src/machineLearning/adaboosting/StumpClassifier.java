package machineLearning.adaboosting;

import weka.core.Instance;

import java.io.FileReader;
import java.util.Arrays;

/**
 * ClassName: StumpClassifier
 * Package: machineLearning.adaboosting
 * Description:The stump classifier.
 *
 * @Author: luv_x_c
 * @Create: 2023/8/17 20:37
 */
public class StumpClassifier extends SimpleClassifier {
    /**
     * The best cut for the current attribute on weightInstances.
     */
    double bestCut;

    /**
     * The class label for attribute value less than bestCut.
     */
    int leftLeafLabel;

    /**
     * The class label for attribute value no less than bestCut.
     */
    int rightLeafLabel;

    /**
     * The only constructor.
     *
     * @param paraWeightedInstances The given instances.
     */
    public StumpClassifier(WeightedInstances paraWeightedInstances) {
        super(paraWeightedInstances);
    }//Of the only constructor

    public void train() {
        //Step1. Randomly choose an attribute.
        selectedAttribute = random.nextInt(numConditions);

        //Step2. Find all attributes values and sort.
        double[] tempValuesArray = new double[numInstances];
        for (int i = 0; i < tempValuesArray.length; i++) {
            tempValuesArray[i] = weightedInstances.instance(i).value(selectedAttribute);
        }//Of for i
        Arrays.sort(tempValuesArray);

        //Step3. Initialize, classify all instances as the same with the original cut.
        int tempNumLabels = numClasses;
        double[] tempLabelCountArray = new double[tempNumLabels];
        int tempCurrentLabel;

        //Step3.1 Scan all labels to obtain their counts.
        for (int i = 0; i < numInstances; i++) {
            // The label of the ith instance
            tempCurrentLabel = (int) weightedInstances.instance(i).classValue();
            tempLabelCountArray[tempCurrentLabel] += weightedInstances.getWeight(i);
        }//Of for i

        //Step3.2 Find the label with the maximal count.
        double tempMaxCorrect = 0;
        int tempBestLabel = -1;
        for (int i = 0; i < tempLabelCountArray.length; i++) {
            if (tempMaxCorrect < tempLabelCountArray[i]) {
                tempMaxCorrect = tempLabelCountArray[i];
                tempBestLabel = i;
            }//Of if
        }//Of for i

        //Steep3.3 The cut is a little  smaller than the minimal value.
        bestCut = tempValuesArray[0] - 0.1;
        leftLeafLabel = tempBestLabel;
        rightLeafLabel = tempBestLabel;

        // Step4. Check candidate cuts one by one.
        // Step4.1 To handle multi-class data, left and right.
        double tempCut;
        double[][] tempLabelCountMatrix = new double[2][tempNumLabels];

        for (int i = 0; i < tempValuesArray.length - 1; i++) {
            // Step4.1 Some attribute values are identical, ignore them.
            if (tempValuesArray[i] == tempValuesArray[i + 1]) {
                continue;
            }//Of if
            tempCut = (tempValuesArray[i] + tempValuesArray[i + 1]) / 2;

            // Step4.2 Scan all labels to obtain their counts wrt, the cut .
            // Initialize again since it is used many times.
            for (int j = 0; j < 2; j++) {
                for (int k = 0; k < tempNumLabels; k++) {
                    tempLabelCountMatrix[j][k] = 0;
                }//Of for k
            }//Of for j

            for (int j = 0; j < numInstances; j++) {
                // The label of the jth instance.
                tempCurrentLabel = (int) weightedInstances.instance(j).classValue();
                if (weightedInstances.instance(j).value(selectedAttribute) < tempCut) {
                    tempLabelCountMatrix[0][tempCurrentLabel] += weightedInstances.getWeight(j);
                } else {
                    tempLabelCountMatrix[1][tempCurrentLabel] += weightedInstances.getWeight(j);
                }//Of if
            }//Of for j

            // Step4.3 Left leaf
            double tempLeftMaxCorrect = 0;
            int tempLeftBestLabel = 0;
            for (int j = 0; j < tempLabelCountMatrix[0].length; j++) {
                if (tempLeftMaxCorrect < tempLabelCountMatrix[0][j]) {
                    tempLeftMaxCorrect = tempLabelCountMatrix[0][j];
                    tempLeftBestLabel = j;
                }//Of if
            }//Of for j

            // Step 4.4 Right leaf
            double tempRightMaxCorrect = 0;
            int tempRightBestLabel = 0;
            for (int j = 0; j < tempLabelCountMatrix[0].length; j++) {
                if (tempRightMaxCorrect < tempLabelCountMatrix[1][j]) {
                    tempRightMaxCorrect = tempLabelCountMatrix[1][j];
                    tempRightBestLabel = j;
                }//Of if
            }//Of for j

            // Step 4.5 Compare with the current best
            if (tempMaxCorrect < tempLeftMaxCorrect + tempRightMaxCorrect) {
                tempMaxCorrect = tempLeftMaxCorrect + tempRightMaxCorrect;
                bestCut = tempCut;
                leftLeafLabel = tempLeftBestLabel;
                rightLeafLabel = tempRightBestLabel;
            }//Of if
        }//Of for i

        System.out.println("Attribute = " + selectedAttribute + ", cut =" + bestCut + ", " +
                "leftLeafLabel = " + leftLeafLabel + ", rightLeafLabel" + rightLeafLabel);
    }//Of train


    @Override
    public int classify(Instance paraInstance) {
        int resultLabel = -1;
        if (paraInstance.value(selectedAttribute) < bestCut) {
            resultLabel = leftLeafLabel;
        } else {
            resultLabel = rightLeafLabel;
        }//Of if
        return resultLabel;
    }//Of classify

    @Override
    public String toString() {
        return "I am a stump classifier.\r\n" + "I choose attribute #" + selectedAttribute
                + " with cut value " + bestCut + ".\r\n" + "The left and right leaf labels are " + leftLeafLabel
                + " and " + rightLeafLabel + ", respectively.\r\n" + "My weighted error is: " + computeWeightedError()
                + ".\r\n" + "My weighted accuracy is : " + computeTrainingAccuracy() + ".";
    }//Of toString

    /**
     * For unit test.
     *
     * @param args Not used.
     */
    public static void main(String[] args) {
        WeightedInstances tempWeightedInstance = null;
        String tempFileName = "E:\\java_code\\data\\sampledata\\iris.arff";
        try {
            FileReader fileReader = new FileReader(tempFileName);
            tempWeightedInstance = new WeightedInstances(fileReader);
            fileReader.close();
        } catch (Exception e) {
            System.out.println("Cannot read the file: " + tempFileName + "\r\n" + e);
            System.exit(0);
        }//OF try

        StumpClassifier tempClassifier = new StumpClassifier(tempWeightedInstance);
        tempClassifier.train();
        System.out.println(tempClassifier);

        System.out.println(Arrays.toString(tempClassifier.computeCorrectnessArray()));
    }//OF main
}//Of class StumpClassifier
