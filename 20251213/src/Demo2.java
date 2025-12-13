/**
 * @author: dwt
 * @Date:2025/12/13 15:22
 * @Description:
 * @Version: 1.0
 */

import java.util.Scanner;

/**
 * 题目要求
 * 给你一个正整数 n ，找出满足下述条件的 中枢整数 x ：
 * 1 和 x 之间的所有元素之和等于 x 和 n 之间所有元素之和。
 * 返回中枢整数 x 。如果不存在中枢整数，则返回 -1 。题目保证对于给定的输入，至多存在一个中枢整数。
 */
public class Demo2 {
    public static void main(String[] args) {
        System.out.print("输入正整数:");
        int n = new Scanner(System.in).nextInt();
        System.out.println("n=" + n);

        //转为数组
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = i + 1;
        }

        //计算
        if(arr.length == 1){
            System.out.println("中枢整数:" + arr[0]);
            return;
        }

        for (int i = 0; i < n  ; i++) {

            if (sum(arr, 0, i) == sum(arr, i, n - 1)) {
                System.out.println("中枢整数:" + arr[i]);
                return;
            }
        }
        System.out.println("-1");
    }

    private static int sum(int[] arr, int start, int end) {
        int sum= 0;
        for(int i = start;i <= end; i++){
            sum += arr[i];
        }
        return sum;
    }
}
