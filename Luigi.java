import java.util.Random;

public class Luigi{
    int wrenchesMax, wrenches;

    public Mario(){
        super("Luigi", 12);
        this.wrenchesMax = 1;
        wrenches =wrenchesMax; 
    }

    public String getSpecialName(){
        return "wrenches";
    }

    public int getSpecial(){
        return wrenches;
    }

    public void setSpecial(int n){
        wrenches = n;
    }

    public int getSpexialMax(){
        return wrenchesMax;
    }

    public String attack(Adventurer other){
        int damage1 = (int) ((3) * (Math.random()) + 1 );
        int damage2 = (int) ((3) * (Math.random()) + 1 );
        other.applyDamage(damage1 + damage2);
        return this.getName() + " throws an exahust pipe boomerang at " + other.getName() + " dealing " + damage1 + " points of damage the first hit and " + damage2 + " points of damage the second hit." ;
    }

    public String specialAttack(Adventurer other ){

    }

    public String support(Adventurer other){

    }

    public String support(){
        this.restoreSpecial(1);
        return this.getName() + " opens an item box, restoring one wrench"; 
    }
}