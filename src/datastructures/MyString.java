    package datastructures;

    /**
     * ClassName: MyString
     * Package: datastructures
     * Description: My string. String is a class provided by the language, so I use another name.
     * It is essentially a sequential list with char type elements.
     *
     * @Author: luv_x_c
     * @Create: 2023/4/23 9:33
     */
    public class MyString {
        /**
         * The maximal length.
         */
        public static final int MAX_LENGTH = 10;

        /**
         * The actual length.
         */
        int length;

        /**
         * The data.
         */
        char[] data;

        /**
         * Construct an empty char array.
         */
        public MyString() {
            length = 0;
            data = new char[MAX_LENGTH];
        }// Of the first constructor

        /**
         * Construct using a system defined string.
         *
         * @param paraString The given string. Its length should not exceed MAX_LENGTH - 1.
         */
        public MyString(String paraString) {
            data = new char[MAX_LENGTH];
            length = paraString.length();

            //Copy data.
            for (int i = 0; i < length; i++) {
                data[i] = paraString.charAt(i);
            }// Of for i
        } // Of the second constructor

        /**
         * Overrides the method claimed in Object, the superclass of any class.
         *
         * @return A String.
         */
        public String toString() {
            String resultString = "";
            for (int i = 0; i < length; i++) {
                resultString += data[i];
            }// Of for i

            return resultString;
        }// Of toString

        /**
         * Locate the position of a substring.
         *
         * @param paraMyString The given substring.
         * @return The first position. -1 for no matching.
         */
        public int locate(MyString paraMyString) {
            boolean tempMatch = false;
            for (int i = 0; i < length - paraMyString.length + 1; i++) {
                tempMatch = true;
                for (int j = 0; j < paraMyString.length; j++) {
                    if (data[i + j] != paraMyString.data[j]) {
                        tempMatch = false;
                        break;
                    }// Of if
                }// Of for j

                if (tempMatch) {
                    return i;
                }// Of if
            }// Of for i
            return -1;
        } //Of locate

        /**
         * Get a substring
         *
         * @param paraStartPosition The start position in the original string.
         * @param paraLength        The length of the new string.
         * @return Get a substring.
         */
        public MyString substring(int paraStartPosition, int paraLength) {
            if (paraStartPosition + paraLength > length) {
                System.out.println("The bound is exceed. ");
                return null;
            }// Of if

            MyString resultString = new MyString();
            resultString.length = paraLength;
            for (int i = 0; i < paraLength; i++) {
                resultString.data[i] = data[paraStartPosition + 1];
            }// Of for i

            return resultString;
        }//Of subString

        /**
         * The entrance  of the program..
         *
         * @param args Not used now.
         */
        public static void main(String[] args) {
            MyString tempFirstString = new MyString("I like ik.");
            MyString tempSecondString = new MyString("ik");
            int tempPosition = tempFirstString.locate(tempSecondString);
            System.out.println("The position of \"" + tempSecondString + "\" in \"" + tempFirstString
                    + "\" is: " + tempPosition);

            MyString tempThirdString = new MyString("ki");
            tempPosition = tempFirstString.locate(tempThirdString);
            System.out.println("The position of \"" + tempThirdString + "\" in \"" + tempFirstString
                    + "\" is: " + tempPosition);

            tempThirdString = tempFirstString.substring(1, 2);
            System.out.println("The substring is: \"" + tempThirdString + "\"");

            tempThirdString = tempFirstString.substring(5, 5);
            System.out.println("The substring is: \"" + tempThirdString + "\"");

            tempThirdString = tempFirstString.substring(5, 6);
            System.out.println("The substring is: \"" + tempThirdString + "\"");
        }// Of main
    }// Of class MyString
