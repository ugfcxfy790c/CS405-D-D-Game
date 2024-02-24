import java.util.Random;

public class Item {
    //potions: healing, resistance,
    private String name;
    private final DamageType type;
    private int uses;
    private boolean active;
    private boolean expended;

    private final Player user;

    public Item(DamageType type, Player user) {
        this.type = type;
        this.uses = 0;
        this.active = false;
        this.user = user;
        this.expended = false;

        switch (this.type) {
            case BLUDGEONING -> this.name = "potion of bludgeoning resistance";
            case ELECTRICITY -> this.name = "potion of electricity resistance";
            case FIRE -> this.name = "potion of fire resistance";
            case POISON -> this.name = "potion of immunity";
            case SLASHING -> this.name = "chain mail armor";
            case PIERCING -> this.name = "shield";
            case HEALTH -> this.name = "potion of healing";
            case INVINCIBILITY -> this.name = "spell of invincibility";
            case POWER -> this.name = "ring of power";
        }
    }

    public static Item spawn(Player user) {
        Random random = new Random();
        double randnum = random.nextDouble() * 90;
        DamageType nType;
        if (randnum < 11) {
            nType = DamageType.BLUDGEONING;
        } else if (randnum >= 11 && randnum < 22) {
            nType = DamageType.SLASHING;
        } else if (randnum >= 22 && randnum < 33) {
            nType = DamageType.PIERCING;
        } else if (randnum >= 33 && randnum < 43) {
            nType = DamageType.ELECTRICITY;
        } else if (randnum >= 43 && randnum < 53) {
            nType = DamageType.FIRE;
        } else if (randnum >= 53 && randnum < 63) {
            nType = DamageType.POISON;
        } else if (randnum >= 63 && randnum < 80) {
            nType = DamageType.HEALTH;
        } else if (randnum >= 80 && randnum < 85) {
            nType = DamageType.INVINCIBILITY;
        } else if (randnum >= 85 && randnum < 90) {
            nType = DamageType.POWER;
        }
        else nType = DamageType.HEALTH;
        return new Item(nType, user);
    }

    public String getName() {
        return this.name;
    }

    public boolean isExpended() {
        return this.expended;
    }

    public boolean isActive() {
        return this.active;
    }

    public void use() {
        if (this.active) {
            return;
        }
        this.active = true;
        switch (this.type) {

            case BLUDGEONING -> this.user.setRes(DamageType.BLUDGEONING, 0.2);
            case ELECTRICITY -> this.user.setRes(DamageType.ELECTRICITY, 0.2);
            case FIRE -> this.user.setRes(DamageType.FIRE, 0.2);
            case POISON -> this.user.setRes(DamageType.POISON, 0.2);
            case SLASHING -> this.user.setRes(DamageType.SLASHING, 0.2);
            case PIERCING -> this.user.setRes(DamageType.PIERCING, 0.2);
            case HEALTH -> {
                this.user.damageToPlayer(-20.0, DamageType.HEALTH);

                this.uses = 3;
            }
            case INVINCIBILITY -> {
                this.user.addAC();
                this.uses = 3;
            }
            case POWER -> {
                this.user.addAtk();
                this.uses = 3;
            }
        }
    }

    public void update() {
        if (this.active) this.uses++;
        if (this.uses >= 3) {
            switch (this.type) {
                case BLUDGEONING -> user.setRes(DamageType.BLUDGEONING, -0.2);
                case ELECTRICITY -> user.setRes(DamageType.ELECTRICITY, -0.2);
                case FIRE -> user.setRes(DamageType.FIRE, -0.2);
                case POISON -> user.setRes(DamageType.POISON, -0.2);
                case SLASHING -> user.setRes(DamageType.SLASHING, -0.2);
                case PIERCING -> user.setRes(DamageType.PIERCING, -0.2);
            }
            this.expended = true;
        }
    }

}
