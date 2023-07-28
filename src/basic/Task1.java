package basic;

import java.util.Arrays;

/**
 * ClassName: Task1
 * Package: basic
 * Description:
 *
 * @Author: luv_x_c
 * @Create: 2023/4/13 17:17
 */
public class Task1 {
    /**
     * The entrance of the program.
     *
     * @param args Not used now.
     */
    public static void main(String[] args) {
        task1();
    }// Of main

    /**
     * Method unit test.
     */
    public static void task1() {
        // Step 1. Generate the data with n students and courses.
        int n = 5;
        int m = 3;
        int lowerBound = 50;
        int upperBound = 100;
        int threshold = 60;

        int[][] data = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                data[i][j] = lowerBound + (int) (Math.random() * (upperBound - lowerBound + 1));
            }// Of j
        }// Of i

        System.out.println("The data is: \r\n" + Arrays.deepToString(data));

        // Step 2. compute the total score of each student.
        int[] totalScores = new int[n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (data[i][j] < threshold) {
                    totalScores[i] = 0;
                    break;
                }// Of if
                totalScores[i] += data[i][j];
            }// Of j
        }// Of i

        System.out.println("The total scores are:\r\n" + Arrays.toString(totalScores));

        // Step 3.  Find the best and worst student.
        // Typical initialization for index: invalid value.
        int tempBestIndex = -1;
        int tempWorstIndex = -1;
        // Typical initialization for best and worst values.
        // They must be replaced by valid values.
        int tempBestScore = 0;
        int tempWorstScore = m * upperBound + 1;
        for (int i = 0; i < n; i++) {
            // Do not consider failed students.
            if (totalScores[i] == 0) {
                continue;
            }// Of if

            if (tempBestScore < totalScores[i]) {
                tempBestScore = totalScores[i];
                tempBestIndex = i;
            }// Of if

            if (tempWorstScore > totalScores[i]) {
                tempWorstScore = totalScores[i];
                tempWorstIndex = i;
            } // Of if
        }// Of for i

        // Step 4. Output the student number and score.
        if (tempBestIndex == -1) {
            System.out.println("Cannot find best student. All students have failed.");
        } else {
            System.out.println("The best student is No." + tempBestIndex + " with scores: "
                    + Arrays.toString(data[tempBestIndex]));
        } // Of if

        if (tempWorstIndex == -1) {
            System.out.println("Cannot find worst student. All students have failed.");
        } else {
            System.out.println("The worst student is No." + tempWorstIndex + " with scores: "
                    + Arrays.toString(data[tempWorstIndex]));
        } // Of if
    }// Of task1
}// Of task1
