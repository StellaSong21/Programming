import javax.swing.*;

/**
 * @author  Yijing Song
 * @date 2017/10/17 11:11
 */

//这个类用来计算公元年的天干地支表示法
public class Era17302010079 {

    public static void main(String[] args){

        //提示：“请输入年份：”
        String oneyear = JOptionPane.showInputDialog("请输入年份：");
        //把year转换成int类型
        int year = Integer.parseInt(oneyear);

        int a,b;

        int x = year - 618;
        a = (x >= 0)? (x % 10):((-x) % 10);
        b = (x >= 0)? (x % 12):((-x) % 12);

        //判断天干
        switch (a){
            case 0:
                System.out.print("戊");
                break;
            case 1:
                System.out.print("己");
                break;
            case 2:
                System.out.print("庚");
                break;
            case 3:
                System.out.print("辛");
                break;
            case 4:
                System.out.print("壬");
                break;
            case 5:
                System.out.print("癸");
                break;
            case 6:
                System.out.print("甲");
                break;
            case 7:
                System.out.print("乙");
                break;
            case 8:
                System.out.print("丙");
                break;
            case 9:
                System.out.print("丁");
                break;
        }

        //判断地支
        switch (b){
            case 0:
                System.out.print("寅");
                break;
            case 1:
                System.out.print("卯");
                break;
            case 2:
                System.out.print("辰");
                break;
            case 3:
                System.out.print("巳");
                break;
            case 4:
                System.out.print("午");
                break;
            case 5:
                System.out.print("未");
                break;
            case 6:
                System.out.print("申");
                break;
            case 7:
                System.out.print("酉");
                break;
            case 8:
                System.out.print("戌");
                break;
            case 9:
                System.out.print("亥");
                break;
            case 10:
                System.out.print("子");
                break;
            case 11:
                System.out.print("丑");
                break;
        }

        System.out.print("年");

        System.exit(0);
    }
}
