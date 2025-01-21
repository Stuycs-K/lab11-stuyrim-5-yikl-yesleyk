import java.util.Random;

public class Luigi extends Adventurer {
    int wrenchesMax, wrenches;

    public Luigi(String Input){
        super(Input, 12);
        this.wrenchesMax = 4;
        wrenches =wrenchesMax;
    }

    public Luigi(){
        super("Luigi", 12);
        this.wrenchesMax = 4;
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

    public int getSpecialMax(){
        return wrenchesMax;
    }

    public String attack(Adventurer other){
        int damage1 = (int) ((3) * (Math.random()) + 1 );
        int damage2 = (int) ((3) * (Math.random()) + 1 );
        other.applyDamage(damage1 + damage2);
        return this.getName() + " throws an exahust pipe boomerang at " + other.getName() + " dealing " + damage1 + " points of damage the first hit and " + damage2 + " points of damage the second hit." ;
    }

    public String specialAttack(Adventurer other ){
        if( wrenches == 0) return this.getName() + " wastes a turn becuase he has insufficient wrenches";
        wrenches--;
        double random = Math.random();
        if (random < 0.7){
            other.applyDamage(6);
            return this.getName() + " hit " + other.getName() + " with a pipe and dealt 6 damage";
        }
        else return this.getName() + " did not hit " + other.getName() + " and did no damage";
    }

    public String support(Adventurer other){
    other.setExtraTurn(true);
      return this.getName() + " hyped up  " + other.getName() + " they now have an extra turn";
    }

    public String support(){
        this.restoreSpecial(1);
        return this.getName() + " opens an item box, restoring one wrench";
    }
}
