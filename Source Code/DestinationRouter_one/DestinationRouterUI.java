/*
   This class is used to provide the
   USer interface for the SourceRouter
*/

import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;
import javax.swing.border.*;
import java.util.Vector;

public class DestinationRouterUI extends JFrame {
    
    private JTable           table        = null;
    private RouterTableModel tableModel   = null;
    
    private JTable           statTable      = null;
    private StatTableModel   statTableModel = null;


    private JTabbedPane      tab          = null;

    private JList            list         = null;
    private RouterListModel  listModel    = null;
    

    private Object colHeader[] = {
                                  "SourceIP",
                                  "TTL",
                                  "Packet Data",
                                  "Protocol Type",
                                 "Packet Size"
                                 };


   private Object statHeader[] = {

    				   "PacketScore",
  				   "ProtocolScore",
				   "Score",
  			           "Thresold"			
       				 };
   
    /*
       Constructor
     */  
    public DestinationRouterUI(String title) throws Exception {
        
        //container to add the components
        Container con = getContentPane();
        con.setLayout(new BorderLayout());
		 Label l1=new Label("No Packet Loss");
        //instantiating the tabbed pane
        tab = new JTabbedPane();
        tab.addTab("Packet Staus",getTablePanel());
        tab.addTab("Packet Log",getLogPanel());
       // tab.addTab("Packet Statistics",getStatTablePanel());

        //add components in the container
        con.add("North",getLabelPanel());
        con.add("Center",tab);
		
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
        JLabel label = new JLabel("Destination Router",
                                                     JLabel.CENTER);
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


    public JPanel getStatTablePanel() throws Exception {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1,1));

        statTable = new JTable();
        statTableModel = new StatTableModel();
        statTableModel.setColumnIdentifiers(statHeader);
        statTable.setModel(statTableModel);

        JScrollPane scrollPane = new JScrollPane(statTable);

        panel.add(scrollPane);
        
        Font font = new Font("TimesRoman",Font.PLAIN,13);
        
        Border etched = BorderFactory.createEtchedBorder();
        Border border = BorderFactory.createTitledBorder(etched,"Packet Statistics",
          TitledBorder.LEFT,TitledBorder.DEFAULT_JUSTIFICATION,font,Color.red);


        
        panel.setBorder(border);

        return panel;
        
        
    }



    /*
       This method is used to return the
       log panel to the frame
    */   
    public JPanel getLogPanel() {

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1,1));
        
        list = new JList();
        listModel = new RouterListModel();
        list.setModel(listModel);

        Font font = new Font("TimesRoman",Font.PLAIN,13);
        
        Border etched = BorderFactory.createEtchedBorder();
        Border border = BorderFactory.createTitledBorder(etched,"Packet Log",
          TitledBorder.LEFT,TitledBorder.DEFAULT_JUSTIFICATION,font,Color.red);


        JScrollPane scrollPane = new JScrollPane(list);
        
        panel.add(scrollPane);

        panel.setBorder(border);

        return panel;
    }


    /*
       This method is used to add data 
       to the table
    */   
    public void addData(Vector data) {
      //System.out.println("in 197 Arrived data in Destination RouterUI:"+data); 
	 // System.out.println("in 198  dataSize in Destination RouterUI:"+data.size()); 
      tableModel.addRow(data);
      for(int i = 0; i < data.size(); i++){
          listModel.addElement(data.elementAt(i));
      }
    }

    public void addToLog(String data) {
      listModel.addElement(data);
    }


   public void addStatData(Vector data) {
     statTableModel.addRow(data);  
   }

	
    /*
       Inner and overriden class for table model
    */   
    class RouterTableModel extends DefaultTableModel {
        RouterTableModel(){
        }
    }

    
   class StatTableModel extends DefaultTableModel {

       StatTableModel() {
       }
   } 
		//panel.add(l1);
    
    /*
       Inner and overriden class for list model
    */   
    class RouterListModel extends DefaultListModel {
        RouterListModel() {
        }
    }  

    
}
   



