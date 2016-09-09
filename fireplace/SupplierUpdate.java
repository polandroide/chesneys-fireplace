 

/**
 *
 * @author Paul Love
 * @version 1.0
 * 
 * A class to update the suppliers in the database.
 */

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class SupplierUpdate extends PanelController{
    
    Supplier supplier;
    JTextField txtCompanyName, txtAddress, txtContact, txtPhone, txtCollection;
    JLabel titleLabel;
    
    /**
     * Sets the label, calls the panelStyle and repaint
     * @param frame - the buyer interface frame
     * @param contentPanel - the buyer interface content panel
     * @param titleLabel - the buyer interface title label
     * @param supplier - the supplier from SupplierAdd or SupplierFind
     */
    public SupplierUpdate(JFrame frame, JPanel contentPanel, JLabel titleLabel, Supplier supplier){
        super(frame, contentPanel);
        this.titleLabel = titleLabel;
        this.titleLabel.setText("Supplier Update");
        this.supplier = supplier;
        setPanelStyle();
        addPanelRepaint();
    }
    /**
     * sets the style of the panel
     */
    private void setPanelStyle(){
        JPanel detailsPanel = new JPanel(new GridLayout(0,2));
        
        detailsPanel.add(new JLabel("Company Name"));
        txtCompanyName = new JTextField(supplier.getCompanyName());
        detailsPanel.add(txtCompanyName);
        detailsPanel.add(new JLabel("Address"));
        txtAddress = new JTextField(supplier.getAddress());
        detailsPanel.add(txtAddress);
        detailsPanel.add(new JLabel("Phone Number"));
        txtPhone = new JTextField(supplier.getPhone());
        detailsPanel.add(txtPhone);
        detailsPanel.add(new JLabel("Contact"));
        txtContact = new JTextField(supplier.getContact());
        detailsPanel.add(txtContact);
        detailsPanel.add(new JLabel("Collection"));
        txtCollection = new JTextField(supplier.getCollection());
        detailsPanel.add(txtCollection);
        
        JButton btReturn = new JButton("Return to details");
        btReturn.addActionListener(new UpdateActionListener());
        detailsPanel.add(btReturn);
        
        JButton btUpdate = new JButton("Save");
        btUpdate.addActionListener(new UpdateActionListener());
        detailsPanel.add(btUpdate);
        
        
        panel.add(detailsPanel);
    }
    /**
     * accesses the supplier to update the supplier fields
     */
    private void saveUpdates()
    {
        supplier.setAddress(txtAddress.getText());
        supplier.setCollection(txtCollection.getText());
        supplier.setCompanyName(txtCompanyName.getText());
        supplier.setContact(txtContact.getText());
        supplier.setPhone(txtPhone.getText());
        
        supplier.update();
        
    }
    /*
     * a class to listen to the action buttons
     * if the save button is pressed it saves the updates
     * and calls the SupplierAllDetails class,
     * if not just the SupplierAllDetails class is called
     */
    private class UpdateActionListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e){
            
            if (e.getActionCommand().equals("Save")){
                saveUpdates();
            }
            SupplierAllDetails fire = new SupplierAllDetails(frame, contentPanel, titleLabel, supplier);
        }
    }
}
