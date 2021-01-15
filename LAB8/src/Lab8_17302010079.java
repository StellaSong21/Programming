import java.util.Scanner;

public class Lab8_17302010079 {

    public static void main(String[] args) {
        System.out.println("勇士，我们已经在此等了你好久，你终于来了！" + "\n准备好了吗？" + "\n请按任意键继续");
        Scanner in = new Scanner(System.in);
        String s = in.next();

        Maze labyrinth0 = new Maze();
        char[][] labyrinth = labyrinth0.labyrinth;

        int count = 0;

        //小人的初始位置
        labyrinth[1][1] = '☺';
        Maze.printLabyrinth(labyrinth);

        do {

            System.out.println("请输入wasd控制小人的移动");
            Scanner input = new Scanner(System.in);
            String operation = input.next();
            char op = operation.charAt(0);

            User.postOfUser(labyrinth, op);
            count = User.numOfTreasure;

            Maze.printLabyrinth(labyrinth);
        } while (labyrinth[labyrinth.length - 2][labyrinth[0].length - 1] != '☺');

        System.out.println("勇士你真的是太棒了，你一共获得了" + count + "个宝藏！");

    }
}
