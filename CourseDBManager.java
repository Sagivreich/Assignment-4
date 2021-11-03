
package com.example.coursedatabase;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CourseDBManager implements CourseDBManagerInterface  {
   public int estimatedsize = 5;
   
    File file;
    CourseDBStructure dbstr = new  CourseDBStructure(estimatedsize);
    @Override
    public void add(String id, int crn, int credits, String roomNum, String instructor) 
    {
          if(crn!=0)
          {
           CourseDBElement element =new CourseDBElement();
           element.CRN = crn;
           element.Course_ID = id;  
           element.instructor_name = instructor;
           element.number_of_credits = credits;
           element.room_number = roomNum;
           dbstr.add(element);
          
           String data =  element.Course_ID + " "+element.CRN + " "+element.number_of_credits 
                                +" "+element.instructor_name + " "+element.room_number;  
               try {   
                   databaseWrite(data) ;
               } catch (IOException ex) {
                   Logger.getLogger(CourseDBManager.class.getName()).log(Level.SEVERE, null, ex);
               }
          }
           
    }

    @Override
    public CourseDBElement get(int crn) 
    {
         System.out.println("In CDE "+ crn);
       try {
           CourseDBElement db= dbstr.get(crn);
           if(db !=null)
           {
               return db;
           }
           else
           {
               return null;
           }
       } catch (IOException ex) {
           Logger.getLogger(CourseDBManager.class.getName()).log(Level.SEVERE, null, ex);
       }
         System.out.println("OUT CDE "+ crn);
       return null;
    }

    @Override
    public void readFile(File input) throws FileNotFoundException 
    {
        try  
        {  
                  this.file= input;
                  FileReader fr=new FileReader(input);   
                  BufferedReader br=new BufferedReader(fr);  
                  //StringBuffer sb=new StringBuffer();    
                  String line;  
                  while((line=br.readLine())!=null)  
                  {  
                 // sb.append(line);
                  String[] splitStr = line.split("\\s+");
                  String id = splitStr[0];
                  int crn = Integer.parseInt(splitStr[1]);
                  int credits= Integer.parseInt(splitStr[2]);
                  String roomNum = splitStr[3];
                  String instructor = splitStr[4];
                  add(id,crn,credits,roomNum,instructor);
                  }  
                  fr.close();      
 
        }  
        catch(IOException e)  
        {  
         e.printStackTrace();  
        } 
    }

    @Override
    public ArrayList<String> showAll() 
    {
       ArrayList<String> strArray = new ArrayList<>();
       LinkedList[] table =  dbstr.HashTable;
       LinkedList<CourseDBElement> linkedlist;
       int size = dbstr.size;
       for(int i=0; i<size;i++)
       {
           if(table[i]!=null)
           {
                linkedlist = table[i];
                if(!linkedlist.isEmpty())
                {
                    for (int j=0;j<linkedlist.size();j++)
                    {
                        String data =  "Course: "+linkedlist.get(j).Course_ID + "  CRN: "+linkedlist.get(j).CRN + "  Credits: "+linkedlist.get(j).number_of_credits 
                                +"  Instructor: "+linkedlist.get(j).instructor_name + "  Room: "+linkedlist.get(j).room_number;  
                        strArray.add(data);
                    }
                }
           }
       }
       return strArray;
    }
    
    public void databaseWrite(String data) throws IOException {
	File dir = new File(".");
	String loc = dir.getCanonicalPath() + File.separator + "src/MyDatabase.txt";
        FileWriter fstream = new FileWriter(loc, true);
	BufferedWriter out = new BufferedWriter(fstream);
	out.write(data+"\n");
	out.close();
}
}
