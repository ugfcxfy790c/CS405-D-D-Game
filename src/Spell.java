public class Spell {

    private final SpellType type;
    private String name;
    private final Player user;
    private boolean used;

    public Spell(SpellType type, Player user) {
        this.used = false;
        this.type = type;
        this.user = user;
        switch (this.type) {
            case WEAKNESS -> this.name = "Spell of Weakness";
            case MADNESS -> this.name = "Mad Wizard's Curse";
            case BLINDING -> this.name = "Blinding Spell";
            case LUCK -> this.name = "Felix Felicis";
        }
    }

    public static Spell spawn(Player user) {
        int number = Enemy.diceRoller(100);
        SpellType nType;
        if (number <= 60) {
            nType = null;
        } else if (number <= 70) {
            nType = SpellType.LUCK;
        } else if (number <= 80) {
            nType = SpellType.BLINDING;
        } else if (number <= 90) {
            nType = SpellType.WEAKNESS;
        } else {
            nType = SpellType.MADNESS;
        }
        return new Spell(nType, user);
    }

    public boolean isUsed() {
        return this.used;
    }

    public String getName() {
        return this.name;
    }

    public void cast(Enemy target) {
        switch (this.type) {
            case WEAKNESS -> target.setMaxDmg(0.6);
            case MADNESS -> target.randomizeRes();
            case BLINDING -> target.blind();
            case LUCK -> this.user.getLucky();
        }
        this.used = true;
    }

}
