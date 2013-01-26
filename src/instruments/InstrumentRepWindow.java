package instruments;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
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

    public InstrumentRepWindow(boolean b) {
        super(new BorderLayout());
        String [] res = null;
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
        JComboBox selectorBox = new JComboBox(res);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

}
