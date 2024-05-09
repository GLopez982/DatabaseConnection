import java.sql.*;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;

import java.util.*;

public class DataQuery {

    // METHOD TO INSERT PROJECTLIST INTO THE TABLE. WILL CREATE THE TABLE IF NOT
    // CREATED.
    // *********** WORK IN PROGRESS ******************** /

    public static void insertProjects(ArrayList<Project> proj) {

        try {

            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/projectdb", "root",
                    "Whitehot2005!");
            String query = "INSERT INTO projects (projectName, managerName, numOfDevelopers) " + "VALUES(?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            // create table
            String createTable = "CREATE TABLE IF NOT EXISTS projects (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY," +
                    "projectName VARCHAR(50)," +
                    "managerName VARCHAR(50)," +
                    "numOfDevelopers INT" +
                    ")";
            PreparedStatement tabelStatement = connection.prepareStatement(createTable);
            tabelStatement.executeUpdate();

            for (Project project : proj) {
                statement.setString(1, project.getProjectName());
                statement.setString(2, project.getManagerName());
                statement.setInt(3, project.getNumOfDevelopers());
                statement.executeUpdate();
            }

            statement.close();
            connection.close();

        } catch (SQLException exception) {
            System.out.println("Error>>> " + exception.getMessage());
            exception.printStackTrace();

        }

    }

    public static void getQuery() {

        try {
            String getQuery = "SELECT * FROM projects";
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/projectdb", "root",
                    "Whitehot2005!");
            Statement resulStatement = connection.createStatement();

            ResultSet resultSet = resulStatement.executeQuery(getQuery);
            ResultSetMetaData resultMeta = resultSet.getMetaData();
            int colCount = resultMeta.getColumnCount();
            StringBuilder resultString = new StringBuilder();

            while(resultSet.next()){

            for(int i = 1; i < colCount; ++i){

                resultString.append(resultSet.getString(i)).append("\t");


            }
                resultString.append("\n");

            
        }

            System.out.println(resultString.toString());

        } catch (SQLException exception) {
            System.out.println("Error>>> " + exception.getMessage());
        }

    }// end of getQuery

    //MAIN METHOD
    public static void main(String[] args) {
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

        // TESTING IF PROJECTS WERE CORRECTLY ADDED. **WORKING**
        for (Project proj : projects) {
            JOptionPane.showMessageDialog(null, proj.toString());
        }

        getQuery();
        //insertProjects(projects);


        keyboard.close();
        

    }// END OF MAIN

}
