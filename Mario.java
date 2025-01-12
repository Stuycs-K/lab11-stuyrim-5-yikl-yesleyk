import java.util.Random;

public class Mario extends Adventurer{
    int mushroomMax, mushroom;

    public Mario(){
        super("Mario", 18);
        this.mushroomMax = 1;
        mushroom =mushroomMax; 
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

    public int getSpexialMax(){
        return mushroomMax;
    }

    public String attack(Adventurer other){
        int damage = (int) ((3) * (Math.random()) + 1 );
        other.applyDamage(damage);
        return this.getName() + " bonks " + other.getName() + " dealing " + damage + " points of damage."
    }

    public String specialAttack(Adventurer other ){

    }

    public String support(Adventurer other){

    }

    public String support(){

    }
}
