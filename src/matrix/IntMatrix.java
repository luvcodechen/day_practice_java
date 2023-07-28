package matrix;

import java.util.Arrays;

/**
 * ClassName: IntMatrix
 * Package: matrix
 * Description: Int matrix. For efficiency we do not define ObjectMatrix.
 *
 * @Author: luv_x_c
 * @Create: 2023/5/21 22:26
 */
public class IntMatrix {
    /**
     * The data.
     */
    int[][] data;

    /**
     * The first constructor
     *
     * @param paraRows    The number of rows.
     * @param paraColumns The number of columns.
     */
    public IntMatrix(int paraRows, int paraColumns) {
        data = new int[paraRows][paraColumns];
    }// Of the first constructor

    /**
     * The second constructor. Construct a copy of the given matrix.
     *
     * @param paraMatrix The given matrix.
     */
    public IntMatrix(int[][] paraMatrix) {
        data = new int[paraMatrix.length][paraMatrix[0].length];

//        for (int i = 0; i < data.length; i++) {
//            System.arraycopy(paraMatrix[i], 0, data[i], 0, data[0].length);
//        }// Of for i
        data=paraMatrix.clone();
    }// Of the second constructor

    /**
     * The third constructor. Construct a copy of the given matrix.
     *
     * @param paraMatrix The given matrix.
     */
    public IntMatrix(IntMatrix paraMatrix) {
        this(paraMatrix.getData());
    }//of  the third constructor

    /**
     * Get identity matrix. The values at the diagonal are all 1.
     *
     * @param paraRows The given rows.
     */
    public static IntMatrix getIdentityMatrix(int paraRows) {
        IntMatrix resultMatrix = new IntMatrix(paraRows, paraRows);
        for (int i = 0; i < paraRows; i++) {
            //According to access control, resultMatrix.data can be visited directly
            resultMatrix.data[i][i] = 1;
        }// Of for i
        return resultMatrix;
    }// Of getIdentityMatrix

    @Override
    public String toString() {
        return Arrays.deepToString(data);
    }// Of toString

    /**
     * Get my data. Warning, the reference to the data instead of a copy the data is returned.
     *
     * @return The data matrix.
     */
    public int[][] getData() {
        return data;
    }// Of getData

    /**
     * Getter.
     *
     * @return The number of rows.
     */
    public int gerRows() {
        return data.length;
    }// Of getRows

    /**
     * Getter.
     *
     * @return The number of columns.
     */
    public int getColumns() {
        return data[0].length;
    }// Of getColumns

    /**
     * Set one the value of one element.
     *
     * @param paraRow    The row of the element.
     * @param paraColumn The column of the element.
     * @param paraValue  The new value.
     */
    public void setValue(int paraRow, int paraColumn, int paraValue) {
        data[paraRow][paraColumn] = paraValue;
    }// Of setValue

    /**
     * Get the value of one element.
     *
     * @param paraRow    The row of the element.
     * @param paraColumn The column of the element.
     * @return The value.
     */
    public int getValue(int paraRow, int paraColumn) {
        return data[paraRow][paraColumn];
    }//Of getValue

    /**
     * Add another matrix to me.
     *
     * @param paraMatrix The other matrix.
     */
    public void add(IntMatrix paraMatrix) throws Exception {
        if (paraMatrix == null) {
            throw new Exception("Cannot add matrices. One of the matrix is null. ");
        }
        // Step1. Get the data of the given matrix.
        int[][] tempData = paraMatrix.getData();

        // Step2. Size check
        if (data.length != tempData.length) {
            throw new Exception("Cannot add matrices. Rows not match: " + data.length + " vs. " + tempData.length + ".");
        }// Of if
        if (data[0].length != tempData[0].length) {
            throw new Exception("Cannot add matrices. Columns not match: " + data[0].length + " vs. " + tempData[0].length + ".");
        }// Of if


        // Step3. Add to me.
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[0].length; j++) {
                data[i][j] += tempData[i][j];
            }// Of for j
        } // Of for i
    }// Of add

    /**
     * Add two existing matrices.
     *
     * @param paraMatrix1 The first matrix.
     * @param paraMatrix2 The second matrix.
     * @return A new matrix.
     */
    public static IntMatrix add(IntMatrix paraMatrix1, IntMatrix paraMatrix2) throws Exception {
        // Step1. Clone the first matrix.
        IntMatrix resultMatrix = new IntMatrix(paraMatrix1);
        //IntMatrix resultMatrix=paraMatrix1.clone();

        // Step2. Add the second one.
        resultMatrix.add(paraMatrix2);

        return resultMatrix;
    }// Of add

    /**
     * Multiply two existing matrices.
     *
     * @param paraMatrix1 The first matrix.
     * @param paraMatrix2 The second matrix.
     * @return A new matrix.
     */
    public static IntMatrix multiply(IntMatrix paraMatrix1, IntMatrix paraMatrix2) throws Exception {
        // Step1. Check size.
        int[][] tempData1 = paraMatrix1.getData();
        int[][] tempData2 = paraMatrix2.getData();
        if (tempData1[0].length != tempData2.length) {
            throw new Exception("Cannot multiply matrices: " + tempData1[0].length + " vs. " + tempData2.length + ".");
        }// Of if

        // Step2. Allocate space.
        int[][] resultData = new int[tempData1.length][tempData2[0].length];

        // Step3. Multiply
        for (int i = 0; i < tempData1.length; i++) {
            for (int j = 0; j < tempData2[0].length; j++) {
                for (int k = 0; k < tempData1[0].length; k++) {
                    resultData[i][j] += tempData1[i][k] * tempData2[k][j];
                }// Of for k
            }// Of for j
        }// Of for i

        // Step4. Construct the matrix object.
        IntMatrix resultMatrix = new IntMatrix(resultData);

        return resultMatrix;
    }// Of multiply

    /**
     * The entrance of the program.
     *
     * @param args Not used now.
     */
    public static void main(String[] args) {
        IntMatrix tempMatrix1 = new IntMatrix(3, 3);
        tempMatrix1.setValue(0, 1, 1);
        tempMatrix1.setValue(1, 0, 1);
        tempMatrix1.setValue(1, 2, 1);
        tempMatrix1.setValue(2, 1, 1);
        System.out.println("The original matrix is： " + tempMatrix1);

        IntMatrix tempMatrix2 = null;
        try {
            tempMatrix2 = IntMatrix.multiply(tempMatrix1, tempMatrix1);
        } catch (Exception e) {
            System.out.println(e);
        } // Of try
        System.out.println("The square matrix is： " + tempMatrix2);

        IntMatrix tempMatrix3 = new IntMatrix(tempMatrix2);
        try {
            tempMatrix3.add(tempMatrix1);
        } catch (Exception e) {
            System.out.println(e);
        } // Of try
        System.out.println("The connectivity matrix is： " + tempMatrix3);

        try {
            tempMatrix3.add(null);
        } catch (Exception e) {
            System.out.println(e);
        }
    }// Of main
}// Of class matrix
