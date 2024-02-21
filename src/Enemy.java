import java.util.Random;

public class Enemy {
    
public static void main(String[] args) {

}   

    private String EType;
    private int difficulty;
    private double[] res;
    private int health;
    private int atk;
    private int maxDmg;
    private int aC;
    
    public Enemy(String EType, int difficulty, double[] res, int health, int atk, int maxDmg, int aC) {
        this.EType = EType;
        this.difficulty = difficulty;
        this.res = res;
        this.health = health;
        this.atk = atk;
        this.maxDmg = maxDmg;
        this.aC = aC;
    }

    public static int diceRoller(int nSides) {
        Random dice = new Random();
        return dice.nextInt(nSides) + 1;
    }

    public String getEType() {
        return this.EType;
    }

    public int getDiff() {
        return this.difficulty;
    }
    
    //Returns the proper resistance multiplier for a given DamageType enum
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

    public int getHealth() {
        return this.health;
    }

    public int getAtk() {
        return this.atk;
    }

    public int getMaxDmg() {
        return this.maxDmg;
    }

    public int getAC() {
        return this.aC;
    }

    public boolean eAttack(int roll) {
        return (roll >= this.aC);
    }

    public void eDamage(int damage) {
        this.health -= damage;
    }

    //Makes an attack roll and does damage to the player if neccesary
    public int dmgPlayer(int pAC) {
        int roll = diceRoller(20) + atk;
        if (roll >= pAC) {
            return dmgRollPlayer();
        }
        return 0;
    }

    private int dmgRollPlayer() {
       return diceRoller(this.maxDmg);
    }
}