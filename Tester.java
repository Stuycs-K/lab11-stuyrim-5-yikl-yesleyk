import java.util.ArrayList;

// import org.w3c.dom.Text;

public class Tester{
  private static final int WIDTH = 79;
  private static final int HEIGHT = 30;
  private static final int BORDER_COLOR = Text.WHITE;
  private static final int BORDER_BACKGROUND = Text.WHITE + Text.BACKGROUND;

  public static void main(String[] args) {
    drawScreen();
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
  public static void drawScreen(){
    drawBackground();
    Text.go(32,1);
    //draw player party
    ArrayList<Adventurer> badParty  = createRandomBadAdventurerParty(3);
    drawParty(badParty, 3);
    //draw enemy party
    ArrayList<Adventurer> goodParty = createRandomGoodAdventurerParty(3);
    drawParty(goodParty, 23);
      }
    }
