/**
 * @param $params$
 * @author feiti$
 * @description: $description$
 * @return $returns$
 * @date 2026/1/10$ 20:28$
 *///TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        String str = "123";

        System.out.println(isPalindrome(str));


    }

    //是否为回文数
    public static boolean isPalindrome(String s) {
        if(s.length() == 1){
            return true;
        }

        String temStr = new StringBuffer(s).reverse().toString();

        return s.equals(temStr);
    }
}