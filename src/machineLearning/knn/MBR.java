package machineLearning.knn;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 * ClassName: MBR
 * Package: machineLearning.knn
 * Description: M-distance.
 *
 * @Author: luv_x_c
 * @Create: 2023/6/14 14:32
 */
public class MBR {

    /**
     * Default rating for 1-5 points.
     */
    public static final double DEFAULT_RATING = 3.0;

    /**
     * The total number of users.
     */
    private int numUsers;

    /**
     * The total number of items.
     */
    private int numItems;

    /**
     * The total number of ratings.(None zero values)
     */
    private int numRatings;

    /**
     * The predictions.
     */
    private double[] predictions;

    /**
     * Compressed matrix. User-item-rating triples.
     */
    private int[][] compressRatingMatrix;

    /**
     * User-Item Rating Matrix, transposed from the compressRatingMatrix.
     * 用户-物品评分矩阵，为 compressRatingMatrix 的转置。
     */
    private int[][] userItemRatingMatrix;

    /**
     * The degree of users.(how many items he has rated).
     */
    private int[] userDegrees;

    /**
     * The average rating of the current user.
     */
    private double[] userAverageRatings;

    /**
     * The degree of items .(How many ratings it has.)
     */
    private int[] itemDegrees;

    /**
     * The average rating of the current item.
     */
    private double[] itemAverageRatings;

    /**
     * The first user start from 0. Let the first user hax x ratings, the second user will start from x.
     */
    private int[] userStartingIndices;

    /**
     * Number of non-neighbor objects.
     */
    private int numNoneNeighbors;

    /**
     * The radius (delta) for determining the neighborhood.
     */
    private double radius;

    /**
     * Construct the rating matrix.
     *
     * @param paraFilename   The rating filename.
     * @param paraNumUsers   Number of users.
     * @param paraNumItems   Number of items.
     * @param paraNumRatings Number of ratings.
     */
    public MBR(String paraFilename, int paraNumUsers, int paraNumItems, int paraNumRatings) throws Exception {
        // Step1. Initialize these arrays.
        numItems = paraNumItems;
        numUsers = paraNumUsers;
        numRatings = paraNumRatings;

        userDegrees = new int[numUsers];
        userStartingIndices = new int[numUsers + 1];
        userAverageRatings = new double[numUsers];
        itemDegrees = new int[numItems];
        compressRatingMatrix = new int[numRatings][3];
        itemAverageRatings = new double[numItems];

        predictions = new double[numRatings];

        System.out.println("Reading " + paraFilename);

        // Step2. Read the data file.
        File tempFile = new File(paraFilename);
        if (!tempFile.exists()) {
            System.out.println("File " + paraFilename + "  does not exists ");
            System.exit(0);
        }// Of if
        BufferedReader tempBufReader = new BufferedReader(new FileReader(tempFile));
        String tempString;
        String[] tempStrArray;
        int tempIndex = 0;
        userStartingIndices[0] = 0;
        userStartingIndices[numUsers] = numRatings;
        while ((tempString = tempBufReader.readLine()) != null) {
            // Each line has three values.
            tempStrArray = tempString.split(",");
            compressRatingMatrix[tempIndex][0] = Integer.parseInt(tempStrArray[0]);
            compressRatingMatrix[tempIndex][1] = Integer.parseInt(tempStrArray[1]);
            compressRatingMatrix[tempIndex][2] = Integer.parseInt(tempStrArray[2]);

            userDegrees[compressRatingMatrix[tempIndex][0]]++;
            itemDegrees[compressRatingMatrix[tempIndex][0]]++;

            if (tempIndex > 0) {
                // Starting to read the data of a new user.
                if (compressRatingMatrix[tempIndex][0] != compressRatingMatrix[tempIndex - 1][0]) {
                    userStartingIndices[compressRatingMatrix[tempIndex][0]] = tempIndex;
                }// OF if
            }// Of if
            tempIndex++;
        }// Of while
        tempBufReader.close();

        double[] tempUserTotalScore = new double[numUsers];
        double[] tempItemTotalScore = new double[numItems];
        for (int i = 0; i < numRatings; i++) {
            tempUserTotalScore[compressRatingMatrix[i][0]] += compressRatingMatrix[i][2];
            tempItemTotalScore[compressRatingMatrix[i][1]] += compressRatingMatrix[i][2];
        }// Of for i

        for (int i = 0; i < numUsers; i++) {
            userAverageRatings[i] = tempUserTotalScore[i] / userDegrees[i];
        }// OF for i
        for (int i = 0; i < numItems; i++) {
            itemAverageRatings[i] = tempItemTotalScore[i] / itemDegrees[i];
        }// Of for i
    }// OF the first constructor



