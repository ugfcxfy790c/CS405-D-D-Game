public class Enemy {
    
public static void main(String[] args) {
    System.out.println();
}

    private String Type;
    private int difficulty;
    private double[] resistances;
    private int health;
    private int atk;
    private int maxDmg;
    
    public Enemy(String Type, int difficulty, double[] resistances, int health, int atk, int maxDmg) {
        this.Type = Type;
        this.difficulty = difficulty;
        this.resistances = resistances;
        this.health = health;
        this.atk = atk;
        this.maxDmg = maxDmg;
    }


        
}