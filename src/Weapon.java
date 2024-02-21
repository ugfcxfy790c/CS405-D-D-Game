public class Weapon {
    private String name;
    private int level;
    private int baseDamage;
    //calls the DamageType enum to determine type.
    DamageType type;

    //weapon has name buff and level. Constructor will interpret the rest
    //input something like ("sword", 0, SLASHING)
    public Weapon(String name, int level, DamageType buff) {
        this.name = name;
        this.level = level;
        this.type = buff;
        //define base damage of each weapon type
        if (this.name == "fists")
            this.baseDamage = 20;
        else if (this.name == "sword")
            this.baseDamage = 80;
        else if (this.name == "axe")
            this.baseDamage = 100;
        else if (this.name.equals("club"))
            this.baseDamage = 40;
        else if (this.name == "spear")
            this.baseDamage = 70;
        //change name based on non-basic types
        if (this.type == DamageType.FIRE)
            this.name += " of fire";
        else if (this.type == DamageType.ELECTRICITY)
            this.name += " of electricity";
        else if (this.type == DamageType.POISON)
            this.name = "poisoned " + this.name;
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
    }

    //will add player armor class when available
    //potentially a player curse could be implemented here by increasing the multiplier of the subtraction
    public double doDamage(Enemy monster) {
        int roll = Enemy.diceRoller(20);
        if (roll > monster.getAC()) {
            return (this.baseDamage / 20.0) * Enemy.diceRoller(20) * monster.getRes(this.type);
        }
        return 0;
    }

}
