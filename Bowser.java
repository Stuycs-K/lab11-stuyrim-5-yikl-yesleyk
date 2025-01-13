import java.util.Random;

public class Bowser extends Adventurer {
    int shellSpinsMax, shellSpins;

    public Bowser(){
        super("Bowser", 35);
        shellSpinsMax = 3;
        shellSpins = shellSpinsMax;
    }

    public String getSpecialName(){
        return "shell spins";
    }

    public int getSpecial(){
        return shellSpins;
    }

    public int getSpecialMax(){
        return shellSpinsMax;
    }

    public void setSpecial(int n){
        shellSpins = n;
    }

    public String attack(Adventurer other){
        int damage = (int) (2 * (Math.random() + 3));
        other.applyDamage(damage);
        return this.getName() + " smashes " + other.getName() + " , dealing " + damage + " points of damage. ";
    }

    //attack all adventurers
    public String attack(Adventurer[] adventurers){
        String txt = "Bowser uses Bowser SMASH! ";
        for (Adventurer marioChar : adventurers){
            txt += attack(marioChar);
        }
        return txt;
    }

    public String support(Adventurer other){
        // N/A???
        return this.getName() + " blesses " + other.getName() + " with with the power of peach nectar, healing " + instantHealing + " points of damage and applying " + "STATUS PLACEHOLDER" + " to " + other.getName() + ".";
    }

    public String support(){
        int healing = (int) (Math.random() + 1);
        this.applyDamage(healing);
        return this.getName() + " uses the powerful singing powers of the turtle, healing " + healing + " points of damage.";
    }

    public String specialAttack(Adventurer other){
        if (this.getSpecial() > 0){
            // smth with the status effect
            setSpecial(getSpecial() - 1);
            return this.getName() + " crawls into his shell and spins, assuming a more vulnerable state that deals more damage, consuming one " + this.getSpecialName() + ". ";
        }
        // smth with the status effect
        return "Unable to use this move due to insufficient " + this.getSpecialName();
    }
}