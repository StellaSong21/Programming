import java.util.Objects;
import java.util.Scanner;

public class Maze_4 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("请勇士报上名字： ");
        String name = input.next();
        System.out.println(name + ",你做好准备了吗？");
        System.out.println("请选择模式： \n1.故事模式  \n2.沙盒模式" +
                " \n任意键退出挑战");
        String u0 = input.next();

        if (Objects.equals(u0, "1")) {
            System.out.println("在很久很久以前，有一个老国王要给自己唯一的女儿" +
                    "选亲,\n要求通关整个迷宫才能得到公主，全国各地的勇士都来到此地，参加招亲。。。。。。" +
                    "\n(任意键继续）");
            Scanner in = new Scanner(System.in);
            String n0 = in.nextLine();
            System.out.println("游戏规则如下：\nw:向上移动" + "\ns:向下移动" + "\na:向左移动" + "\nd:向右移动" +
                    "\nj:攻击怪物" + "\nk:查看当前得分" + "\nh:查看帮助" + "\nb:后退一步" + "\nq:结束游戏");
            System.out.println("（任意键开始游戏。。。）");
            String n = in.nextLine();
            Maze1 m1 = new Maze1();
            m1.s(m1);
            System.out.println("哇，你太棒了！");
            int x1 = m1.getCountOfMonster();
            int z1 = m1.getCountOfTrace();
            int y1 = m1.getCountOfTreasure();
            System.out.println("您当前的信息为：" + "\nmonster: " + x1 +
                    "\ntreasure: " + y1 + "\ntrace: " + z1);
            System.out.println("是否进入下一关 \n（任意键继续）");
            String n1 = in.nextLine();
            Maze2 m2 = new Maze2();
            m2.s(m2);
            System.out.println("哇，你太棒了！");
            int x2 = m2.getCountOfMonster();
            int z2 = m2.getCountOfTrace();
            int y2 = m2.getCountOfTreasure();
            System.out.println("您当前的信息为：" + "\nmonster: " + (x1 + x2) +
                    "\ntreasure: " + (y1 + y2) + "\ntrace: " + (z1 + z2));
            System.out.println("是否进入下一关 \n（任意键继续）");
            String n2 = in.nextLine();
            Maze3 m3 = new Maze3();
            m3.s(m3);
            System.out.println("哇，你太棒了！");
            int x3 = m3.getCountOfMonster();
            int z3 = m3.getCountOfTrace();
            int y3 = m3.getCountOfTreasure();
            System.out.println("您当前的信息为：" + "\nmonster: " + (x1 + x2 + x3) +
                    "\ntreasure: " + (y1 + y2 + y3) + "\ntrace: " + (z1 + z2 + z3));
            System.out.println("是否进入下一关 \n（任意键继续）");
            String n3 = in.nextLine();
            Maze4 m4 = new Maze4();
            m4.s(m4);
            System.out.println("恭喜你，得到了公主！");
            int x4 = m4.getCountOfMonster();
            int z4 = m4.getCountOfTrace();
            int y4 = m4.getCountOfTreasure();
            System.out.println("您最终的得分为：" + "\nmonster: " + (x1 + x2 + x3 + x4) +
                    "\ntreasure: " + (y1 + y2 + y3 + y4) + "\ntrace: " + (z1 + z2 + z3 + z4)
                    + "\n总分为：" + ((x1 + x2 + x3 + x4) * 100 + (y1 + y2 + y3 + y4) * 100 - (z1 + z2 + z3 + z4) + 1000));
        } else if (Objects.equals(u0, "2")) {
            System.out.println("请选择关卡：\n1.第一关\n2.第二关\n3.第三关\n4.第四关\n(任意键退出)");
            int x = input.nextInt();
            switch (x) {
                case 1:
                    Maze1 m1 = new Maze1();
                    m1.s(m1);
                    System.out.println("哇，你太棒了！");
                    int x1 = m1.getCountOfMonster();
                    int z1 = m1.getCountOfTrace();
                    int y1 = m1.getCountOfTreasure();
                    System.out.println("您的信息为：" + "\nmonster: " + x1 +
                            "\ntreasure: " + y1 + "\ntrace: " + z1 + "\n您的总分为："
                            + (x1 * 100 + y1 * 100 - z1 + 1000));
                    break;
                case 2:
                    Maze2 m2 = new Maze2();
                    m2.s(m2);
                    System.out.println("哇，你太棒了！");
                    int x2 = m2.getCountOfMonster();
                    int z2 = m2.getCountOfTrace();
                    int y2 = m2.getCountOfTreasure();
                    System.out.println("您的信息为：" + "\nmonster: " + x2 +
                            "\ntreasure: " + y2 + "\ntrace: " + z2 + "\n您的总分为："
                            + (x2 * 100 + y2 * 100 - z2 + 1000));
                    break;
                case 3:
                    Maze3 m3 = new Maze3();
                    m3.s(m3);
                    System.out.println("哇，你太棒了！");
                    int x3 = m3.getCountOfMonster();
                    int z3 = m3.getCountOfTrace();
                    int y3 = m3.getCountOfTreasure();
                    System.out.println("您的信息为：" + "\nmonster: " + x3 +
                            "\ntreasure: " + y3 + "\ntrace: " + z3 + "\n您的总分为："
                            + (x3 * 100 + y3 * 100 - z3 + 1000));
                    break;
                case 4:
                    Maze4 m4 = new Maze4();
                    m4.s(m4);
                    System.out.println("哇，你太棒了！");
                    int x4 = m4.getCountOfMonster();
                    int z4 = m4.getCountOfTrace();
                    int y4 = m4.getCountOfTreasure();
                    System.out.println("您的信息为：" + "\nmonster: " + x4 +
                            "\ntreasure: " + y4 + "\ntrace: " + z4 + "\n您的总分为："
                            + (x4 * 100 + y4 * 100 - z4 + 1000));
                    break;
                default:
                    System.exit(0);
            }
        } else {
            System.exit(0);
        }
    }
}
