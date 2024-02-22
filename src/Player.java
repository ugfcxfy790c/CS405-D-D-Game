//Hello 

public class Player{

    private double health;
    private Weapon[] weapons;
    private Item[] inventory;
    private int aC;
    private int atk;
    private double[] resistances;

    public Player(double health, int aC, int atk) {
        this.health = health;
        this.aC = aC;
        this.atk = atk;
        this.resistances = new double[] {1.0, 1.0, 1.0, 1.0, 1.0, 1.0};
    }

    public int getAC() {
        return this.aC;
    }

    public int getAtk() {
        return this.atk;
    }
}