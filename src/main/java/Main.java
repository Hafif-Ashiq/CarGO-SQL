import Essentials.*;
import Pages.*;
import java.sql.*;

public class Main {
    public static void main(String[] args) {

        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");

            // Connection Code!
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/orcl","LAB_FINAL","database");

            Statement stmt = con.createStatement();

        }
        catch(Exception e){
            System.out.println("Error Connecting........");
        }

        Login_Signup main_page = new Login_Signup(Pages.Main.screen_Width, Pages.Main.screen_Height, Pages.Main.screen_Title, Pages.Main.screen_Image_Location);
        main_page.setVisible(true);
        Static.route.push(main_page);
    }
}
