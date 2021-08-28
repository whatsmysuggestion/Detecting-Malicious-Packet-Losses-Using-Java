/*
   This class is used to obtain the ip address, port and algorithm
   apecific details from the config file 
*/
import java.io.*;
import java.util.Vector;

public class ConfigFileParser  implements NetConfigIF {
  
   private  File            file               = null;
   private  FileInputStream fileInputStream    = null;
   private  DataInputStream dataInputStream    = null;

   /*
       Constructor
   */
   public ConfigFileParser() throws Exception {

     file = new File(CONFIG_FILE_NAME);
     fileInputStream = new FileInputStream(file);
     dataInputStream = new DataInputStream(fileInputStream); 

   }

   /* 
      This method is used to parse the file and
      obtain the details from it  
   */

   public Vector getNetworkDetails() throws Exception {

      Vector vector = new Vector();

      String str = dataInputStream.readLine();

      while ( str != null ) {
  
            vector.addElement(str);
            str = dataInputStream.readLine();

      }  

      return vector; 

      
   }


   /*
     This method is used to close the connectivity to the file
   */
   public void closeFile() throws Exception {
     fileInputStream.close(); 
   }



}