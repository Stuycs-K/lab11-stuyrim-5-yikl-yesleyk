import java.util.Random;
public abstract class Adventurer{
  private String name;
  private int HP,maxHP;
    //counters
  private int bubbleBarrier, revival;
  //flags 
  private boolean revivalFlag, bubbleBarrierFlag , extraTurnFlag  , sleepFlag ; 


  //Abstract methods are meant to be implemented in child classes.
  /*
  all adventurers must have a custom special
  consumable resource (mana/rage/money/witts etc)
  */

  //give it a short name (fewer than 13 characters)
  public abstract String getSpecialName();
  //accessor methods
  public abstract int getSpecial();
  public abstract int getSpecialMax();
  public abstract void setSpecial(int n);

  //concrete method written using abstract methods.
  //refill special resource by amount, but only up to at most getSpecialMax()
  public int restoreSpecial(int n){
    if( n > getSpecialMax() - getSpecial()){
      n = getSpecialMax() - getSpecial();
    }
    setSpecial(getSpecial()+n);
    return n;
  }

  /*
  all adventurers must have a way to attack enemies and
  support their allys
  */
  //hurt or hinder the target adventurer
  public abstract String attack(Adventurer other);

  /*This is an example of an improvement that you can make to allow
   * for more flexible targetting.
   */
  //heal or buff the party
  //public abstract String support(ArrayList<Adventurer> others);

  //heal or buff the target adventurer
  public abstract String support(Adventurer other);

  //heal or buff self
  public abstract String support();

  //hurt or hinder the target adventurer, consume some special resource
  public abstract String specialAttack(Adventurer other);

  // methods for status effects (needs to be in all adventurers so they can all be affected)
  private void setFlag(int n, boolean x){
    if (n > 0 )  x = true;
    else  x = false; 
  }
  
  public void setBubbleBarrier(int n){
    bubbleBarrier = n;
    setFlag(n , bubbleBarrierFlag);
  }

  public void setSleep(boolean n){
    sleepFlag = n;
  }

  public void setRevival(int n){
    revival = n;
    setFlag(n, revivalFlag);
  }

  public void setExtraTurn(boolean n){
    extraTurnFlag = n;
  }

  public int getBubbleBarrier(){
    return bubbleBarrier;
  }

  public boolean getExtraTurn(){
    return extraTurnFlag;
  }

  public boolean getSleep(){
    return sleepFlag;
  }

  public int getRevival(){
    return revival;
  }

  public int bubbleBarrierEffect(int damage){
    setBubbleBarrier(getBubbleBarrier() - 1);
    return (int) (damage * .6);
  }

  public String revivalEffect(){
    if (this.getHP() <= 0){
      setRevival(0);
      this.setHP(5);
      return this.getName() + " was revived using a Revival Mushroom! They have resurrected with 5HP";
  }else return "";
}
  

  /*
  standard methods
  */

  public void applyDamage(int amount){
    if (bubbleBarrierFlag) this.HP -= 0.5 * amount;
    else if (this instanceof Mario) this.HP -= 0.6 * amount;
    else this.HP -= amount;
  }

  public void heal(int amount){
    this.HP += amount;
    if (getHP() > getmaxHP()) setHP(getmaxHP());
  }

  //You did it wrong if this happens.
  public Adventurer(){
    this("Lester-the-noArg-constructor-string");
  }

  public Adventurer(String name){
    this(name, 10);
    this.bubbleBarrier = 0;
    this.revival = 0;
  }

  public Adventurer(String name, int hp){
    this.name = name;
    this.HP = hp;
    this.maxHP = hp;
    this.bubbleBarrier = this.revival = 0;
    this.revivalFlag = this.bubbleBarrierFlag = this.extraTurnFlag = this.blessingFlag = this.sleepFlag = false ;
  }

  //toString method
  public String toString(){
    return this.getName();
  }

  //Get Methods
  public String getName(){
    return name;
  }

  public int getHP(){
    return HP;
  }

  public int getmaxHP(){
    return maxHP;
  }
  public void setmaxHP(int newMax){
    maxHP = newMax;
  }

  //Set Methods
  public void setHP(int health){
    this.HP = health;
  }

  public void setName(String s){
    this.name = s;
  }

  public void decreaseCounter(){
    if (bubbleBarrierFlag ){
      setBubbleBarrier(getBubbleBarrier() - 1);
    }
    if (revivalFlag){
      setRevival(getRevival() - 1);
    }
  }
}
