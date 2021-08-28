/*
    This class is used to forward the packets
    to the Recevier
*/

import java.io.*;
import java.net.*;
import java.util.Vector;
import javax.swing.*;

public class PacketForwarder implements ReceiverConfigIF {


    private Socket         socket        = null;
    private OutputStream   outputStream  = null;
    private PrintStream    printStream   = null;

    private String         packet        = null;
   
    /*
       Constructor 
    */   
    public PacketForwarder(String packet) throws Exception {
        
        this.packet = packet;         
        
        //api call for connecting 
        connectToReceiver();
        
        // api call to forward the packet
        sendPacket(packet);
  
        //api call to close the connection
    //    closeConnection();
        
    }

    /*
       This method is used to connect the Router
       to the receiver
    */   
    public void connectToReceiver() throws Exception {
        
        //connecting the socket to the Receiver
        socket = new Socket(RECEIVER_IP,RECEIVER_PORT);
        
        //Initialize streams required for data transfer
        outputStream = socket.getOutputStream();
        printStream  = new PrintStream(outputStream);

    }


    /*
       This method is used to forward the packets
       to the Recevier
    */
    public void sendPacket(String packet) throws Exception {
        if ( packet != null){
               printStream.println(packet.trim());
               printStream.flush();
        }
    }


   


    /*
       This method is used to close the connection
       with the Receiver
    */
    public void closeConnection() throws Exception {
        printStream.close();
        outputStream.close();
        socket.close();
       
    }
    
}

