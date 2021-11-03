/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.coursedatabase;

import java.io.IOException;
import java.util.LinkedList;

/**
 *
 * @author Pc Planet
 */
public class CourseDBStructure implements CourseDBStructureInterface {
     LinkedList<CourseDBElement> linkedlist;
     public LinkedList[] HashTable;
     public int size;
     public CourseDBStructure(int estimated){
       
         HashTable = new LinkedList[estimated];
         for(int i=0;i<estimated;i++)
         {
             HashTable[i]=null;
         }
         this.size = estimated;
         
     }
       public CourseDBStructure(String test,int size){
         // String and an int
         HashTable = new LinkedList[size];
          for(int i=0;i<size;i++)
         {
             HashTable[i]=null;
         }
          this.size = size;
     }
    @Override
    public void add(CourseDBElement element) {
       int crn = element.CRN;
       int hashcode = crn % size;
       if(HashTable[hashcode] == null)
       {
           linkedlist = new LinkedList<CourseDBElement>();
           linkedlist.add(element);
           HashTable[hashcode]= linkedlist;
       }
       else
       {
           HashTable[hashcode].add(element);
       }
       
    }

    @Override
    public CourseDBElement get(int crn) throws IOException {
        int hashcode = crn % size;
        linkedlist = HashTable[hashcode];
     
        for(int i=0;i<linkedlist.size();i++)
        {
            if(linkedlist.get(i).CRN == crn )
            {
                CourseDBElement CRD = linkedlist.get(i);
                return CRD;
            }
        }
       
        return null;
    }

    @Override
    public int getTableSize() {
        return size;
    }
    
}
