package basic;

/**
 * ClassName: RandomTest
 * Package: basic
 * Description: random随机数, (int)(Math.random()*(b-a+1))+a,获取[a,b]之间随机整数
 *
 * @Author: luv_x_c
 * @Create: 2023/4/12 21:01
 */
public class RandomTest {
    public static void main(String[] args) {
        double tempRandom = Math.random();
        System.out.println(tempRandom);
    }
}
