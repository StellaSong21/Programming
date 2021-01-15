//人物的移动 有足迹
class User {
    private static char p = '+';
    private static char[][] maze = Maze.maze;
    private static int numOfTreasure = 0;
    private static int numOfMoster = 0;
    private static int numOfMove = 0;

    private static int[] position() {
        int[] position = new int[2];
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[0].length; j++) {
                if (maze[i][j] == p) {
                    position[0] = i;
                    position[1] = j;
                }
            }
        }
        return position;
    }

    static int[] getPosition() {
        int x = position()[0];
        int y = position()[1];
        return new int[]{x, y};
    }

    //坐标变化
    static void instruction(char instruction) {
        int i = position()[0];
        int j = position()[1];

        switch (instruction) {
            case 'w':
                User.w(i, j);
                break;
            case 'a':
                User.a(i, j);
                break;
            case 's':
                User.s(i, j);
                break;
            case 'd':
                User.d(i, j);
                break;
            case 'j':
                User.j(i, j);
                break;
            case 'k':
                User.k(i, j);
                break;
            case 'l':
                User.l();
                break;
            case 'h':
                User.h();
                break;
            case 'b':
                Stack.pop();
                maze[Stack.peek()[0]][Stack.peek()[1]] = p;
                break;
            default:
                System.out.println("无效输入！");
        }

    }

    private static void w(int x, int y) {
        char w = maze[x - 1][y];
        if (w == '#') {
            System.out.println("无效输入！");
        } else if (w == '$') {
            System.out.println("倘若要捡起宝物，请按k");
        } else if (w == ' ' || w == '*') {
            maze[x][y] = '*';
            maze[x - 1][y] = p;
            Stack.push(x - 1,y);
            numOfMove++;
        }
    }

    private static void a(int x, int y) {
        char w = maze[x][y - 1];
        if (w == '#') {
            System.out.println("无效输入！");
        } else if (w == '$') {
            System.out.println("倘若要捡起宝物，请按k");
        } else if (w == ' ' || w == '*') {
            maze[x][y] = '*';
            maze[x][y - 1] = p;
            Stack.push(x, y -1);
            numOfMove++;
        }
    }

    private static void s(int x, int y) {
        char w = maze[x + 1][y];
        if (w == '#') {
            System.out.println("无效输入！");
        } else if (w == '$') {
            System.out.println("倘若要捡起宝物，请按k");
        } else if (w == ' ' || w == '*') {
            maze[x][y] = '*';
            maze[x + 1][y] = p;
            Stack.push(x + 1,y);
            numOfMove++;
        }
    }

    private static void d(int x, int y) {
        char w = maze[x][y + 1];
        if (w == '#') {
            System.out.println("无法穿墙！");
        } else if (w == '$') {
            System.out.println("倘若要捡起宝物，请按k");
        } else if (w == ' ' || w == '*') {
            maze[x][y] = '*';
            maze[x][y + 1] = p;
            Stack.push(x,y + 1);
            numOfMove++;
        }
    }


    //攻击怪物
    private static void j(int x, int y) {
        if (maze[x - 1][y] == '&') {
            maze[x - 1][y] = ' ';
            numOfMoster++;
        }
        if (maze[x][y - 1] == '&') {
            maze[x][y - 1] = ' ';
            numOfMoster++;
        }
        if (maze[x + 1][y] == '&') {
            maze[x + 1][y] = ' ';
            numOfMoster++;
        }
        if (maze[x][y + 1] == '&') {
            maze[x][y + 1] = ' ';
            numOfMoster++;
        }
    }

    //捡起宝物
    private static void k(int x, int y) {
        for (int i = x - 1; i <= x + 1; i++) {
            for (int j = y - 1; j <= y + 1; j++) {
                if (maze[i][j] == '$') {
                    maze[i][j] = ' ';
                    numOfTreasure++;
                }
            }
        }
    }

    //查看帮助
    private static void h() {
        System.out.println("w：向上" + "\na：向左" + "\ns：向下" + "\nd：向右" +
                "\nh：查看帮助" + "\nj：攻击怪物" + "\nk：捡起宝物" + "\nl：查看目前得分");
    }

    //查看目前得分
    private static void l() {
        System.out.println("您目前走了" + numOfMove + "步" + "\n得到" + numOfTreasure + "个宝物" +
                "\n解决" + numOfMoster + "个怪物");
    }


}

