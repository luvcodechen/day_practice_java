package machineLearning.knn;

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
    private int [][]compressRatingMatrix;

    /**
     * The degree of users.(how many item he has rated).
     */
    private int[]userDegrees;


    

}
