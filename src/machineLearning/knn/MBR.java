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
     * The degree of users.(how many items he has rated).
     */
    private int[] userDegrees;

    /**
     * The average rating of the current user.
     */
    private double[] userAverageRatings;

    /**
     * The degree of items .
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

    }
}
