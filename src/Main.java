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
        Enemy e1 = new Enemy("Goblin", difficulty, new double[] {1, 1, 1, 1, 1, 1}, 13, DamageType.PIERCING);
        Enemy e2 = new Enemy("Cultist", difficulty, new double[] {1, 1.5, 1, 0, 1, 1}, 10, DamageType.SLASHING);
        Enemy e3 = new Enemy("Skeleton", difficulty, new double[] {5, 0, 0.5, 1, 0.5, 0}, 12, DamageType.BLUDGEONING);
        Enemy e4 = new Enemy("Dragon Goddess Tiamat", difficulty, new double[] {1, 1.5, 1, 0, 0, 0}, 18, DamageType.FIRE);
        Enemy e5 = new Enemy("Beholder", difficulty, new double[] {1, 0.5, 0.5, 1, 2, 1}, 15, DamageType.ELECTRICITY);
        Enemy e6 = new Enemy("Obirith", difficulty, new double[] {0, 1, 0, 1.5, 1, 2}, 14, DamageType.POISON);
        Enemy e7 = new Enemy("Mad Wizard", difficulty, new double[] {Enemy.diceRoller(100)/70, Enemy.diceRoller(100)/70, Enemy.diceRoller(100)/70, Enemy.diceRoller(100)/70, Enemy.diceRoller(100)/70, Enemy.diceRoller(100)/70}, 9, DamageType.FIRE);
        Enemy e8 = new Enemy("Storm Giant", difficulty, new double[] {1, 2, 1, 1, 0, 0.5}, 16, DamageType.ELECTRICITY);
        Enemy e9 = new Enemy("Wraith", difficulty, new double[] {0.5, 1, 0, 1, 3, 0}, 12, DamageType.POISON);
        Enemy e10 = new Enemy("Red Slaad", difficulty, new double[] {1, 0.5, 0.75, 0, 2, 1.5}, 12, DamageType.SLASHING);
        Enemy e11 = new Enemy("Blue Slaad", difficulty, new double[] {1, 0.5, 0.75, 2, 0, 1.5}, 12, DamageType.SLASHING);
        Enemy e12 = new Enemy("Terminator", difficulty, new double[] {0.5, 1, 0, 1.5, 2.5, 0}, 19, DamageType.BLUDGEONING);
        Enemy e13 = new Enemy("Mr. Cosgrove", difficulty, new double[] {2, 2, 2, 2, 0, 2}, 21, DamageType.POISON);
        Enemy e14 = new Enemy("Sphinx", difficulty, new double[] {1, 1, 2, 0.25, 0.5, 1}, 14, DamageType.PIERCING);
        Enemy e15 = new Enemy("Atropal", difficulty, new double[] {0, 1.5, 0, 1, 0, 2}, 8, DamageType.FIRE);
        Enemy e16 = new Enemy("Baby Tarasque", difficulty, new double[] {0.5, 0.5, 0.5, 0.1, 5, 0.1}, 15, DamageType.SLASHING);

        Enemy[] eList = new Enemy[] {e1, e2, e3, e4, e5, e6, e7, e8, e9, e10, e11, e12, e13, e14, e15, e16};
        return eList;
    }

    public static void fight(Enemy[] eList, Player player) {
        Enemy enemy = Enemy.spawnEnemy(eList);
        System.out.println("A " + enemy.getEType() + " emerges from the shadows!");
        while(player.getHealth() > 0 && enemy.getHealth() > 0) {
            //Player's turn function, pass in enemy
            if (enemy.getHealth() >= 0) {
                double damage = enemy.dmgPlayer(player.getAC());
                player.damageToPlayer(damage, enemy.getType());
            }
        }
        if (player.getHealth() <= 0) {
            System.out.println("You are dead...");
            //System.exit(0);
        }
        if (enemy.getHealth() <= 0) {
            System.out.println("The " + enemy.getEType() + " has been slain!");
        }

    }
}
