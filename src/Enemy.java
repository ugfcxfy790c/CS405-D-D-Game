import java.util.Random;

public class Enemy {
    
public static void main(String[] args) {

}   
    private final String EType;
    private final String introductor;
    private final double difficulty;
    private double[] res;
    private double health;
    private double atk;
    private double maxDmg;
    private final DamageType type;
    private int aC;
    private boolean blinded;
    private boolean the;
    static Random dice = new Random();
    
    public Enemy(String intro, boolean the, String EType, double difficulty, double[] res, int aC, DamageType type) {
        this.the = the;
        this.introductor = intro;
        this.blinded = false;
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
        else if (type == DamageType.PSYCHIC) {
            return this.res[6];
        }
        return 1.0;
    }

    public void randomizeRes() {
        for (int i = 0; i < this.res.length; i++) {
            this.res[i] = (dice.nextDouble() * 2) + 0.1;
        }
    }

    public double getHealth() {
        return this.health;
    }

    public double getAtk() {
        return this.atk;
    }

    public boolean usesThe() { return this.the; }

    public void blind() {
        this.blinded = true;
    }

    public String introduce() { return this.introductor; }

    public void setMaxDmg(double multiplier) {
        this.maxDmg *= multiplier;
    }

    public int getAC() {
        return this.aC;
    }

    public DamageType getType() {
        return this.type;
    }


    public void eDamage(double damage) {
        this.health -= damage;
    }

    //Makes an attack roll and does damage to the player if neccesary
    public int dmgPlayer(int pAC) {
        double roll = diceRoller(20) + atk;
        if (this.blinded) roll -= this.atk;
        if (roll >= pAC) {
            return dmgRollPlayer();
        }
        return 0;
    }

    private int dmgRollPlayer() {
       return diceRoller((int)this.maxDmg) + (int)this.atk;
    }

    public static Enemy spawnEnemy(Enemy[] eList) {
        return eList[diceRoller(eList.length) - 1];
    }

    public static Enemy enrage(Enemy enemy) {
        enemy.health *= 10;
        enemy.atk += 4;
        enemy.aC += 3;
        return enemy;
    }
}