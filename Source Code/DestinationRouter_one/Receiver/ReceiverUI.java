/*
   This class is used to provider 
   UI for the Receiver node in the
   network
*/

import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;
import javax.swing.border.*;
import java.util.Vector;

public class ReceiverUI extends JFrame {

    private JTable           table        = null;
    private RouterTableModel tableModel   = null;

     private Object colHeader[] = {
                                  "SourceIP",
                                  "TTL",
                                  "Packet Data",
                                  "Protocol Type",
                                  "Packet Size",
                                 };




    /*
       Constructor
    */  
    public ReceiverUI(String title) throws Exception {
        
        //container to add the components
        Container con = getContentPane();
        con.setLayout(new BorderLayout());


        //add components in the container
        con.add("North",getLabelPanel());
        con.add("Center",getTablePanel());

        //set the parameters required for frame
        setTitle(title);
        setSize(800,400);
        setLocation(100,100);
        setVisible(true);
    }


     /*
       This method is used to return the
       Label to the frame
    */   
    public JPanel getLabelPanel() throws Exception {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1,1));

        Font font = new Font("TimesRoman",Font.PLAIN,25);
        JLabel label = new JLabel("Receiver",JLabel.CENTER);
        label.setForeground(Color.blue);

        panel.add(label);

        font = new Font("TimesRoman",Font.PLAIN,13);
        Border etched = BorderFactory.createEtchedBorder();
        Border border = BorderFactory.createTitledBorder(etched,"Title",
          TitledBorder.LEFT,TitledBorder.DEFAULT_JUSTIFICATION,font,Color.red);

        
        panel.setBorder(border);

        return panel;
        
    }


    /*
       This method is used to return the table
       to the frame
    */   
    public JPanel getTablePanel() throws Exception {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1,1));

        table = new JTable();
        tableModel = new RouterTableModel();
        tableModel.setColumnIdentifiers(colHeader);
        table.setModel(tableModel);

        JScrollPane scrollPane = new JScrollPane(table);

        panel.add(scrollPane);
        
        Font font = new Font("TimesRoman",Font.PLAIN,13);
        
        Border etched = BorderFactory.createEtchedBorder();
        Border border = BorderFactory.createTitledBorder(etched,"Packet Status",
          TitledBorder.LEFT,TitledBorder.DEFAULT_JUSTIFICATION,font,Color.red);


        
        panel.setBorder(border);

        return panel;
        
        
    }

     /*
       This method is used to add data 
       to the table
    */   
    public void addData(Vector data) {
      tableModel.addRow(data);
    }

    /*
       Inner and overriden class for table model
    */   
    class RouterTableModel extends DefaultTableModel {
        RouterTableModel(){
        }
    }




}
