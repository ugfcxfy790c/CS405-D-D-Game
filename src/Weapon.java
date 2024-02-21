public class Weapon {
    private String name;
    private int level;
    private int baseDamage;
    //calls the DamageType enum to determine type.
    DamageType type;

    //weapon has name buff and level. Constructor will interpret the rest
    //input something like ("sword", 0, SLASHING)
    public Weapon(String name) {
        this.name = name;
        this.level = 0;

        if (this.name == "sword")
            this.type = DamageType.SLASHING;
        else if (this.name == "poisoned dagger")
            this.type = DamageType.POISON;
        else if (this.name.equals("club"))
            this.type = DamageType.BLUDGEONING;
        else if (this.name == "spear")
            this.type = DamageType.PIERCING;
        else if (this.name == "fists")
            this.type = DamageType.BLUDGEONING;
        else if (this.name == "torch")
            this.type = DamageType.FIRE;
        else if (this.name == "taser")
            this.type = DamageType.ELECTRICITY;
        //define base damage of each weapon type
        if (this.name == "fists")
            this.baseDamage = 20;
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
            if (this.name == "sword")
                this.baseDamage = 80;
            else if (this.name == "poison dagger")
                this.baseDamage = 100;
            else if (this.name.equals("club"))
                this.baseDamage = 40;
            else if (this.name == "spear")
                this.baseDamage = 70;
            else if (this.name == "torch")
                this.baseDamage = 60;
            else if (this.name == "taser")
                this.baseDamage = 70;
        }
        else {
            if (this.name == "sword")
                this.baseDamage *= 1.1;
            else if (this.name == "poison dagger")
                this.baseDamage *= 1.1;
            else if (this.name.equals("club"))
                this.baseDamage *= 1.1;
            else if (this.name == "spear")
                this.baseDamage *= 1.1;
            else if (this.name == "torch")
                this.baseDamage *= 1.1;
            else if (this.name == "taser")
                this.baseDamage *= 1.1;
        }
    }

    //will add player armor class when available
    //potentially a player curse could be implemented here by increasing the multiplier of the subtraction
    public double doDamage(Enemy monster, Player user) {
        int roll = Enemy.diceRoller(20) + user.getAtk();
        if (roll > monster.getAC()) {
            return (this.baseDamage / 20.0) * (Enemy.diceRoller(20) + user.getAtk()) * monster.getRes(this.type);
        }
        return 0;
    }

}
