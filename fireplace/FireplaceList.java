 

/**
 *
 * @author Paul Love
 * @version 1.0
 * 
 * This class creates a list of all the stock in the database.
 */

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTable;
import java.awt.BorderLayout;
        
public class FireplaceList extends PanelController {
    
    
    JTable table;
    /**
     * Creates the parent class and calls to set the style
     * @param frame - the buyer interface frame
     * @param contentPanel - the buyer interface content panel
     * @param titleLabel - the buyer interface title label
     */
    public FireplaceList(JFrame frame, JPanel contentPanel, JLabel titleLabel){
        super(frame, contentPanel);
        titleLabel.setText("Fireplace List");
        this.setPanelStyle();
        super.addPanelRepaint();
    }
    /**
     * sets the style of the panel
     */
    private void setPanelStyle(){
        panel = new JPanel();
        panel.setLayout(new BorderLayout());
        Fireplace fireplace = new Fireplace();
        table = fireplace.getAllSupplierTable();
        panel.add(table, BorderLayout.CENTER);
    }
}
