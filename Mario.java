import java.util.Random;

public class Mario extends Adventurer{
    int mushroomMax, mushroom;

    public Mario(String Input){
        super(Input, 18);
        this.mushroomMax = 2;
        mushroom = mushroomMax;
    }

    public Mario(){
        super("Mario", 18);
        this.mushroomMax = 2;
        mushroom = mushroomMax;
    }

    public String getSpecialName(){
        return "mushrooms";
    }

    public int getSpecial(){
        return mushroom;
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
        if (getSpecial()  <= 0) return this.getName() + " wastes a turn looking for mushrooms he doesnt have";
        int before = other.getSpecial();
        other.setSpecial(other.getSpecial() - 2);
        if(other.getSpecial() < 0) other.setSpecial(0);
        setSpecial(getSpecial() - 1);
        return this.getName() + " has taken away " + (before - other.getSpecial()) + " " + other.getSpecialName() + " from " + other.getName();
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
