package datastructures;

/**
 * ClassName: Recursion
 * Package: datastructures
 * Description:A method can (directly or indirectly) invoke itself. The system
 * automatically creates a stack for it.
 *
 * @Author: luv_x_c
 * @Create: 2023/4/20 10:19
 */
public class Recursion {
    /**
     * Sum to N. No loop, however a stack is used.
     *
     * @param paraN The given value.
     * @return The sum.
     */
    public static int sumToN(int paraN) {
        if (paraN <= 0) {
            // Basis.
            return 0;
        } // Of if

        return sumToN(paraN - 1) + paraN;
    }// Of sumToN

    /**
     * Fibonacci sequence.
     *
     * @param paraN The given value.
     * @return The sum.
     */
    public static int fibonacci(int paraN) {
        if (paraN <= 0) {
            //Negative values are invalid. Index 0 corresponds to the first element 0.
            return 0;
        }// Of if
        if (paraN == 1) {
            // Basis
            return 1;
        }// Of if

        return fibonacci(paraN - 1) + fibonacci(paraN - 2);
    }// Of fibonacci

    /**
     * The entrance of the program.
     *
     * @param args Not used now.
     */
    public static void main(String[] args) {
        int tempValue = 5;
        System.out.println("0 sum to " + tempValue + " = " + sumToN(tempValue));
        tempValue = -1;
        System.out.println("0 sum to " + tempValue + " = " + sumToN(tempValue));

        for (int i = 0; i < 10; i++) {
            System.out.println("Fibonacci " + i + ": " + fibonacci(i));
        }//Of for i
    }// Of main
}// Of class Recursion
