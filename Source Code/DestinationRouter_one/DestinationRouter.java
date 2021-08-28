
/*
   This class is used to intialize the destination router
*/

public class DestinationRouter {

    public DestinationRouter() throws Exception {
      // while(true) {
         DecryptionEngine decryption = new  
                                       DecryptionEngine();
      // } 
    }

    public static void main(String[] args) throws Exception {
		System.out.println("in Destination Router");
       DestinationRouter obj = new DestinationRouter();
    }
}