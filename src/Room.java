public class Room {
    public static void main(String[] args) {
        
    }

    private int difficulty;
    private Enemy enemy;
    private Item[] item;
    private Weapon[] weapon;


    //Change to select random enemy and item based on difficulty
    public Room(int difficulty) {
        this.difficulty = difficulty;
        this.enemy = enemy;
        this.item = item;
        this.weapon = weapon;
    }

    public int getDifficulty() {
        return this.difficulty;
    }

    public Enemy getEnemy() {
        return this.enemy;
    }

    public Item[] getItem() {
        return this.item;
    }

    public Weapon[] getWeapon() {
        return weapon;
    }

    

}
