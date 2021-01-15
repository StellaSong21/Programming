class Monster {
    private char m = '♚';
    private char p = '□';
    private int count;

    void getRandomPosition(char[][] maze) {
        randomPosition(maze);
    }

    private void randomPosition(char[][] maze) {
        int count = 0;
        while (count < 3) {
            int i = (int) (Math.random() * maze.length);
            int j = (int) (Math.random() * maze[0].length);
            if (maze[i][j] == p) {
                maze[i][j] = m;
                count++;
            }
        }
    }

    private int[][] nowPosition(char[][] maze) {
        count = 0;
        int[][] position = new int[3][2];
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[0].length; j++) {
                if (i != maze.length - 1 || j != maze[0].length - 1) {
                    if (maze[i][j] == m) {
                        position[count][0] = i;
                        position[count][1] = j;
                        count++;
                    }
                }
            }
        }
        return position;
    }

    void randomMove(char[][] maze) {
        int[][] position = nowPosition(maze);
        int count = 0;
        while (count < this.count) {
            randomMove(position[count], maze);
            count++;
        }
    }

    private void randomMove(int[] position, char[][] maze) {
        int p = (int) (Math.random() * 4);
        int x = position[0];
        int y = position[1];
        switch (p) {
            case 0:
                if (x != 0 && y != 1)
                    x--;
                if (maze[x][y] == '□' || maze[x][y] == '☻') {
                    maze[x][y] = m;
                    maze[x + 1][y] = '□';
                }
                break;
            case 1:
                if (y != 0 && y != 1)
                    y--;
                if (maze[x][y] == '□' || maze[x][y] == '☻') {
                    maze[x][y] = m;
                    maze[x][y + 1] = '□';
                }
                break;
            case 2:
                if (x != maze.length - 2 && x != maze.length - 1)
                    x++;
                if (maze[x][y] == '□' || maze[x][y] == '☻') {
                    maze[x][y] = m;
                    maze[x - 1][y] = '□';
                }
                break;
            default:
                if (y != maze[0].length - 2 && y != maze[0].length - 1)
                    y++;
                if (maze[x][y] == '□' || maze[x][y] == '☻') {
                    maze[x][y] = m;
                    maze[x][y - 1] = '□';
                }
        }
        position[0] = x;
        position[1] = y;
    }

}
