package datastructures.stack;

/**
 * ClassName: CharStack
 * Package: datastructures.stack
 * Description:
 *
 * @Author: luv_x_c
 * @Create: 2023/4/17 21:05
 */
public class CharStack {
    /**
     * The depth.
     */
    public static final int MAX_DEPTH = 10;
    /**
     * The actual depth.
     */
    int depth;
    /**
     * The data.
     */
    char[] data;

    /**
     * Construct an empty char stack.
     */
    public CharStack() {
        depth = 0;
        data = new char[MAX_DEPTH];
    }// Of the first constructor

    /**
     * Overrides the method claimed in Object, the superclass of any class.
     */
    public String toString() {
        String resultString = "";
        for (int i = 0; i < depth; i++) {
            resultString += data[i];
        }// Of for i

        return resultString;
    }// Of toString

    /**
     * Push an element.
     *
     * @param paraChar The given char.
     * @return Success or not.
     */
    public boolean push(char paraChar) {
        if (depth == MAX_DEPTH) {
            System.out.println("Stack full. ");
            return false;
        }// Of if

        data[depth++] = paraChar;
        return true;
    }// Of push

    /**
     * Pop an element.
     *
     * @return The popped char.
     */
    public char pop() {
        if (depth == 0) {
            System.out.println("Nothing to pop. ");
            return '\0';
        }// Of if

        char resultChar = data[depth - 1];
        depth--;
        return resultChar;
    }// Of pop

    /**
     * Is the bracket matching?
     *
     * @param paraString The given expression.
     * @return Match or not.
     */
    public static boolean bracketMatching(String paraString) {
        // Step 1. Initialize the stack through pushing a '#' at the bottom.
        CharStack tempStack = new CharStack();
        tempStack.push('#');
        char tempChar, tempPoppedChar;

        // Step 2. Process the string. For a string, length() is a method.
        // instead of a member variable.
        for (int i = 0; i < paraString.length(); i++) {
            tempChar = paraString.charAt(i);

            switch (tempChar) {
                case '(':
                case '[':
                case '{':
                    tempStack.push(tempChar);
                    break;
                case ')':
                    tempPoppedChar = tempStack.pop();
                    if (tempPoppedChar != '(') {
                        return false;
                    }// Of if
                    break;
                case ']':
                    tempPoppedChar = tempStack.pop();
                    if (tempPoppedChar != '[') {
                        return false;
                    }// Of if
                    break;
                case '}':
                    tempPoppedChar = tempStack.pop();
                    if (tempPoppedChar != '{') {
                        return false;
                    }// Of if
                    break;
                default:
                    // Do nothing
            }// Of switch
        }// Of for

        tempPoppedChar = tempStack.pop();
        if (tempPoppedChar != '#') {
            return false;
        } // Of if

        return true;
    }// Of bracketMatching

    /**
     * The entrance of the program.
     *
     * @param args Not used now.
     */
    public static void main(String[] args) {
        CharStack tempStack = new CharStack();

        for (char ch = 'a'; ch < 'm'; ch++) {
            tempStack.push(ch);
            System.out.println("The current stack is: " + tempStack);
        } // Of for ch

        char tempChar;
        for (int i = 0; i < 12; i++) {
            tempChar = tempStack.pop();
            System.out.println("Poped: " + tempChar);
            System.out.println("The current stack is: " + tempStack);
        } // Of for i
    }// Of main
}// Of CharStack
