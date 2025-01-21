import java.util.Random;

public class Mario extends Adventurer{
    int mushroomMax, mushroom;

    public Mario(String Input){
        super(Input, 18);
        this.mushroomMax = 1;
        mushroom = mushroomMax;
    }

    public Mario(){
        super("Mario", 18);
        this.mushroomMax = 1;
        mushroom = mushroomMax;
    }

    public String getSpecialName(){
        return "mushrooms";
    }

    public int getSpecial(){
        return mushroomMax;
    }

    public void setSpecial(int n){
        mushroom = n;
    }

    public int getSpecialMax(){
        return mushroomMax;
    }

    public String attack(Adventurer other){
        int damage = (int) ((3) * (Math.random()) + 1 );
        other.applyDamage(damage);
        return this.getName() + " bonks " + other.getName() + " dealing " + damage + " points of damage.";
    }

    public String specialAttack(Adventurer other ){
        if (getSpecial()  == 0) return this.getName() + " wastes a turn looking for mushrooms he doesnt have";
        other.setRevival(2);
        setSpecial(getSpecial() - 1);
      return this.getName() + " has given the power of reviving for 2 turns to " + other.getName();
    }

    public String support(Adventurer other){
      other.setBubbleBarrier(3);
      return this.getName() + " uses a bubble on " + other.getName() + ", blocking incoming damage to that character for 3 turns.";
    }

    public String support(){
      this.setBubbleBarrier(3);
      return this.getName() + " uses a bubble on himself, blocking incoming damage to that character for 3 turns.";
    }

}
