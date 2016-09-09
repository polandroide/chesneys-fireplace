 

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Paul Love
 * @version 1.0
 * 
 * A class to give all the Details of the supplier
 * and a link to the update panel.
 */

public class SupplierAllDetails extends PanelController{
    
    Supplier supplier;
    JLabel titleLabel;
    /**
     * Creates the parent class and calls to set the style
     * @param frame - the buyer interface frame
     * @param contentPanel - the buyer interface content panel
     * @param titleLabel - the buyer interface title label
     * @param supplier - the supplier from SupplierFind or SupplierAdd
     */
    public SupplierAllDetails(JFrame frame, JPanel contentPanel, JLabel titleLabel, Supplier supplier){
        super(frame, contentPanel);
        this.titleLabel = titleLabel;
        this.titleLabel.setText("Supplier Details");
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
        detailsPanel.add(new JLabel(": " + supplier.getCompanyName()));
        detailsPanel.add(new JLabel("Address"));
        detailsPanel.add(new JLabel(": " + supplier.getAddress()));
        detailsPanel.add(new JLabel("Phone Number"));
        detailsPanel.add(new JLabel(": " + supplier.getPhone()));
        detailsPanel.add(new JLabel("Contact"));
        detailsPanel.add(new JLabel(": " + supplier.getContact()));
        detailsPanel.add(new JLabel("Collection"));
        detailsPanel.add(new JLabel(": " + supplier.getCollection()));
        
        JButton btUpdate = new JButton("Update");
        btUpdate.addActionListener(new UpdateActionListener());
        detailsPanel.add(btUpdate);
        
        panel.add(detailsPanel);
        
    }
    /**
     * a class to create the SupplierUpdate through a button press action
     */
    private class UpdateActionListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            SupplierUpdate fire = new SupplierUpdate(frame, contentPanel, titleLabel, supplier);
        }
    }
}
