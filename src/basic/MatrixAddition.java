package basic;

import java.util.Arrays;

/**
 * ClassName: MatrixAddition
 * Package: basic
 * Description:
 *
 * @Author: luv_x_c
 * @Create: 2023/4/10 9:35
 */
public class MatrixAddition {
    /**
     * The entrance of the program.
     *
     * @param args Not used now.
     */
    public static void main(String[] args) {
        matrixAdditionTest();
    } // Of main

    /**
     * Sum the elements of a matrix.
     *
     * @param paraMatrix The given matrix.
     * @return The sum of all its elements.
     */
    public static int matrixElementSum(int[][] paraMatrix) {
        int resultSum = 0;
        for (int i = 0; i < paraMatrix.length; i++) {
            for (int j = 0; j < paraMatrix[0].length; j++) {
                resultSum += paraMatrix[i][j];
            } // Of j
        } // Of i

        return resultSum;
    } // Of matrixElementSum

    /**
     * Unit test for respective method.
     */
    public static void matrixElementSumTest() {
        int[][] tempMatrix = new int[3][4];
        for (int i = 0; i < tempMatrix.length; i++) {
            for (int j = 0; j < tempMatrix[0].length; j++) {
                tempMatrix[i][j] = i * 10 + j;
            } // Of j
        } // Of i

        System.out.println("The matrix is : \r\n" + Arrays.deepToString(tempMatrix));
        System.out.println("The matrix elements sum is: " + matrixElementSum(tempMatrix) + "\r\n");
    } // Of matrixElementSumTest

    /**
     * Addition two matrix. Attention: NO error check is provided at this moment.
     *
     * @param paraMatrix1 The first matrix.
     * @param paraMatrix2 The second matrix,it should have the same size as the first one.
     * @return The addition of these matrix.
     */
    public static int[][] matrixAddition(int[][] paraMatrix1, int[][] paraMatrix2) {
        int[][] resultMatrix = new int[paraMatrix1.length][paraMatrix2[0].length];

        for (int i = 0; i < paraMatrix1.length; i++) {
            for (int j = 0; j < paraMatrix1[0].length; j++) {
                resultMatrix[i][j] = paraMatrix1[i][j] + paraMatrix2[i][j];
            } // Of j
        } // Of i

        return resultMatrix;
    } // Of matrixAddition

    public static void matrixAdditionTest() {
        int[][] tempMatrix = new int[3][4];
        for (int i = 0; i < tempMatrix.length; i++) {
            for (int j = 0; j < tempMatrix[0].length; j++) {
                tempMatrix[i][j] = i * 10 + j;
            } // Of j
        } // Of i

        System.out.println("The matrix is : \r\n" + Arrays.deepToString(tempMatrix));
        int[][] tempNewMatrix = matrixAddition(tempMatrix, tempMatrix);
        System.out.println("The new matrix is : \r\n" + Arrays.deepToString(tempNewMatrix));
    } // Of matrixAdditionTest
} // Of class MatrixAddition
