package datastructures.tree;

/**
 * ClassName: Hanoi
 * Package: datastructures.tree
 * Description:
 *
 * @Author: luv_x_c
 * @Create: 2023/5/16 17:24
 */
public class Hanoi {
    /**
     * Move a number of plates.
     *
     * @param paraSource       The source pole.
     * @param paraIntermediary The intermediary pole.
     * @param paraDestination  The destination pole.
     * @param paraNumber       The number of the plates.
     */
    public static void hanoi(char paraSource, char paraIntermediary, char paraDestination, int paraNumber) {
        if (paraNumber == 1) {
            System.out.println(paraSource + "->" + paraDestination + " ");
            return;
        }//Of if

        hanoi(paraSource, paraDestination, paraIntermediary, paraNumber - 1);
        System.out.println(paraSource + "->" + paraDestination + " ");
        hanoi(paraIntermediary, paraSource, paraDestination, paraNumber - 1);
    }// Of hanoi

    /**
     * The entrance of the program.
     *
     * @param args Not used now.
     */
    public static void main(String[] args) {
        hanoi('a', 'b', 'c', 4);
    }//  Of main

}// Of class Hanoi
