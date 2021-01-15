public class User {
    public static int numOfTreasure = 0;

    private static int[] post(int x, int y, char operation) {

        switch (operation) {
            case 'w':
                x -= 1;
                break;
            case 'a':
                y -= 1;
                break;
            case 's':
                x += 1;
                break;
            case 'd':
                y += 1;
                break;
            default:
                break;
        }
        int[] post;
        post = new int[]{x, y};
        return post;

    }


    public static void postOfUser(char[][] labyrinth, char operation) {
        int i = 0;
        int j = 0;
        for (int m = 0; m < labyrinth.length; m++) {
            for (int n = 0; n < labyrinth[0].length; n++) {
                if (labyrinth[m][n] == '☺') {
                    i = m;
                    j = n;
                }
            }
        }
        int x = post(i, j, operation)[0];
        int y = post(i, j, operation)[1];
        if (labyrinth[x][y] == '¤') {
            numOfTreasure++;
            labyrinth[x][y] = '☺';
            labyrinth[i][j] = '　';
        } else if (labyrinth[x][y] == '　') {
            labyrinth[x][y] = '☺';
            labyrinth[i][j] = '　';
        } else
            System.out.println("Invalid input!");
    }
}
