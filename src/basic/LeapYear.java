package basic;

/**
 * ClassName: LeapYear
 * Package: basic
 * Description:The complex usage of the if statement.
 *
 * @Author: luv_x_c
 * @Create: 2023/4/7 16:05
 */
public class LeapYear {
    /**
     * The entrance of the program
     *
     * @param args Not used now.
     */
    public static void main(String[] args) {
        // Test isLeapYear
        int tempYear = 2023;

        System.out.print("" + tempYear + " is ");
        if (!isLeapYear(tempYear)) {
            System.out.print("NOT ");
        }// Of if
        System.out.println("a leap year.");

        tempYear = 2020;

        System.out.print("" + tempYear + " is ");
        if (!isLeapYear(tempYear)) {
            System.out.print("NOT ");
        }// Of if
        System.out.println("a leap year.");

        tempYear = 2100;

        System.out.print("" + tempYear + " is ");
        if (!isLeapYear(tempYear)) {
            System.out.print("NOT ");
        }// Of if
        System.out.println("a leap year.");

        tempYear = 2004;

        System.out.print("" + tempYear + " is ");
        if (!isLeapYear(tempYear)) {
            System.out.print("NOT ");
        }// Of if
        System.out.println("a leap year.");

        // Test isLeapYearV2
        System.out.println("Now use the second version.");

        tempYear = 2023;
        System.out.print("" + tempYear + " is ");
        if (!isLeapYearV2(tempYear)) {
            System.out.print("NOT ");
        }// Of if
        System.out.println("a leap year.");

        tempYear = 2020;

        System.out.print("" + tempYear + " is ");
        if (!isLeapYearV2(tempYear)) {
            System.out.print("NOT ");
        }// Of if
        System.out.println("a leap year.");

        tempYear = 2100;

        System.out.print("" + tempYear + " is ");
        if (!isLeapYearV2(tempYear)) {
            System.out.print("NOT ");
        }// Of if
        System.out.println("a leap year.");

        tempYear = 2004;

        System.out.print("" + tempYear + " is ");
        if (!isLeapYearV2(tempYear)) {
            System.out.print("NOT ");
        }// Of if
        System.out.println("a leap year.");
    }//Of main

    /**
     * Is the given year leap?
     *
     * @param paraYear The give year.
     * @return boolean
     */
    public static boolean isLeapYear(int paraYear) {
        if ((paraYear % 4 == 0) && (paraYear % 100 != 0) || (paraYear % 400 == 0)) {
            return true;
        } else {
            return false;
        }// Of if
    }// Of isLeapYear

    /**
     * Is the given year leap? Replace the complex condition with a number of if.
     *
     * @param paraYear The given year.
     * @return boolean
     */
    public static boolean isLeapYearV2(int paraYear) {
        if (paraYear % 4 != 0) {
            return false;
        } else if (paraYear % 400 == 0) {
            return true;
        } else if (paraYear % 100 == 0) {
            return false;
        } else {
            return true;
        }// Of if
    }// Of isLeapYearV2
}// Of class LeapYear
