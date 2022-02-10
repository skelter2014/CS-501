package cs501;

public class RecursiveRuler {

    public static void main(String[] args){
        drawRuler(1,5 );

    }

    public static void drawRuler(int inches, int majorLength){
        drawLine(majorLength,0);
        for (int j=1; j <= inches; j++){
            drawInterval(majorLength - 1);
            drawLine(majorLength,j);
        }
    }
    private static void drawInterval(int centralLength) {
        if (centralLength >=1){
            drawInterval(centralLength - 1);
            drawLine(centralLength);
            drawInterval(centralLength - 1);
        }
    }

    private static void drawLine(int ticklength, int tickLabel) {
        for (int j=0; j < ticklength; j++){
            System.out.print("-");
        }
        if (tickLabel >=0){
            System.out.print(" " + tickLabel);
        }
        System.out.print("\n");

    }



    private static void drawLine(int tickLength) {
        drawLine(tickLength, -1);
    }
    
}
