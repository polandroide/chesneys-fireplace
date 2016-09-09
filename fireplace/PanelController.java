
 

/**
 *
 * @author Paul Love
 * @version 1.0
 * 
 * an abstract class from which all panels inherit
 * it contains the references to the buyer interface
 * frame and content panel. In hindsight, maybe it
 * would have been better to create static variables
 * instead of passing references around.
 */

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;

abstract public class PanelController {
    
    protected JFrame frame;
    protected JPanel contentPanel; //
    protected JPanel panel;
    protected Color colour;
    /**
     * 
     * @param frame - reference to the buyer Interface frame
     * @param contentPanel - reference to the buyer Interface 
     * panel which is a boarder layout. The panel in this 
     * code below is the CENTER panel which is added to the
     * contentPanel. Overkill - maybe.
     * 
     */
    public PanelController(JFrame frame, JPanel contentPanel)
    {
        panel = new JPanel(); // to wipe the existing data
        this.contentPanel = contentPanel;
        this.colour =new Color(249, 239, 208);
        this.contentPanel.setBackground(this.colour);
        this.frame = frame;
    }
    /**
     * a method to clean the existing panel
     *and then to repaint the buyer interface frame
     * 
     */
    protected void addPanelRepaint()
    {
        contentPanel.removeAll();
        contentPanel.add(panel);
        frame.validate();
        frame.repaint();
    }
}
