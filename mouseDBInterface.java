import java.sql.SQLException;
import java.util.*;
public class mouseDBInterface {

    public void menu() throws SQLException {
        mouseDBAccessor acc = new mouseDBAccessor();
        Scanner kb = new Scanner(System.in);
        String input;

        System.out.println();
        System.out.println("Welcome to the MouseLab DB Program");
        System.out.println();
        System.out.println("=====================================================");
        System.out.println("Please select an option:");
        System.out.println("1. View mice with selected parameters");
        System.out.println("2. Add a mouse with required information");
        System.out.println("3. Delete a mouse");
        System.out.println("=====================================================");
        input = kb.nextLine();
        if(input.equals("1")){
            //clears terminal, doesnt work for some reason
            //clearScreen();
            acc.viewMouse();

        }
        if(input.equals("2")){
            //clears terminal, doesnt work for some reason
            //clearScreen();
            acc.addMouse();

        }
        if(input.equals("3")){
            //clears terminal, doesnt work for some reason
            //clearScreen();
            acc.deleteMouse();

        }




    }
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}