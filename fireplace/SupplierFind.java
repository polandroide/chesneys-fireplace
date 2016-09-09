 

import java.awt.GridLayout;
import javax.swing.*;
import java.awt.event.*;
/**
 *
 * @author Paul Love
 * @version 1.0
 */
public class SupplierFind extends PanelController{
    
    JTextField txtName, txtAddress, txtId;
    JPanel searchPanel, messagePanel, displayPanel;
    JLabel titleLabel;
    JButton button, btDetails, btUpdate;
    Supplier supplier;
    
    /**
     * Creates the parent class and calls to set the style
     * @param frame - the buyer interface frame
     * @param contentPanel - the buyer interface content panel
     * @param titleLabel - the buyer interface title label
     */
    public SupplierFind(JFrame frame, JPanel contentPanel, JLabel titleLabel){
        super(frame, contentPanel);
        this.titleLabel = titleLabel;
        this.frame = frame;
        this.contentPanel = contentPanel;
        this.titleLabel.setText("Supplier Find");
        this.setPanelStyle();
        super.addPanelRepaint();
    }
    /**
     * a method to set the style of the panel
     */
    private void setPanelStyle(){
        
        panel.setLayout(new GridLayout(0,1));
        
        searchPanel = new JPanel(new GridLayout(3,4));
        searchPanel.add(new JLabel("Find By"));
        searchPanel.add(new JLabel(""));
        searchPanel.add(new JLabel(""));
        searchPanel.add(new JLabel(""));
        searchPanel.add(new JLabel("Supplier Name "));
        txtName = new JTextField();
        searchPanel.add(txtName);
        searchPanel.add(new JLabel("Address"));
        txtAddress = new JTextField();
        searchPanel.add(txtAddress);
        searchPanel.add(new JLabel("ID"));
        txtId = new JTextField();
        searchPanel.add(txtId);
        searchPanel.add(new JLabel(""));
        button = new JButton("Search");
        button.addActionListener(new SupplierFindActionListener());
        searchPanel.add(button);
        searchPanel.setBackground(colour);
        
        panel.add(searchPanel);
        
        messagePanel = new JPanel();
        messagePanel.setBackground(colour);
        panel.add(messagePanel);
        displayPanel = new JPanel(new GridLayout(3,4));
        displayPanel.setBackground(colour);
        panel.add(displayPanel);
    }
    /**
     * a method run after the button press that checks
     * the text input and tries to locate the Supplier
     * in the database through the Supplier object
     */
    private void setFoundPanelStyle(){
        
        String companyName = txtName.getText();
        String address =     txtAddress.getText();
        String id =          txtId.getText();
        
        
        supplier = new Supplier();
        displayPanel.removeAll();
        messagePanel.removeAll();;
        boolean supplierFound = false;
        if(!companyName.isEmpty()){
            supplier.findByCompanyName(companyName);
            if (!supplier.getCompanyName().equals("0")){
                messagePanel.add(new JLabel(supplier.getCompanyName() + " Found"));
                supplierFound = true;
            }else{
                messagePanel.add(new JLabel("No company with that name found."));
            }
            
        }else if(!address.isEmpty()){
            supplier.findByCompanyAddress(address);
            if (!supplier.getCompanyName().equals("0")){
                messagePanel.add(new JLabel(supplier.getCompanyName() + " Found"));
                supplierFound = true;
            }else{
                messagePanel.add(new JLabel("No company with that address found."));
            }
        }else if(!id.isEmpty()){
            try{
                Integer.parseInt(id);
                supplier.findById(id);
                if (!supplier.getCompanyName().equals("0")){
                    messagePanel.add(new JLabel(supplier.getCompanyName() + " Found"));
                    supplierFound = true;
                }else{
                    messagePanel.add(new JLabel("No company with that ID found."));
                }
            }catch(Exception ex){
                messagePanel.add(new JLabel("ID must be a numerical value."));
            }
            
        }else{
            messagePanel.add(new JLabel("There is no input in the given fields."));
        }
        
        if (supplierFound){
            txtName.setText("");
            txtId.setText("");
            txtAddress.setText("");
            displayPanel.add(new JLabel("Company Name "));
            displayPanel.add(new JLabel(supplier.getCompanyName()));
            displayPanel.add(new JLabel("Phone"));
            displayPanel.add(new JLabel(supplier.getPhone()));
            displayPanel.add(new JLabel("Contact"));
            displayPanel.add(new JLabel(supplier.getContact()));
            displayPanel.add(new JLabel("Collection"));
            displayPanel.add(new JLabel(supplier.getCollection()));
            btDetails = new JButton("All Details");
            displayPanel.add(btDetails);
            btDetails.addActionListener(new DetailsActionListener());
            displayPanel.add(new JLabel(""));
            btUpdate = new JButton("Update Details");
            btUpdate.addActionListener(new UpdateActionListener());
            displayPanel.add(btUpdate);
        }
        
        frame.validate();
        frame.repaint();
        
    }
    
    private class SupplierFindActionListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            setFoundPanelStyle();
        }
    }
    
    private class DetailsActionListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            SupplierAllDetails fire = new SupplierAllDetails(frame, contentPanel, titleLabel, supplier);
        }
    }
    
    private class UpdateActionListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            SupplierUpdate fire = new SupplierUpdate(frame, contentPanel, titleLabel, supplier);
        }
    }
}
