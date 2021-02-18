import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.*;

public class mouseDBRunner {

    public static void main(String Args[]) throws SQLException {
        mouseDBInterface ux = new mouseDBInterface();
        boolean goer = true;
        do{
            ux.menu();
            //implement something to stop loop if user indicates

        }
        while(goer);

    }
    
}