import java.util.InputMismatchException;
import java.util.Scanner;

public class Lecture12 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        boolean c = true;

        do {
            try {
                int n = input.nextInt();

                System.out.println(n);
                c = false;
            } catch (InputMismatchException p) {
                System.out.println("  dfsd");
                input.nextLine();
            }
        } while (c);
    }

    public void m3() throws Exception {
    }
}
