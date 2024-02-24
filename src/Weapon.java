public class Weapon {
    private String name;
    private int level;
    private double baseDamage;
    //calls the DamageType enum to determine type.
    DamageType type;

    //weapon has name buff and level. Constructor will interpret the rest
    //input something like ("sword", 0, SLASHING)
    public Weapon(String name) {
        this.name = name;
        this.level = 0;

        switch (this.name) {
            case "sword" -> this.type = DamageType.SLASHING;
            case "poisoned dagger" -> this.type = DamageType.POISON;
            case "club" -> this.type = DamageType.BLUDGEONING;
            case "spear" -> this.type = DamageType.PIERCING;
            case "fists" -> this.type = DamageType.BLUDGEONING;
            case "torch" -> this.type = DamageType.FIRE;
            case "taser" -> this.type = DamageType.ELECTRICITY;
            case "tuning fork" -> this.type = DamageType.PSYCHIC;
        }
        //define base damage of each weapon type
        if (this.name.equals("fists"))
            this.baseDamage = 20;
            this.level = 1;
        else
            this.baseDamage = 0;
    }

    public String getName() {
        return this.name;
    }
    public int getLevel() {
        return this.level;
    }

    public DamageType getType() {
        return this.type;
    }

    public void levelUp() {
        this.level ++;
        if (this.level == 1) {
            switch (this.name) {
                case "sword" -> this.baseDamage = 80;
                case "poison dagger" -> this.baseDamage = 100;
                case "club" -> this.baseDamage = 40;
                case "spear" -> this.baseDamage = 70;
                case "torch" -> this.baseDamage = 60;
                case "taser" -> this.baseDamage = 70;
                case "tuning fork" -> this.baseDamage = 50;
            }
        }
        else {
            this.baseDamage *= 1.1;
        }
    }

    //will add player armor class when available
    //potentially a player curse could be implemented here by increasing the multiplier of the subtraction
    public double doDamage(Enemy monster, Player user) {
        double roll = Enemy.diceRoller(20) + user.getAtk();
        if (roll >= monster.getAC()) {
            return (this.baseDamage / 20.0) * (Enemy.diceRoller(20) + user.getAtk()) * monster.getRes(this.type);
        }
        return 0;
    }

}
