
 
import javax.swing.JFrame;
/**
 *
 * @author Paul Love
 * @version 1.0
 * The main class that instantiates a BuyerInterface object
 */
public class PaulsCataloguer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        BuyerInterface buyer = new BuyerInterface();
        buyer.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        buyer.frame.setBounds(100, 100, 750, 350);
        buyer.frame.setVisible(true);
    }
}
