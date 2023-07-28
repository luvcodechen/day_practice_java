package datastructures.queue;

/**
 * ClassName: CircleObjectQueue
 * Package: datastructures.queue
 * Description: Circle Object queue.
 *
 * @Author: luv_x_c
 * @Create: 2023/4/26 15:51
 */
public class CircleObjectQueue {
    /**
     * The total space. One space can never be used.
     */
    public static final int TOTAL_SPACE = 10;
    /**
     * The data.
     */
    Object[] data;
    /**
     * The index of the head;
     */
    int head;

    /**
     * The index of the tail.
     */
    int tail;

    /**
     * The constructor
     */
    public CircleObjectQueue() {
        data = new Object[TOTAL_SPACE];
        head = 0;
        tail = 0;
    }// Of the first constructor

    /**
     * Enqueue
     *
     * @param paraValue The value of the new node.
     */
    public void enqueue(Object paraValue) {
        if ((tail + 1) % TOTAL_SPACE == head) {
            System.out.println("Queue if full. ");
            return;
        }// Of if

        data[tail % TOTAL_SPACE] = paraValue;
        tail++;
    }// Of enqueue

    /**
     * Dequeue
     *
     * @return The value at the head.
     */
    public Object dequeue() {
        if (head == tail) {
            System.out.println("No element in the queue. ");
            return null;
        }// Of  if

        Object resultValue = data[head % TOTAL_SPACE];
        head++;

        return resultValue;
    }// Of dequeue

    /**
     * Overrides the method claimed in Object, the superclass of any class.
     *
     * @return A String。
     */
    public String toString() {
        String resultString = "";

        if (head == tail) {
            return "empty";
        }// Of if

        for (int i = head; i < tail; i++) {
            resultString += data[i % TOTAL_SPACE] + ", ";
        }// Of for i

        return resultString;
    }// Of toString

    /**
     * Is the queue empty?
     *
     * @return True if empty.
     */
    public boolean isEmpty() {
        return head == tail;
    }// Of isEmpty

    /**
     * The entrance of the program.
     *
     * @param args Not used  now.
     */
    public static void main(String[] args) {
        CircleObjectQueue tempQueue = new CircleObjectQueue();

    }// Of main
}// Of class CircleObjectQueue
