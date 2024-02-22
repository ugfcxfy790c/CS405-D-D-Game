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
        Room.fight(enemyList(Math.sqrt(roomNumber)), player);
    }

    public static Enemy[] enemyList(double difficulty) {
        Enemy e1 = new Enemy("Goblin", difficulty, new double[] {1, 1, 1, 1, 1, 1}, 10);

        Enemy[] eList = new Enemy[] {e1};
        return eList;
    }
}
