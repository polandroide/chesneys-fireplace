 

import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

/**
 *
 * @author Paul Love
 * @version 1.0
 * 
 * This class adds a new supplier to the database.
 */
public class SupplierAdd extends PanelController{
    
    JTextField txtName, txtAddress, txtPhone, txtContact, txtCollection;
    JLabel output, titleLabel;
    /**
     * Creates the parent class and calls to set the style
     * @param frame - the buyer interface frame
     * @param contentPanel - the buyer interface content panel
     * @param titleLabel - the buyer interface title label
     */
    public SupplierAdd(JFrame frame, JPanel contentPanel, JLabel titleLabel){
        super(frame, contentPanel);
        this.titleLabel = titleLabel;
        this.titleLabel.setText("Supplier Add");
        setPanelStyle();
        addPanelRepaint();
    }
    /**
     * sets the style of the panel
     */
    private void setPanelStyle(){
        JPanel gridPanel = new JPanel(new GridLayout(3, 4));
        gridPanel.setBackground(colour);
        JLabel name = new JLabel("Company Name");
        gridPanel.add(name);
        txtName = new JTextField();
        gridPanel.add(txtName);
        JLabel contact = new JLabel("Contact Name");
        gridPanel.add(contact);
        txtContact = new JTextField();
        gridPanel.add(txtContact);
        JLabel address = new JLabel("Address");
        gridPanel.add(address);
        txtAddress = new JTextField();
        gridPanel.add(txtAddress);
        JLabel phone = new JLabel("Phone");
        gridPanel.add(phone);
        txtPhone = new JTextField();
        gridPanel.add(txtPhone);
        JLabel collection = new JLabel("Collection");
        gridPanel.add(collection);
        txtCollection = new JTextField();
        gridPanel.add(txtCollection);
        gridPanel.add(new JLabel(""));
        
        panel = new JPanel(new GridLayout(3,1));
        panel.setBackground(colour);
        panel.add(gridPanel);
        
        JButton button = new JButton("Add");
        button.addActionListener(new SupplierAddActionListener());
        gridPanel.add(button);
        
        output = new JLabel("Enter company details and press the 'Add' button to add a supplier.");
        output.setBackground(colour);
        panel.add(output);
    }
    /**
     * Method called after the button press. It checks
     * to see whether the input is valid before
     * calling the methods in Supplier to add the supplier
     */
    private void addSupplierDetails(){
        String companyName  = txtName.getText();
        String address      = txtAddress.getText();
        String phone        = txtPhone.getText();
        String contact      = txtContact.getText();
        String collection   = txtCollection.getText();
        
        if (companyName.isEmpty() || address.isEmpty() || phone.isEmpty() || contact.isEmpty() || collection.isEmpty()){
            output.setText("All of the above fields must be filled.");
        }else{
            Supplier supplier = new Supplier();
            supplier.setAddress(address);
            supplier.setCollection(collection);
            supplier.setCompanyName(companyName);
            supplier.setContact(contact);
            supplier.setPhone(phone);
            if (supplier.save()){
                this.txtAddress.setText("");
                this.txtCollection.setText("");
                this.txtContact.setText("");
                this.txtName.setText("");
                this.txtPhone.setText("");
                supplier.message = supplier.getCompanyName() + " added successfully.";
                supplier.showInformation();
            }else{
                output.setText( supplier.getCompanyName() + " not added.");
                supplier.showError();
            }
            SupplierAllDetails fire = new SupplierAllDetails(frame, panel, titleLabel, supplier);
        }
    }
    
    /**
     * a class for the action listener to call the
     * method to add the supplier
     */
    private class SupplierAddActionListener implements ActionListener{
        
        @Override
        public void actionPerformed(ActionEvent e){
            addSupplierDetails();
        }
        
    }
}
