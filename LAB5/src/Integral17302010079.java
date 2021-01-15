import java.text.DecimalFormat;
import java.util.Scanner;

/**
 * @author Yijing Song
 * @time 2017/10/24 12:03
 */

//这个类是用来计算正弦函数的定积分的
public class Integral17302010079 {

    public static void main(String[] args){

        //从控制台得到定积分的上界和下界
        Scanner input = new Scanner(System.in);

        System.out.println("请输入函数的下界：");
        double a = input.nextDouble();

        System.out.println("请输入函数的上界：");
        double b = input.nextDouble();
        //初始化定积分S
        double S = 0;

        int n = 100000;
        double dx = (b - a) / n;

        for(int i = 1;i <= n;i++){
            S = S + dx * Math.sin(a + i * dx);
        }
        //对结果格式化
        DecimalFormat df = new DecimalFormat("0.0000");
        System.out.println("得到的积分为" + df.format(S));
    }
}
