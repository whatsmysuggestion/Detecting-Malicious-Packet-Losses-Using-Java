/*
   This class is used to validate the given user name
   and password
*/

import java.io.*;
import java.util.StringTokenizer;

public class PasswordValidator {

   private  File              file        = null;
   private  FileInputStream   fis         = null;
   private  DataInputStream   dis         = null;

   private boolean            flag        = false;   

   private final static String FILE_NAME = "pass.db"; 


  
  public PasswordValidator(String uname,String passwd) throws Exception {
     initialize();
     validate(uname,passwd);
     closeFile();
     
  }

  public void initialize() throws Exception {

     file = new File(FILE_NAME);
     fis = new FileInputStream(file);
     dis = new DataInputStream(fis);

  }


  public void validate(String uname,String passwd) throws Exception {

      String str = dis.readLine();
      while( str != null ) {

          StringTokenizer st = new StringTokenizer(str,"/");
          String userName = st.nextToken().trim();
          String password = st.nextToken().trim();
          if ( userName.equals(uname.trim())) {
             if ( password.equals(passwd.trim())) {
                flag = true;
             }
         }
         
         if ( flag ) {
            break; 
         }       
           
     
      }
  }


  public boolean isValid() throws Exception {

     if ( flag )  {
         return true;
     }
     else {
         return false;
     } 
     
  }  


  public void closeFile() throws Exception {
     dis.close();
     fis.close(); 
  } 
   
}
