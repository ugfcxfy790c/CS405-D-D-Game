import java.util.Scanner;
public class Main {

    static Scanner input = new Scanner(System.in);

    public static void main(String[] Args) {
        Player player = new Player(100, 15, 5);
        int gameLength = 30;
        for (int i = 0; i < gameLength; i++) {
            newRoom(i + 1, player);
        }
        System.out.println("You win!");
    }

    public static void newRoom(int roomNumber, Player player) {
        System.out.println("\nRoom " + roomNumber);
        fight(enemyList(Math.sqrt(roomNumber)), player, roomNumber);
        player.updateInventory();
        Item ite = Item.spawn(player);
        player.addToInventory(ite);
        System.out.println("You found a " + ite.getName() + " on the ground!");
        rewards(player);


    }

    public static Enemy[] enemyList(double difficulty) {

        Enemy e1 = new Enemy("Goblin", difficulty, new double[] {1, 1, 1, 1, 1, 1, 1}, 13, DamageType.PIERCING);
        Enemy e2 = new Enemy("Cultist", difficulty, new double[] {1, 1.5, 1, 0, 1, 1, 1.5}, 10, DamageType.SLASHING);
        Enemy e3 = new Enemy("Skeleton", difficulty, new double[] {5, 0, 0.5, 1, 0.5, 0, 1}, 12, DamageType.BLUDGEONING);
        Enemy e4 = new Enemy("Dragon Goddess Tiamat", difficulty, new double[] {1, 1.5, 1, 0, 0, 0, 1}, 18, DamageType.FIRE);
        Enemy e5 = new Enemy("Beholder", difficulty, new double[] {0.5, 1.5, 0.5, 1, 2, 1, 0}, 15, DamageType.PSYCHIC);
        Enemy e6 = new Enemy("Obirith", difficulty, new double[] {0, 1, 0, 1.5, 1, 2, 1.5}, 14, DamageType.POISON);
        Enemy e7 = new Enemy("Mad Wizard", difficulty, new double[] {Enemy.diceRoller(100)/70.0, Enemy.diceRoller(100)/70.0, Enemy.diceRoller(100)/70.0, Enemy.diceRoller(100)/70.0, Enemy.diceRoller(100)/70.0, Enemy.diceRoller(100)/70.0, Enemy.diceRoller(100) / 70.0}, 9, DamageType.FIRE);
        Enemy e8 = new Enemy("Storm Giant", difficulty, new double[] {1, 2, 1, 1, 0, 0.5, 1}, 16, DamageType.ELECTRICITY);
        Enemy e9 = new Enemy("Wraith", difficulty, new double[] {0.5, 1, 0, 1, 3, 0, 1}, 12, DamageType.POISON);
        Enemy e10 = new Enemy("Red Slaad", difficulty, new double[] {1, 0.5, 0.75, 0, 2, 1.5, 0}, 12, DamageType.SLASHING);
        Enemy e11 = new Enemy("Blue Slaad", difficulty, new double[] {1, 0.5, 0.75, 2, 0, 1.5, 0}, 12, DamageType.SLASHING);
        Enemy e12 = new Enemy("Terminator", difficulty, new double[] {0.5, 1, 0, 1.5, 2.5, 0, 0}, 19, DamageType.BLUDGEONING);
        Enemy e13 = new Enemy("Mr. Cosgrove", difficulty, new double[] {2, 2, 2, 2, 0, 2, -3}, 18, DamageType.PSYCHIC);
        Enemy e14 = new Enemy("Sphinx", difficulty, new double[] {1, 1, 2, 0.25, 0.5, 1, 2}, 14, DamageType.PSYCHIC);
        Enemy e15 = new Enemy("Atropal", difficulty, new double[] {0, 1.5, 0, 1, 0, 2, 0.75}, 8, DamageType.FIRE);
        Enemy e16 = new Enemy("Baby Tarasque", difficulty, new double[] {0.5, 0.5, 0.5, 0.1, 5, 0.1, 1}, 15, DamageType.SLASHING);


        //return new Enemy[]{e1, e2, e3, e4, e5, e6, e7, e8, e9, e10, e11, e12, e13, e14, e15, e16};
        return new Enemy[] {e7};
    }

