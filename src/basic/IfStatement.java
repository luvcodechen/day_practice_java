package basic;

/**
 * ClassName: IfStatement
 * Package: basic
 * Description:
 *
 * @Author: luv_x_c
 * @Create: 2023/4/6 -11:23
 */
public class IfStatement {
    /**
     * The entrance of the program
     *
     * @param args Not used now
     */
    public static void main(String[] args) {
        int tempNumber1, tempNumber2;

        tempNumber1 = 5;

        if (tempNumber1 >= 0) {
            tempNumber2 = tempNumber1;
        } else {
            tempNumber2 = -tempNumber1;
        }// Of if

        System.out.println("The absolute value of " + tempNumber1 + " is " + tempNumber2);

        tempNumber1 = -3;

        if (tempNumber1 >= 0) {
            tempNumber2 = tempNumber1;
        } else {
            tempNumber2 = -tempNumber1;
        }// Of if

        System.out.println("The absolute value of " + tempNumber1 + " is " + tempNumber2);

        tempNumber1 = 8;
        System.out.println("The absolute value of " + tempNumber1 + " is " + abs(tempNumber1));

        tempNumber1 = -2;
        System.out.println("The absolute value of " + tempNumber1 + " is " + abs(tempNumber1));

    }//Of main


    /**
     * ********************
     * The absolute value of the given parameter.
     *
     * @param paraValue The given value.
     *                  ********************
     */
    public static int abs(int paraValue) {
        if (paraValue >= 0) {
            return paraValue;
        } else {
            return -paraValue;
        }//Of if

    }// Of abs

}
