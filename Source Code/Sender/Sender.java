import java.io.*;
import java.net.*;
import java.util.*;

public class Sender implements ConfigIF, SourceConfigIF {

    // socket reference
    Socket         socket     = null;
    //OutputStream Reference
    OutputStream   os         = null;
    PrintStream    ps         = null;
    Vector         packets    = null;


    
   /*
      Constuctor for intialization
   */   
    
    public Sender() throws Exception {
        intializeSetup();
        intializeStream();
        /*packets = getInput();
        sendPackets(packets);
        closeConnection();*/
    }

    /*
        This method is used to connect to server
        and setup the client 
    */   
    public void intializeSetup()throws Exception {
        //connects to server by providing
        // ip address and port number
        socket = new Socket(routerIP,routerPort);
System.out.println("in 36 routerIP=="+routerIP);
System.out.println("in 37 routerPort=="+routerPort);
    }

    /*
       This method is used to obtain the 
       stream required for packets transfer
    */
    public void intializeStream() throws Exception{
        //Obtain the output stream from the 
        //socket created
        os = socket.getOutputStream();
		System.out.println("in 48 os=="+os);
        //Wrap the output stream to the print stream
        ps = new PrintStream(os);
		System.out.println("in 51 ps=="+ps);
    }
    

    /*
       This Methos is used to obtain the packets from
       user
    */
    public Vector getInput() throws Exception {
       //Obtain the input from the user 
       DataInputStream dis = new DataInputStream(System.in);
       System.out.println("Enter No Of Packets:");
       //read the input 
       String str = dis.readLine();
       
       //Calculate the total number of packets to be sent
       int packetCount = Integer.parseInt(str);
       System.out.println("Enter the Packets");
       
       //Intialize the vector to hold packets
       packets = new Vector();
       
       //Read the packets from the user
       for(int i = 0; i < packetCount; i++) {
           str = dis.readLine();
           packets.addElement(str);
       }
       return packets;
    }

    /*
       This method is used to send packets to the server

    */   

    public void sendPackets(Vector packets) throws Exception{

      //calculate the start time of transfer  
      long startTime = System.currentTimeMillis();
      ps.println("START");       
      //send all packets to server
      for(int i = 0; i < packets.size(); i++) {
          StringBuffer buffer = new StringBuffer();
          
          String str = (String) packets.elementAt(i);
        //   System.out.println(" in 96  str=="+str);
          buffer.append(sourceIP);
          buffer.append("&");
          
         /* buffer.append(destIP);
          buffer.append("&");*/
          
          buffer.append(String.valueOf(2));
          buffer.append("&");

          buffer.append(str);
          buffer.append("&");
          
          buffer.append("TCP");
          buffer.append("&");

          buffer.append(buffer.length());

          ps.println(buffer.toString());
         // ps.println("END");
      }
      ps.println("END");
      
      //calculate the end time
      long endTime = System.currentTimeMillis();
      //calculate the total time
      long totalTime = endTime - startTime;

      System.out.println("Start Time :"+ startTime);
      System.out.println("End   Time :"+ endTime);
      System.out.println("Total Time :"+ totalTime);
        
    }


    /*
       This method is used to close the connection
       with the server
    */
    public void closeConnection() throws Exception {
        ps.close();
        socket.close();
        System.out.println("Client Connection Closed");
    }
    
    public static void main(String[] args) throws Exception {
       Sender sender = new Sender(); 
    }
     
}
