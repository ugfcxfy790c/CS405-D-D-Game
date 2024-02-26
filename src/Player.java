public class Player {

    private double health;
    private Weapon[] weapons;
    private Item[] inventory;
    private int aC;
    private double atk;
    private double[] res;
    private int itemCount;
    private int spellCount;
    private boolean lucky;
    private Spell[] spells;


    public Player(double health, int aC, int atk) {
        this.spellCount = 0;
        this.lucky = false;
        this.health = health;
        this.aC = aC;
        this.atk = atk;
        this.res = new double [] {1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0};
        this.inventory = new Item[15];
        this.spells = new Spell[5];
        this.weapons = new Weapon[8];
        this.weapons[0] = new Weapon("fists");
        this.weapons[1] = new Weapon("sword");
        this.weapons[2] = new Weapon("poison dagger");
        this.weapons[3] = new Weapon("club");
        this.weapons[4] = new Weapon("spear");
        this.weapons[5] = new Weapon("torch");
        this.weapons[6] = new Weapon("taser");
        this.weapons[7] = new Weapon("tuning fork");
    }


    public int getAC() {
        return this.aC;
    }

    public void getLucky() {
        this.lucky = true;
    }

    public int getItemCount() {return this.itemCount;}

    public void addAC(int mod) {
        this.aC+= mod;
    }

    public double getAtk() {
        return this.atk;
    }

    public void addAtk(int mod) {
        this.atk += mod;
    }

    public double getHealth(){
        return this.health;
    }

    public void updateInventory() {
        for (int i = 0; i < this.inventory.length; i ++) {
            if (this.inventory[i] != null) {
                this.inventory[i].update();
                if (this.inventory[i].isExpended()) this.inventory[i] = null;
            }
        }
        for (Spell spell : this.spells) {
            if (spell != null && !spell.isCharged()) {
                spell.charge();
            }
        }
    }

    public int getSpellCount() {
        return this.spellCount;
    }

    public double getRes(DamageType type) {
        if (type == DamageType.BLUDGEONING) {
            return this.res[0];
        }
        else if (type == DamageType.PIERCING) {
            return this.res[1];
        }
        else if (type == DamageType.SLASHING) {
            return this.res[2];
        }
        else if (type == DamageType.FIRE) {
            return this.res[3];
        }
        else if (type == DamageType.ELECTRICITY) {
            return this.res[4];
        }
        else if (type == DamageType.POISON) {
            return this.res[5];
        }
        else if (type == DamageType.PSYCHIC) {
            return this.res[6];
        }
        return 1.0;
    }

    public void setRes(DamageType type, double additor) {
        if (type == DamageType.BLUDGEONING) {
            this.res[0] -= additor;
        }
        else if (type == DamageType.PIERCING) {
            this.res[1] -= additor;
        }
        else if (type == DamageType.SLASHING) {
            this.res[2] -= additor;
        }
        else if (type == DamageType.FIRE) {
            this.res[3] -= additor;
        }
        else if (type == DamageType.ELECTRICITY) {
            this.res[4] -= additor;
        }
        else if (type == DamageType.POISON) {
            this.res[5] -= additor;
        }
        else if (type == DamageType.PSYCHIC) {
            this.res[6] -= additor;
        }
    }

    public double damageToEnemy(Enemy enemy, int index) {
        double damage = weapons[index].doDamage(enemy, this, this.lucky);
        enemy.eDamage(damage);
        return damage;
    }

    public String weaponString() {
        String print = "";
        for (int i = 0; i < this.weapons.length; i ++) {
            if (this.weapons[i] != null && this.weapons[i].getLevel() > 0) {
                print += i + ":  " + this.weapons[i].getName() + "  ";
            }
        }
        return print;
    }

    public Weapon getWeapon(int index) {
        return this.weapons[index];
    }

    public Item getItem(int index) { return this.inventory[index]; }

    public Spell getSpell(int index) { return this.spells[index]; }

    public String showInventory() {
        String print = "";
        this.itemCount = 0;
        for (int i = 0; i < this.inventory.length; i ++) {
            if (this.inventory[i] != null && !this.inventory[i].isActive()) {
                print += i + ":  " + this.inventory[i].getName() + "  ";
                this.itemCount ++;
            }
        }
        return print;
    }

    public String showSpells() {
        this.spellCount = 0;
        String print= "";
        for (int i = 0; i < this.spells.length; i++) {
            if (this.spells[i] != null && this.spells[i].isCharged()) {
                print += i + ": " + this.spells[i].getName() + "    ";
                this.spellCount++;
            }
        }
        return print;
    }

    public void addToInventory(Item item) {
        for (int i = 0; i < this.inventory.length; i++) {
            if (this.inventory[i] == null) {
                this.inventory[i] = item;
                break;
            }
        }
    }

    public void addSpell(Spell spell) {
        for (int i = 0; i < this.spells.length; i++) {
            if (this.spells[i] == null) {
                this.spells[i] = spell;
                if (spell != null) System.out.println("You unlocked " + spell.getName() + ".");
                break;
            }
        }
    }

    public double damageToPlayer(double damage, DamageType type) {
        double dmg = (damage*getRes(type));
        this.health -= dmg;
        return dmg;
    }

}