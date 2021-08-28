/*
   This class is used to connect the incoming client into  a
   seperate thread
 */
import java.io.*;
import java.net.*;

public class ConnectionManager implements RouterConfigIF, Runnable{

    private ServerSocket      serverSocket     = null;
    private Thread            invokeThread     = null;   

    public static final ConnectionManager  connection = null;

    public SourceRouterUI sourceRouterUI = null;

    /*
       This constructor is used to initialize the broker in a
       specific port and start a thread for incoming client 
     */ 
    public ConnectionManager()  {
        try {
            serverSocket = new ServerSocket(SOURCE_ROUTER_PORT);
            sourceRouterUI = new SourceRouterUI("SourceRouter");
            invokeThread = new Thread(this);
            invokeThread.start();           
        }catch(Exception exception) { System.out.println(exception);}
    }

    /*
       This methos is used to accept the incoming client connections
       and start them is seperate the thread class called ClientServiceHandler   
     */
    public void run() {
        try {
            while(true) {
                Socket clientSocket = serverSocket.accept();
                RoutingServiceHandler routingServiceHandler = new 
                          RoutingServiceHandler(clientSocket,sourceRouterUI);
                                                   
                routingServiceHandler.start(); 
            }             
        }catch(Exception exception) { System.out.println(exception);}
    }

    /*
       Future Use ( will be used once the patterns are introduced)
       This method can be used for obtaining singleton pattern
     */
    public ConnectionManager getInstance() throws Exception {
        return connection;             
    }

}
