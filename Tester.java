import java.util.ArrayList;

// import org.w3c.dom.Text;

public class Tester{
    private static final int WIDTH = 79;
    private static final int HEIGHT = 30;
    private static final int BORDER_COLOR = Text.WHITE;
    private static final int BORDER_BACKGROUND = Text.WHITE + Text.BACKGROUND;

    public static void main(String[] args) {
        drawBackground();
        Text.go(32,1);
        ArrayList<Adventurer> badParty  = createRandomBadAdventurerParty(3);
        ArrayList<Adventurer> goodparty = createRandomGoodAdventurerParty(3);
        System.out.println( badParty);
        System.out.println( goodparty);


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
          /*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
          //YOUR CODE HERE
          /*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<*/
          String[] words = text.split(" ");
          String line = "";
          int currRow = row;

          for (int i = 0; i < words.length; i++){
            if (line.length() > 0){
              if (line.length() + words[i].length() + 1 > width){
                drawText(line,currRow,col);
                currRow++;
                line = "";
              }
              else line += " ";
            }
            line += words[i];
          }
          // if line wasnt full print it
          if (line.length() > 0){
            drawText(line,currRow,col);
          }
        }

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
      
      }

      public static String colorByPercent(int hp, int maxHP){
        String output = String.format("%2s", hp+"")+"/"+String.format("%2s", maxHP+"");
        if (hp < 0.25 * maxHP) Text.colorize(output, Text.RED);
        else if (hp < 0.75 * maxHP) Text.colorize(output, Text.YELLOW);
        else Text.colorize(output, Text.WHITE);
        return output;
      }