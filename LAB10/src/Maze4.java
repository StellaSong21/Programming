import java.util.Scanner;

public class Maze4 extends Maze1 {
    private static char[][] maze4 = new char[][]{
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
    private Monster m = new Monster();

    public void position(char[][] maze) {
        Treasure t = new Treasure();
        t.random(maze);
    }

    public void position0(char[][] maze) {
        m.getRandomPosition(maze);
    }

    public void move(char[][] maze){
        m.randomMove(maze);
    }

    public void s() {
        Maze4 e = new Maze4();
        Scanner input = new Scanner(System.in);
        Treasure t = new Treasure();
        t.random(maze4);
        Monster m = new Monster();
        m.getRandomPosition(maze4);
        e.printMaze(maze4);
        while (maze4[maze4.length - 2][maze4[0].length - 1] != o) {
            String g = input.next();
            char order = g.charAt(0);
            e.moveOfUser(order, maze4);
            m.randomMove(maze4);
            e.printMaze(maze4);
            if(!isWin()) {
                System.out.println("请输入命令： ");
            }
        }
    }
    public boolean isWin(){
        return maze4[maze4.length - 2][maze4[0].length - 1] == o;
    }

}
