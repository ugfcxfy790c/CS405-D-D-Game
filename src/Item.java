public class Item {
    //potions: healing, resistance,
    private String name;
    private DamageType type;
    //multiplier for resistance, addition for HEALTH
    private double power;

    public Item(DamageType type, double power) {
        this.type = type;
        this.power = power;

        switch (this.type) {
            case DamageType.BLUDGEONING -> this.name = "potion of bludgeoning resistance";
            case DamageType.ELECTRICITY -> this.name = "potion of electricity resistance";
            case DamageType.FIRE -> this.name = "potion of fire resistance";
            case DamageType.POISON -> this.name = "potion of immunity";
            case DamageType.SLASHING -> this.name = "chainmail armor";
            case DamageType.PIERCING -> this.name = "shield";
            case DamageType.HEALTH -> this.name = "potion of healing";
        }
    }
}
