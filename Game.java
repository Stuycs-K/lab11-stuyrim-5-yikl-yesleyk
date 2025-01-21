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
    // move cursor to bottom right side box
    Text.go(17,42);
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
    //clearing box
 
    for(int i = row ; i < row + height  ; i++){
      for( int j = col ; j < col + width; j ++){
        Text.go(i, j );
        System.out.print(" ");
      }
      //System.out.print("\r");
    }
    Text.go(32,1);
    
    //print text
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
          currLine = word;
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
      String[] funnyadjectives = new String[] {"Evil " , "Bad-to-the-Bone " , "Terrible "};
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
      String[] funnyadjectives = new String[] {"Angelic " , "Super " , "Terrific "};
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
        String text = colorbyAliveness(curr) + '\n' +
                      "HP: " + colorByPercent(curr.getHP(),curr.getmaxHP()) + '\n' +
                      curr.getSpecialName() + ": " + curr.getSpecial();
        TextBox(startRow, column, 22, 3, text);
        column += 27;
      }
      Text.go(17,42);
    }

  //Use this to create a colorized number string based on the % compared to the max value.
  public static String colorByPercent(int hp, int maxHP){
    String output = String.format("%2s", hp+"")+"/"+String.format("%2s", maxHP+"");
    if (hp < 0.25 * maxHP) return Text.colorize(output, Text.RED);
    else if (hp < 0.75 * maxHP) return Text.colorize(output, Text.YELLOW);
    else return Text.colorize(output, Text.GREEN);
  }

  public static String colorbyAliveness(Adventurer currChar){
    String name = currChar.getName();
    if (currChar.getHP() <= 0){
      return Text.colorize(name, Text.GRAY);
    }
    return name;

    //should do this based on dead or alive
  }

  //Display the party and enemies
  //Do not write over the blank areas where text will appear.
  //Place the cursor at the place where the user will by typing their input at the end of this method.
  public static void drawScreen(ArrayList<Adventurer> enemies, ArrayList<Adventurer> party){
    //draw player party
    drawParty(enemies, 3);
    //draw enemy party
    drawParty(party, 23);

    Text.go(17,42);
  }

  public static String userInput(Scanner in){
      //Move cursor to prompt location
      Text.go(17,42);
      //System.out.print("\033[2K");
      Text.go(17,42);
      //show cursor
      Text.showCursor();
      String input = in.nextLine();
      //clear the text that was written
      TextBox(17,42,36,1,"                            ");
      Text.go(17,42);
      //System.out.print("\033[2K");
      Text.hideCursor();
      /*System.out.print("\r");
      System.out.print("     ");
      System.out.print("\r");*/
      //drawBackground();
      return input;
  }
  
  public static void quit(){
    Text.reset();
    Text.showCursor();
    //Text.go(17,42);
    Text.go(31, 1);
  }

public static void drawLosingScreen(ArrayList<Adventurer> enemies){
  Text.hideCursor();
  for (int i = 1; i < WIDTH; i++){
    for (int j = 1; j < HEIGHT; j ++){
      Text.go(j,i);
      System.out.print(Text.colorize(" ", Text.RED+Text.BACKGROUND));
    }
  }
  String textToDisplay = "Aw Man you have been defeated by: \n";
  String textChars = "";
  for( int i = 0; i < enemies.size(); i++){
    if (i < enemies.size() - 1) textToDisplay += enemies.get(i).getName() + ", ";
    else textChars += enemies.get(i).getName();
  }
  Text.go(14,25);
  System.out.print(Text.colorize(textToDisplay,Text.RED + Text.BACKGROUND, Text.WHITE));
  Text.go(15,25);
  System.out.print(Text.colorize(textChars,Text.RED + Text.BACKGROUND, Text.WHITE));
  Text.hideCursor();
}

