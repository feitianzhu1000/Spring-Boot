/**
 * @author: dwt
 * @Date:2025/12/13 14:24
 * @Description: 判断一个数组是否任意两个数是否为等差数组
 * @Version: 1.0
 */
public class Demo {
    public static void main(String[] args) {
        int[] arr = {7,6,3,1};
        boolean flag = isArithmetic(arr);
        System.out.println("是否为等差数组:" + flag);
    }

    private static boolean isArithmetic(int[] arr) {
        boolean flag = true;
        for(int i = 0; i < arr.length - 2; i++){
            if((arr[i+1] - arr[i]) != (arr[i+2] - arr[i+1])){
                flag = false;
                break;
            }
        }

        return flag;
    }
}
