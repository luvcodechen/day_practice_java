package basic;

/**
 * ClassName: ForStatement
 * Package: basic
 * Description:
 *
 * @Author: luv_x_c
 * @Create: 2023/4/9 11:07
 */
public class ForStatement {
    /**
     * The entrance of the program.
     *
     * @param args Not used now.
     */
    public static void main(String[] args) {
        forStatementTest();
    } // Of main

    /**
     * Method unit test.
     */
    public static void forStatementTest() {
        int tempN = 10;
        System.out.println("1 add to " + tempN + " is: " + addToN(tempN));

        tempN = 0;
        System.out.println("1 add to " + tempN + " is: " + addToN(tempN));

        int tempStepLength = 1;
        tempN = 10;
        System.out.println("1 add to " + tempN + " with step length " + tempStepLength + " is: " + addToNWithStepLength(tempN, tempStepLength));

        tempStepLength = 2;
        System.out.println("1 add to " + tempN + " with step length " + tempStepLength + " is: " + addToNWithStepLength(tempN, tempStepLength));

    } // Of forStatementTest

    /**
     * add from 1 to N
     *
     * @param paraN The given upper bound.
     * @return The sum.
     */
    public static int addToN(int paraN) {
        int resultSum = 0;

        for (int i = 1; i <= paraN; i++) {
            resultSum += i;
        } // Of for i

        return resultSum;
    } // Of addToN

    /**
     * Add from 1 to N with a step length.
     *
     * @param paraN          The given upper bound.
     * @param paraStepLength The given step length.
     * @return The sum.
     */
    public static int addToNWithStepLength(int paraN, int paraStepLength) {
        int resultSum = 0;

        for (int i = 1; i <= paraN; i += paraStepLength) {
            resultSum += i;
        } // Of for i

        return resultSum;
    } // Of addToNWithStepLength

} // Of class ForStatement
