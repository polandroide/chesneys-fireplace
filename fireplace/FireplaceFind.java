
 

import java.awt.GridLayout;
import javax.swing.*;
import java.awt.event.*;

/**
 *
 * @author Paul Love
 * @version 1.0
 * a class to create a panel for the fireplace 
 * database search
 */

public class FireplaceFind extends PanelController{
    
    JTextField txtName, txtId;
    JPanel searchPanel, messagePanel, displayPanel;
    JLabel titleLabel;
    JButton button, btDetails, btUpdate;
    Fireplace fireplace;
    /**
     * Creates the parent class and calls to set the style
     * @param frame - the buyer interface frame
     * @param contentPanel - the buyer interface content panel
     * @param titleLabel - the buyer interface title label
     */
    public FireplaceFind(JFrame frame, JPanel contentPanel, JLabel titleLabel){
        super(frame, contentPanel);
        this.titleLabel = titleLabel;
        this.frame = frame;
        this.contentPanel = contentPanel;
        this.titleLabel.setText("Fireplace Find");
        this.setPanelStyle();
        super.addPanelRepaint();
    }
    
    /**
     * sets the style of the panel
     */
    private void setPanelStyle(){
        
        panel.setLayout(new GridLayout(0,1));
        searchPanel = new JPanel(new GridLayout(3,4));
        searchPanel.setBackground(colour);
        searchPanel.add(new JLabel("Find By"));
        searchPanel.add(new JLabel(""));
        searchPanel.add(new JLabel(""));
        searchPanel.add(new JLabel(""));
        searchPanel.add(new JLabel("Fireplace Name "));
        txtName = new JTextField();
        searchPanel.add(txtName);
        searchPanel.add(new JLabel(""));
        searchPanel.add(new JLabel(""));
        searchPanel.add(new JLabel("ID"));
        txtId = new JTextField();
        searchPanel.add(txtId);
        searchPanel.add(new JLabel(""));
        button = new JButton("Search");
        button.addActionListener(new FireplaceFindActionListener());
        searchPanel.add(button);
        
        panel.add(searchPanel);
        
        messagePanel = new JPanel();
        messagePanel.setBackground(colour);
        panel.add(messagePanel);
        displayPanel = new JPanel(new GridLayout(3,4));
        displayPanel.setBackground(colour);
        panel.add(displayPanel);
    }
    /**
     * check to see whether the fireplace exists
     */
    private void setFoundPanelStyle(){
       
        String companyName = txtName.getText();
        String id =          txtId.getText();
        
        fireplace = new Fireplace();
        displayPanel.removeAll();
        messagePanel.removeAll();;
        boolean fireFound = false;
        if(!companyName.isEmpty()){
            fireplace.findByFireName(companyName);
            if (!fireplace.getFireName().equals("0")){
                messagePanel.add(new JLabel(fireplace.getFireName() + " Found"));
                fireFound = true;
            }else{
                messagePanel.add(new JLabel("No company with that name found."));
            }
        }else if(!id.isEmpty()){
            try{
                Integer.parseInt(id);
                fireplace.findById(id);
                if (!fireplace.getFireName().equals("0")){
                    messagePanel.add(new JLabel(fireplace.getFireName() + " Found"));
                    fireFound = true;
                }else{
                    messagePanel.add(new JLabel("No company with that ID found."));
                }
            }catch(Exception ex){
                messagePanel.add(new JLabel("ID must be a numerical value."));
            }
            
        }else{
            messagePanel.add(new JLabel("There is no input in the given fields."));
        }
        
        if (fireFound){
            txtName.setText("");
            txtId.setText("");
            displayPanel.add(new JLabel("Fireplace Name "));
            displayPanel.add(new JLabel(fireplace.getFireName()));
            displayPanel.add(new JLabel("Colour"));
            displayPanel.add(new JLabel(fireplace.getColour()));
            displayPanel.add(new JLabel("Material"));
            displayPanel.add(new JLabel(fireplace.getMaterial()));
            displayPanel.add(new JLabel("Price"));
            displayPanel.add(new JLabel(Float.toString(fireplace.getPrice())));
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
    
    private class FireplaceFindActionListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            setFoundPanelStyle();
        }
    }
    
    private class DetailsActionListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            FireplaceAllDetails fire = new FireplaceAllDetails(frame, contentPanel, titleLabel, fireplace);
        }
    }
    
    private class UpdateActionListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            FireplaceUpdate fire = new FireplaceUpdate(frame, contentPanel, titleLabel, fireplace);
        }
    }
}
