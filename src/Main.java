import java.util.Scanner;
public class Main {

    static Scanner input = new Scanner(System.in);
    
    public static void main(String[] Args) {
        Player player = new Player(100, 15, 5);
        int gameLength = 30;
        for (int i = 0; i < gameLength; i++) {
            if (i == gameLength - 1) {
                finalBoss(player);
            }
            else {
                int randCond = Enemy.diceRoller(100);
                System.out.println("\n");
                roomCondition(player, randCond, false);
                newRoom(i + 1, player);
                roomCondition(player, randCond, true);
            }
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
        //print statement for below already included in addSpell()
        //null condition already included in spawn()
        Spell spe = Spell.spawn(player);
        player.addSpell(spe);
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


        return new Enemy[]{e1, e2, e3, e4, e5, e6, e7, e8, e9, e10, e11, e12, e13, e14, e15, e16};

    }

    public static void fight(Enemy[] eList, Player player, int roomNumber) {
        boolean enrage = false;
        Enemy enemy = Enemy.spawnEnemy(eList);
        int entry = Enemy.diceRoller(5);
        String entMessage;
        if (enemy.getEType().equals("Mr. Cosgrove")) {
            entMessage = " walks slowly into the wroom while cackling maniacally!";
        }
        else if (enemy.getEType() == "Zariel, Archduke of Avernus") {
            entMessage = " charges at you!";
        }
        else if (entry == 1) {
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

        if (enemy.getEType().equals("Sphinx")) {
            System.out.println("Answer me this, and you will be greatly rewarded. Answer wrong, and die painfully! Answer nothing, and I will not be as angry while fighting you.");
            System.out.println("Will you attempt to answer the Sphinx's riddle? (0 if No, 1 if Yes)");
            int riddler = input.nextInt();
            input.nextLine();
            if (riddler == 0) {
                System.out.println("Very well. To battle!");
            }

            else if (riddler == 1) {
                enemy = riddle(enemy, player);
            }
            else {
                invalidReply();
                fight(eList, player, roomNumber);
            }
        }

        if (roomNumber == 1 && enemy.getRes(DamageType.BLUDGEONING) == 0) {
            System.out.println("The gods, noticing that the " + enemy.getEType() + " is immune to your only source of damage, take pity on you.");
            enemy.eDamage(enemy.getHealth());
        }
        while(player.getHealth() > 0 && enemy.getHealth() > 0) {
            System.out.println("Your turn:");
            boolean running = true;
            while (running) {
                System.out.println("Do you want to use a weapon, item, or spell? (WEAPON/ITEM/SPELL/ENRAGE)");
                String choice;
                choice = input.nextLine().toUpperCase();

                switch (choice) {
                    case "ITEM" -> {
                        System.out.println(player.showInventory());
                        if (player.getItemCount() == 0) {
                            System.out.println("You have no items.");
                        } else {
                            int itemSelect = input.nextInt();
                            input.nextLine();
                            Item item = player.getItem(itemSelect);
                            if (item != null) {
                                item.use();
                                System.out.println("You use your " + item.getName() + ".");
                                running = false;
                            }
                            else System.out.println("That's not a valid item.");
                        }
                    }
                    case "WEAPON" -> {
                        System.out.println("Choose your weapon:");
                        System.out.println(player.weaponString());
                        int weaponSelect = input.nextInt();
                        input.nextLine();
                        if (player.getWeapon(weaponSelect).getLevel() > 0) {
                            double dmg = player.damageToEnemy(enemy, weaponSelect);
                            System.out.println("You attack the " + enemy.getEType() + " with your " + player.getWeapon(weaponSelect).getName() + ", doing " + dmg + " damage.");
                            if (enemy.getEType().equals("Mr. Cosgrove") && player.getWeapon(weaponSelect).getType() == DamageType.PSYCHIC) {
                                System.out.println("The mental pain only seems to heal him!");
                            }
                            running = false;
                        }
                        else System.out.println("You don't have that weapon.");
                    }

                    case "SPELL" -> {
                        System.out.println(player.showSpells());
                        if (player.getSpellCount() > 0) {
                            int spellSelect = input.nextInt();
                            input.nextLine();
                            Spell selection = player.getSpell(spellSelect);
                            if (selection != null) {
                                System.out.println("You use " + selection.getName() + ".");
                                selection.cast(enemy);
                                running = false;
                            }
                            else System.out.println("That spell isn't available righ now.");
                        }
                        else System.out.println("You have no available spells right now.");
                    }
                    case "ENRAGE" -> {
                        if (enrage == true) {
                            System.out.println("You won't get any extra rewards for doing this, but if you want to...");
                        }
                        System.out.println("The enemy is furious!!!");
                        enrage = true;
                        enemy = Enemy.enrage(enemy);
                    }
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
                if (enrage) {
                    System.out.println("Because you enraged the enemy, you are rewarded!");
                    rewards(player);
                    rewards(player);
                }
            }

        }
    }

    public static void rewards (Player player){
        boolean i = true;
        while (i) {
            System.out.println("\n" + "Choose a weapon to acquire/level up:");
            System.out.println("Fists, Sword, Poisoned Dagger, Club, Spear, Torch, Taser, Tuning Fork");
            Scanner input = new Scanner(System.in);
            String choice = input.nextLine();
            if (choice.equalsIgnoreCase("FISTS")) {
                player.getWeapon(0).levelUp();
                i = false;
                System.out.println("You have leveled up your Fists!");
            } else if (choice.equalsIgnoreCase("SWORD")) {
                player.getWeapon(1).levelUp();
                i = false;
                System.out.println("You have leveled up your Sword!");
            } else if (choice.equalsIgnoreCase("POISONED DAGGER")) {
                player.getWeapon(2).levelUp();
                i = false;
                System.out.println("You have leveled up your Poisoned Dagger!");
            } else if (choice.equalsIgnoreCase("CLUB")) {
                player.getWeapon(3).levelUp();
                i = false;
                System.out.println("You have leveled up your Club!");
            } else if (choice.equalsIgnoreCase("SPEAR")) {
                player.getWeapon(4).levelUp();
                i = false;
                System.out.println("You have leveled up your Spear!");
            } else if (choice.equalsIgnoreCase("TORCH")) {
                player.getWeapon(5).levelUp();
                i = false;
                System.out.println("You have leveled up your Torch!");
            } else if (choice.equalsIgnoreCase("TASER")) {
                player.getWeapon(6).levelUp();
                System.out.println("You have leveled up your Taser!");
                i = false;
            } else if (choice.equalsIgnoreCase("TUNING FORK")) {
                player.getWeapon(7).levelUp();
                System.out.println("You have leveled up your Tuning Fork!");
                i = false;
            }
            else {
                System.out.println("Invalid weapon.");
            }
        }

    }

    public static void roomCondition(Player player, int rand, boolean condition) {
        if (rand <= 50) {
            if (!condition) {
                System.out.println("You enter a boring square room...");
            }
        }
        else if (rand <= 60) {
            if (!condition) {
                System.out.println("You enter a room so dark you can barely see...");
                player.addAtk(-2);
            }
            else {
                player.addAtk(2);
            }
        }
        else if (rand <= 70) {
            if (!condition) {
                System.out.println("You enter a room with muddy ground. Your movement is restricted...");
                player.addAC(-2);
            }
            else {
                player.addAC(2);
            }
        }
        else if (rand <= 80) {
            System.out.println("As you enter the room, you accidentally step on a glyph of warding...");
            player.damageToPlayer(5, null);
        }
        else if (rand <= 90) {
            if (!condition) {
                System.out.println("As you enter the room, you feel inspired...");
                player.addAC(1);
                player.addAtk(1);
            }
            else {
                player.addAC(-1);
                player.addAtk(-1);
            }
        }
        else {
            if (!condition) {
                System.out.println("In the next room, you find a wacky potion on the floor. You drink it without hesitation...");
                player.setRes(DamageType.BLUDGEONING, (Enemy.diceRoller(100) - 50) / 100.0);
     
                player.setRes(DamageType.PIERCING, (Enemy.diceRoller(100) - 50) / 100.0);
                player.setRes(DamageType.SLASHING, (Enemy.diceRoller(100) - 50) / 100.0);
                player.setRes(DamageType.FIRE, (Enemy.diceRoller(100) - 50) / 100.0);
                player.setRes(DamageType.ELECTRICITY, (Enemy.diceRoller(100) - 50) / 100.0);
                player.setRes(DamageType.POISON, (Enemy.diceRoller(100) - 50) / 100.0);
                player.setRes(DamageType.PSYCHIC, (Enemy.diceRoller(100) - 50) / 100.0);
            }
        }

    }

    public static Enemy riddle(Enemy sphinx, Player player) {
        int riddle = Enemy.diceRoller(5);
        String answer;
        if (riddle == 1) {
            System.out.println("I am 3/7 chicken, 2/3 cat, and 2/4 goat. What am I?");
            answer = "CHICAGO";
        }
        else if (riddle == 2) {
            System.out.println("The more you take, the more you leave behind. What am I?");
            answer = "FOOTSTEPS";
        }
        else if (riddle == 3) {
            System.out.println("I always stand before you, but can never be seen. What am I?");
            answer = "FUTURE";
        }
        else if (riddle == 4) {
            System.out.println("I am a tree you can hold in your hand. What am I?");
            answer = "PALM";
        }
        else {
            System.out.println("Who makes me has no need of me. Who buys me has no use for me. Who uses me never sees nor feels me. What am I?");
            answer = "COFFIN";
        }
        System.out.println("Respond, or Die! (Responce should be one word)");
        if (input.nextLine().toUpperCase().equals(answer)) {
            System.out.println("Very Clever! You may take your reward and go.");
            System.out.println("Your armor has been strengthened!");
            player.addAC(2);
            sphinx.eDamage(sphinx.getHealth());
        }
        else {
            System.out.println("Fool!");
            sphinx = Enemy.enrage(sphinx);
        }
        return sphinx;
    }


    public static void finalBoss(Player player) {
        System.out.println("You enter a throne room. Your journey is almost over...");
        Enemy boss = new Enemy("Zariel, Archduke of Avernus", 10, new double[] {0.5, 0.5, 0.5, 0, 0.5, 0.5, 0.5}, 16, DamageType.FIRE);
        fight(new Enemy[] {boss}, player, 10);
    }

    public static void invalidReply(){
        System.out.println("Invalid reply!!  ");

    }
}