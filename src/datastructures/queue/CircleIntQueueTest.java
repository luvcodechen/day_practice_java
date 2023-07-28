package datastructures.queue;

/**
 * ClassName: CircleIntQueueTest
 * Package: datastructures.queue
 * Description:
 *
 * @Author: luv_x_c
 * @Create: 2023/4/22 9:24
 */
public class CircleIntQueueTest {
    public static void main(String[] args) {
        CircleIntQueue tempQueue = new CircleIntQueue();
        System.out.println("Initialized, the list is: " + tempQueue.toString());

        for (int i = 0; i < 5; i++) {
            tempQueue.enqueue(i + 1);
        } // Of for i
        System.out.println("Enqueue, the queue is: " + tempQueue.toString());

        int tempValue = tempQueue.dequeue();
        System.out.println("Dequeue " + tempValue + ", the queue is: " + tempQueue.toString());

        for (int i = 0; i < 6; i++) {
            tempQueue.enqueue(i + 10);
            System.out.println("Enqueue: " + (i + 10) + ", the queue is: " + tempQueue.toString());
        } // Of for i

        for (int i = 0; i < 3; i++) {
            tempValue = tempQueue.dequeue();
            System.out.println("Dequeue " + tempValue + ", the queue is: " + tempQueue.toString());
        } // Of for i

        for (int i = 0; i < 6; i++) {
            tempQueue.enqueue(i + 100);
            System.out.println("Enqueue: " + (i + 100) + ", the queue is: " + tempQueue.toString());
        } // Of for i
    }// Of main
}
