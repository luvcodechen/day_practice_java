package datastructures.queue;

/**
 * ClassName: CircleIntQueue
 * Package: datastructures.queue
 * Description: Circle int queue.
 *
 * @Author: luv_x_c
 * @Create: 2023/4/22 8:40
 */
public class CircleIntQueue {
    /**
     * The total space. One space can never  be used.
     */
    public static final int TOTAL_SPACE = 10;
    /**
     * The data.
     */
    int[] data;
    /**
     * The index of the queue's head.
     */
    int head;
    /**
     * The index of the queue's rear.
     */
    int rear;

    /**
     * The constructor.
     */
    public CircleIntQueue() {
        data = new int[TOTAL_SPACE];
        head = 0;
        rear = 0;
    }// Of the first constructor

    /**
     * Enqueue.
     *
     * @param paraValue The value if the new node.
     */
    public void enqueue(int paraValue) {
        if ((rear + 1) % TOTAL_SPACE == head) {
            System.out.println("Queue if full. ");
            return;
        }// Of if

        data[rear++] = paraValue;
        rear = rear % TOTAL_SPACE;
    }// Of enqueue

    /**
     * Dequeue.
     *
     * @return The head of the queue.
     */
    public int dequeue() {
        if (head == rear) {
            System.out.println("The queue is empty. ");
            return -1;
        }// Of if

        int resultValue = data[head++];

        head = head % TOTAL_SPACE;
        return resultValue;
    }// Of dequeue

    /**
     * Overrides the method claimed in Object, the superclass of any class.
     *
     * @return A String of the queue.
     */
    public String toString() {
        String resultString = "";

        if (head == rear) {
            return "empty";
        }// Of if

        for (int i = head; i != rear; i = (++i) % TOTAL_SPACE) {
            resultString += data[i] + ", ";
        }// Of for i

        return resultString;
    }// Of toString
}// Of class CircleIntQueue
