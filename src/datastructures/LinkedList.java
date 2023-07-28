package datastructures;

/**
 * ClassName: LinkedList
 * Package: datastructures
 * Description:
 *
 * @Author: luv_x_c
 * @Create: 2023/4/15 21:42
 */
public class LinkedList {

    class Node {
        /**
         * The data.
         */
        int data;

        /**
         * The reference to the next node.
         */
        Node next;

        /**
         * The constructor
         *
         * @param paraValue The data.
         */
        public Node(int paraValue) {
            data = paraValue;
            next = null;
        }// Of the constructor
    }//Of class Node

    /**
     * The header node, The data is never used.
     */
    Node header;

    /**
     * Constructor sn empty linked list.
     */
    public LinkedList() {
        header = new Node(0);
    }// Of the first constructor

    public String toString() {
        String resultString = "";

        if (header.next == null) {
            return "empty";
        }// Of if

        Node tempNode = header.next;
        while (tempNode != null) {
            resultString = tempNode.data + ",";
            tempNode = tempNode.next;
        }// Of while

        return resultString;
    }// Of toString

    /**
     * Reset to empty. Free the space through garbage collection.
     */
    public void reset() {
        header.next = null;
    }// Of reset

    /**
     * ********************
     * Locate the given value. If it appears in multiple positions, simply
     * return the first one.
     *
     * @param paraValue The given value.
     * @return The position. -1 for not found.
     * ********************
     */
    public int locate(int paraValue) {
        int tempPosition = -1;
        Node tempNode = header.next;
        int tempCurrentPosition = 0;
        while (tempNode != null) {
            if (tempNode.data == paraValue) {
                tempPosition = tempCurrentPosition;
                break;
            }// Of if

            tempNode = tempNode.next;
            tempCurrentPosition++;
        }// Of while

        return tempPosition;
    }// Of locate

    /**
     * ********************
     * Insert a value to a position. If the list is already full, do nothing.
     *
     * @param paraPosition The given position.
     * @param paraValue    The given value.
     * @return Success or not.
     * ********************
     */
    public boolean insert(int paraPosition, int paraValue) {
        Node tempNode = header;
        Node tempNewNode;

        for (int i = 0; i < paraPosition; i++) {
            if (tempNode.next == null) {
                System.out.println("The position " + paraPosition + " is illegal.");
                return false;
            }// Of if
            tempNode = tempNode.next;
        }// Of for i

        // Construct a new node.
        tempNewNode = new Node(paraValue);

        // Now link them.
        tempNewNode.next = tempNode.next;
        tempNode.next = tempNewNode;

        return true;
    }// Of insert

    /**
     * ********************
     * Delete a value at a position.
     *
     * @param paraPosition The given position.
     * @return Success or not.
     * ********************
     */
    public boolean delete(int paraPosition) {
        if (header.next == null) {
            System.out.println("Cannot delete element form an empty list.");
            return false;
        }//Of if

        Node tempNode = header;

        for (int i = 0; i < paraPosition; i++) {
            if (tempNode.next.next == null) {
                System.out.println("The position " + paraPosition + " is illegal. ");
                return false;
            }// Of if
            tempNode = tempNode.next;
        }// Of for i

        tempNode.next = tempNode.next.next;
        return true;
    }// Of delete

    /**
     * ********************
     * The entrance of the program.
     *
     * @param args Not used now.
     *             ********************
     */
    public static void main(String args[]) {
        LinkedList tempFirstList = new LinkedList();
        System.out.println("Initialized, the list is: " + tempFirstList.toString());

        for (int i = 0; i < 5; i++) {
            tempFirstList.insert(0, i);
        } // Of for i
        System.out.println("Inserted, the list is: " + tempFirstList.toString());

        tempFirstList.insert(6, 9);

        tempFirstList.delete(4);

        tempFirstList.delete(2);
        System.out.println("Deleted, the list is: " + tempFirstList.toString());

        tempFirstList.delete(0);
        System.out.println("Deleted, the list is: " + tempFirstList.toString());

        for (int i = 0; i < 5; i++) {
            tempFirstList.delete(0);
            System.out.println("Looped delete, the list is: " + tempFirstList.toString());
        } // Of for i
    }// Of main
}// Of class LinkedList
