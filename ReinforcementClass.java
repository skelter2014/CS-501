import java.util.Scanner;;

public class ReinforcementClass {
    boolean bool = false;
    char c = 0;
    byte b = 0;
    short s = 0;
    int i = 0;
    long l = 0;
    float f = 0.0f;
    double d = 0.0;

    public static void main(String[] args) {
        ReinforcementClass tester = new ReinforcementClass();
        tester.inputAllBaseTypes();

        System.out.println("Number 3 is odd: " + ReinforcementClass.isOdd(3));
        System.out.println("Number 4 is odd: " + ReinforcementClass.isOdd(4));

    }

    public static boolean isOdd(int num){
        return (num & 0x1) == 1;
    }
    public void inputAllBaseTypes() {
        Scanner scan = new Scanner(System.in);

        if (scan.hasNextBoolean()) {
            bool = scan.nextBoolean();
        }
        if (scan.hasNextByte()) {
            b = scan.nextByte();
            c = (char) b;
            i = c;
            l = c;
            s = (short) c;
            d = c;
            f = (float)c;
        }

        System.out.println(toString());
        scan.close();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("boolean = " + this.bool);
        sb.append("\n\rbyte = " + this.b);
        sb.append("\n\rchar = " + this.c);
        sb.append("\n\rint = " + this.i);
        sb.append("\n\rlong = " + this.l);
        sb.append("\n\rshort = " + this.s);
        sb.append("\n\rdouble = " + this.d);
        sb.append("\n\rfloat = " + this.f);

        return sb.toString();

    }

}