import java.util.Scanner;

public class HexDigit2Dec {
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        String hex = input.next();

        if (hex.length() != 1){
            System.out.println("You ");
            System.exit(1);
        }

        char ch = Character.toUpperCase(hex.charAt(0));
        if (ch <= 'F' && ch >= 'A'){
            int v = ch - 'A' + 10;
            System.out.println(v);
        }else if (Character.isDigit(ch)){
            System.out.println(ch);
        }else {
            System.out.println();
        }
    }
}
