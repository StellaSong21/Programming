class User {
    private int countOfMonster;
    private int countOfTreasure;
    private int countOfTrace;
    private char p = '☺';
    private char o = '□';
    private char z = '☻';
    private StcckOfTrack0 c = new StcckOfTrack0();

    private int[] position(char[][] maze) {
        int[] position = new int[2];
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[i].length; j++) {
                if (maze[i][j] == p) {
                    position[0] = i;
                    position[1] = j;
                }
            }
        }

        return position;
    }

    void order(char order, char[][] maze) {
        moveOfUser(order, maze);
    }

    private void moveOfUser(char order, char[][] maze) {
        int[] position = position(maze);
        int x = position[0];
        int y = position[1];
        switch (order) {
            case 'w':
                if (x != 0) {
                    x--;
                    if (maze[x][y] == '♛') {
                        countOfTreasure++;
                    }
                    if (maze[x][y] == '♚') {
                        System.out.println("你死了！");
                        System.exit(0);
                    }
                    if (maze[x][y] == '■') {
                        System.out.println("不能穿墙哟");
                    }
                    if (maze[x][y] == o || maze[x][y] == '♛' || maze[x][y] == z) {
                        c.push(position);
                        position[0]--;
                        int[] last0 = c.peek1();
                        int[] last1 = c.peek2();
                        int[] last2 = c.peek3();
                        maze[last2[0]][last2[1]] = o;
                        maze[last1[0]][last1[1]] = z;
                        maze[last0[0]][last0[1]] = z;
                        maze[x][y] = p;
                        countOfTrace++;
                    }
                }
                break;
            case 'a':
                if (y != 0) {
                    y--;
                    if (maze[x][y] == '♛') {
                        countOfTrace++;
                    }
                    if (maze[x][y] == '♚') {
                        System.out.println("你死了！");
                        System.exit(0);
                    }
                    if (maze[x][y] == '■') {
                        System.out.println("不能穿墙哟");
                    }
                    if (maze[x][y] == o || maze[x][y] == '♛' || maze[x][y] == z) {
                        c.push(position);
                        position[1]--;
                        int[] last0 = c.peek1();
                        int[] last1 = c.peek2();
                        int[] last2 = c.peek3();
                        maze[last2[0]][last2[1]] = o;
                        maze[last1[0]][last1[1]] = z;
                        maze[last0[0]][last0[1]] = z;
                        maze[x][y] = p;
                        countOfTrace++;
                    }
                }
                break;
            case 's':
                if (x != maze.length - 1) {
                    x++;
                    if (maze[x][y] == '♛') {
                        countOfTreasure++;
                    }
                    if (maze[x][y] == '♚') {
                        System.out.println("你死了！");
                        System.exit(0);
                    }
                    if (maze[x][y] == '■') {
                        System.out.println("不能穿墙哟");
                    }
                    if (maze[x][y] == o || maze[x][y] == '♛' || maze[x][y] == z) {
                        c.push(position);
                        position[0]++;
                        int[] last0 = c.peek1();
                        int[] last1 = c.peek2();
                        int[] last2 = c.peek3();
                        maze[last2[0]][last2[1]] = o;
                        maze[last1[0]][last1[1]] = z;
                        maze[last0[0]][last0[1]] = z;
                        maze[x][y] = p;
                        countOfTrace++;
                    }
                }
                break;
            case 'd':
                if (y != maze[0].length - 1) {
                    y++;
                    if (maze[x][y] == '♛') {
                        countOfTreasure++;
                    }
                    if (maze[x][y] == '♚') {
                        System.out.println("你死了！");
                        System.exit(0);
                    }
                    if (maze[x][y] == '■') {
                        System.out.println("不能穿墙哟");
                    }
                    if (maze[x][y] == o || maze[x][y] == '♛' || maze[x][y] == z) {
                        c.push(position);
                        position[1]++;
                        int[] last0 = c.peek1();
                        int[] last1 = c.peek2();
                        int[] last2 = c.peek3();
                        maze[last2[0]][last2[1]] = o;
                        maze[last1[0]][last1[1]] = z;
                        maze[last0[0]][last0[1]] = z;
                        maze[x][y] = p;
                        countOfTrace++;
                    }
                }
                break;
            case 'j':
                if (maze[x + 1][y] == '♚') {
                    maze[x + 1][y] = o;
                    countOfMonster++;
                } else if (maze[x - 1][y] == '♚') {
                    maze[x - 1][y] = o;
                    countOfMonster++;
                } else if (maze[x][y + 1] == '♚') {
                    maze[x][y + 1] = o;
                    countOfMonster++;
                } else if (maze[x][y - 1] == '♚') {
                    maze[x][y - 1] = o;
                    countOfMonster++;
                } else
                    System.out.println("周围没有怪物，不要太紧张");
                break;
            case 'k':
                System.out.println("moster: " + countOfMonster + "\ntrack: "
                        + countOfTrace + "\ntreasure: " + countOfTreasure);
                break;
            case 'h':
                System.out.println("w:向上移动" + "\ns:向下移动" + "\na:向左移动" + "\nd:向右移动" +
                        "\nj:攻击怪物" + "\nk:查看当前信息" + "\nh:查看帮助" + "\nb:后退一步" + "");
                break;
            case 'b':
                if (!c.isEmrty()) {
                    lastPosition(x, y, maze);
                    countOfTrace++;
                } else
                    System.out.println("您已退回起点。。。");
                break;
            case 'q':
                System.out.println("好可惜，你放弃");
                System.exit(0);
        }
    }

    private void lastPosition(int x, int y, char[][] maze) {
        c.pop();
        int[] last0 = c.peek();
        int[] last1 = c.peek1();
        int[] last2 = c.peek2();
        maze[last2[0]][last2[1]] = z;
        maze[last1[0]][last1[1]] = z;
        maze[last0[0]][last0[1]] = p;
        maze[x][y] = o;
    }

}
