package instruments;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * A more advanced window for the Instrument Replacer, which lets one select
 * the instruments that they want to replace with drop-down combo boxes.
 * @author RehdBlob
 * @since 2013.01.26
 * @since 1.07
 */
@SuppressWarnings("serial")
public class InstrumentRepWindow extends JPanel implements ActionListener {

    /**
     * The selection choices that one has when trying to replace instruments.
     */
    String [] res = null;

    InstrumentRepWindow(boolean b) {
        super(new BorderLayout());
        if (b) {
            res = ("mario mushroom yoshi " +
                    "star flower gameboy dog cat " +
                    "pig swan face plane boat car " +
                    "heart piranha coin shyguy boo").split("\\s");
        } else {
            res  = ("mario mushroom yoshi " +
                    "star flower gameboy dog cat " +
                    "pig swan face plane boat car " +
                    "heart piranha coin shyguy boo delete").split("\\s");
        }
    }
    /**
     * Creates a window that allows one to select different instruments
     * for replacement.
     * @param b Whether we're on the first window or the second window.
     * Basically just checks whether it's possible to select "delete"
     * or not.
     */
    public void makeWindow(boolean b) {
        JPanel panelUpper = new InstrumentRepWindow(b);
        JComboBox selectorBox = new JComboBox(res);
        JFrame theWindow = new JFrame();
        theWindow.setContentPane(panelUpper);
        theWindow.pack();
        theWindow.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

}