public static void drawWinningScreen(ArrayList<Adventurer> enemies){
  Text.hideCursor();
  for (int i = 1; i < WIDTH; i++){
    for (int j = 1; j < HEIGHT; j ++){
      Text.go(j,i);
      System.out.print(Text.colorize(" ", Text.GREEN+Text.BACKGROUND));
    }
  }
  String textToDisplay = "Congratulations you have defeated: \n";
  String textChars = "";
  for( int i = 0; i < enemies.size(); i++){
    if (i < enemies.size() - 1) textToDisplay += enemies.get(i).getName() + ", ";
    else textChars += enemies.get(i).getName();
  }
  Text.go(14,25);
  System.out.print(Text.colorize(textToDisplay,Text.GREEN + Text.BACKGROUND, Text.WHITE));
  Text.go(15,25);
  System.out.print(Text.colorize(textChars,Text.GREEN + Text.BACKGROUND, Text.WHITE));
  Text.hideCursor();
}

  public static void deathChecker(ArrayList<Adventurer> group, boolean[] status){
    for (int i = 0; i < group.size(); i++){
      if (group.get(i).getHP() <= 0){
        group.get(i).setHP(0);
        if(!status[i]){
          status[i] = true;

        }
      }
    }
  }

  public static boolean isInteger(String str){
    try {
      Integer.parseInt(str);
      return true;
    } catch (NumberFormatException e) {
      return false;
    }
  }

  public static boolean isAllTrue(boolean[] arr){
    for ( int i = 0 ; i < arr.length ; i ++){
      if (!arr[i]) return false; 
    }
    return true; 
  }

  public static void printLastActions(ArrayList<String> actions, String newAction){
    if (actions.size() == 0){ 
      actions.add(newAction);
      TextBox(10 , 2 ,36, 5, actions.get(0));
    }
    else if (actions.size() == 1){
      actions.add(newAction);
      TextBox(15 , 2 ,36, 5, Text.colorize(actions.get(0), Text.GRAY));
      TextBox(10 , 2 ,36, 5, actions.get(1));
    }
    else {
      actions.set(0, actions.get(1));
      actions.set(1, newAction);
      TextBox(15 , 2 ,36, 5, Text.colorize(actions.get(0), Text.GRAY));
      TextBox(10 , 2 ,36, 5, Text.colorize(actions.get(1), Text.WHITE));
    }
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
    ArrayList<String> actions = new ArrayList<String>(2);

    //You can add parameters to draw screen!
    drawBackground();
    drawScreen(enemies, party);//initial state.


    //Main loop

    //display this prompt at the start of the game.
    if(deadParty[whichPlayer]) printLastActions(actions, party.get(whichPlayer).getName() + " has fallen and cannot move anymore");
    else{
    String preprompt = "Enter command for "+party.get(whichPlayer)+": \n >> attack (a) \n >> special (sp) \n >> support (su) \n >> quit (q)";
    TextBox(10 , 42 ,36 , 11, preprompt);
    }

    // validifying userInput


    while(! (input.equalsIgnoreCase("q") || input.equalsIgnoreCase("quit"))){
      //Read user input



      String[] userInputErrors = {"Too many arguments. Try again.", "Invalid move. Try again.", "Select an existing character.", "Too little arguments. Try again.", "Second argument must be an integer.", "Please select a living character. "};

      //example debug statment
      //TextBox(24,2,1,78,"input: "+input+" partyTurn:"+partyTurn+ " whichPlayer="+whichPlayer+ " whichOpp="+whichOpponent );

      //display event based on last turn's input
      if(partyTurn){
        boolean validinput = false;
        String[] inputs = new String[2];
        int target = -1;
        String action = null;
        Adventurer currAdv = party.get(whichPlayer);

        while(!validinput){
          input = userInput(in);
          inputs = input.split(" ");
          action = inputs[0];
          currAdv = party.get(whichPlayer);
          if ((inputs[0].equalsIgnoreCase("q") || inputs[0].equalsIgnoreCase("quit"))){
            System.out.print("You have quit.");
            quit();
            break;
          }
          // not enough args
          if (inputs.length > 2){
            TextBox(16, 42, 36, 2, userInputErrors[0]);
          }
          // too many args
          else if (inputs.length < 2){
            TextBox(16, 42, 36, 2, userInputErrors[3]);
          }
          // first arg not a move
          else {
            if (!(inputs[0].equals("attack") || inputs[0].equals("a") || 
                  inputs[0].equals("special") || inputs[0].equals("sp") || 
                  inputs[0].equals("su") || inputs[0].equals("support"))){
              TextBox(16,42,36,2,userInputErrors[1]);
            }
            // second arg not an int
            else if (!(isInteger(inputs[1]))){
              TextBox(16,42,36,2,userInputErrors[4]);
            } else {
              // 2nd arg IS an int, set target
              target = Integer.parseInt(inputs[1]);
              // invalid enemy int
              if ((action.equals("attack") || action.equals("a")) || 
                   ((action.equals("special") || action.equals("sp")))){
                    // target is not in the index of the enemies
                    if (target < 0 || target >= enemies.size()){
                      TextBox(16,42,36,2,userInputErrors[2]);
                    }
                    else if (deadEnemies[target]){
                      TextBox(16,42,36,2,userInputErrors[5]);
                    }
                    else validinput = true;
              }
              else if ((action.equals("su") || action.equals("support"))){
                    // target is not in the index of the party
                    if (target < 0 || target >= party.size()){
                      TextBox(16,42,36,2,userInputErrors[2]);
                    }
                    else if (deadParty[target]){
                      TextBox(16,42,36,2,userInputErrors[5]);
                    }
                    else validinput = true;
              }
            }
          }
          //else validinput = true;
          //fix other stuff like if the support is for someone whos dead
        }
    //  target = Integer.parseInt(inputs[1]);

      
        // check for invalid inputs

        String words ="";
        //Process user input for the last Adventurer:
        /* 
        if(party.get(whichPlayer).getSleep()){
          String sleepyname = party.get(whichPlayer).getName();
          party.get(whichPlayer).setSleep(false);
          whichPlayer++;
          words = sleepyname + " has gone to sleep and missed their turn";
          printLastActions(actions, words);
        }
        if(whichPlayer < party.size()){
          */

        if(action.equals("attack") || action.equals("a")){
          words = currAdv.attack(enemies.get(target));
          printLastActions(actions, words);
        }
        else if(action.equals("special") || action.equals("sp")){
          words = currAdv.specialAttack(enemies.get(target));
          printLastActions(actions, words);
        }
        else if(action.equals("su") || action.equals("support")){
          //"support 0" or "su 0" or "su 2" etc.
          //assume the value that follows su  is an integer.
          if (target == whichPlayer) words = currAdv.support();
          else words = currAdv.support(party.get(target));
          printLastActions(actions, words);
        }

        //You should decide when you want to re-ask for user input
        //If no errors:
        party.get(whichPlayer).decreaseCounter();
        if(party.get(whichPlayer).getRevival() > 0){
          words = party.get(whichPlayer).revivalEffect();
          printLastActions(actions, words);
        }
        if(!(party.get(whichPlayer).getExtraTurn()))whichPlayer++;

        else party.get(whichPlayer).setExtraTurn(false);
        // debug sttaemetn whichplayer
        /*Text.go(31,1);
        System.out.print(whichPlayer);
        Text.go(17,42);*/
      //  }
        deathChecker(party,deadParty);


        if(whichPlayer < party.size() ){
          //while(deadParty[whichPlayer] && whichPlayer < party.size())whichPlayer++;
          if(deadParty[whichPlayer]) printLastActions(actions, party.get(whichPlayer).getName() + " has fallen and cannot move anymore");
    else{
          //This is a player turn.
          //Decide where to draw the following prompt:
          /* 
          if(whichPlayer < party.size()){   
          if(party.get(whichPlayer).getSleep()){
            String sleepyname = party.get(whichPlayer).getName();
            party.get(whichPlayer).setSleep(false);
            whichPlayer++;
            words = sleepyname + " has gone to sleep and missed their turn";
            printLastActions(actions, words);
          }*/
          if(whichPlayer < party.size()){ 
          String prompt = "Enter command for "+party.get(whichPlayer)+": \n >> attack (a) \n >> special (sp) \n >> support (su) \n >> quit (q)";
          TextBox(10 , 42 ,36 , 11, prompt);
          }
        }
              /* 
          }else{
            String prompt = "press enter to see enemy turn";
            TextBox(10 , 42 ,36 , 11, prompt);

            partyTurn = false;
            
            whichOpponent = 0;
          }
            */
          
          
        }else{
          //This is after the player's turn, and allows the user to see the enemy turn
          //Decide where to draw the following prompt:
          String prompt = "press enter to see enemy turn";
          TextBox(10 , 42 ,36 , 11, prompt);
          
          partyTurn = false;
          whichPlayer = 0;
          whichOpponent = 0;
        }
        //done with one party member
      deathChecker(enemies,deadEnemies);
      }else{
        //not the party turn!

        String words = " ";

        //enemy attacks a randomly chosen person with a randomly chosen attack.z`
        //Enemy action choices go here!
        
        // enemy acttions
        boolean proceed = false;
          while (!proceed){
            input = userInput(in);
            if (input == ""){
              proceed = true;
            }
          }

        if(enemies.get(whichOpponent).getSleep()){
            String sleepyname = enemies.get(whichOpponent).getName();
            enemies.get(whichOpponent).setSleep(false);
            whichOpponent++;
            words =sleepyname + " has gone to sleep and missed their turn";
            printLastActions(actions, words);
        }
        if(deadEnemies[whichOpponent]){
          printLastActions(actions, enemies.get(whichOpponent).getName() + " has fallen and cannot move anymore");
          whichOpponent++;
        } 
        

        if( whichOpponent < enemies.size()){

        
        Adventurer currEnemy = enemies.get(whichOpponent);
        int enemyMove = (int) (3 * Math.random());
        if (enemyMove == 0){
          // attack
          int enemyTarget = (int) (party.size() * Math.random());
          Adventurer attacked = party.get(enemyTarget);
          words = currEnemy.attack(attacked);
          printLastActions(actions, words);
        }
        if (enemyMove == 1){
          // special attack
          int enemyTarget = (int) (party.size() * Math.random());
          Adventurer attacked = party.get(enemyTarget);
          words = currEnemy.specialAttack(attacked);
          }
          printLastActions(actions, words);
        if (enemyMove == 2){
          // support
          int ally = (int) (enemies.size() * Math.random());
          if (ally == whichOpponent){
            words = currEnemy.support();
          }
          else words = currEnemy.support(enemies.get(ally));

          printLastActions(actions, words);
        }

      

        //Decide where to draw the following prompt:
        String prompt = "press enter to see next turn";
        TextBox(10 , 42 ,36 , 11, prompt);
        enemies.get(whichOpponent).decreaseCounter();

        if(!(enemies.get(whichOpponent).getExtraTurn())) whichOpponent++;
        else enemies.get(whichOpponent).setExtraTurn(false);

        deathChecker(party,deadParty);
        deathChecker(enemies,deadEnemies);
        if( whichOpponent < enemies.size()){
        while(deadEnemies[whichOpponent] && whichOpponent < enemies.size())whichOpponent++;
      }


      
       }

          
      }//end of one enemy.

      //modify this if statement.
      if(!partyTurn && whichOpponent >= enemies.size()){
        //THIS BLOCK IS TO END THE ENEMY TURN
        //It only triggers after the last enemy goes.
        whichPlayer = 0;
        turn++;
        partyTurn=true;
        //display this prompt before player's turn
        String prompt = "Enter command for "+party.get(whichPlayer)+": \n >> attack (a) \n >> special (sp) \n >> support (su) \n >> quit (q)";
        TextBox(10 , 42 ,36 , 11, prompt);
      }

      //display the updated screen after input has been processed.
      
      drawScreen(enemies, party);
      if (isAllTrue(deadParty)) drawLosingScreen(enemies);
      if (isAllTrue(deadEnemies)) drawWinningScreen(enemies);


    }//end of main game loop


    //After quit reset things:
    quit();
  }
}
