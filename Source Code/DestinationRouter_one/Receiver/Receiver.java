public class Receiver {
 
   public Receiver() throws Exception {

      ReceiverUI receiverUI = new ReceiverUI("Receive"); 

      while(true) {  
        PacketReceiver receiver = new PacketReceiver(receiverUI);        
      }
   }


   public static void main(String[] args) throws Exception {
      Receiver obj = new Receiver();
   } 
}