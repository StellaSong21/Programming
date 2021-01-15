package core;

import java.util.Stack;

import static core.Icon.*;

public class Map {
    private int row = 31;
    private int column = 61;
    private Icon[][] map;
    private int[] pos;
    private Stack<int[]> stack1 = new Stack<>();
    private boolean[][] visit = new boolean[row][column];
    private int[][] adj = {{0, 2}, {0, -2}, {2, 0}, {-2, 0}};
    private static Monster[] monsters;

    public Map() {
        this(1);
    }

    //构造第i层，row * column的迷宫
    public Map(int level) {
        switch (level) {
            case 1:
                this.row = 11;
                this.column = 21;
                break;
            case 2:
                this.row = 21;
                this.column = 41;
                break;
            case 3:
                this.row = 25;
                this.column = 51;
                break;
            case 4:
                this.row = 25;
                this.column = 51;
        }

        map = new Icon[row][column];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                map[i][j] = WALL;
            }
        }

        map[1][1] = GRASS;
        pos = new int[]{1, 1};
        stack1.push(pos);
        map = makeMap();
        map[row - 2][column - 2] = END;
        map[1][1] = HERO;

        if (level != 1) {
            for (int i = 0; i < 3; i++) {
                map[treasure()[i][0]][treasure()[i][1]] = TREASURE;
            }
        }

        if (level == 3) {
            monsters = new Monster[]{new Monster(row, column, map), new Monster(row, column, map),
                    new Monster(row, column, map), new Monster(row, column, map), new Monster(row, column, map), new Monster(row, column, map)};
            for (int i = 0; i < 6; i++) {
                monsters[i].setATK(10);
                monsters[i].setDEF(20);
                monsters[i].setHP(20);
                map[monsters[i].getPlace()[0]][monsters[i].getPlace()[1]] = MONSTER;
            }
        }

        if (level == 4) {
            monsters = new Monster[]{new Monster(row, column, map), new Monster(row, column, map),
                    new Monster(row, column, map), new Monster(row, column, map), new Monster(row, column, map), new Monster(row, column, map)};
            for (int i = 0; i < 6; i++) {
                monsters[i].setATK(35);
                monsters[i].setDEF(50);
                monsters[i].setHP(30);
                map[monsters[i].getPlace()[0]][monsters[i].getPlace()[1]] = MONSTER;
            }
        }

    }

    public Icon[][] getMap(int level) {
        return map;
    }

    //随机生成迷宫
    private Icon[][] makeMap() {
        stack1.push(pos);

        while (!stack1.empty()) {
            int length = addressable(pos).length;
            if (length == 0) {
                pos = stack1.pop();
                continue;
            }

            int[] next = addressable(pos)[(int) (Math.random() * addressable(pos).length)];

            if (visit[next[0]][next[1]]) {
                pos = stack1.pop();
            } else {
                stack1.push(next);
                visit[next[0]][next[1]] = true;
                map[next[0]][next[1]] = GRASS;
                map[(pos[0] + next[0]) / 2][(pos[1] + next[1]) / 2] = GRASS;
                pos = next;
            }
        }

        return map;
    }

    //判断四周是否存在未被访问的位置
    private int[][] addressable(int[] pos) {
        int[][] tmp = new int[4][2];
        int num = 0;

        for (int[] anAdj : adj) {
            int x = pos[0] + anAdj[0];
            int y = pos[1] + anAdj[1];
            if (x >= 0 && x < row - 1 && y >= 0 && y < column - 1) {
                if (!visit[x][y]) {
                    tmp[num] = new int[]{x, y};
                    num++;
                }
            }
        }

        int[][] dir = new int[num][2];
        for (int i = 0; i < num; i++)
            System.arraycopy(tmp[i], 0, dir[i], 0, 2);

        return dir;
    }

    //随机生成宝物的位置
    private int[][] treasure() {
        int num = 0;
        int[][] treasure = new int[3][2];

        while (num < 3) {
            int x = (int) (Math.random() * row);
            int y = (int) (Math.random() * column);

            if (map[x][y] == GRASS) {
                treasure[num] = new int[]{x, y};
                num++;
            }
        }

        return treasure;
    }

    public static Monster[] getMonsters() {
        return monsters;
    }

}
