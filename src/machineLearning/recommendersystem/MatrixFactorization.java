package machineLearning.recommendersystem;

import java.util.Random;

/**
 * ClassName: MatrixFactorization
 * Package: machineLearning.recommendersystem
 * Description: Matrix factorization for recommender systems.
 *
 * @Author: luv_x_c
 * @Create: 2023/9/11 10:44
 */
public class MatrixFactorization {
    /**
     * Used to generate random numbers.
     */
    Random random = new Random();

    /**
     * Number of users.
     */
    int numUsers;

    /**
     * Number of items.
     */
    int numItems;

    /**
     * Number of ratings.
     */
    int numRatings;

    /**
     * Training data.
     */
    Triple[] dataset;

    /**
     * A parameter for controller learning regular.
     */
    double alpha;




    public class Triple {
        public int user;
        public int item;
        public double rating;

        public Triple() {
            user = -1;
            item = -1;
            rating = -1;
        }// Of the first constructor

        public Triple(int paraUser, int paraItem, double paraRating) {
            user = paraUser;
            item = paraItem;
            rating = paraRating;
        }//Of the second constructor

        @Override
        public String toString() {
            return "Triple{" +
                    "user=" + user +
                    ", item=" + item +
                    ", rating=" + rating +
                    '}';
        }
    }//Of class Triple
}// Of class MatrixFactorization