    public static void fight(Enemy[] eList, Player player, int roomNumber) {
        Enemy enemy = Enemy.spawnEnemy(eList);
        int entry = Enemy.diceRoller(5);
        String entMessage;
        if (entry == 1) {
            entMessage = " emerges from the shadows!";
        } else if (entry == 2) {
            entMessage = " enters the room and screams!";
        } else if (entry == 3) {
            entMessage = " is released from storage into the room!";
        } else if (entry == 4) {
            entMessage = " drops down from the ceiling!";
        } else {
            entMessage = " pops into existence!";
        }
        System.out.println("A " + enemy.getEType() + entMessage);

        if (roomNumber == 1 && enemy.getRes(DamageType.BLUDGEONING) == 0) {
            System.out.println("The gods, noticing that the " + enemy.getEType() + " is immune to your only source of damage, take pity on you.");
            enemy.eDamage(enemy.getHealth());
        }
        while(player.getHealth() > 0 && enemy.getHealth() > 0) {
            System.out.println("Your turn:");
            boolean running = true;
            while (running) {
                System.out.println("Do you want to use a weapon or an item? (WEAPON/ITEM)");
                String choice;
                choice = input.nextLine().toUpperCase();
                if (choice.equals("ITEM")) {
                    System.out.println(player.showInventory());
                    if (player.getItemCount() == 0) {
                        System.out.println("You have no items.");
                    } else {
                        int itemSelect = input.nextInt();
                        input.nextLine();
                        Item item = player.getItem(itemSelect);
                        item.use();
                        System.out.println("You use your " + item.getName() + ".");
                        running = false;
                    }
                } else if (choice.equals("WEAPON")) {
                    System.out.println("Choose your weapon:");
                    System.out.println(player.weaponString());
                    int weaponSelect = input.nextInt();
                    input.nextLine();
                    double dmg = player.damageToEnemy(enemy, weaponSelect);
                    System.out.println("You attack the " + enemy.getEType() + " with your " + player.getWeapon(weaponSelect).getName() + ", doing " + dmg + " damage.");
                    if (enemy.getEType() == "Mr. Cosgrove" && player.getWeapon(weaponSelect).getType() == DamageType.PSYCHIC) {
                        System.out.println("The mental pain only seems to heal him!");
                    }
                    running = false;
                }
            }

            if (enemy.getHealth() >= 0) {
                if (enemy.getHealth() >= 0) {
                    double damage = player.damageToPlayer(enemy.dmgPlayer(player.getAC()), enemy.getType());
                    System.out.println("The " + enemy.getEType() + " attacks, doing " + damage + " " + enemy.getType() + " " + "damage.");
                    System.out.println("You have " + player.getHealth() + " health left");
                }
            }
            if (player.getHealth() <= 0) {
                System.out.println("You are dead...");
                System.exit(0);
            }
            if (enemy.getHealth() <= 0) {
                System.out.println("The " + enemy.getEType() + " has been slain!");
            }

        }
    }

    public static void rewards (Player player){
        System.out.println("\n" + "Choose a weapon to acquire/level up:");
        System.out.println("Fists, Sword, Poisoned Dagger, Club, Spear, Torch, Taser, Tuning Fork");
        Scanner input = new Scanner(System.in);
        String choice = input.nextLine();
        if (choice.toUpperCase().equals("FISTS")) {
            player.getWeapon(0).levelUp();
            System.out.println("You have leveled up your Fists!");
        }
        if (choice.toUpperCase().equals("SWORD")) {
            player.getWeapon(1).levelUp();
            System.out.println("You have leveled up your Sword!");
        }
        if (choice.toUpperCase().equals("POISONED DAGGER")) {
            player.getWeapon(2).levelUp();
            System.out.println("You have leveled up your Poisoned Dagger!");
        }
        if (choice.toUpperCase().equals("CLUB")) {
            player.getWeapon(3).levelUp();
            System.out.println("You have leveled up your Club!");
        }
        if (choice.toUpperCase().equals("SPEAR")) {
            player.getWeapon(4).levelUp();
            System.out.println("You have leveled up your Spear!");
        }
        if (choice.toUpperCase().equals("TORCH")) {
            player.getWeapon(5).levelUp();
            System.out.println("You have leveled up your Torch!");
        }
        if (choice.toUpperCase().equals("TASER")) {
            player.getWeapon(6).levelUp();
            System.out.println("You have leveled up your Taser!");
        }
        if (choice.toUpperCase().equals("TUNING FORK")) {
            player.getWeapon(7).levelUp();
            System.out.println("You have leveled up your Tuning Fork!");
        }
    }

}