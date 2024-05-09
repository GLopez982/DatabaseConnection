public class Project {

    private String projectName;
    private String managerName;
    private int numOfDevelopers;

    //CONSTRUCTOR FOR THE PROJECT OBJECT
    public Project(String projectName, String managerName, int numOfDevelopers){
        this.projectName = projectName;
        this.managerName = managerName;

        if(numOfDevelopers <= 0)
            System.out.println("Please assign at least one developer");
        else
            this.numOfDevelopers = numOfDevelopers;
        
        
    }


    //SETTERS AND GETTERS FOR ATTRIBUTES
    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public void setNumOfDevelopers(int numOfDevelopers) {
        this.numOfDevelopers = numOfDevelopers;
    }


    public String getProjectName() {
        return projectName;
    }


    public String getManagerName() {
        return managerName;
    }


    public int getNumOfDevelopers() {
        return numOfDevelopers;
    }

    @Override
    public String toString(){
        return projectName + " \t" + managerName + " \t" + numOfDevelopers;

    }

    public static void main(String[] args) {
        Project VirtualProject = new Project("Virtual Dr", "Chad Bullsworth",2 );
        Project VisualWorks = new Project("Visual Assist", "Sheila Thompson", 45);

        StringBuilder ProjectList = new StringBuilder();

        ProjectList.append(VirtualProject).append("\n");
     
        ProjectList.append(VisualWorks);





        System.out.println(ProjectList);

    }



    

    





    
    
}
