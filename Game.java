import java.util.*;

public class Game{
  private static final int WIDTH = 82;
  private static final int HEIGHT = 30;
  private static final int BORDER_COLOR = Text.BLACK;
  private static final int BORDER_BACKGROUND = Text.WHITE + Text.BACKGROUND;

  public static void main(String[] args) {
    run();
  }

  //Display the borders of your screen that will not change.
  //Do not write over the blank areas where text will appear or parties will appear.
  public static void drawBackground(){
    Text.clear();
    // top border
    for(int j = 1; j <=WIDTH; j ++){
      Text.go(0,j);
      System.out.print(Text.colorize(" ", BORDER_BACKGROUND));
    }
    // 
    for(int i = 2; i < HEIGHT ; i++){
      // left  border
      Text.go(i,0);
      System.out.print(Text.colorize(" ", BORDER_BACKGROUND));
      // right border
      Text.go(i,WIDTH);
      System.out.print(Text.colorize(" ", BORDER_BACKGROUND));
      // vertical borders separating characters
      if (i < 10 || i > 21){
        Text.go(i,27);
        System.out.print(Text.colorize(" ", BORDER_BACKGROUND));
        Text.go(i,53);
        System.out.print(Text.colorize(" ", BORDER_BACKGROUND));
      }
      // vetical borders between 2 boxes in middle
      else {
        Text.go(i,40);
        System.out.print(Text.colorize(" ", BORDER_BACKGROUND));
      }
    }
    // bottom border
    for(int j = 1; j <=WIDTH ; j ++){
      Text.go(HEIGHT-1,j);
      System.out.print(Text.colorize(" ", BORDER_BACKGROUND));
    }
    // draw horizontal borders at rows 9 and 11
    for(int j = 1; j <=WIDTH ; j ++){
      Text.go(9,j);
      System.out.print(Text.colorize(" ", BORDER_BACKGROUND));
      Text.go(21,j);
      System.out.print(Text.colorize(" ", BORDER_BACKGROUND));
    }
    // move cursor to bottom
    Text.go(32,1);
  }

