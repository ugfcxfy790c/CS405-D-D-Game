public class Spell {

    private final SpellType type;
    private String name;
    private final Player user;
    private int charge;

    public Spell(SpellType type, Player user) {
        this.charge = 4;
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
            return null;
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

    public String getName() {
        return this.name;
    }

    public void charge() {
        this.charge ++;
    }

    public boolean isCharged() {
        return this.charge == 4;
    }

    public void cast(Enemy target) {
        if (this.charge < 4) return;
        switch (this.type) {
            case WEAKNESS -> target.setMaxDmg(0.6);
            case MADNESS -> target.randomizeRes();
            case BLINDING -> target.blind();
            case LUCK -> this.user.getLucky();
        }
        this.charge = 0;
    }
}
