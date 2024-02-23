//Hello 

import java.util.Random;

public class Player {

    private double health;
    private Weapon[] weapons;
    private Item[] inventory;
    private int aC;
    private double atk;
    private double[] res;


    public Player(double health, int aC, int atk) {
        this.health = health;
        this.aC = aC;
        this.atk = atk;
        this.res = new double [] {1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0};
        this.inventory = new Item[9];
    }

    public static int diceRoller(int nSides) {
        Random dice = new Random();
        return dice.nextInt(nSides) + 1;
    }

    public int getAC() {
        return this.aC;
    }

    public void addAC() {
        this.aC++;
    }

    public double getAtk() {
        return this.atk;
    }

    public void addAtk() {
        this.atk++;
    }

    public double getHealth(){
        return this.health;
    }

    public Item[] getItems(){
        return this.inventory;
    }
    
    public Weapon getCurrentWeapon(){
        if (this.)
        return this.weapons[2];
    }

    public void updateInventory() {
        for (int i = 0; i < 9; i ++) {
            if (this.inventory[i] != null) {
                this.inventory[i].update();
                if (this.inventory[i].isExpended()) this.inventory[i] = null;
            }
        }
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


    public int damageToEnemy(Enemy enemy) {
        double enemyResistance = enemy.getRes(DamageType.BLUDGEONING);

        return 0;
    }

    public void damageToPlayer(double damage) {
        this.health -= damage;
    }

}