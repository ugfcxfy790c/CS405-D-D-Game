public class Enemy {
    
public static void main(String[] args) {

}   

    private String EType;
    private int difficulty;
    private double[] res;
    private int health;
    private int atk;
    private int maxDmg;
    
    public Enemy(String EType, int difficulty, double[] res, int health, int atk, int maxDmg) {
        this.EType = EType;
        this.difficulty = difficulty;
        this.res = res;
        this.health = health;
        this.atk = atk;
        this.maxDmg = maxDmg;
    }

    public String getEType() {
        return this.EType;
    }

    public int getDiff() {
        return this.difficulty;
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
        else {
            return this.res[5];
        }

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
}