import java.util.Scanner;

class Maze4 {
    private User u = new User();
    private static int[][] map = {
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 2, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1},
            {1, 0, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1},
            {1, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1, 0, 1},
            {1, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 1, 1},
            {1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1, 0, 1, 0, 1, 0, 1, 0, 0, 0, 1},
            {1, 0, 1, 0, 1, 0, 1, 0, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1},
            {1, 0, 1, 0, 1, 0, 0, 0, 1, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1, 0, 1, 0, 1},
            {1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 1},
            {1, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1, 1, 0, 1},
            {1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 1},
            {1, 0, 1, 1, 1, 1, 1, 0, 1, 0, 1, 0, 1, 0, 1, 1, 1, 0, 1, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1, 0, 1},
            {1, 0, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1},
            {1, 0, 1, 0, 1, 0, 0, 0, 1, 0, 1, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1},
            {1, 1, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 1, 1, 0, 1, 1, 1},
            {1, 0, 1, 0, 1, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 1, 0, 1},
            {1, 0, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1, 0, 1, 0, 1, 0, 1},
            {1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}

    };
    private Monster m = new Monster();

    private void position(char[][] maze) {
        Treasure t = new Treasure();
        t.random(maze);
    }

    private char[][] maze(int[][] map) {
        char[][] maze = new char[map.length][map[0].length];
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (map[i][j] == 1) {
                    maze[i][j] = '■';
                }
                if (map[i][j] == 0) {
                    maze[i][j] = '□';
                }
                if (map[i][j] == 2) {
                    maze[i][j] = '☺';
                }
            }
        }
        return maze;
    }

    private void printMaze(char[][] maze) {
        for (char[] i : maze) {
            for (char j : i) {
                System.out.print(j);
            }
            System.out.println();
        }
    }

    private void moveOfUser(char order, char[][] maze) {
        u.order(order, maze);
    }

    private void position0(char[][] maze) {
        m.getRandomPosition(maze);
    }

    private void move(char[][] maze) {
        m.randomMove(maze);
    }

    void s(Maze4 m0) {
        Scanner input = new Scanner(System.in);
        char[][] maze4 = maze(map);
        m0.position(maze4);
        m0.position0(maze4);
        m0.printMaze(maze4);
        System.out.println("请输入命令： ");
        while (maze4[maze4.length - 2][maze4[0].length - 1] != '☺') {
            String g = input.next();
            char order = g.charAt(0);
            moveOfUser(order, maze4);
            m0.move(maze4);
            m0.printMaze(maze4);
            if (!isWin(maze4)) {
                System.out.println("请输入命令： ");
            }
        }
    }

    private boolean isWin(char[][] maze) {
        return maze[maze.length - 2][maze[0].length - 1] == '☺';
    }

    int getCountOfMonster() {
        return u.getCountOfMonster();
    }

    int getCountOfTreasure() {
        return u.getCountOfTreasure();
    }

    int getCountOfTrace() {
        return u.getCountOfTrace();
    }
}
