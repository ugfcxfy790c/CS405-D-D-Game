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

    public static void fight(Enemy[] eList, Player player) {
        Enemy enemy = Enemy.spawnEnemy(eList);
        System.out.println("A " + enemy.getEType() + " emerges from the shadows!");
        while(player.getHealth() > 0 && enemy.getHealth() > 0) {
            //Player's turn function, pass in enemy
            if (enemy.getHealth() <= 0) {
                double damage = enemy.dmgPlayer(player.getAC());
                player.damageToPlayer(damage);
            }
        }

    }
}
