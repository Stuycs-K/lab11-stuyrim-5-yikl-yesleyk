public class Tester{
    private static final int WIDTH = 79;
    private static final int HEIGHT = 30;
    private static final int BORDER_COLOR = Text.WHITE;
    private static final int BORDER_BACKGROUND = Text.WHITE + Text.BACKGROUND;

    public static void main(String[] args) {
        drawBackground();
        Text.go(32,1);
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

        }
      }


      //changing something