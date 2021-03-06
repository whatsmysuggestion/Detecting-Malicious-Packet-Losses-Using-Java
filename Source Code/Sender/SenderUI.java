import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.util.Vector;

public class SenderUI extends JFrame implements ActionListener,ConfigIF {

    private  Container     container      = null;
    private  JButton       closeButton    = null;
    private  JButton       sendButton     = null;
    private  JList         packetsList    = null;
    private  Vector        packetVector   = null;
    private  Sender        sender         = null;

    private  JLabel        routerIPLabel  = null;


    public SenderUI() throws Exception {
        
        container = getContentPane();
        
        //setLayout to the containet
        container.setLayout(new BorderLayout());
        
        sender = new Sender();
        Vector vector = getInput();
        
        container.add("North",getLabelPanel());
        container.add("Center",getListPanel(vector));
        container.add("South",getButtonPanel());


        setSize(350,400);
        setLocation(150,150);
        setVisible(true);
    }


    public Vector getInput() throws Exception {

        //Get the no of packets from the user
        String input = JOptionPane.showInputDialog(
                         "Enter the Number Of Packets");

        int totalPackets = Integer.parseInt(input);
        packetVector = new Vector();
        		System.out.println("totalpackets="+totalPackets);
        for(int i = 0; i < totalPackets; i++){
           String packet = JOptionPane.showInputDialog(
                         "Enter the Packet-"+(i+1));

           packetVector.addElement(packet.trim());
    
        }


        return packetVector;
    }


    public JPanel getLabelPanel() throws Exception {
        
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2,1));
        
        Font font = new Font("TimesRoman",Font.PLAIN,20);
        JLabel label = new JLabel("                        SENDER");
        label.setFont(font);
        label.setForeground(Color.blue);

        routerIPLabel = new JLabel("                           Source Router IP :"+routerIP);
        routerIPLabel.setForeground(Color.red);
        Border etched = BorderFactory.createEtchedBorder();
        font = new Font("TimesRoman",Font.PLAIN,13);
        Border border = BorderFactory.createTitledBorder(etched,
                 "RSVP Sender",TitledBorder.LEFT,
                  TitledBorder.DEFAULT_JUSTIFICATION,font,Color.gray);


        panel.setBorder(border); 
        panel.add(label);
        panel.add(routerIPLabel);

        return panel;
    }


    public JPanel getListPanel(Vector vector) throws Exception {

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1,1));

        packetsList = new JList(vector);
        JScrollPane scrollPane = new JScrollPane(packetsList);
        
        Border etched = BorderFactory.createEtchedBorder();
        Font font = new Font("TimesRoman",Font.PLAIN,13);
        Border border = BorderFactory.createTitledBorder(etched,
                 "Generated Packets",TitledBorder.CENTER,
                  TitledBorder.DEFAULT_JUSTIFICATION,font,Color.red);


        panel.setBorder(border);
        panel.add(scrollPane);

        return panel;

    }


    public JPanel getButtonPanel() throws Exception {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER));

       sendButton = new JButton("Send");
       closeButton = new JButton("Exit");

       Border etched = BorderFactory.createEtchedBorder();
       Font font = new Font("TimesRoman",Font.PLAIN,13);
       Border border = BorderFactory.createTitledBorder(etched,
                 "",TitledBorder.LEFT,
                  TitledBorder.DEFAULT_JUSTIFICATION,font,Color.red);



       panel.setBorder(border);
       panel.add(sendButton);
       panel.add(closeButton);


       sendButton.addActionListener(this);
       closeButton.addActionListener(this);

       return panel;
       
    }


    public void actionPerformed(ActionEvent event){
      try {
          if(event.getSource() == sendButton) 
			  {
              routerIPLabel.setForeground(Color.green);
              sender.sendPackets(packetVector);
			  System.out.println(" in 146 packetVector== "+packetVector);
            //  sender.closeConnection();
          }
          else if(event.getSource() == closeButton)
			  {
              System.exit(0);
          }
          
      }catch(Exception exception) { exception.printStackTrace();}
        
    }


    public static void main(String[] args) throws Exception {
      SenderUI senderUI = new SenderUI();
      senderUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
  
    
    
}


