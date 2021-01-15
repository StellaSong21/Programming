import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        char[][] maze = Maze.newMaze();
        System.out.println("请输入用户名： ");
        Scanner input = new Scanner(System.in);
        String user = input.next();
        System.out.println(user + ",你终于来了！" + "\n任意键开始游戏（h键可获得帮助）");
        String x = input.next();
        char y = x.charAt(0);
        if (y == 'h')
            System.out.println("w：向上" + "\na：向左" + "\ns：向下" + "\nd：向右" +
                    "\nh：查看帮助" + "\nj：攻击怪物" + "\nk：捡起宝物" + "\nl：查看目前得分");
        printMaze(maze);
        if (maze[maze.length - 2][maze[0].length - 1] != '+') {
            Maze.moveOfMoster();
            String instruction0 = input.next();
            char instruction = instruction0.charAt(0);
            User.instruction(instruction);
            printMaze(maze);
        }
        System.out.println("You Win!");
    }

    private static void printMaze(char[][] maze) {
        for (char[] aMaze : maze) {
            for (int j = 0; j < maze[0].length; j++) {
                System.out.print(aMaze[j]);
            }
            System.out.println();
        }
    }
}

