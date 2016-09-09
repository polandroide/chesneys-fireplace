 


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.GridLayout;
import javax.swing.JComboBox;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Paul Love
 * @version 1.0
 * 
 * This class is the user interface to add a new fireplace to the database.
 */
public class FireplaceAdd extends PanelController{
    
    JTextField txtFireplaceName, txtMaterial, txtPowerType, txtColour, txtQuantity, txtPrice;
    JComboBox cbSupplier;
    JLabel titleLabel;
    List<String> supplierNames;
    Fireplace fireplace;
    
    /**
     * Creates the parent class and calls to set the style
     * @param frame - the buyer interface frame
     * @param contentPanel - the buyer interface content panel
     * @param titleLabel  - 
     */
    public FireplaceAdd(JFrame frame, JPanel contentPanel, JLabel titleLabel)
    {
        super(frame, contentPanel);
        this.titleLabel = titleLabel;
        this.titleLabel.setText("Fireplace Add");
        this.setSupplierNames();
        this.setPanelStyle();
        super.addPanelRepaint();
    }
    
    /**
     * sets the style of the panel
     */
    private void setPanelStyle()
    {
        JPanel innerPanel = new JPanel(new GridLayout(0,4));
        innerPanel.add(new JLabel("Fireplace Name: "));
        innerPanel.setBackground(colour);
        this.txtFireplaceName = new JTextField();
        innerPanel.add(this.txtFireplaceName);
        innerPanel.add(new JLabel("Material"));
        this.txtMaterial = new JTextField();
        innerPanel.add(this.txtMaterial);
        innerPanel.add(new JLabel("Colour"));
        this.txtColour = new JTextField();
        innerPanel.add(this.txtColour);
        innerPanel.add(new JLabel("Power Type"));
        this.txtPowerType = new JTextField();
        innerPanel.add(this.txtPowerType);
        innerPanel.add(new JLabel("Supplier"));
        this.cbSupplier = new JComboBox();
        
        for (String name : this.supplierNames)
        {
            cbSupplier.addItem(name);
        }
        
        innerPanel.add(cbSupplier);
        innerPanel.add(new JLabel("Quantity"));
        this.txtQuantity = new JTextField("0");
        innerPanel.add(this.txtQuantity);
        innerPanel.add(new JLabel("Price"));
        this.txtPrice = new JTextField("0.00");
        innerPanel.add(this.txtPrice);
        innerPanel.add(new JLabel(""));
        
        JButton button = new JButton("Add Item");
        button.addActionListener(new AddItemActionListener());
        innerPanel.add(button);
        super.panel.add(innerPanel);
        
        
    }
    /**
     * sets the suppliers' names
     */
    private void setSupplierNames()
    {
        Supplier supplier = new Supplier();
        supplierNames = supplier.getAllSupplierNames();
    }
    /**
     * creates a new fireplace object and attempts
     * to save it to the database
     */
    private void saveNewItem()
    {
        fireplace = new Fireplace();
        
        if(this.txtColour.getText().isEmpty() || this.txtFireplaceName.getText().isEmpty() || this.txtMaterial.getText().isEmpty() || this.txtPowerType.getText().isEmpty() || this.txtPrice.getText().isEmpty() || this.txtQuantity.getText().isEmpty()){
            fireplace.message = "All of the above fields need to be filled.";
            fireplace.showError();
        }else{
            if (getNumericalTypes()){
                fireplace.setColour(this.txtColour.getText());
                fireplace.setFireName(this.txtFireplaceName.getText());
                fireplace.setMaterial(this.txtMaterial.getText());
                fireplace.setPowerType(this.txtPowerType.getText());
                fireplace.setCompanyName(this.cbSupplier.getSelectedItem().toString());
                fireplace.message = this.cbSupplier.getSelectedItem().toString();
                if (fireplace.save()){
                    fireplace.message = "Fireplace successfully added.";
                    fireplace.showInformation();
                    FireplaceAllDetails fire = new FireplaceAllDetails(frame, panel, titleLabel, fireplace);
                }else{
                    fireplace.message = "Fireplace not added.";
                    fireplace.showError();
                }
            }
        }
    }
    /**
     * checks whether the values input into the textfield 
     * are numerical.
     * @return - true if the values are numerical
     */
    private boolean getNumericalTypes(){
        boolean numericalType = false;
        
        try{
            fireplace.setQuantity(Integer.parseInt(this.txtQuantity.getText()));
            fireplace.setPrice(Float.parseFloat(this.txtPrice.getText()));
            numericalType = true;
        }catch(Exception ex){
            fireplace.message = "Both the Price and Quantity must be numerical values.";
            fireplace.showError();            
        }
        
        return numericalType;
    }
    /*
     * action listener for the button
     */
    private class AddItemActionListener implements ActionListener{
        
        @Override
        public void actionPerformed(ActionEvent e){
            saveNewItem();
        }
    }
}
