import java.util.ArrayList;
import java.util.Scanner;

// import org.w3c.dom.Text;

public class Tester{
  private static final int WIDTH = 79;
  private static final int HEIGHT = 30;
  private static final int BORDER_COLOR = Text.WHITE;
  private static final int BORDER_BACKGROUND = Text.WHITE + Text.BACKGROUND;

  public static void main(String[] args) {
    //run();
  }
  public static void drawBackground(){
    Text.clear();
    for(int j = 1; j <=WIDTH; j ++){
      Text.go(0,j);
      System.out.print(Text.colorize(" ", BORDER_BACKGROUND));
    }
    for(int i = 2; i < HEIGHT ; i++){
      Text.go(i,0);
      System.out.print(Text.colorize(" ", BORDER_BACKGROUND));
      Text.go(i,WIDTH);
      System.out.print(Text.colorize(" ", BORDER_BACKGROUND));
      if (i < 10 || i > 21){
        Text.go(i,27);
        System.out.print(Text.colorize(" ", BORDER_BACKGROUND));
        Text.go(i,53);
        System.out.print(Text.colorize(" ", BORDER_BACKGROUND));
      }
      else {
        Text.go(i,40);
        System.out.print(Text.colorize(" ", BORDER_BACKGROUND));
      }
    }
    for(int j = 1; j <=WIDTH ; j ++){
      Text.go(HEIGHT-1,j);
      System.out.print(Text.colorize(" ", BORDER_BACKGROUND));
    }
    for(int j = 1; j <=WIDTH ; j ++){
      Text.go(9,j);
      System.out.print(Text.colorize(" ", BORDER_BACKGROUND));
    }
    for(int j = 1; j <=WIDTH ; j ++){
      Text.go(21,j);
      System.out.print(Text.colorize(" ", BORDER_BACKGROUND));
    }
    Text.go(32,1);
  }
  public static void drawText(String s,int startRow, int startCol){
    Text.go(startRow,startCol);
    System.out.println(Text.colorize(s,BORDER_COLOR));
  }
  /*Use this method to place text on the screen at a particular location.
  *When the length of the text exceeds width, continue on the next line
  *for up to height lines.
  *All remaining locations in the text box should be written with spaces to
  *clear previously written text.
  *@param row the row to start the top left corner of the text box.
  *@param col the column to start the top left corner of the text box.
  *@param width the number of characters per row
  *@param height the number of rows
  */
  public static void TextBox(int row, int col, int width, int height, String text){

    String[] lines = text.split("\n");
    for (String line : lines){
      if (line.length() > width){
        String[] words = line.split(" ");
        String currLine = "";

        for (String word : words){
          if (currLine.length() + 1 + word.length() <= width) {
            if (currLine.length() > 0) currLine += " ";
            currLine += word;
          }
          else {
            drawText(line, row, col);
            row++;
            currLine = "";
          }
        }
        if (currLine.length() > 0){
          drawText(line,row,col);
          row++;
        }
      }
      drawText(line, row, col);
      row++;
    }
  }

  public static ArrayList<Adventurer> createRandomBadAdventurerParty(int capacity){
    String[] funnyadjectives = new String[] {"evil " , "bad-to-the-bone " , "terrible "};
    ArrayList<Adventurer> result = new ArrayList<Adventurer>(0);
    boolean[] usedAdjs = new boolean[funnyadjectives.length];

    for (int i = 0; i < capacity; i++ ){
      int randomidx = -1;
      int random = (int)(Math.random() * 3);

      for (int j = 0; j < funnyadjectives.length; j++){
        if (!usedAdjs[j]) {
          randomidx = j;
          usedAdjs[j] = true;
          break;
        }
      }

      if (random == 0){
        result.add(new Mario( funnyadjectives[randomidx] + "Mario" ));
      }
      if (random == 1){
        result.add(new Luigi( funnyadjectives[randomidx] + "Luigi" ));
      }
      if (random == 2){
        result.add(new Peach( funnyadjectives[randomidx] + "Peach" ));
      }
    }
    return result;
  }

  public static ArrayList<Adventurer> createRandomGoodAdventurerParty(int capacity){
    String[] funnyadjectives = new String[] {"angelic " , "super " , "terrific "};
    ArrayList<Adventurer> result = new ArrayList<Adventurer>(0);
    boolean[] usedAdjs = new boolean[funnyadjectives.length];

    for (int i = 0; i < capacity; i++ ){
      int randomidx = -1;
      int random = (int)(Math.random() * 3);

      for (int j = 0; j < funnyadjectives.length; j++){
        if (!usedAdjs[j]) {
          randomidx = j;
          usedAdjs[j] = true;
          break;
        }
      }

      if (random == 0){
        result.add(new Mario( funnyadjectives[randomidx] + "Mario" ));
      }
      if (random == 1){
        result.add(new Luigi( funnyadjectives[randomidx] + "Luigi" ));
      }
      if (random == 2){
        result.add(new Peach( funnyadjectives[randomidx] + "Peach" ));
      }
    }
    return result;
  }

