/*
   This class is used to handle the client request in a seperate Thread
   This class handles all the operations required by the client
 */

import java.io.*;
import java.net.*;
import java.util.Vector;

public class RoutingServiceHandler extends Thread {

    private  Socket                 socket             = null;
    private  InputStream            inputStream        = null;
    private  OutputStream           outputStream       = null;
    private  InputStreamReader      inputStreamReader  = null;
    private  BufferedReader         bufferedReader     = null;
    private  PrintStream            printStream        = null; 
    private  Vector                 packetVector       = null; 

    private  SourceRouterUI         sourceRouterUI     = null;

    /*
       This constructor is used to initialize the socket and the
       streams for a client connection 
     */
    public RoutingServiceHandler(Socket socket,
                SourceRouterUI sourceRouterUI) throws Exception {

        this.socket          = socket;
        this.sourceRouterUI  = sourceRouterUI; 

        inputStream = socket.getInputStream();
        outputStream = socket.getOutputStream();

        inputStreamReader = new InputStreamReader(inputStream);
        bufferedReader = new BufferedReader(inputStreamReader);
        printStream = new PrintStream(outputStream);    

        //packetForwarder  = new PacketForwarder();

    }        

    /*
       This method continously look for the incoming client request
       and once its arrived, it handles request appropriately
     */
    public void run() {
        try{
            System.out.println("Run Method called");  
			
            while(true) {
                String str = bufferedReader.readLine();
                
                if(str  != null) {
                    // This statement is used to process the incoming data
                    if ( str.equals("START") ) {
                       str = bufferedReader.readLine();
					  
                       packetVector = new Vector();
                       while ( !str.equals("END") ) { 
						    System.out.println("in RoutingServicehandler run()"+str);

                          packetVector.addElement(str);
						  
                          str = bufferedReader.readLine();
                       } 
                    } 
                    //processReceviedData(str);
					System.out.println("before processReceivedData"+packetVector);
                    processReceviedData(packetVector);
					
                }
            }
        }catch(Exception exception) { System.out.println("Exception "+exception);}  
    }

    /*
       This method process the incoming data :
     */
    public void processReceviedData(Vector data) throws Exception{
       SecurityEngine securityEngine = new SecurityEngine(data,sourceRouterUI); 
    }  


    
  /*  public void closeConnection() throws Exception {
        printStream.close();
        inputStreamReader.close();
        socket.close(); 
    }		 */

}
