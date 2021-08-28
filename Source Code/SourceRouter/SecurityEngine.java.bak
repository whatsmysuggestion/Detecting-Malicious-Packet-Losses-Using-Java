/*
   This class is used to receive the data and encrypt it according to
   their security algorithms like RC4 Encryption..........
*/
import java.io.*;
import java.net.*;
import java.util.*;
import javax.crypto.*;
import java.security.*;
import javax.swing.*;


public class SecurityEngine extends ConfigFileParser {

     private   Socket               socket               = null;
     private   OutputStream         outputStream         = null;
     private   ObjectOutputStream   objectOutputStream   = null;
     private   Vector               packets              = null; 
	private String algo="Encipher";
	//private String cipherText=null;
     
     private static final String SEPERATOR = "/"; 

     private String ip         = null;
     private String algorithm  = null;
     private int    port       = 0;

     private SourceRouterUI sourceRouterUI = null;
       
     /*
        Constructor
     */
     public SecurityEngine(Vector packets,
                     SourceRouterUI sourceRouterUI) throws Exception {

	   System.out.println("packets in SecurityEngine"+packets);
       this.packets = packets;  
       this.sourceRouterUI = sourceRouterUI; 
       
       Vector vector = getNetworkDetails();
       processVector(vector, packets);
       closeConnection();  
    
     }



     /*
        This method is used to obtain the ip and algorithm
     */    
     public void processVector(Vector vector, Vector packets) throws Exception { 	
        int size = vector.size();
        for ( int i = 0; i < size; i++) {
           String details = (String) vector.elementAt(i);
           StringTokenizer st = new StringTokenizer(details,SEPERATOR);
           ip =  st.nextToken();
           port = Integer.parseInt(st.nextToken().trim());
           algorithm = st.nextToken();           
         
           encryptAndSend(ip.trim(),port,algorithm.trim(),packets);
            
        }    
     }

   
     /*
        This method is used encrypt and send data to the
        destination router 
     */
     public void encryptAndSend(String ip,int port,
                   String algorithm,Vector packets)throws Exception{ 
       try {

		   System.out.println("SourceRouterUI"+SourceRouterUI.chooser.isSelected());
		   System.out.println("in encryptAndSend"+ip+""+port);
		   System.out.println("packets in encryptAndSend"+packets);
         socket = new Socket(ip,port);
         outputStream = socket.getOutputStream();
         objectOutputStream = new ObjectOutputStream(outputStream);
int i=0,j=0,total=0;
int pack_size=packets.size();
   for( int iter = 0; iter <pack_size; iter++ ) {

           addPacketToTable((String)packets.elementAt(iter));

       
	String value= (String) packets.elementAt(iter);
                   System.out.println("Value from SecurityEngine in 84 "+value);
	RC4CipherEntity r=new RC4CipherEntity();
	String cipherText=r.doEncryption(value,algo);
		 // System.out.println(cipherText);
      	StringBuffer sb = new StringBuffer(cipherText);
		int cap=sb.capacity();
		total=total+cap;
		System.out.println("StringBuffer capacity "+cap+"bytes");

 	objectOutputStream = new ObjectOutputStream(outputStream);
		System.out.println("cypertext before sending to receiving router"+cipherText);
		i=(int)(Math.random()*10);
		System.out.println("i  "+i);
		if(SourceRouterUI.chooser.isSelected())
	   {
		//if(total<=200)
		System.out.println("total value"+total);
		if(total<=200)
		{
        objectOutputStream.writeObject(cipherText);
		sourceRouterUI.addToLog(algo+" "+sb);
		System.out.println("in Security Engine 94 Values of algo n sb are "+algo+sb);
	  }
	  else
	   {
		  j++;
			objectOutputStream.writeObject("probs");
		//   System.out.println("SHARATH");
			//JOptionPane.showMessageDialog(null,"1 packet lossing");
			
	   }
	   }
	   else
	   {
		objectOutputStream.writeObject(cipherText);
		sourceRouterUI.addToLog(algo+" "+sb);
	   }
	   //i++;

       // objectOutputStream.flush();
       // sourceRouterUI.addToLog(algo+" "+sb);
		//System.out.println("in Security Engine 94 Values of algo n sb are "+algo+sb);
         }

		// System.out.println("total packets size is: "+total);
		 if(j!=0)
		 JOptionPane.showMessageDialog(null,j+" packets lossing bcz of buffer overflow");
         closeConnection(); 
      }catch(Exception e) { e.printStackTrace();}

     }


     public void addPacketToTable(String data) throws Exception { 

       Vector vector = new Vector();
       StringTokenizer tokenizer = new StringTokenizer(data,"&");
       while ( tokenizer.hasMoreElements() ) {
          vector.addElement(tokenizer.nextToken().trim());
       } 

       sourceRouterUI.addData(vector);

 
     }     

     /*
        This method is used to close the connection
     */ 
     public void closeConnection() throws Exception {
       objectOutputStream.close();
       socket.close();
       
     }

    
    
} 