  /*Display a List of 2-4 adventurers on the rows row through row+3 (4 rows max)
  *Should include Name HP and Special on 3 separate lines.
  *Note there is one blank row reserved for your use if you choose.
  *Format:
  *Bob          Amy        Jun
  *HP: 10       HP: 15     HP:19
  *Caffeine: 20 Mana: 10   Snark: 1
  * ***THIS ROW INTENTIONALLY LEFT BLANK***
  */
  public static void drawParty(ArrayList<Adventurer> party,int startRow){
    int column = 4;
    for (int i = 0; i < party.size(); i++){
      Adventurer curr = party.get(i);
      String text = curr.getName() + '\n' +
                    "HP: " + colorByPercent(curr.getHP(),curr.getmaxHP()) + '\n' +
                    curr.getSpecialName() + ": " + curr.getSpecial();
      TextBox(startRow, column, 25, 3, text);
      column += 27;
    }
    Text.go(32,1);
  }

  public static String colorByPercent(int hp, int maxHP){
    String output = String.format("%2s", hp+"")+"/"+String.format("%2s", maxHP+"");
    if (hp < 0.25 * maxHP) Text.colorize(output, Text.RED);
    else if (hp < 0.75 * maxHP) Text.colorize(output, Text.YELLOW);
    else Text.colorize(output, Text.WHITE);
    return output;
  }
  public static void drawScreen(ArrayList<Adventurer> enemies, ArrayList<Adventurer> party){
    drawBackground();
    Text.go(32,1);
    //draw player party
    drawParty(enemies, 3);
    //draw enemy party
    drawParty(party, 23);
  }
  public static String userInput(Scanner in){
      //Move cursor to prompt location
      Text.go(32,1);
      //show cursor
      Text.showCursor();
      String input = in.nextLine();
      //clear the text that was written
      System.out.println('\r');
      return input;
  }

  public static void quit(){
    Text.reset();
    Text.showCursor();
    Text.go(32,1);
  }

  public static void run(){
    //Clear and initialize
    Text.hideCursor();
    Text.clear();


    //Things to attack:
    //Make an ArrayList of Adventurers and add 1-3 enemies to it.
    //If only 1 enemy is added it should be the boss class.
    //start with 1 boss and modify the code to allow 2-3 adventurers later.
    ArrayList<Adventurer>enemies = new ArrayList<Adventurer>();
    int enemyCount = (int) (Math.random() * 3);
    if (enemyCount == 1){
      enemies.add(new Boss());
    }
    else {
      enemies = createRandomBadAdventurerParty(enemyCount);
    }

    //Adventurers you control:
    //Make an ArrayList of Adventurers and add 1-3 Adventurers to it.
    ArrayList<Adventurer> party = new ArrayList<>();
    createRandomGoodAdventurerParty((int) (Math.random() * 3));


    boolean partyTurn = true;
    int whichPlayer = 0;
    int whichOpponent = 0;
    int turn = 0;
    String input = "";//blank to get into the main loop.
    Scanner in = new Scanner(System.in);
    //Draw the window border

    //You can add parameters to draw screen!
    drawScreen(enemies, party);//initial state.

    //Main loop

    //display this prompt at the start of the game.
    String preprompt = "Enter command for "+party.get(whichPlayer)+": attack/special/quit";

    while(! (input.equalsIgnoreCase("q") || input.equalsIgnoreCase("quit"))){
      //Read user input
      input = userInput(in);

      //example debug statment
      TextBox(24,2,1,78,"input: "+input+" partyTurn:"+partyTurn+ " whichPlayer="+whichPlayer+ " whichOpp="+whichOpponent );

      //display event based on last turn's input
      if(partyTurn){

        //Process user input for the last Adventurer:
        if(input.equals("attack") || input.equals("a")){
          /*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
          //YOUR CODE HERE
          /*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<*/
        }
        else if(input.equals("special") || input.equals("sp")){
          /*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
          //YOUR CODE HERE
          /*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<*/
        }
        else if(input.startsWith("su ") || input.startsWith("support ")){
          //"support 0" or "su 0" or "su 2" etc.
          //assume the value that follows su  is an integer.
          /*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
          //YOUR CODE HERE
          /*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<*/
        }

        //You should decide when you want to re-ask for user input
        //If no errors:
        party.get(whichPlayer).decreaseCounter();
        whichPlayer++;



        if(whichPlayer < party.size()){
          //This is a player turn.
          //Decide where to draw the following prompt:
          String prompt = "Enter command for "+party.get(whichPlayer)+": attack/special/quit";


        }else{
          //This is after the player's turn, and allows the user to see the enemy turn
          //Decide where to draw the following prompt:
          String prompt = "press enter to see enemy turn";

          partyTurn = false;

          whichOpponent = 0;
        }
        //done with one party member
      }else{
        //not the party turn!


        //enemy attacks a randomly chosen person with a randomly chosen attack.z`
        //Enemy action choices go here!
        /*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
        //YOUR CODE HERE
        /*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<*/


        //Decide where to draw the following prompt:
        String prompt = "press enter to see next turn";

        party.get(whichOpponent).decreaseCounter();

        whichOpponent++;

      }//end of one enemy.

      //modify this if statement.
      if(!partyTurn && whichOpponent >= enemies.size()){
        //THIS BLOCK IS TO END THE ENEMY TURN
        //It only triggers after the last enemy goes.
        whichPlayer = 0;
        turn++;
        partyTurn=true;
        //display this prompt before player's turn
        String prompt = "Enter command for "+party.get(whichPlayer)+": attack/special/quit";
      }

      //display the updated screen after input has been processed.
      drawScreen(enemies, party);


    }//end of main game loop


    //After quit reset things:
    quit();
  }


    }
