import java.util.Scanner;

public class Maze2 extends Maze1 {

    private static char[][] maze2 = new char[][]{
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

//    public void moveOfUser(char order, char[][] maze2) {
//        u.order(order, Maze2.maze2);
//    }

    public void position(char[][] maze){
        Treasure t = new Treasure();
        t.random(maze);
    }
    public void s() {
        Maze2 e = new Maze2();
        Scanner input = new Scanner(System.in);
        Treasure t = new Treasure();
        t.random(maze2);
        e.printMaze(maze2);
        while (maze2[maze2.length - 2][maze2[0].length - 1] != o) {
            String g = input.next();
            char order = g.charAt(0);
            e.moveOfUser(order, maze2);
            e.printMaze(maze2);
            if(!isWin()) {
                System.out.println("请输入命令： ");
            }
        }
    }

    public boolean isWin(){
        return maze2[maze2.length - 2][maze2[0].length - 1] == o;
    }

}
