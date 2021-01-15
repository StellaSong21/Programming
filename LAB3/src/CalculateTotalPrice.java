import javax.swing.*;
import java.util.Scanner;

    public class CalculateTotalPrice {

/**
* 这个类是用来计算总价的
*
* @author Yijing Song
* @date 2017/10/10 15:30
*/

        public static void main (String[] args){

            //输入商品总价
            String priceStr = JOptionPane.showInputDialog("请输入商品总价：");
            double price = Double.parseDouble(priceStr);

            //输入身份
            System.out.println("请输入您的身份：");
            Scanner in = new Scanner(System.in);
            int a = in.nextInt();
            int b = in.nextInt();
            int c = in.nextInt();

            double priceTotal = 0;

            //用随机数确定当日政策
            int discount = (int) ( Math.random() * 4) + 1;
            //输出当日政策
            System.out.println(discount);

            switch (discount){
                case 1:
                    if (a == 1)
                        priceTotal = price * 0.7;
                    else if (c == 1)
                        priceTotal = price * 0.9;
                    else if (b == 1)
                        priceTotal = price * 0.95;
                    else
                        priceTotal = price;
                    break;
                case 2:
                    if (b == 1)
                        priceTotal = price * 0.6;
                    else if (c == 1)
                        priceTotal = price * 0.9;
                    else
                        priceTotal = price;
                    break;
                case 3:
                    if (c == 1)
                        priceTotal = price * 0.8;
                    else if (b == 1)
                        priceTotal = price * 0.95;
                    else
                        priceTotal = price;
                    break;
                case 4:
                    if (c == 1)
                        priceTotal = price * 0.9;
                    else if (b == 1)
                        priceTotal = price * 0.95;
                    else
                        priceTotal = price;
                    break;
            }
            System.out.println(priceTotal);
            System.exit(0);
        }
    }

