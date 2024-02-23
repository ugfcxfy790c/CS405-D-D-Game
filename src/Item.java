public class Item {
    //potions: healing, resistance,
    private String name;
    private DamageType type;
    //multiplier for resistance, addition for HEALTH
    private double power;
    private int uses;

    public Item(DamageType type, double power) {
        this.type = type;
        this.power = power;
        this.uses = 0;

        switch (this.type) {
            case DamageType.BLUDGEONING -> this.name = "potion of bludgeoning resistance";
            case DamageType.ELECTRICITY -> this.name = "potion of electricity resistance";
            case DamageType.FIRE -> this.name = "potion of fire resistance";
            case DamageType.POISON -> this.name = "potion of immunity";
            case DamageType.SLASHING -> this.name = "chain mail armor";
            case DamageType.PIERCING -> this.name = "shield";
            case DamageType.HEALTH -> this.name = "potion of healing";
            case DamageType.INVINCIBILITY -> this.name = "spell of invincibility";
            case DamageType.POWER -> this.name = "ring of power";
        }
    }

    public DamageType getType() {
        return this.type;
    }

    public double getPower() {
        return this.power;
    }

    public void use(Player user) {
        switch (this.type) {
            case DamageType.BLUDGEONING -> user.setRes(DamageType.BLUDGEONING, 0.2);
            case DamageType.ELECTRICITY -> user.setRes(DamageType.ELECTRICITY, 0.2);
            case DamageType.FIRE -> user.setRes(DamageType.FIRE, 0.2);
            case DamageType.POISON -> user.setRes(DamageType.POISON, 0.2);
            case DamageType.SLASHING -> user.setRes(DamageType.SLASHING, 0.2);
            case DamageType.PIERCING -> user.setRes(DamageType.PIERCING, 0.2);
            case DamageType.HEALTH -> user.damageToPlayer(-20);
            case DamageType.INVINCIBILITY -> user.addAC();
            case DamageType.POWER -> user.addAtk();
        }
    }

}
