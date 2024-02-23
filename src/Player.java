//Hello 

import java.util.Random;

public class Player {

    private double health;
    private Weapon[] weapons;
    private Item[] inventory;
    private int aC;
    private double atk;
    private double[] res;

    Player player = new Player(100, 15, 5);

    public Player(double health, int aC, int atk) {
        this.health = health;
        this.aC = aC;
        this.atk = atk;
        this.res = new double [] {1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0};
        this.inventory = new Item[9];
    }

    public Player getPlayer(){
        return this.player;
    }

    public int getAC() {
        return this.aC;
    }

    public double getAtk() {
        return this.atk;
    }

    public double getHealth(){
        return this.health;
    }

    public Item[] getItems(){
        return this.inventory;
    }
    

    public double getRes(DamageType type) {
        if (type == DamageType.BLUDGEONING) {
            return this.res[0];
        }
        else if (type == DamageType.PIERCING) {
            return this.res[1];
        }
        else if (type == DamageType.SLASHING) {
            return this.res[2];
        }
        else if (type == DamageType.FIRE) {
            return this.res[3];
        }
        else if (type == DamageType.ELECTRICITY) {
            return this.res[4];
        }
        else if (type == DamageType.POISON) {
            return this.res[5];
        }
        return 1.0;
    }

    public void setRes(DamageType type, double additor) {
        if (type == DamageType.BLUDGEONING) {
            this.res[0] -= additor;
        }
        else if (type == DamageType.PIERCING) {
            this.res[1] -= additor;
        }
        else if (type == DamageType.SLASHING) {
            this.res[2] -= additor;
        }
        else if (type == DamageType.FIRE) {
            this.res[3] -= additor;
        }
        else if (type == DamageType.ELECTRICITY) {
            this.res[4] -= additor;
        }
        else if (type == DamageType.POISON) {
            this.res[5] -= additor;
        }
    }

    public static int diceRoller(int nSides) {
        Random dice = new Random();
        return dice.nextInt(nSides) + 1;
    }

    public double damageToEnemy(Enemy enemy, int index) {
        return weapons[index].doDamage(enemy, this) + this.atk;
    }

    public void damageToPlayer(double damage, DamageType type) {
        this.health -= (damage*getRes(type));
    }

}