  //Display a line of text starting at
  //(columns and rows start at 1 (not zero) in the terminal)
  //use this method in your other text drawing methods to make things simpler.
  public static void drawText(String s,int startRow, int startCol){
    /*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
    //YOUR CODE HERE
    /*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<*/
    Text.go(startRow,startCol);
    System.out.print(s);
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
      String[] words = line.split(" ");
      String currLine = "";
      for (String word : words){
        if (currLine.length() + 1 + word.length() <= width) {
          if (currLine.length() > 0) currLine += " ";
          currLine += word;
        }
        else {
          drawText(currLine, row, col);
          row++;
          currLine = "";
        }
      }
      if (currLine.length() > 0){
        drawText(currLine,row,col);
        row++;
      }
    }
  }




    //return a random adventurer (choose between all available subclasses)
    //feel free to overload this method to allow specific names/stats.
    public static ArrayList<Adventurer> createRandomBadAdventurerParty(int capacity){
      String[] funnyadjectives = new String[] {"evil " , "bad-to-the-bone " , "terrible "};
      ArrayList<Adventurer> result = new ArrayList<Adventurer>(0); 
      for (int i = 0; i < capacity; i++ ){
        int random = (int)(Math.random() * 3);
        if (random  == 0){
          result.add(new Mario( funnyadjectives[i] + "Mario" ));
        }
        if (random  == 1){
          result.add(new Luigi( funnyadjectives[i] + "Luigi" ));
        }
        if (random  == 2){
          result.add(new Peach( funnyadjectives[i] + "Peach" ));
        }
    }
    return result;
    }

    public static ArrayList<Adventurer> createRandomGoodAdventurerParty(int capacity){
      String[] funnyadjectives = new String[] {"angelic " , "super " , "terrific "};
      ArrayList<Adventurer> result = new ArrayList<Adventurer>(0); 
      for (int i = 0; i < capacity; i++ ){
        int random = (int)(Math.random() * 3);
        if (random  == 0){
          result.add(new Mario( funnyadjectives[i] + "Mario" ));
        }
        if (random  == 1){
          result.add(new Luigi( funnyadjectives[i] + "Luigi" ));
        }
        if (random  == 2){
          result.add(new Peach( funnyadjectives[i] + "Peach" ));
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


  //Use this to create a colorized number string based on the % compared to the max value.
  public static String colorByPercent(int hp, int maxHP){
    String output = String.format("%2s", hp+"")+"/"+String.format("%2s", maxHP+"");
    if (hp < 0.25 * maxHP) return Text.colorize(output, Text.RED);
    else if (hp < 0.75 * maxHP) return Text.colorize(output, Text.YELLOW);
    else return Text.colorize(output, Text.GREEN);
  }





  //Display the party and enemies
  //Do not write over the blank areas where text will appear.
  //Place the cursor at the place where the user will by typing their input at the end of this method.
  public static void drawScreen(ArrayList<Adventurer> enemies, ArrayList<Adventurer> party){
    
    //draw player party
    drawParty(enemies, 3);
    //draw enemy party
    drawParty(party, 23);

    Text.go(32,1);
  }


  public static String userInput(Scanner in){
      //Move cursor to prompt location
      Text.go(31,1);
      //show cursor
      Text.showCursor();
      String input = in.nextLine();
      //clear the text that was written
      System.out.print("\r");
      System.out.print("     ");
      System.out.print("\r");
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

    ArrayList<Adventurer>enemies = new ArrayList<Adventurer>();
    int enemyCount = ((int) (Math.random() * 3) + 1);
    if (enemyCount == 1){
      enemies.add(new Boss());
    }
    else {
      enemies = createRandomBadAdventurerParty(enemyCount);
    }
    boolean[] deadEnemies = new boolean[enemies.size()];

    //Adventurers you control:
    //Make an ArrayList of Adventurers and add 1-3 Adventurers to it.
    ArrayList<Adventurer> party = createRandomGoodAdventurerParty((int) (Math.random() * 3) + 1);
    boolean[] deadParty = new boolean[party.size()];

    boolean partyTurn = true;
    int whichPlayer = 0;
    int whichOpponent = 0;
    int turn = 0;
    String input = "";//blank to get into the main loop.
    Scanner in = new Scanner(System.in);

    //You can add parameters to draw screen!
    drawBackground();
    drawScreen(enemies, party);//initial state.

    //Main loop

    //display this prompt at the start of the game.
    String preprompt = "Enter command for "+party.get(whichPlayer)+": \n >> attack (a) \n >> special (sp) \n >> support (su) \n >> quit (q)";
    TextBox(10 , 41 ,37 , 11, preprompt);

    // validifying userInput
    input = userInput(in);
    String[] inputs = input.split(" ");
    String[] userInputErrors = {"too little arguments","too many arguments", "invalid move", "invalid character", "this party member has fainted. choose another."};

    if (partyTurn && inputs.length < 2){
      TextBox(17, 41, 37, 11, userInputErrors[0]);
    }
    
    if (partyTurn && inputs.length > 2){
      TextBox(17, 41, 37, 11, userInputErrors[1]);
    }
    if (partyTurn && !(inputs[0].equals("attack") || inputs[0].equals("a") || 
    inputs[0].equals("special") || inputs[0].equals("sp") || 
    inputs[0].startsWith("su ") || inputs[0].startsWith("support "))){
      TextBox(17,41,37,11,userInputErrors[2]);
    }
    


    while(! (input.equalsIgnoreCase("q") || input.equalsIgnoreCase("quit"))){
      //Read user input
      String action = inputs[0];
      int target = Integer.parseInt(inputs[1]);
      Adventurer currAdv = party.get(whichPlayer);

      //example debug statment
      //TextBox(24,2,1,78,"input: "+input+" partyTurn:"+partyTurn+ " whichPlayer="+whichPlayer+ " whichOpp="+whichOpponent );

      //display event based on last turn's input
      if(partyTurn){
        //input = userInput(in);

        // check for invalid inputs
        /*while( ||
             target > enemies.size()){
              TextBox(10 , 2 ,37 , 11, "invalid move");
              Text.go(32,1);
              input = userInput(in);
              inputs = input.split(" ");
              action = inputs[0];
              target = Integer.parseInt(inputs[1]);
              currAdv = party.get(whichPlayer);
             }
            */

        String words ="";
        //Process user input for the last Adventurer:

        

        if(action.equals("attack") || action.equals("a")){
          while (target > enemies.size() || target < 0){
            TextBox(17,41,37,11,userInputErrors[3]);
          }
          words = currAdv.attack(enemies.get(target));
          TextBox(10 , 2 ,37, 11, words);
        }
        else if(action.equals("special") || action.equals("sp")){
          words = currAdv.specialAttack(enemies.get(target));
          TextBox(10 , 2 ,37 , 11, words);
        }
        else if(action.equals("su") || action.equals("support")){
          //"support 0" or "su 0" or "su 2" etc.
          //assume the value that follows su  is an integer.
          if (target == whichPlayer) words = currAdv.support();
          else words = currAdv.support(party.get(target));
          TextBox(10 , 2 ,37 , 11, words);
        }

        //You should decide when you want to re-ask for user input
        else {
          // re ask for input?
        }
        //If no errors:
        party.get(whichPlayer).decreaseCounter();
        if(party.get(whichPlayer).getRevival() > 0){
          words = party.get(whichPlayer).revivalEffect();
          TextBox(10 , 2 ,37 , 11, words);
        }
        if(!(party.get(whichPlayer).getExtraTurn())) whichPlayer++;
        else party.get(whichPlayer).setExtraTurn(false);
       


        if(whichPlayer < party.size()){
          //This is a player turn.
          //Decide where to draw the following prompt:
          String prompt = "Enter command for "+party.get(whichPlayer)+": attack/special/quit";
          TextBox(10 , 41 ,37 , 11, prompt);


        }else{
          //This is after the player's turn, and allows the user to see the enemy turn
          //Decide where to draw the following prompt:
          String prompt = "press enter to see enemy turn";
          TextBox(10 , 41 ,37 , 11, prompt);

          partyTurn = false;

          whichOpponent = 0;
        }
        //done with one party member
      }else{
        //not the party turn!

        String words = " ";

        //enemy attacks a randomly chosen person with a randomly chosen attack.z`
        //Enemy action choices go here!
        
        // enemy acttions
        Adventurer currEnemy = enemies.get(whichOpponent);
        int enemyMove = (int) (3 * Math.random());
        if (enemyMove == 0){
          // attack
          int enemyTarget = (int) (party.size() * Math.random());
          Adventurer attacked = party.get(enemyTarget);
          words = currEnemy.attack(attacked);
          TextBox(10 , 2 ,37 , 11, words);
        }
        if (enemyMove == 1){
          // special attack
          int enemyTarget = (int) (party.size() * Math.random());
          Adventurer attacked = party.get(enemyTarget);
          words = currEnemy.specialAttack(attacked);
          TextBox(10 , 2 ,37 , 11, words);
        }
        if (enemyMove == 2){
          // support
          int ally = (int) (enemies.size() * Math.random());
          if (ally == whichOpponent){
            words = currEnemy.support();
          }
          else words = currEnemy.support(enemies.get(ally));

          TextBox(10 , 2 ,37 , 11, words);
        }



        //Decide where to draw the following prompt:
        String prompt = "press enter to see next turn";
        TextBox(10 , 41 ,37 , 11, prompt);
        enemies.get(whichOpponent).decreaseCounter();

        if(!(enemies.get(whichOpponent).getExtraTurn())) whichOpponent++;
        else enemies.get(whichOpponent).setExtraTurn(false);

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
        TextBox(10 , 41 ,37 , 11, prompt);
      }

      //display the updated screen after input has been processed.
      
      drawScreen(enemies, party);


    }//end of main game loop


    //After quit reset things:
    quit();
  }
}
