class Treasure {

    void random(char[][] maze) {
        randomPosition(maze);
    }

    private void randomPosition(char[][] maze) {
        int count = 0;
        while (count < 3) {
            int x = (int) (Math.random() * maze.length);
            int y = (int) (Math.random() * maze[0].length);
            if (maze[x][y] == '□') {
                maze[x][y] = '♛';
                count++;
            }
        }
    }

}
