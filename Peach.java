import java.util.Random;

public class Peach extends Adventurer {
    int starsMax, stars;

    public Peach(String Input){
        super(Input, 14);
        starsMax = 3;
        stars = starsMax;
    }

    public Peach(){
        super("Peach", 14);
        starsMax = 3;
        stars = starsMax;
    }

    public String getSpecialName(){
        return "stars";
    }

    public int getSpecial(){
        return stars;
    }

    public int getSpecialMax(){
        return starsMax;
    }

    public void setSpecial(int n){
        stars = n;
    }

    public String attack(Adventurer other){
        int damage = (int) (Math.random() + 1);
        other.applyDamage(damage);
        return this.getName() + " pierces " + other.getName() + " with her colorful bright pink parasol, dealing " + damage + " points of damage.";
    }

    public String support(Adventurer other){
        // maybe we could give her like 3-5 pts of instant healing before healing over time
        int instantHealing = (int) (Math.random() * 2) +3;
        other.heal(instantHealing);
        // something to apply healing status effect
        return this.getName() + " blesses " + other.getName() + " with with the power of peach nectar, healing " + instantHealing + " points of damage and applying " + "STATUS PLACEHOLDER" + " to " + other.getName() + ".";
    }

    public String support(){
        // maybe we could give her like 3-5 pts of instant healing before healing over time
        int instantHealing = (int) (Math.random() * 2) +3;
        this.heal(instantHealing);
        // something to apply healing status effect
        return this.getName() + " blesses herself with with the power of peach nectar, healing " + instantHealing + " points of damage and applying " + "STATUS PLACEHOLDER" + " to herself.";
    }

    public String specialAttack(Adventurer other){
        if (this.getSpecial() == 0)return this.getName() + " wastes her turn looking for stars that no longer exist in the night sky";
        if (this.getSpecial() > 0){
            other.setSleep(true);
            setSpecial(getSpecial() - 1);
            return this.getName() + " uses the power of the beautiful night sky to lull " + other.getName() + " to sleep, consuming one " + this.getSpecialName() + ". ";
        }
        // smth with the status effect
        return "Unable to use this move due to insufficient " + this.getSpecialName();
    }
}
