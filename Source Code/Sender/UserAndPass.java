import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

public class UserAndPass extends JDialog implements ActionListener {

   private  Container      container  =  null;
   private  JLabel         userLabel  =  null;
   private  JLabel         passLabel  =  null;
   private  JTextField     userText   =  null;
   private  JPasswordField passField  =  null;
   private  JButton        okButton   =  null;
   private  JButton        canButton  =  null; 
   private  JButton       clearButton =  null;

   private final int width  = 300;
   private final int height = 190;
   private final int locX   = 150;
   private final int locY   = 150; 
 
   public UserAndPass() {

     container  =  getContentPane();
     container.setLayout(new BorderLayout());
    
     BevelBorder bevelBorder = new BevelBorder(BevelBorder.RAISED);
     
     container.add("North" , getLabelPanel());
     container.add("Center" ,getUserAndPassPanel());
     container.add("South" , getButtonPanel()); 

     //setBorder(bevelBorder);
     setSize(width,height);
     setVisible(true);
     setLocation(locX,locY);
     setResizable(false);
      
   }


   public JPanel getLabelPanel() {

      JPanel labelPanel = new JPanel();
      labelPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
      
      Font font = new Font("TimesRoman",Font.PLAIN,18); 
      JLabel titleLabel = new JLabel(" Login Dialog");
      titleLabel.setForeground(Color.blue);
      titleLabel.setFont(font);
      labelPanel.add(titleLabel);

      Border etched = BorderFactory.createEtchedBorder();
      Border titled = BorderFactory.createTitledBorder(etched,"",TitledBorder.LEFT,TitledBorder.DEFAULT_JUSTIFICATION);

      labelPanel.add(titleLabel);
      labelPanel.setBorder(titled);

      return labelPanel;
   }

   public JPanel getUserAndPassPanel() {
      
       JPanel userAndPassPanel = new JPanel();
       userAndPassPanel.setLayout(new GridLayout(2,2,5,5));
       
       Font font = new Font("TimesRoman",Font.PLAIN,15);
       userLabel = new JLabel("          UserName");
       userLabel.setFont(font);
       passLabel = new JLabel("          Password");
       passLabel.setFont(font);
       userText  = new JTextField(20);
       passField = new JPasswordField(20);
       
       Border etched = BorderFactory.createEtchedBorder();
       Border titled = BorderFactory.createTitledBorder(etched,"",TitledBorder.LEFT,TitledBorder.DEFAULT_JUSTIFICATION);
      

      userAndPassPanel.add(userLabel);
      userAndPassPanel.add(userText);
      userAndPassPanel.add(passLabel);
      userAndPassPanel.add(passField);

      userAndPassPanel.setBorder(titled);

      return userAndPassPanel; 
       
   }

 
   public JPanel getButtonPanel() {
     
     JPanel buttonPanel = new JPanel();
     buttonPanel.setLayout(new FlowLayout());
     
     Font font = new Font("TimesRoman",Font.PLAIN,15);

     okButton    = new JButton("Login");
     okButton.setForeground(Color.blue);
     okButton.setFont(font);

     canButton   = new JButton("Cancel");
     canButton.setForeground(Color.blue);
     canButton.setFont(font);
 
     clearButton = new JButton("Clear");
     clearButton.setForeground(Color.blue);
     clearButton.setFont(font);

     Border etched = BorderFactory.createEtchedBorder();
     Border titled = BorderFactory.createTitledBorder(etched,"",TitledBorder.LEFT,TitledBorder.DEFAULT_JUSTIFICATION);

      buttonPanel.add(okButton);
      buttonPanel.add(canButton);
      buttonPanel.add(clearButton);

      buttonPanel.setBorder(titled);

      okButton.addActionListener(this);
      canButton.addActionListener(this);
      clearButton.addActionListener(this); 

      return buttonPanel;       

   }


   public void actionPerformed(ActionEvent actionEvent) {
    try {
     if(actionEvent.getSource() == okButton) {
        boolean status = verifyUserAndPass();
        if(!status) {
         JOptionPane.showMessageDialog(this,"Enter Proper Username and Password");
         userText.setText("");
         passField.setText("");
        }
       else if(status) {
         this.dispose();
         SenderUI senderUI = new SenderUI();
         senderUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       }
     }

     else if(actionEvent.getSource() == canButton) {
         this.dispose();
         System.exit(0);
     }

     else if(actionEvent.getSource() == clearButton) {
         userText.setText("");
         passField.setText("");
     }
    }catch(Exception exception) { exception.printStackTrace();}
   }

   public boolean verifyUserAndPass() throws Exception {
     String user = userText.getText().trim();
     String pass = passField.getText().trim();
     if ( user.trim().equals("") ||
                  pass.trim().equals("") ) {
         return false;
     }
     PasswordValidator validator = new PasswordValidator(user,pass);
     if ( validator.isValid() ) {
         return true;
     }
     else {

        return false;
     }
    
  } 
 

   public static void main(String[] args) {
     UserAndPass uap = new UserAndPass();
   } 
}






