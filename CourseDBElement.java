
package com.example.coursedatabase;

public class CourseDBElement implements Comparable {
   
   public String Course_ID;
   public int number_of_credits;
   public String room_number ;
   public String instructor_name;
   public int CRN;
   
    @Override
    public int compareTo(Object o) {
        int crn = (int)o;
        return crn;
    }
    public void setCRN(int crn)
    {
        this.CRN=crn;
    }
}