    /**
     * Construct the rating matrix and transpose it.
     * 构造评分矩阵并进行转置。
     *
     * @param paraFilename   The rating filename.
     * @param paraNumUsers   Number of users.
     * @param paraNumItems   Number of items.
     * @param paraNumRatings Number of ratings.
     */
    public MBR(String paraFilename, int paraNumUsers, int paraNumItems, int paraNumRatings,int paraConstructor) throws Exception {
        // Step1. Initialize these arrays.
        numItems = paraNumItems;
        numUsers = paraNumUsers;
        numRatings = paraNumRatings;

        userDegrees = new int[numUsers];
        userStartingIndices = new int[numUsers + 1];
        userAverageRatings = new double[numUsers];
        itemDegrees = new int[numItems];
        compressRatingMatrix = new int[numRatings][3];
        itemAverageRatings = new double[numItems];

        predictions = new double[numRatings];

        // Step2. Read the data file and construct the userItemRatingMatrix.
        System.out.println("Reading " + paraFilename);
        userItemRatingMatrix = new int[numItems][numUsers]; // Transposed matrix

        File tempFile = new File(paraFilename);
        if (!tempFile.exists()) {
            System.out.println("File " + paraFilename + " does not exist");
            System.exit(0);
        }

        BufferedReader tempBufReader = new BufferedReader(new FileReader(tempFile));
        String tempString;
        String[] tempStrArray;
        int tempIndex = 0;
        userStartingIndices[0] = 0;
        userStartingIndices[numUsers] = numRatings;
        while ((tempString = tempBufReader.readLine()) != null) {
            tempStrArray = tempString.split(",");
            int userIndex = Integer.parseInt(tempStrArray[0]);
            int itemIndex = Integer.parseInt(tempStrArray[1]);
            int rating = Integer.parseInt(tempStrArray[2]);

            compressRatingMatrix[tempIndex][0] = userIndex;
            compressRatingMatrix[tempIndex][1] = itemIndex;
            compressRatingMatrix[tempIndex][2] = rating;

            // Transpose and store in the userItemRatingMatrix
            userItemRatingMatrix[itemIndex][userIndex] = rating;

            userDegrees[userIndex]++;
            itemDegrees[itemIndex]++;

            if (tempIndex > 0 && compressRatingMatrix[tempIndex][0] != compressRatingMatrix[tempIndex - 1][0]) {
                userStartingIndices[compressRatingMatrix[tempIndex][0]] = tempIndex;
            }

            tempIndex++;
        }
        tempBufReader.close();

        // Calculate average ratings for users and items.
        double[] tempUserTotalScore = new double[numUsers];
        double[] tempItemTotalScore = new double[numItems];
        for (int i = 0; i < numRatings; i++) {
            tempUserTotalScore[compressRatingMatrix[i][0]] += compressRatingMatrix[i][2];
            tempItemTotalScore[compressRatingMatrix[i][1]] += compressRatingMatrix[i][2];
        }
        for (int i = 0; i < numUsers; i++) {
            userAverageRatings[i] = tempUserTotalScore[i] / userDegrees[i];
        }
        for (int i = 0; i < numItems; i++) {
            itemAverageRatings[i] = tempItemTotalScore[i] / itemDegrees[i];
        }
    }

