import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.sql.*;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class MenuFrame extends JFrame implements ActionListener {

    private final int WIDTH = 300;
    private final int HEIGHT = 300;
    private String[] queryString = { "Add to database", "Get database", "Quit" };
    private JComboBox<String> queryChoice = new JComboBox<>(queryString);
    private JButton execute = new JButton("Execute");

    public MenuFrame() {
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setLayout(new FlowLayout());
        add(queryChoice);
        add(execute);

        queryChoice.setBackground(Color.BLACK);
        execute.setBackground(Color.orange);

        execute.addActionListener(this);

    }

    public void actionPerformed(ActionEvent event) {
        Object source = event.getSource();
        String getChoice = queryChoice.getSelectedItem().toString();

        if (getChoice == "Add to database") {

            Project newProject;
            ArrayList<Project> projects = new ArrayList<>();
            boolean isRunning = true;
            char addProject;
            Scanner keyboard = new Scanner(System.in);
            String projectName;
            String managerName;
            int numOfDevelopers;

            // WHILE LOOP FOR ADDING PROJECTS TO THE PROJECT LIST
            while (isRunning) {
                System.out.println("Press 'A' to add a project or press Q to quit");
                addProject = keyboard.next().charAt(0);

                // IF LOOP FOR ADDING PROJECT
                if (Character.toUpperCase(addProject) == 'A') {
                    projectName = JOptionPane.showInputDialog(null, "What is your project name? ");
                    managerName = JOptionPane.showInputDialog(null, "What is the Manager's name? ");
                    numOfDevelopers = Integer
                            .parseInt(JOptionPane.showInputDialog(null, "How many developers are on the Project?"));
                    newProject = new Project(projectName, managerName, numOfDevelopers);

                    projects.add(newProject);

                    System.out.println("");

                    // IF LOOP FOR QUITTING PROJECT ADDITION
                } else if (Character.toUpperCase(addProject) == 'Q') {
                    isRunning = false;
                } else {
                    System.out.println("Please make a valid choice");
                }

            }
            keyboard.close();

        }

        // IF LOOP FOR GETTING THE DATABASE WHEN SELECTED.
        if (getChoice == "Get database") {
            try {
                String getQuery = "SELECT * FROM projects";
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/projectdb", "root",
                        "Whitehot2005!");
                Statement resulStatement = connection.createStatement();

                ResultSet resultSet = resulStatement.executeQuery(getQuery);
                ResultSetMetaData resultMeta = resultSet.getMetaData();
                int colCount = resultMeta.getColumnCount();
                StringBuilder resultString = new StringBuilder();

                // ITERATES THROUGH THE RESULTSET UNTIL THE NEXT IS NULL
                while (resultSet.next()) {

                    // FOR LOOP FOR GOING THROUGH THE COLUMNS AND APPENEDING THE RESULTS TO THE
                    // STRINGBUILDER STRING.
                    for (int i = 1; i < colCount; ++i) {

                        resultString.append(resultSet.getString(i)).append("\t");

                    }
                    // APPENDS A NEW LINE AFTER THE RESULTS TO PROVIDE ROWS.
                    resultString.append("\n");

                }

                // PRINTING OUT THE RESULTS AND CLOSING RESOURCES.
                System.out.println(resultString.toString());
                connection.close();
                resulStatement.close();

            } catch (SQLException exception) {
                System.out.println("Error>>> " + exception.getMessage());
            }

        }

        if(getChoice == "Quit"){
            dispose();
        }

    }

    public static void main(String[] args) {
        MenuFrame frame = new MenuFrame();
        frame.setVisible(true);
    }

}
