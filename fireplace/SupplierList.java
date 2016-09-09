 


import java.awt.BorderLayout;
import javax.swing.*;
/**
 *
 * @author Paul Love
 * @version 1.0
 * A class that lists all the entries for the
 * company's suppliers
 */
public class SupplierList extends PanelController{
    
    private JTable table;
    private JLabel titleLabel;
    /**
     * Creates the parent class and calls to set the style
     * @param frame - the buyer interface frame
     * @param contentPanel - the buyer interface content panel
     * @param titleLabel - the buyer interface title label
     */
    public SupplierList(JFrame frame, JPanel contentPanel, JLabel titleLabel){
        super(frame, contentPanel);
        this.titleLabel = titleLabel;
        this.titleLabel.setText("List All Suppliers");
        setPanelStyle();
        super.addPanelRepaint();
    }
    /**
     * sets the style of the panel
     */
    private void setPanelStyle(){
        panel.setLayout(new BorderLayout());
        Supplier supplier = new Supplier();
        table = supplier.getAllSupplierTable();
        panel.add(table, BorderLayout.CENTER);
    }
}
