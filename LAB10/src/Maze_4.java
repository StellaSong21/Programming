import java.util.Scanner;

public class Maze_4 {
    public static void main(String[] args) {
        System.out.println("请输入用户名： ");
        Scanner input = new Scanner(System.in);
        String name = input.next();
        System.out.println("请选择模式： \n1.故事模式  \n2.沙盒模式");
        int u = input.nextInt();
        if (u == 1) {
            Maze1 m1 = new Maze1();
            m1.s();
            System.out.println("哇，你太棒了！是否进入下一关？\n（任意键开始下一关）");
            Scanner in = new Scanner(System.in);
            String n = in.nextLine();
            Maze2 m2 = new Maze2();
            m2.s();
            System.out.println("哇，你太棒了！是否进入下一关 \n（任意键开始下一关）");
            String n0 = in.nextLine();
            Maze3 m3 = new Maze3();
            m3.s();
            System.out.println("哇，你太棒了！是否进入下一关？\n（任意键开始下一关）");
            String n1 = in.nextLine();
            Maze4 m4 = new Maze4();
            m4.s();
        }
        if (u == 2) {
            System.out.println("请选择关卡：");
            int x = input.nextInt();
            switch (x) {
                case 0:
                    Maze1 m1 = new Maze1();
                    m1.s();
                    break;
                case 1:
                    Maze2 m2 = new Maze2();
                    m2.s();
                    break;
                case 2:
                    Maze3 m3 = new Maze3();
                    m3.s();
                    break;
                case 3:
                    Maze4 m4 = new Maze4();
                    m4.s();
                    break;
            }
        }
    }
}
