import java.util.Arrays;

/**
 * @author: dwt
 * @Date:2025/12/13 14:24
 * @Description: 判断一个数组是否任意两个数是否为等差数组
 * @Version: 1.0
 */
public class Demo {
    public static void main(String[] args) {
        int[] arr = {3,9,7};
        boolean flag = isArithmetic(arr);
        System.out.println("是否为等差数组:" + flag);
    }

    private static boolean isArithmetic(int[] arr) {
        boolean flag = true;

        //判断长度
        if(arr == null){
            System.out.println("数组为空，返回false");
            return flag;
        }

        if(arr.length < 3){
            System.out.println("数组长度小于3，返回true");
            return true;
        }

        //先排序
        Arrays.sort(arr);

        //获取第一个和第二个的差

        int diff = arr[1] - arr[0];

        for(int i = 1; i < arr.length - 1; i++){
            if((arr[i+1] - arr[i]) != diff){
                flag = false;
                break;
            }
        }

        return flag;
    }

}
