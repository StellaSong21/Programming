package core;

import java.util.Stack;

import static core.Icon.*;

public class Map {
    private int row = 25;
    private int column = 51;
    private Icon[][] map;
    private int[] pos;
    private Stack<int[]> stack1 = new Stack<>();
    private boolean[][] visit = new boolean[row][column];
    private int[][] adj = {{0, 2}, {0, -2}, {2, 0}, {-2, 0}};

    public Map() {
        map = new Icon[row][column];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                map[i][j] = WALL;
            }
        }

        map[1][1] = GRASS;
        pos = new int[]{1, 1};

        stack1.push(pos);

        map = random();
    }

    private Icon[][] random() {
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

    private int[][] addressable(int[] pos) {
        int[][] tmp = new int[4][2];
        int num = 0;

        for (int i = 0; i < adj.length; i++) {
            int x = pos[0] + adj[i][0];
            int y = pos[1] + adj[i][1];
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

    public Icon[][] getMap() {
        return map;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setColumn(int column) {
        this.column = column;
    }

}
