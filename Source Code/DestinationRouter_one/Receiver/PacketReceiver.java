/*
   This class is used to receive the incoming packets

*/

import java.io.*;
import java.net.*;
import java.util.*;

public class PacketReceiver implements NetConfigIF {
    ServerSocket    serverSocket   = null;
    Socket          socket         = null;
    
    InputStream        is           = null;
    InputStreamReader  isr          = null;
    BufferedReader     br           = null;

    ReceiverUI         receiverUI   = null;


    public PacketReceiver(ReceiverUI receiverUI) throws Exception {
 
          this.receiverUI = receiverUI;

          intializeSetup();
          intializeStream();
          receivePackets();
          closeConnection();
    }

    public void intializeSetup() throws Exception {
      serverSocket = new ServerSocket(RECEIVER_PORT);
      socket = serverSocket.accept();
        
    }

    public void intializeStream() throws Exception {
      is = socket.getInputStream();
      isr = new InputStreamReader(is);
      br = new BufferedReader(isr);
    }

    public void receivePackets() throws Exception {
      String str = br.readLine();
      System.out.println("Received packet:"+str);
      Vector vector = new Vector();
      StringTokenizer tokenizer = new StringTokenizer(str,"&");
      while ( tokenizer.hasMoreElements() ) {
         vector.addElement(tokenizer.nextToken().trim());
      } 

      receiverUI.addData(vector);

    }


    public void closeConnection() throws Exception {
       isr.close();
       is.close();
       socket.close();
       serverSocket.close();
    }

}