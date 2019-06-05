//Author: Johnny Marquez

import java.io.*;
import java.util.*;
import javax.swing.JOptionPane;

public class TaskData {
    
    private ArrayList<String> employeeList = new ArrayList<>();
    private ArrayList<String> projectList = new ArrayList<>();
    private LinkedHashMap<String, HashSet<String>> mapEmployeeProject = new LinkedHashMap<>();
    private LinkedHashMap<String, HashSet<String>> mapProjectEmployee = new LinkedHashMap<>();
    
    public void readEmployeeFile()
    {
       String employeeName;
       String employeeFileName = "employee.txt";
       
          BufferedReader in = null;
            try{	
                    in = new BufferedReader(new FileReader(employeeFileName));
                    String line = in.readLine();
                    
                    while(line != null)  
                    {
                        employeeName = line;
                        employeeList.add(employeeName);
                        line = in.readLine();
                    }
                    in.close();
		}
		catch(IOException ioe)
		{
                        JOptionPane.showMessageDialog(null, "File was not found!", "Error", JOptionPane.ERROR_MESSAGE);
		}  
    }
    
    public void readProjectFile()
    {
        String projectName;
        String projectFileName = "project.txt";
       
          BufferedReader in = null;
            try{	
                    in = new BufferedReader(new FileReader(projectFileName));
                    String line = in.readLine();
                    
                    while(line != null)  
                    {
                        projectName = line;
                        projectList.add(projectName);
                        line = in.readLine();
                    }
                    in.close();
		}
		catch(IOException ioe)
		{
                        JOptionPane.showMessageDialog(null, "File was not found!", "Error", JOptionPane.ERROR_MESSAGE);
		}  
    }
    
    public void addEmployee(String employeeName)
    {
        String employeeFileName = "employee.txt";
        employeeList.add(employeeName);
        
         try
        {
            PrintWriter out = new PrintWriter(
                                new BufferedWriter(
                                    new FileWriter(employeeFileName, true)));
            
            if(employeeName != null)
            {
                out.write("\n" + employeeName);
                out.flush();
                out.close();
                
                JOptionPane.showMessageDialog(null, employeeName + " has been successfully added.", "Successfully added", JOptionPane.INFORMATION_MESSAGE); 
            }
        }
        catch(IOException e)
        {
              JOptionPane.showMessageDialog(null, "File not found!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void addProject(String projectName)
    {
        String projectFileName = "project.txt";
        projectList.add(projectName);
        
         try
        {
            PrintWriter out = new PrintWriter(
                                new BufferedWriter(
                                    new FileWriter(projectFileName, true)));
            
            if(projectName != null)
            {
                out.write("\n" + projectName);
                out.flush();
                out.close();

                JOptionPane.showMessageDialog(null, projectName + " has been successfully added.", "Successfully added", JOptionPane.INFORMATION_MESSAGE);     
            }
        }
        catch(IOException e)
        {
              JOptionPane.showMessageDialog(null, "File not found!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void readWorkonFile()
    {
        String columns[], employee, project;
        String workonFileName = "workon.txt";
        BufferedReader in = null;
            
            try{	
                in = new BufferedReader(new FileReader(workonFileName));
                String line = in.readLine();

                while(line != null)  
                {                            
                   columns = line.split("<>");
                   employee = columns[0];
                   project = columns[1];
                     
                   
                   if(mapEmployeeProject.containsKey(employee))
                      mapEmployeeProject.get(employee).add(project);
                   else        
                      mapEmployeeProject.put(employee, new HashSet<>(Collections.singletonList(project)));
                   
                   if(mapProjectEmployee.containsKey(project))
                      mapProjectEmployee.get(project).add(employee);
                   else        
                    mapProjectEmployee.put(project, new HashSet<>(Collections.singletonList(employee)));                   
                                      
                   line = in.readLine();
                }

                in.close();
            }
            catch(IOException ioe)
            {
                    JOptionPane.showMessageDialog(null, "File was not found!", "Error", JOptionPane.ERROR_MESSAGE);
            }            
            
    }
    
    public void writeWorkonFile(LinkedHashMap<String, HashSet<String>> assignEmployeeLinkedHashMap)
    {
        String assignEmployeeString = "";
        String workonFileName = "workon.txt";
        Set keyset = assignEmployeeLinkedHashMap.keySet();
        Object[] keysetArray = keyset.toArray();
        
        try
        {
            PrintWriter out = new PrintWriter(
                                new BufferedWriter(
                                    new FileWriter(workonFileName)));
                
                for(int i = 0; i < assignEmployeeLinkedHashMap.size(); i++)
                {
                    Object [] projectArray = assignEmployeeLinkedHashMap.get(keysetArray[i]).toArray();
                    
                    for(int n = 0; n < assignEmployeeLinkedHashMap.get(keysetArray[i]).size(); n++)
                    {
                        assignEmployeeString += keysetArray[i] + "<>" + projectArray[n] + "\n";
                    }
                }
                
                JOptionPane.showMessageDialog(null, "Assignment successful.", "Successfully assigned", JOptionPane.INFORMATION_MESSAGE);
                
                out.write(assignEmployeeString);
                out.flush();
                out.close();
           
        }
        catch(IOException e)
        {
              JOptionPane.showMessageDialog(null, "File not found!", "Error", JOptionPane.ERROR_MESSAGE);
        }        
    }
       
    public ArrayList<String> getEmployees()
    {
        return employeeList;
    }
    
    public ArrayList<String> getProjects()
    {
        return projectList;
    }                 
    
    public LinkedHashMap<String, HashSet<String>> getEmployeesByProject()
    {
        return mapEmployeeProject;
    }
    
    public LinkedHashMap<String, HashSet<String>> getProjectsByEmployee()
    {
        return mapProjectEmployee;
    }
}
