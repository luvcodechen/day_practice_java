package datastructures.queue;

/**
 * ClassName: LinkedQueue
 * Package: datastructures.queue
 * Description: Linked queue.
 *
 * @Author: luv_x_c
 * @Create: 2023/4/21 8:31
 */
public class LinkedQueue {
    /**
     * An inner class.
     */
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
    }// Of  class Node

    /**
     * The header of the queue.
     */
    Node header;

    /**
     * The tail of the queue.
     */
    Node tail;

    /**
     * Construct an empty sequential list.
     */
    public LinkedQueue() {
        header = new Node(-1);
        tail = header;
    }// Of the first constructor

    /**
     * Enqueue
     *
     * @param paraValue The value of the new node.
     */
    public void enqueue(int paraValue) {
        Node tempNode = new Node(paraValue);
        tail.next = tempNode;
        tail = tempNode;
    }// Of Enqueue

    /**
     * Dequeue
     *
     * @return The value of the header.
     */
    public int dequeue() {
        if (header == tail) {
            System.out.println("No element in the queue. ");
            return -1;
        }// Of if

        int resultValue = header.next.data;

        header.next = header.next.next;

        // The queue becomes empty.
        if (header.next == null) {
            tail = header;
        }// Of if

        return resultValue;
    }// Of de queue

    public String toString() {
        String resultString = "";

        if (header.next == null) {
            return "empty";
        }// Of if

        Node tempNode = header.next;
        while (tempNode != null) {
            resultString += tempNode.data + ", ";
            tempNode = tempNode.next;
        } // Of while

        return resultString;
    }// Of toString

    /**
     * The entrance of the program.
     *
     * @param args Note used now.
     */
    public static void main(String[] args) {
        LinkedQueue tempQueue = new LinkedQueue();
        System.out.println("Initialized, the list is: " + tempQueue.toString());

        for (int i = 0; i < 5; i++) {
            tempQueue.enqueue(i + 1);
        } // Of for i
        System.out.println("Enqueue, the queue is: " + tempQueue.toString());

        tempQueue.dequeue();
        System.out.println("Dequeue, the queue is: " + tempQueue.toString());

        int tempValue;
        for (int i = 0; i < 5; i++) {
            tempValue = tempQueue.dequeue();
            System.out.println("Looped delete " + tempValue + ", the new queue is: " + tempQueue.toString());
        } // Of for i

        for (int i = 0; i < 3; i++) {
            tempQueue.enqueue(i + 10);
        } // Of for i
        System.out.println("Enqueue, the queue is: " + tempQueue.toString());
    }// Of main
}// Of class LinkedQueue
