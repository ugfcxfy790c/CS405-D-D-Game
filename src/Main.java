public class Main {

    public static void main(String[] Args) {
        Player player = new Player(100, 15, 5);
        int gameLength = 30;
        for (int i = 0; i < gameLength; i++) {
            newRoom(i + 1, player);
        }   
        System.out.println("You win!");
    }

    public static void newRoom(int roomNumber, Player player) {
        System.out.println("Room " + roomNumber);
        fight(enemyList(Math.sqrt(roomNumber)), player);
    }

    public static Enemy[] enemyList(double difficulty) {
        Enemy e1 = new Enemy("Goblin", difficulty, new double[] {1, 1, 1, 1, 1, 1}, 13);
        Enemy e2 = new Enemy("Cultist", difficulty, new double[] {1.5, 1, 1, 0, 1, 1}, 10);
        Enemy[] eList = new Enemy[] {e1, e2};
        return eList;
    }

    public static void fight(Enemy[] eList, Player player) {
        Enemy enemy = Enemy.spawnEnemy(eList);
        System.out.println("A " + enemy.getEType() + " emerges from the shadows!");
        while(player.getHealth() > 0 && enemy.getHealth() > 0) {
            //Player's turn function, pass in enemy
            if (enemy.getHealth() >= 0) {
                double damage = enemy.dmgPlayer(player.getAC());
                player.damageToPlayer(damage);
            }
        }

    }
}
