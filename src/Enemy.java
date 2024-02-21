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
    
    public double getBludgeoningRes() {
        return this.resistances[0];
    }
}