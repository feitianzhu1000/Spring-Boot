/**
 * @author: dwt
 * @Date:2025/12/13 20:51
 * @Description:
 * @Version: 1.0
 */

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 给定一个字符串 s ，请你找出其中不含有重复字符的 最长 子串 的长度
 */
public class Demo3 {
    public static void main(String[] args) {
        String str = "abcabdddd";

        char[] arr = str.toCharArray();

        List arrList = new ArrayList<>();
        List tempList = new ArrayList<>();

        if(arr != null){
            for(int i = 0; i < arr.length; i++){
                arrList.add(arr[i]);
            }

            if(arrList.size() > 1){
                tempList = (List) arrList.stream().distinct().collect(Collectors.toList());

                arrList.clear();

                arrList.addAll(tempList);
            }

            System.out.println(arrList.size());

        }
    }
}
