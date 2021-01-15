package core;

import static core.Icon.*;

public class Monster {
    private int ATK;
    private int HP;
    private int DEF;
    private int[] place;

    Monster(int row, int column, Icon[][] map) {
        int x = (int) (Math.random() * row);
        int y = (int) (Math.random() * column);

        while (map[x][y] != GRASS) {
            x = (int) (Math.random() * row);
            y = (int) (Math.random() * column);
        }
        place = new int[]{x, y};
    }

    public void rand(Icon[][] map) {
        if (HP > 0) {
            int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
            int i = (int) (Math.random() * 4);
            int x = place[0] + dir[i][0];
            int y = place[1] + dir[i][1];
           if (map[x][y] == GRASS){
                x = place[0] + dir[i][0];
                y = place[1] + dir[i][1];
                if (map[x][y] == GRASS) {
                    map[place[0]][place[1]] = GRASS;
                    place[0] = x;
                    place[1] = y;
                    map[x][y] = MONSTER;
                }
            }
        }
    }

    void setATK(int ATK) {
        this.ATK = ATK;
    }

    void setDEF(int DEF) {
        this.DEF = DEF;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public int getATK() {
        return ATK;
    }

    public int getDEF() {
        return DEF;
    }

    public int getHP() {
        return HP;
    }

    void setPlace(int[] place) {
        this.place = place;
    }

    public int[] getPlace() {
        return place;
    }
}
