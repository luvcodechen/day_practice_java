package basic;

import java.util.Scanner;

/**
 * ClassName: ScannerTest
 * Package: basic
 * Description:
 *
 * @Author: luv_x_c
 * @Create: 2023/4/12 17:24
 */
public class ScannerTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String name = scanner.next();
        System.out.println("name = "+name);
        int anInt = scanner.nextInt();
        String s = scanner.next();
    }
}
