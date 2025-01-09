public class Tester{
    private static final int WIDTH = 82;
    private static final int HEIGHT = 30;
    private static final int BORDER_COLOR = Text.WHITE;
    private static final int BORDER_BACKGROUND = Text.WHITE + Text.BACKGROUND;

    public static void main(String[] args) {
        drawBackground();
        //run();
    }
    

    public static void drawBackground(){
        for(int j = 1; j <=WIDTH; j ++){
                Text.go(0,j);
                System.out.print(Text.colorize(" ", BORDER_COLOR));
            }
            for(int i = 2; i < HEIGHT ; i++){
                Text.go(i,0);
                System.out.print(Text.colorize(" ", BORDER_COLOR));
                Text.go(i,WIDTH);
                System.out.print(Text.colorize(" ", BORDER_COLOR));
                    
                }
            for(int j = 1; j <=WIDTH ; j ++){
                Text.go(HEIGHT,j);
                System.out.print(Text.colorize(" ", BORDER_COLOR));
            }
        }
      }