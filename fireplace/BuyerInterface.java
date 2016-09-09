 

import java.awt.*; // used for Color, Flow, Border and Grid Layout Classes 
import java.awt.event.*;// used for event-handling classes
import javax.swing.*;   // used for JFrame, JPanel, JButton, etc.
import java.awt.Font;

/**
 *
 * @author Paul Love
 * @version 1.0
 * This class creates the frame, menu and the default panel
 * for the Buyer Interface
 */
public class BuyerInterface{
    
    public JFrame frame;
    private JPanel contentPanel;
    private JLabel titleLabel;
    
    /**
     * The buyer interface constructor sets the 
     * content and the menu bar
     */
    public BuyerInterface()
    {
        this.frame = new JFrame("Buyer Interface");
        this.setInterfaceStyle();
        this.setMenuPanel();
        frame.validate(); // validate the interface with the new components
        frame.repaint(); //  repaint the container with the menu bar
        //this.addBothPanels(); 
    }
    /**
     * sets the look and feel of the Buyer 
     */
    private void setInterfaceStyle()
    {
        Container frameContainer = frame.getContentPane();
        frameContainer.setBackground(new Color(249, 239, 208));
        JLabel label = new JLabel();
        label.setText("Chesney's Fireplaces 2012 (c)");
        frameContainer.add(label, BorderLayout.SOUTH);
        this.titleLabel = new JLabel("Welcome to Chesney's Fireplaces");
        frameContainer.add(this.titleLabel, BorderLayout.NORTH);
        
        //by adding additional panels to the content panel, the programmer will always know from which panel to remove any previous panels.
        this.contentPanel = new JPanel();
        this.contentPanel.setBackground(new Color(249, 239, 208));
        frameContainer.add(this.contentPanel, BorderLayout.CENTER);
        this.contentPanel.add(this.getDefaultContentPanel());
    }
    /**
     * Creates and returns the default screen for the interface
     * @return - the default JPanel
     */
    private JPanel getDefaultContentPanel(){
        JPanel defaultPanel = new JPanel();
        JLabel label = new JLabel("Chesney's Fireplaces");
        label.setFont(new Font("Monotype Corsiva", Font.PLAIN, 60));
        label.setBackground(new Color(249, 239, 208));
        defaultPanel.setBackground(new Color(249, 239, 208));
        label.setOpaque(true);
        label.setBorder(null);
        defaultPanel.add(label);
        return defaultPanel;
    }
    /**
     * sets the Menu Panel
     */
    private void setMenuPanel(){
        JMenuBar menuBar = new JMenuBar();
        //set home button
        JMenu home = new JMenu("Home");
        home.setMnemonic(KeyEvent.VK_H);
        JMenuItem hDefault = new JMenuItem("Default");
        hDefault.addActionListener(new HomeActionListener());
        home.add(hDefault);
        menuBar.add(home);
        
        //set supplier button
        JMenu supplier = new JMenu("Supplier");
        supplier.setMnemonic(KeyEvent.VK_S);
        
        JMenuItem sList = new JMenuItem("List", KeyEvent.VK_L);
        sList.addActionListener(new SupplierActionListener());
        supplier.add(sList);
        
        JMenuItem sFind = new JMenuItem("Find", KeyEvent.VK_F);
        sFind.addActionListener(new SupplierActionListener());
        supplier.add(sFind);
        
        JMenuItem sAdd = new JMenuItem("Add", KeyEvent.VK_A);
        sAdd.addActionListener(new SupplierActionListener());
        supplier.add(sAdd);
        
        
        menuBar.add(supplier);
        
        //set fireplace button
        JMenu fireplace = new JMenu("Fireplace");
        fireplace.setMnemonic(KeyEvent.VK_F);
        
        JMenuItem fStock = new JMenuItem("List", KeyEvent.VK_L);
        fStock.addActionListener(new FireplaceActionListener());
        fireplace.add(fStock);
        
        JMenuItem fFind = new JMenuItem("Find", KeyEvent.VK_F);
        fFind.addActionListener(new FireplaceActionListener());
        fireplace.add(fFind);
        
        JMenuItem fAdd = new JMenuItem("Add", KeyEvent.VK_A);
        fAdd.addActionListener(new FireplaceActionListener());
        fireplace.add(fAdd);
                
        menuBar.add(fireplace);
        
        frame.setJMenuBar(menuBar);
    }
    /**
     * an action listener for the default home page
     */
    
    
    private class HomeActionListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            titleLabel.setText(e.getActionCommand());
            contentPanel.removeAll();
            contentPanel.add(getDefaultContentPanel());
            frame.validate();
            frame.repaint();
        }
    }
    
    //an action listener for menu items in the fireplace section
    private class SupplierActionListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            String actionCommand = e.getActionCommand();
            
            if (actionCommand.equals("List"))
            {
                SupplierList fire = new SupplierList(frame, contentPanel, titleLabel);
            }
            else if(actionCommand.equals("Find"))
            {
                SupplierFind fire = new SupplierFind(frame, contentPanel, titleLabel);
            }
            else if(actionCommand.equals("Add"))
            {
                SupplierAdd fire = new SupplierAdd(frame, contentPanel, titleLabel);
            }
            else{
                //JPanel panel = getDefaultContentPanel();
            }            
        }
    }
    
    //an action listener for menu items in the fireplace section
    private class FireplaceActionListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            String actionCommand = e.getActionCommand();
            
            if (actionCommand.equals("List"))
            {
                FireplaceList fire = new FireplaceList(frame, contentPanel, titleLabel);
            }
            else if(actionCommand.equals("Find"))
            {
                FireplaceFind fire = new FireplaceFind(frame, contentPanel, titleLabel);
            }
            else if(actionCommand.equals("Add"))
            {
                FireplaceAdd fire = new FireplaceAdd(frame, contentPanel, titleLabel);
            }
            else{
                JPanel panel = getDefaultContentPanel();
            }      
            
        }
    }
}
