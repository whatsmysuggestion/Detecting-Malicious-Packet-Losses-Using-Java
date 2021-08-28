/*
   This class is used to decrypt the data arrived 
*/

import java.io.*;
import java.net.*;
import java.util.*;
import java.security.*;
import javax.crypto.*;

public class DecryptionEngine implements NetConfigIF {
     ServerSocket      serverSocket        = null;   
     Socket            socket              = null;
     InputStream       inputStream         = null;
     ObjectInputStream objectInputStream   = null;
	 InputStreamReader inputStreamReader   = null;
	 BufferedReader bufferedReader		   = null;	
     
     DestinationRouterUI  destinationRouterUI = null;
     public String algo="Encipher";

     public DecryptionEngine() throws Exception {
      try { 
        destinationRouterUI = new DestinationRouterUI("DestinationRouter"); 
		initializeStream();
        while( true ) {
			System.out.println("*********");
			System.out.println("in while with true");
			System.out.println("*********");

			 
          String data =this.decryptData();
		  System.out.println("data in DecpyptionEngine after decrypt"+data);
          sendDataToReceiver(data);
        }
        //closeConnection();
       }catch (Exception e) { 
		// return;
	 //  e.printStackTrace();
	   }
     }

     public void initializeStream() throws Exception {
        serverSocket = new ServerSocket(ROUTER_PORT);
        socket = serverSocket.accept();
        inputStream = socket.getInputStream();
         objectInputStream = new ObjectInputStream(inputStream); 
	


     }


     public String decryptData() throws Exception {

      
			  System.out.println("in method decryptData() in 44");
   
		
        objectInputStream = new ObjectInputStream(inputStream); 
		

	String cipher=(String)objectInputStream.readObject();

	  

	System.out.println("in 50 the cipher data is"+cipher);

	RC4CipherEntity r=new RC4CipherEntity ();
	String str=r.doDecryption(cipher,algo);
	 System.out.println("in  53  of decryptionEngine Data Decrypted:"+ str);
   	 StringBuffer sb = new StringBuffer(); 
     destinationRouterUI.addToLog(str);
   	Vector vector = new Vector();
	System.out.println("in 61 of DecryptionEngine Data Decrypted in Vector is :"+ vector);
       	StringTokenizer tokenizer = new StringTokenizer(str,"&");
       	while ( tokenizer.hasMoreElements() ) 
	     {
			System.out.println("while loop iteration");
        	  vector.addElement(tokenizer.nextToken().trim());
      	 } 

       	destinationRouterUI.addData(vector);
      	 
 		 System.out.println("in 69 of Decryption Engine Data Decrypted after while loop of  vector is:"+ str);
  				   return str;
     	}


     public void closeConnection() throws Exception {

       	 objectInputStream.close();
        	inputStream.close();
       	 serverSocket.close(); 
        	socket.close();
     
     	}

     public void sendDataToReceiver(String data) throws Exception {

		 System.out.println("sendDataToReceiver"+data);
    
        PacketForwarder forwarder = new PacketForwarder(data);
     }
}


