import java.util.Random;

public class Enemy {
    
public static void main(String[] args) {

}   
    private String EType;
    private double difficulty;
    private double[] res;
    private double health;
    private double atk;
    private double maxDmg;
    private DamageType type;
    private int aC;
    
    public Enemy(String EType, double difficulty, double[] res, int aC, DamageType type) {
        this.EType = EType;
        this.difficulty = difficulty;
        this.res = res;
        this.health = 5 * Math.pow(difficulty, 2);
        this.atk = 3 * (difficulty / 2);
        this.maxDmg = 5 * difficulty;
        this.aC = aC;
        this.type = type;
    }

    public static int diceRoller(int nSides) {
        Random dice = new Random();
        return dice.nextInt(nSides) + 1;
    }

    public String getEType() {
        return this.EType;
    }

    public double getDiff() {
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

    public double getHealth() {
        return this.health;
    }

    public double getAtk() {
        return this.atk;
    }

    public double getMaxDmg() {
        return this.maxDmg;
    }

    public int getAC() {
        return this.aC;
    }

    public DamageType getType() {
        return this.type;
    }

    public boolean eAttack(int roll) {
        return (roll >= this.aC);
    }

    public void eDamage(int damage) {
        this.health -= damage;
    }

    //Makes an attack roll and does damage to the player if neccesary
    public int dmgPlayer(int pAC) {
        double roll = diceRoller(20) + atk;
        if (roll >= pAC) {
            return dmgRollPlayer();
        }
        return 0;
    }

    private int dmgRollPlayer() {
       return diceRoller((int)this.maxDmg);
    }

    public static Enemy spawnEnemy(Enemy[] eList) {
        return eList[diceRoller(eList.length) - 1];
    }
}