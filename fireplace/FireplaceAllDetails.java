
 

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Paul Love
 * @version 1.0
 */

public class FireplaceAllDetails extends PanelController{
    
    Fireplace fireplace;
    JLabel titleLabel;
    /**
     * Creates the parent class and calls to set the style
     * @param frame - the buyer interface frame
     * @param contentPanel - the buyer interface content panel
     * @param titleLabel - the buyer interface title label
     * @param fireplace - uses the fireplace object from either the
     * fireplaceAdd or fireplaceFind
     */
    public FireplaceAllDetails(JFrame frame, JPanel contentPanel, JLabel titleLabel, Fireplace fireplace){
        super(frame, contentPanel);
        this.titleLabel = titleLabel;
        this.titleLabel.setText("Fireplace Details");
        this.fireplace = fireplace;
        setPanelStyle();
        addPanelRepaint();
    }
    /**
     * sets the panel style
     */
    private void setPanelStyle(){
        JPanel detailsPanel = new JPanel(new GridLayout(0,3));
        
        detailsPanel.add(new JLabel("Fireplace Name"));
        detailsPanel.add(new JLabel(": " + fireplace.getFireName()));
        
        JButton btUpdate = new JButton("Update");
        btUpdate.addActionListener(new UpdateActionListener());
        detailsPanel.add(btUpdate);
        
        detailsPanel.add(new JLabel("Material"));
        detailsPanel.add(new JLabel(": " + fireplace.getMaterial()));
        detailsPanel.add(new JLabel(""));
        detailsPanel.add(new JLabel("Colour"));
        detailsPanel.add(new JLabel(": " + fireplace.getColour()));
        detailsPanel.add(new JLabel(""));
        detailsPanel.add(new JLabel("Supplier"));
        detailsPanel.add(new JLabel(": " + fireplace.getCompanyName()));
        detailsPanel.add(new JLabel(""));
        detailsPanel.add(new JLabel("Power Type"));
        detailsPanel.add(new JLabel(": " + fireplace.getPowerType()));
        detailsPanel.add(new JLabel(""));
        detailsPanel.add(new JLabel("Price"));
        detailsPanel.add(new JLabel(": " + fireplace.getPrice()));
        detailsPanel.add(new JLabel(""));
        detailsPanel.add(new JLabel("Quantity"));
        detailsPanel.add(new JLabel(": " + fireplace.getQuantity()));
        
        JButton btQuantity =  new JButton("Add");
        btQuantity.addActionListener(new UpdateActionListener());
        detailsPanel.add(btQuantity);
        
        panel.add(detailsPanel);
        
    }
    /**
     * a class for the update button that attempts to update the fireplace
     */
    private class UpdateActionListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            
            if (e.getActionCommand().equals("Add")){
                 String quantity = JOptionPane.showInputDialog(null, "Enter quantity to add : ", "Add Fireplace", 1);
                 try{
                     int intQuantity = Integer.parseInt(quantity);
                     if (fireplace.addQuantity(intQuantity)){
                         FireplaceAllDetails fire = new FireplaceAllDetails(frame, panel, titleLabel, fireplace);
                     }else{
                         fireplace.message = "Couldn't update quantity";
                         fireplace.showError();
                     }
                 }catch(Exception ex){
                     fireplace.message = "Input must be numerical.";
                     fireplace.showError();
                 }
            }else{
                FireplaceUpdate fire = new FireplaceUpdate(frame, contentPanel, titleLabel, fireplace);
            }
        }
    }
}
