import java.util.Random;

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
        int roll = Enemy.diceRoller(8);
        return (this.baseDamage / 8.0) * roll * monster.getRes(this.type);
    }

}
