package datastructures.stack;


/**
 * ClassName: CharStackTest
 * Package: datastructures.stack
 * Description:
 *
 * @Author: luv_x_c
 * @Create: 2023/4/19 22:31
 */
public class CharStackTest {
    /**
     * ********************
     * The entrance of the program.
     *
     * @param args Not used now.
     *             ********************
     */
    public static void main(String args[]) {
        CharStack tempStack = new CharStack();

        boolean tempMatch;
        String tempExpression = "[2 + (1 - 3)] * 4";
        tempMatch = tempStack.bracketMatching(tempExpression);
        System.out.println("Is the expression " + tempExpression + " bracket matching? " + tempMatch);

        tempExpression = "( )  )";
        tempMatch = tempStack.bracketMatching(tempExpression);
        System.out.println("Is the expression " + tempExpression + " bracket matching? " + tempMatch);

        tempExpression = "()()(())";
        tempMatch = tempStack.bracketMatching(tempExpression);
        System.out.println("Is the expression " + tempExpression + " bracket matching? " + tempMatch);

        tempExpression = "({}[])";
        tempMatch = tempStack.bracketMatching(tempExpression);
        System.out.println("Is the expression " + tempExpression + " bracket matching? " + tempMatch);

        tempExpression = ")(";
        tempMatch = tempStack.bracketMatching(tempExpression);
        System.out.println("Is the expression " + tempExpression + " bracket matching? " + tempMatch);
    }// Of main
}
