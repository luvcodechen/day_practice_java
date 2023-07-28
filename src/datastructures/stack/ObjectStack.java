package datastructures.stack;

/**
 * ClassName: ObjectStack
 * Package: datastructures.stack
 * Description:
 *
 * @Author: luv_x_c
 * @Create: 2023/5/13 21:51
 */
public class ObjectStack {
    /**
     * The depth.
     */
    public static final int MAX_DEPTH = 10;
    /**
     * The actual depth.
     */
    int top;
    /**
     * The data.
     */
    Object[] data;

    /**
     * Construct an empty sequential list.
     */
    public ObjectStack() {
        top = 0;
        data = new Object[MAX_DEPTH];
    }// Of the first constructor

    /**
     * Overrides the method claimed in Object, the superclass of any class.
     */
    public String toString() {
        String resultString = "";
        for (int i = 0; i < top; i++) {
            resultString += data[i];
        }// Of for i

        return resultString;
    }// Of toString

    /**
     * Push an element.
     *
     * @param paraObject The given object.
     * @return Success or not.
     */
    public boolean push(Object paraObject) {
        if (top == MAX_DEPTH) {
            System.out.println("Stack full. ");
            return false;
        }// Of if

        data[top] = paraObject;
        top++;

        return true;
    }// Of push

    /**
     * Pop an element.
     *
     * @return The object at the top of the stack.
     */
    public Object pop() {
        if (top == 0) {
            System.out.println("Stack is empty. ");
            return '\0';
        }//Of if

        Object resultObject = data[top - 1];
        top--;

        return resultObject;
    }// Of pop

    /**
     *
     * @return The top value of the stack.
     */
    public Object getTop() {
        if (top != 0) {
            return data[top - 1];
        } else {
            return null;
        }// Of if
    }// of getTop

    /**
     * Is the stack empty?
     *
     * @return True if empty.
     */
    public boolean isEmpty() {
        return top == 0;
    }// Of isEmpty

    /**
     * The entrance of the program.
     *
     * @param args Not used now.
     */
    public static void main(String[] args) {
        ObjectStack tempStack = new ObjectStack();

        for (char ch = 'a'; ch < 'm'; ch++) {
            if (tempStack.push(ch)) {
                System.out.println("The current stack is :" + tempStack);
            }// Of if
            else {
                System.out.println("push error. ");
            }

        }//Of for ch

        char tempChar;
        for (int i = 0; i < 12; i++) {
            tempChar = (Character) tempStack.pop();
            System.out.println("Popped is :" + tempChar);
            System.out.println("The current stack is : " + tempStack);
        }// Of for i
    }// Of main
}// Of class ObjectStack
