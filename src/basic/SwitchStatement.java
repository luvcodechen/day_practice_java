package basic;

/**
 * ClassName: SwitchStatement
 * Package: basic
 * Description:
 *
 * @Author: luv_x_c
 * @Create: 2023/4/8 10:39
 */
public class SwitchStatement {
    /**
     * The entrance of the program
     *
     * @param args Not used now
     */
    public static void main(String[] args) {
        scoreToLevelTest();
    }// Of main

    /**
     * @param paraScore From 0 to 100.
     * @return The level from A to F.
     */
    public static char scoreToLevel(int paraScore) {
        // E stands for error, and F stands for fail.
        char resultLevel;

        // Divide by 10, the result ranges from 0 to 10
        int tempDigitalLevel = paraScore / 10;

        // The use of break is important.
        switch (tempDigitalLevel) {
            case 10:
            case 9:
                resultLevel = 'A';
                break;
            case 8:
                resultLevel = 'B';
                break;
            case 7:
                resultLevel = 'C';
                break;
            case 6:
                resultLevel = 'D';
                break;
            case 5:
            case 4:
            case 3:
            case 2:
            case 1:
            case 0:
                resultLevel = 'F';
                break;
            default:
                resultLevel = 'E';
                break;
        }// Of switch
        return resultLevel;

        /*
         * 新用法
         *  resultLevel = switch (tempDigitalLevel) {
         *             case 10, 9 -> 'A';
         *             case 8 -> 'B';
         *             case 7 -> 'C';
         *             case 6 -> 'D';
         *             case 5, 4, 3, 2, 1, 0 -> 'F';
         *             default -> 'E';
         *         };// Of switch
         *         return resultLevel;
         */
    }// Of scoreToLevel

    /**
     * ********************
     * Method unit test.
     * ********************
     */
    public static void scoreToLevelTest() {
        int tempScore = 100;
        System.out.println("Score " + tempScore + " to level is " + scoreToLevel(tempScore));

        tempScore = 91;
        System.out.println("Score " + tempScore + " to level is: " + scoreToLevel(tempScore));

        tempScore = 82;
        System.out.println("Score " + tempScore + " to level is: " + scoreToLevel(tempScore));

        tempScore = 75;
        System.out.println("Score " + tempScore + " to level is: " + scoreToLevel(tempScore));

        tempScore = 66;
        System.out.println("Score " + tempScore + " to level is: " + scoreToLevel(tempScore));

        tempScore = 52;
        System.out.println("Score " + tempScore + " to level is: " + scoreToLevel(tempScore));

        tempScore = 8;
        System.out.println("Score " + tempScore + " to level is: " + scoreToLevel(tempScore));

        tempScore = 120;
        System.out.println("Score " + tempScore + " to level is: " + scoreToLevel(tempScore));
    }// Of scoreToLevelTest

}// Of class SwitchStatement
