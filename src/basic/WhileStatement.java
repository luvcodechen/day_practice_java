package basic;

/**
 * ClassName: WhileStatement
 * Package: basic
 * Description:
 *
 * @Author: luv_x_c
 * @Create: 2023/4/12 16:13
 */
public class WhileStatement {
    /**
     * The entrance of the program.
     *
     * @param args Not used now.
     */
    public static void main(String[] args) {
        whileStatementTest();
    }// Of main

    /**
     * The sum not exceeding a given value.
     */
    public static void whileStatementTest() {
        int tempMax = 100;
        int tempValue = 0;
        int tempSum = 0;

        // Approach 1.
        while (tempSum <= tempMax) {
            tempValue++;
            tempSum += tempValue;
            System.out.println("tempValue = " + tempValue + ",tempSum = " + tempSum);
        }// Of while
        tempSum -= tempValue;

        // Approach 2.
        System.out.println("\r\nAlternative approach.");
        tempSum = 0;
        tempValue = 0;
        while (true) {
            tempValue++;
            tempSum += tempValue;
            System.out.println("tempValue = " + tempValue + ",tempSum = " + tempSum);

            if (tempMax < tempSum) {
                break;
            }// Of while
            tempSum -= tempValue;

            System.out.println("The sum not exceeding " + tempMax + " is " + tempSum);
        }// Of while

    }// Of whileStatementTest
}// Of class WhileStatement
