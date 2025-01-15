import java.util.Random;
public abstract class Adventurer{
  private String name;
  private int HP,maxHP;
    //counters
  private int bubbleBarrier, blessing, sleep, revival;
  //flags 
  private boolean revivalFlag, bubbleBarrierFlag , extraTurnFlag , blessingFlag , sleepFlag ; 


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
  private boolean setFlag(int n, boolean x){
    if (n > 0 ) return true;
    else return false; 
  }
  
  public void setBubbleBarrier(int n){
    bubbleBarrier = n;
    setFlag(n , bubbleBarrierFlag);
  }

  public void setBlessing(int n){
    blessing = n;
    setFlag(n , blessingFlag);
  }

  public void setSleep(int n){
    sleep = n;
    setFlag( n , sleepFlag);
  }

  public void setRevival(int n){
    revival = n;
    setFlag(n, revivalFlag);
  }

  public int getBubbleBarrier(){
    return bubbleBarrier;
  }

  public int getBlessing(){
    return blessing;
  }

  public int getSleep(){
    return sleep;
  }

  public int getRevival(){
    return revival;
  }

  public int bubbleBarrierEffect(int damage){
    setBubbleBarrier(getBubbleBarrier() - 1);
    return (int) (damage * .6);
  }

  public int blessingEffect(){
    setBlessing(getBlessing() - 1);
    return (int) (Math.random() + 2);
  }

  public String sleepEffect(){
    setSleep(getSleep() - 1);
    return this.getName() + " is currently sleeping. They fail to ";
  }

  public String revivalEffect(){
    if (this.getHP() <= 0){
      setRevival(0);
      this.setHP(5);
      return this.getName() + " was revived using a Revival Mushroom! They have resurrected with 5HP";
    }
    else {
      setRevival(getRevival() - 1);
      return "";
    }
  }

  /*
  standard methods
  */

  public void applyDamage(int amount){
    this.HP -= amount;
  }

  //You did it wrong if this happens.
  public Adventurer(){
    this("Lester-the-noArg-constructor-string");
  }

  public Adventurer(String name){
    this(name, 10);
    this.bubbleBarrier = 0;
    this.blessing = 0;
    this.sleep = 0;
    this.revival = 0;
  }

  public Adventurer(String name, int hp){
    this.name = name;
    this.HP = hp;
    this.maxHP = hp;
    this.bubbleBarrier = this.blessing = this.sleep = this.revival = 0;
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
    if (blessingFlag ){
      setBlessing(getBlessing() - 1);
    }
    if (sleepFlag){
      setSleep(getSleep() - 1);
    }
    if (revivalFlag){
      setRevival(getRevival() - 1);
    }
  }
}
