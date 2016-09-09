 

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
/**
 *
 * @author Paul Love
 * @version 1.0
 * Class that creates a panel to update a fireplace record
 */

public class FireplaceUpdate extends PanelController{
    
    Fireplace fireplace;
    JTextField txtFireName, txtMaterial, txtColour, txtPrice, txtQuantity, txtPowerType;
    JLabel titleLabel;
    JComboBox cbSupplier;
    List<String> supplierNames;
    
    /**
     * Creates the parent class and calls to set the style
     * @param frame - the buyer interface frame
     * @param contentPanel - the buyer interface content panel
     * @param titleLabel - the buyer interface title label
     * @param fireplace - uses the fireplace object from either the
     * fireplaceAdd or fireplaceFind
     */
    public FireplaceUpdate(JFrame frame, JPanel contentPanel, JLabel titleLabel, Fireplace fireplace){
        super(frame, contentPanel);
        this.titleLabel = titleLabel;
        this.titleLabel.setText("Fireplace Details");
        this.fireplace = fireplace;
        setPanelStyle();
        addPanelRepaint();
    }
    /**
     * sets the style of the panel
     */
    private void setPanelStyle(){
        JPanel detailsPanel = new JPanel(new GridLayout(0,2));
        
        detailsPanel.add(new JLabel("Fireplace Name"));
        txtFireName = new JTextField(fireplace.getFireName());
        detailsPanel.add(txtFireName);
        detailsPanel.add(new JLabel("Material"));
        txtMaterial = new JTextField(fireplace.getMaterial());
        detailsPanel.add(txtMaterial);
        detailsPanel.add(new JLabel("Colour"));
        txtColour = new JTextField(fireplace.getColour());
        detailsPanel.add(txtColour);
        detailsPanel.add(new JLabel("Supplier"));
        
        cbSupplier = new JComboBox();
        setSupplierNames();
        for (String supplier: supplierNames){
            cbSupplier.addItem(supplier);
            if (supplier.equals(fireplace.getCompanyName())){
                cbSupplier.setSelectedItem(supplier);
            }
        }
        
        detailsPanel.add(cbSupplier);
        detailsPanel.add(new JLabel("Power Type"));
        txtPowerType = new JTextField(fireplace.getPowerType());
        detailsPanel.add(txtPowerType);
        detailsPanel.add(new JLabel("Price"));
        txtPrice = new JTextField(Float.toString(fireplace.getPrice()));
        detailsPanel.add(txtPrice);
        detailsPanel.add(new JLabel("Quantity"));
        txtQuantity = new JTextField(Integer.toString(fireplace.getQuantity()));
        detailsPanel.add(txtQuantity);
        JButton btReturn = new JButton("Return to Details");
        btReturn.addActionListener(new UpdateActionListener());
        detailsPanel.add(btReturn);
        JButton btUpdate = new JButton("Save");
        btUpdate.addActionListener(new UpdateActionListener());
        detailsPanel.add(btUpdate);
        
        panel.add(detailsPanel);
    }
    /*
     * retrieves all the supplier names from the database
     */
    private void setSupplierNames()
    {
        Supplier supplier = new Supplier();
        supplierNames = supplier.getAllSupplierNames();
    }
    /*
     * attempts to save the details of the fireplace
     */
    private void saveDetails(){
        if (txtFireName.getText().isEmpty() || txtMaterial.getText().isEmpty() || txtColour.getText().isEmpty() || txtPrice.getText().isEmpty() || txtQuantity.getText().isEmpty() || txtPowerType.getText().isEmpty()){
            fireplace.message = "All the above fields need to be filled";
            fireplace.showError();
        }else{
            if (setNumericalInputs()){
                fireplace.setColour(txtColour.getText());
                fireplace.setCompanyName(cbSupplier.getSelectedItem().toString());
                fireplace.setFireName(txtFireName.getText());
                fireplace.setMaterial(txtMaterial.getText());
                fireplace.setPowerType(txtPowerType.getText());
                if (fireplace.update()){
                    FireplaceAllDetails fire = new FireplaceAllDetails(frame, panel, titleLabel, fireplace);
                }else{
                    fireplace.message = "Couldn't update fireplace details";
                    fireplace.showError();
                }
            }else{
                fireplace.message = "The fields Quantity and Price must both be numerical values";
                fireplace.showError();
            }
        }
    }
    /*
     * checks whether the value in the textfields is numberical
     */
    private boolean setNumericalInputs(){
        try{
            fireplace.setQuantity(Integer.parseInt(txtQuantity.getText()));
            fireplace.setPrice(Float.parseFloat(txtPrice.getText()));
            return true;
        }catch(Exception ex){
            return false;
        }
    }
    /*
     * adds a listener for the button to save the details
     */
    private class UpdateActionListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            String event = e.getActionCommand();
            
            if (event.equals("Save")){
                saveDetails();
            }else{
                FireplaceAllDetails fire = new FireplaceAllDetails(frame, panel, titleLabel, fireplace);
            }
                
            
        }
    }
}
