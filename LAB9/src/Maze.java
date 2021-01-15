//设计地图和获得地图
class Maze {
    //设计地图
    private static char w = '#';
    private static char p = ' ';
    static char[][] maze = new char[][]{
            {w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w},
            {w, '+', p, p, p, p, w, w, w, w, w, p, w, w, w, p, w, w, w, p, p, p, p, p, p, p, p, p, p, p, w, w, w, p, p, p, w, w},
            {w, p, w, w, w, p, p, p, p, p, p, p, p, p, p, p, p, p, w, p, w, p, p, w, w, w, w, w, w, p, w, p, p, p, w, p, w, w},
            {w, w, p, p, p, p, w, w, w, w, w, w, w, w, p, w, w, p, w, p, w, w, p, w, w, p, w, w, w, p, w, w, w, w, w, p, p, w},
            {w, w, p, w, w, p, w, p, p, p, p, p, p, w, w, w, w, p, w, p, p, w, w, p, p, p, p, p, p, p, p, w, p, w, w, w, p, w},
            {w, p, p, w, w, p, w, p, w, w, w, w, p, p, p, p, p, p, w, p, w, p, w, w, w, w, w, w, w, w, w, w, p, p, p, p, p, w},
            {w, p, w, p, w, p, w, w, w, p, w, w, w, w, w, w, w, p, w, p, p, p, p, p, p, p, p, p, p, p, p, p, w, p, w, w, w, w},
            {w, p, w, p, w, p, p, p, p, p, p, p, p, p, p, w, w, w, w, w, w, p, w, w, w, w, w, w, w, w, w, p, w, p, p, w, p, w},
            {w, p, p, p, w, w, w, w, w, w, w, p, w, p, w, w, p, p, p, p, w, p, w, p, w, p, p, p, p, p, w, p, w, w, p, w, p, w},
            {w, w, w, p, w, p, p, p, p, w, w, p, w, p, p, p, p, w, w, p, w, p, w, p, w, p, w, w, w, p, w, p, p, w, p, w, p, w},
            {w, p, w, p, w, p, w, w, p, w, w, p, w, w, w, w, p, w, w, w, w, p, w, p, w, p, w, p, w, p, w, p, w, p, p, w, p, w},
            {w, p, w, p, w, p, p, w, p, w, w, p, p, p, p, p, p, p, p, p, p, p, w, p, w, p, p, p, w, p, w, p, w, w, p, p, p, w},
            {w, p, w, w, w, w, w, w, p, p, p, w, w, p, w, w, w, w, w, w, w, w, w, p, w, w, w, w, w, p, w, p, w, w, w, p, w, w},
            {w, p, p, p, p, p, p, p, p, w, p, w, w, p, w, w, p, w, w, p, w, w, w, p, p, p, p, p, p, p, w, p, p, p, w, p, w, w},
            {w, w, p, w, w, p, w, w, p, w, p, p, p, p, p, p, p, p, p, p, w, p, p, p, w, w, p, w, w, w, p, w, w, p, p, p, p, w},
            {w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w},
    };

    static char[][] newMaze() {
        treasure();
        moster();
        return maze;
    }

    private static void treasure() {
        char t = '$';
        int i = 0;
        int count = 0;
        while (count < 3) {
            while (i < 3) {
                int m = (int) (Math.random() * maze.length);
                int n = (int) (Math.random() * maze[0].length);
                if (maze[m][n] != w) {
                    maze[m][n] = t;
                    i++;
                    count++;
                    break;
                } else
                    i--;

            }
        }
    }

    private static void moster() {
        int i = 0;
        int count = 0;
        while (count < 3) {
            while (i < 3) {
                int m = (int) (Math.random() * maze.length);
                int n = (int) (Math.random() * maze[0].length);
                if (maze[m][n] != w) {
                    maze[m][n] = '&';
                    i++;
                    count++;
                    break;
                } else
                    i--;

            }
        }
    }

    private static int[][] postOfMoster() {
        int[][] postOfMoster = new int[3][2];
        int k = 0;
        a:
        {
            for (int i = 0; i < maze.length; i++) {
                for (int j = 0; j < maze[0].length; j++) {
                    if (maze[i][j] == '&') {
                        postOfMoster[k][0] = i;
                        postOfMoster[k][1] = j;
                        k++;
                        if (k == 3) {
                            break a;
                        }
                    }
                }
            }
        }
        return postOfMoster;
    }

    static void moveOfMoster() {
        int[][] postOfMoster = Maze.postOfMoster();
        for (int i = 0; i < 3; i++) {
            int count = 0;
            while (count < 1) {
                int random = (int) (Math.random() * 4);
                switch (random) {
                    case 0:
                        if (maze[postOfMoster[i][0] - 1][postOfMoster[i][1]] == ' ') {
                            maze[postOfMoster[i][0]][postOfMoster[i][1]] = ' ';
                            maze[postOfMoster[i][0] - 1][postOfMoster[i][1]] = '&';
                            count++;
                        } else
                            break;
                    case 1:
                        if (maze[postOfMoster[i][0]][postOfMoster[i][1] - 1] == ' ') {
                            maze[postOfMoster[i][0]][postOfMoster[i][1]] = ' ';
                            maze[postOfMoster[i][0]][postOfMoster[i][1] - 1] = '&';
                            count++;
                        } else
                            break;
                    case 2:
                        if (maze[postOfMoster[i][0] + 1][postOfMoster[i][1]] == ' ') {
                            maze[postOfMoster[i][0]][postOfMoster[i][1]] = ' ';
                            maze[postOfMoster[i][0] + 1][postOfMoster[i][1]] = '&';
                            count++;
                        } else
                            break;
                    case 3:
                        if (maze[postOfMoster[i][0]][postOfMoster[i][1] + 1] == ' ') {
                            maze[postOfMoster[i][0]][postOfMoster[i][1]] = ' ';
                            maze[postOfMoster[i][0]][postOfMoster[i][1] + 1] = '&';
                            count++;
                        } else
                            break;
                }
            }
        }
    }
}

