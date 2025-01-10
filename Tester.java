public class Tester{
    private static final int WIDTH = 79;
    private static final int HEIGHT = 30;
    private static final int BORDER_COLOR = Text.WHITE;
    private static final int BORDER_BACKGROUND = Text.WHITE + Text.BACKGROUND;

    public static void main(String[] args) {

        drawBackground();
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
    // public static void drawBG(){
    //     for(int i = 0; i < 80; i++){
    //         Text.color(BORDER_BACKGROUND);
    //         Text.go(1,i+1);
    //         System.out.println("!");
    //         Text.go(30,i+1);
    //         System.out.println("!");
    //       }
    //       for(int i = 0; i < 30; i++){
    //         Text.color(BORDER_BACKGROUND);
    //         Text.go(i+1,1);
    //         System.out.println("!");
    //         Text.go(i+1,80);
    //         System.out.println("!");
    //       }
    // }
      }