    /**
     * Set the radius.
     *
     * @param paraRadius The given radius.
     */
    public void setRadius(double paraRadius) {
        if (paraRadius > 0) {
            radius = paraRadius;
        } else {
            radius = 0.1;
        }// OF if
    }// Of setRadius

    /**
     * Leave-one-out prediction. The predicted values are stored in predictions.
     */
    public void leaveOneOutPrediction() {
        double tempItemAverageRating;
        // Make each line of the code shorter.
        int tempUser, tempItem, tempRating;
        System.out.println("\r\nLeaveOneOutPrediction for radius " + radius);

        numNoneNeighbors = 0;
        for (int i = 0; i < numRatings; i++) {
            tempUser = compressRatingMatrix[i][0];
            tempItem = compressRatingMatrix[i][1];
            tempRating = compressRatingMatrix[i][2];

            // Step1. Recompute average rating of the current item.
            tempItemAverageRating =
                    (itemAverageRatings[tempItem] * itemDegrees[tempItem] - tempRating) / (itemDegrees[tempItem] - 1);

            // Step2. Recompute neighbors, at the same time obtain the ratings
            // OF neighbors
            int tempNeighbors = 0;
            double tempTotal = 0;
            int tempComparedItem;
            for (int j = userStartingIndices[tempUser]; j < userStartingIndices[tempUser + 1]; j++) {
                tempComparedItem = compressRatingMatrix[j][1];
                if (tempItem == tempComparedItem) {
                    continue;// Ignore itself
                }// Of if

                if (Math.abs(tempItemAverageRating - itemAverageRatings[tempComparedItem]) < radius) {
                    tempTotal += compressRatingMatrix[j][2];
                    tempNeighbors++;
                }// Of if
            }// OF for j

            //Step3. Predict as the average value of neighbors.
            if (tempNeighbors > 0) {
                predictions[i] = tempTotal / tempNeighbors;
            } else {
                predictions[i] = DEFAULT_RATING;
                numNoneNeighbors++;
            }// Of if
        }// OF for i
    }// of LeaveOneOutPrediction

    /**
     * Compute the MAE based on the deviation of each leave-one-out.
     */
    public double computeMAE() throws Exception {
        double tempTotalError = 0;
        for (int i = 0; i < predictions.length; i++) {
            tempTotalError += Math.abs(predictions[i] - compressRatingMatrix[i][2]);
        }// OF for i

        return tempTotalError / predictions.length;
    }// OF computeMAE

    /**
     * ************************
     * Compute the MAE based on the deviation of each leave-one-out.
     * ************************
     */
    public double computeRSME() throws Exception {
        double tempTotalError = 0;
        for (int i = 0; i < predictions.length; i++) {
            tempTotalError += (predictions[i] - compressRatingMatrix[i][2])
                    * (predictions[i] - compressRatingMatrix[i][2]);
        } // Of for i

        double tempAverage = tempTotalError / predictions.length;

        return Math.sqrt(tempAverage);
    }// Of computeRSME

    /**
     * The entrance of the program.
     *
     * @param args Not used now.
     */
    public static void main(String[] args) {
        try {
            MBR tempRecommender = new MBR("E:\\java_code\\data\\sampledata\\movielens-943u1682m.txt", 943, 1682,
                    100000,22);
            for (double tempRadius = 0.2; tempRadius < 0.6; tempRadius += 0.1) {
                tempRecommender.setRadius(tempRadius);

                tempRecommender.leaveOneOutPrediction();
                double tempMAE = tempRecommender.computeMAE();
                double tempRSME = tempRecommender.computeRSME();

                System.out.println("Radius = " + tempRadius + ", MAE = " + tempMAE + ", RSME = " + tempRSME
                        + ", numNonNeighbors = " + tempRecommender.numNoneNeighbors);
            } // Of for tempRadius
        } catch (Exception ee) {
            System.out.println(ee);
        } // Of try
    }// Of main
}// Of class MBR
