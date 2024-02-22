//Hello 

import java.util.Random;

public class Player {

    private double health;
    private Weapon[] weapons;
    private Item[] inventory;
    private int aC;
    private int atk;
    private double[] resistances;

    Player player = new Player(100, 15, 5);

    public Player(double health, int aC, int atk) {
        this.health = health;
        this.aC = aC;
        this.atk = atk;
        this.resistances = new double[] { 1.0, 1.0, 1.0, 1.0, 1.0, 1.0 };
    }

    public static int diceRoller(int nSides) {
        Random dice = new Random();
        return dice.nextInt(nSides) + 1;
    }

    public Player getPlayer(){
        return this.player;
    }

    public int getAC() {
        return this.aC;
    }

    public int getAtk() {
        return this.atk;
    }

    public int damageToEnemy(Enemy enemy) {
        return 0;
    }

    public void damageToPlayer(double damage) {
        this.health -= damage;
    }

}