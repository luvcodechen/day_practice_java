package basic;

import java.util.Arrays;

/**
 * ClassName: MatrixMultiplication
 * Package: basic
 * Description:
 *
 * @Author: luv_x_c
 * @Create: 2023/4/11 14:28
 */
public class MatrixMultiplication {
    /**
     * The entrance of the program.
     *
     * @param args NOte used now.
     */
    public static void main(String[] args) {
        multiplicationTest();
    }// Of main

    /**
     * Matrix multiplication. The columns od the first matrix should be equal to the rows of the second one.
     *
     * @param paraFirstMatrix  The first matrix.
     * @param paraSecondMatrix The first matrix.
     * @return The result matrix.
     */
    public static int[][] multiplication(int[][] paraFirstMatrix, int[][] paraSecondMatrix) {
        int m = paraFirstMatrix.length;
        int n = paraFirstMatrix[0].length;
        int p = paraSecondMatrix[0].length;

        // Step 1. Dimension check
        if (paraSecondMatrix.length != n) {
            System.out.println("The two matrices cannot be multiplied");
            return null;
        }// Of if

        // Step 2. The loop
        int[][] resultMatrix = new int[m][p];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < p; j++) {
                for (int k = 0; k < n; k++) {
                    resultMatrix[i][j] += paraFirstMatrix[i][k] * paraSecondMatrix[k][j];
                }// Of k
            }// Of j
        }// Of i
        return resultMatrix;
    }// Of multiplication

    /**
     * Unit test for the respective method.
     */
    public static void multiplicationTest() {
        int[][] tempFirstMatrix = new int[2][3];
        for (int i = 0; i < tempFirstMatrix.length; i++) {
            for (int j = 0; j < tempFirstMatrix[0].length; j++) {
                tempFirstMatrix[i][j] = i + j;
            }// Of j
        }// Of i
        System.out.println("The first matrix is :\r\n" + Arrays.deepToString(tempFirstMatrix));

        int[][] tempSecondMatrix = new int[3][2];
        for (int i = 0; i < tempSecondMatrix.length; i++) {
            for (int j = 0; j < tempSecondMatrix[0].length; j++) {
                tempSecondMatrix[i][j] = i + j;
            }// Of j
        }// Of i
        System.out.println("The second matrix is:\r\n" + Arrays.deepToString(tempSecondMatrix));

        int[][] tempThirdMatrix = multiplication(tempFirstMatrix, tempSecondMatrix);
        System.out.println("The third matrix is:\r\n" + Arrays.deepToString(tempThirdMatrix));

        System.out.println("Trying to multiply the first matrix with itself.\r\n");
        tempThirdMatrix = multiplication(tempFirstMatrix, tempFirstMatrix);
        System.out.println("The result matrix is:\r\n" + Arrays.deepToString(tempThirdMatrix));
    }// Of class MatrixMultiplication

}// Of MatrixMultiplication
