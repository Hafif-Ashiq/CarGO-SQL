package Pages;

import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.*;

import Essentials.*;
import Essentials.Button;

public class Store {
    protected JFrame frame;
    protected JPanel panel;
    protected ImageIcon image;
    protected JLabel label;
    protected Font font;
    private ArrayList<Backend.Manufacturer> array = new ArrayList<>();
    // Side Buttons
    private Button Home, Store, Booking, Back;
    
    // Argumented Constructor
    public Store(int width, int height, String title, String location, String text, String Name) {

        this.frame = new JFrame();

        // Essentials for menu display
        this.frame.setSize(width, height);
        this.frame.setLayout(null);
        this.frame.setResizable(false);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.getContentPane().setBackground(new Color(Main.bg, Main.bg, Main.bg));

        // Title and Logo
        this.image = new ImageIcon(location);
        this.frame.setIconImage(image.getImage());
        this.frame.setTitle(title);

        // Side Buttons
        int start = 144;
        
        // Adding Text Store
        Text store = new Text(this.frame, 350, 30, 180, 100, "Store", "C:\\Users\\Abdullah\\Desktop\\DatabaseProject\\src\\main\\Fonts\\Montserrat-Bold.ttf", 40f);
        store.setInvertColor();
        
        //Adding Logo to the Top
        new Picture(this.frame, "C:\\Users\\Abdullah\\Desktop\\DatabaseProject\\src\\main\\Logo\\CarGo-Light-PNG.png", 200, 42, 60, 60);
        new Text(this.frame, 40, 26, 140, 60, "Welcome,", "C:\\Users\\Abdullah\\Desktop\\DatabaseProject\\src\\main\\Fonts\\Montserrat-SemiBold.ttf", 18f);
        new Text(this.frame, 40, 55, 180, 60, Name, "C:\\Users\\Abdullah\\Desktop\\DatabaseProject\\src\\main\\Fonts\\Montserrat-Bold.ttf", 30f);
        
        JPanel dark = new JPanel();
        dark.setBounds(0, 0, 300, 144);
        dark.setBackground(new Color(Main.fg, Main.fg, Main.fg));
        frame.add(dark);

        //frame.add(homeLogo.setLogo());

        // Current Stick-Bar Indicating what page we currently on
        JPanel stick = new JPanel();
        stick.setBounds(0, start*2+2, 7, 144);
        stick.setBackground(new Color(Main.bg, Main.bg, Main.bg));
        stick.setLayout(new BorderLayout(40, 10));
        frame.add(stick);
        
        Home = new Button(false);
        frame.add(Home.newButton(0, start+1, 300, 144, "Home", "C:\\Users\\Abdullah\\Desktop\\DatabaseProject\\src\\main\\Fonts\\Montserrat-Bold.ttf", 20f));
        
        Store = new Button(false);
        frame.add(Store.newButton(0, start*2+2, 300, 144, "Store", "C:\\Users\\Abdullah\\Desktop\\DatabaseProject\\src\\main\\Fonts\\Montserrat-Bold.ttf", 20f));
        Store.setEnabled(false);
        
        Booking = new Button(false);
        frame.add(Booking.newButton(0, start*3+3, 300, 144, "Booking", "C:\\Users\\Abdullah\\Desktop\\DatabaseProject\\src\\main\\Fonts\\Montserrat-Bold.ttf", 20f));

        Back = new Button(false);
        frame.add(Back.newButton(0, start*4+4, 300, 144, "Back", "C:\\Users\\Abdullah\\Desktop\\DatabaseProject\\src\\main\\Fonts\\Montserrat-Bold.ttf", 20f));
        
        
        /* JPanel scrollPanel = new JPanel();        
        scrollPanel.setBounds(0 ,0 , 1280, 2000);
        scrollPanel.setBackground(new Color(203, 203, 203));
         *///scrollPanel.setPreferredSize(new Dimension( 860+5,2000));
        //scrollPanel.setLayout(new GridLayout());
        
        /* JScrollPane scroll = new JScrollPane(scrollPanel);
         *///frame.add(scroll);
        
        //frame.add(scrollPane);
        

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");


            // Connection Code!
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/orcl", "LAB_FINAL", "database");

            Statement stmt = con.createStatement();

            String company = Static.current_user;

            ResultSet rs = stmt.executeQuery("SELECT * FROM manufacturer NATURAL join picture");

            // String company, int id, Picture carPicture
            while (rs.next()){
                array.add(new Backend.Manufacturer(rs.getString(4),rs.getInt(2),new Backend.Picture(rs.getInt(1),rs.getString(6),rs.getString(5))));
            }
        }
        catch (Exception e){
            System.out.println("Error <Store>....");
        }

        // Use this as an array list
        int start_x = 0;
        int start_y = 0;

        // frame.add(scrollPanel)
        for(int i = 1; i <= array.size(); i++){
            if (i % 4 == 0){
                new Companies(frame, 350 + start_x, 140 + start_y, array.get(i-1).getCarPicture().getPicture_address(), array.get(i-1).getCompany());
                start_x = 0;
                start_y = start_y + 265;
            }
            else{
                new Companies(frame, 350 + start_x, 140 + start_y, array.get(i-1).getCarPicture().getPicture_address(), array.get(i-1).getCompany());
                start_x = start_x + 225;
            }
        }
    }

    public void dispose() {
        this.frame.dispose();
    }

    // Must call Method to display
    public void setVisible(boolean set) {
        this.frame.setVisible(set);
    }

    // Getters
    public JFrame getFrame() {
        return this.frame;
    }
}
