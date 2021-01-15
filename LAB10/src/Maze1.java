import java.util.Scanner;

class Maze1 {
    static char w = '■';
    static char p = '□';
    static char o = '☺';
    private User u = new User();

    private static char[][] maze1 = new char[][]{
            {w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w},
            {w, o, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w},
            {w, p, p, p, p, p, p, p, p, p, p, p, p, p, p, p, p, p, p, p, p, p, p, p, p, p, p, p, p, p, p, w, w, w, w, w, w, w, w, w},
            {w, p, p, p, p, p, p, p, p, p, p, p, p, p, p, p, p, p, p, p, p, p, p, p, p, p, p, p, p, p, p, w, w, w, w, w, w, w, w, w},
            {w, p, p, p, p, p, p, p, p, p, p, p, p, p, p, p, p, p, p, p, p, p, p, p, p, p, p, p, p, p, p, w, w, w, w, w, w, w, w, w},
            {w, p, p, p, p, p, p, p, p, p, p, p, p, p, p, p, p, p, p, p, p, p, p, p, p, p, p, p, p, p, p, w, w, w, w, w, w, w, w, w},
            {w, p, p, p, p, p, p, p, p, p, p, p, p, p, p, p, p, p, p, p, p, p, p, p, p, p, p, p, p, p, p, w, w, w, w, w, w, w, w, w},
            {w, p, p, p, p, p, p, p, p, p, p, p, p, p, p, p, p, p, p, p, p, p, p, p, p, p, p, p, p, p, p, w, w, w, w, w, w, w, w, w},
            {w, p, p, p, p, p, p, p, p, p, p, p, p, p, p, p, p, p, p, p, p, p, p, p, p, p, p, p, p, p, p, w, w, w, w, w, w, w, w, w},
            {w, p, p, p, p, p, p, p, p, p, p, p, p, p, p, p, p, p, p, p, p, p, p, p, p, p, p, p, p, p, p, w, w, w, w, w, w, w, w, w},
            {w, p, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w},
            {w, p, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w},
            {w, p, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w},
            {w, p, p, p, p, p, p, p, p, p, p, p, p, p, p, p, p, p, p, p, p, p, p, p, p, p, p, p, p, p, p, p, p, p, p, p, p, p, p, p},
            {w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w},
    };

    void moveOfUser(char order, char[][] maze) {
        u.order(order, maze);
    }

    void printMaze(char[][] maze) {
        for (char[] i : maze) {
            for (char j : i) {
                System.out.print(j);
            }
            System.out.println();
        }
    }

    public void s() {
        Maze1 e = new Maze1();
        Scanner input = new Scanner(System.in);
        e.printMaze(maze1);
        while (maze1[maze1.length - 2][maze1[0].length - 1] != o) {
            String g = input.next();
            char order = g.charAt(0);
            e.moveOfUser(order, maze1);
            e.printMaze(maze1);
            if(!isWin()) {
                System.out.println("请输入命令： ");
            }
        }
    }

    public boolean isWin(){
        return maze1[maze1.length - 2][maze1[0].length - 1] == o;
    }

}
