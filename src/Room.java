public class Room {
    public static void main(String[] args) {

    }

    private int difficulty;
    private Item[] item;


    //Change to select random enemy and item based on difficulty
    public Room(int difficulty, Item[] item) {
        this.difficulty = difficulty;
        this.item = item;
    }

    public int getDifficulty() {
        return this.difficulty;
    }

    public Item[] getItem() {
        return this.item;
    }

    public static void fight(Enemy[] eList) {
        Enemy enemy = Enemy.spawnEnemy(eList);
        System.out.println("A " + enemy.getEType() + " emerges from the shadows!");
    }
}
