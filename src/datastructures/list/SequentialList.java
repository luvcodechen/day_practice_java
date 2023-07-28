package datastructures.list;

/**
 * ClassName: SequentialList
 * Package: SequentialList.list
 * Description:
 *
 * @Author: luv_x_c
 * @Create: 2023/4/14 10:59
 */
public class SequentialList {
    /**
     * The maximal length of the list. It is a constant.
     */
    public static final int MAX_LENGTH = 10;

    /**
     * The actual length not exceeding MAX_LENGTH. Attention: length is not only
     * the member variable of Sequential list, but also the member variable of
     * Array. In fact, a name can be the member variable of different classes.
     */
    int length;
    /**
     * The data stored in an array.
     */
    int[] data;

    /**
     * ********************
     * Construct an empty sequential list.
     * ********************
     */
    public SequentialList() {
        length = 0;
        data = new int[MAX_LENGTH];
    }// Of the first constructor

    /**
     * Construct a sequential list using an array.
     *
     * @param paraArray The given array. Its length should not exceed MAX_LENGTH, For simplicity now we do not check it.
     */
    public SequentialList(int[] paraArray) {
        data = new int[MAX_LENGTH];
        length = paraArray.length;

        // Copy data.
        for (int i = 0; i < paraArray.length; i++) {
            data[i] = paraArray[i];
        }// Of for i
    }// Of the second constructor

    @Override
    public String toString() {
        String resultString = "";

        if (length == 0) {
            return "empty";
        }// Of if

        for (int i = 0; i < length - 1; i++) {
            resultString += data[i] + ", ";
        }// Of for i

        resultString += data[length - 1];
        return resultString;
    }// Of toString

    /**
     * ********************
     * Reset to empty.
     * ********************
     */
    public void reset() {
        length = 0;
    }// Of reset

    /**
     * Find the index of the given value. If it appears in multiple positions,
     * simply return the first one.
     *
     * @param paraValue The given value.
     * @return The position. -1 for not found.
     */
    public int indexOf(int paraValue) {
        int tempPosition = -1;

        for (int i = 0; i < data.length; i++) {
            if (data[i] == paraValue) {
                tempPosition = i;
                break;
            }// Of if
        }// Of for i
        return tempPosition;
    }// Of indexOf

    /**
     * Insert a value to a position. If the list is already full, do nothing.
     *
     * @param paraPosition The given position.
     * @param paraValue    The given value.
     * @return Success or not.
     */
    public boolean insert(int paraPosition, int paraValue) {
        if (length == MAX_LENGTH) {
            System.out.println("List full.");
            return false;
        }// Of if
        if (paraPosition < 0 || paraPosition > length) {
            System.out.println("The position " + paraPosition + " is out of bounds.");
            return false;
        }// Of if

        // From tail to head. The last one is moved to a new position. Because length < MAX_LENGTH, no exceeding occurs.
        for (int i = length; i > paraPosition; i--) {
            data[i] = data[i - 1];
        }// Of for i

        data[paraPosition] = paraValue;
        length++;
        return true;
    }// Of insert

    /**
     * Delete a value at a position.
     *
     * @param paraPosition The given position.
     * @return Success or not.
     */
    public boolean delete(int paraPosition) {
        if (paraPosition < 0 || paraPosition >= length) {
            System.out.println("The position \" + paraPosition + \" is out of bounds.");
            return false;
        }// Of if
        // From head to tail.
        for (int i = paraPosition; i < length - 1; i++) {
            data[i] = data[i + 1];
        }// Of for i

        length--;

        return true;
    }

    /**
     * The entrance of the program.
     *
     * @param args Not used now.
     */
    public static void main(String[] args) {
        int[] tempArray = {1, 2, 3, 4, 5, 8};
        SequentialList tempFirstList = new SequentialList(tempArray);
        System.out.println("Initialized, the list is: " + tempFirstList.toString());
        System.out.println("Again, the list is: " + tempFirstList);

        int tempValue = 4;
        int tempPosition = tempFirstList.indexOf(tempValue);
        System.out.println("The position of " + tempValue + " is " + tempPosition);

        tempValue = 5;
        tempPosition = tempFirstList.indexOf(tempValue);
        System.out.println("The position of " + tempValue + " is " + tempPosition);

        tempPosition = 2;
        tempValue = 5;
        tempFirstList.insert(tempPosition, tempValue);
        System.out.println(
                "After inserting " + tempValue + " to position " + tempPosition + ", the list is: " + tempFirstList);

        tempPosition = 8;
        tempValue = 10;
        tempFirstList.insert(tempPosition, tempValue);
        System.out.println(
                "After inserting " + tempValue + " to position " + tempPosition + ", the list is: " + tempFirstList);

        tempPosition = 3;
        tempFirstList.delete(tempPosition);
        System.out.println("After deleting data at position " + tempPosition + ", the list is: " + tempFirstList);

        for (int i = 0; i < 8; i++) {
            tempFirstList.insert(i, i);
            System.out.println("After inserting " + i + " to position " + i + ", the list is: " + tempFirstList);
        } // Of for i

        tempFirstList.reset();
        System.out.println("After reset, the list is: " + tempFirstList);
    }// Of main
}// Of class SequentialList
