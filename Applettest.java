package applettest;

import java.sql.*;
import java.io.*;

public class Applettest{
  public static void main(String [] args) {
    Connection con = null;
    try {
      Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
      con = DriverManager.getConnection("jdbc:odbc:extrabyte");
      Statement sta = con.createStatement(); 
      BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
      String name; int c;
      float sem1gpa,sem2gpa,sem3gpa;
      System.out.println(" Enter 1 to continue insertion or enter 0 to stop\n\n");
      int count = 0;
       float cgpa;

       // real type is for float
     // int ij=sta.executeUpdate("CREATE TABLE student_details(name VARCHAR(32) ,sem1gpa REAL ,sem2gpa REAL, sem3gpa REAL,cgpa REAL) " );

      System.out.println(" Enter your choice\n");
      int choice = Integer.parseInt(br.readLine());

      while(choice==1) {
        name = br.readLine();
        sem1gpa = Float.parseFloat(br.readLine());
        sem2gpa = Float.parseFloat(br.readLine());
        sem3gpa = Float.parseFloat(br.readLine());

        // approx calculation of cgpa
        cgpa = (sem1gpa + sem2gpa + sem3gpa) / 3;

        c = sta.executeUpdate("INSERT INTO student_details"
                                + " ( name , sem1gpa , sem2gpa , sem3gpa , cgpa)"
                                + " VALUES ('" + name + "', '" + sem1gpa + "', '" + sem3gpa + "', '" + sem3gpa + "', '" + cgpa + "')");

      count = count + c;

      System.out.println(" Enter your choice\n");
      choice = Integer.parseInt(br.readLine());
      }


      System.out.println("Number of rows inserted: "+count);


      ResultSet rs = sta.executeQuery( "SELECT name,cgpa FROM student_details ORDER BY cgpa DESC" ) ;
      
      System.out.println("Result set is :"+rs);
      // Loop To display the searched entry of the Data base
      while( rs.next() ) {
          System.out.print("Name:"+rs.getString("name") + "       ") ;
          System.out.println("CGPA :"+rs.getString("cgpa")) ;         
      }
      sta.close();
      con.close();        
    } catch (Exception e) {
        e.printStackTrace();
    }
  }
}