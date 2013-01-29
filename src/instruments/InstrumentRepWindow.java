package instruments;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
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
    static String [] res = null;

    /** This denotes the current selected instrument. */
    String theSelection = "mario";

    /**
     * This is the window that contains the buttons and combo box of this
     * class.
     */
    static JFrame theWindow = null;

    /**
     * Initializes our array of Strings (<code>res</code>) to one of two sets:
     * the first does not allow selection of "delete," while the second does.
     * @param b False means you can't select "delete."
     */
    InstrumentRepWindow(boolean b) {
        if (!b) {
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
    public static void makeWindow(boolean b) {
        JPanel panelUpper = new InstrumentRepWindow(b);
        panelUpper.setLayout(new BoxLayout(panelUpper, BoxLayout.PAGE_AXIS));
        JComboBox selectorBox = new JComboBox(res);
        selectorBox.setPreferredSize(new Dimension(100, 25));
        panelUpper.add(selectorBox);
        theWindow = new JFrame("Instrument Replacer");
        JButton ok = new JButton("Ok");
        ok.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {


            }

        });
        JButton cancel = new JButton("Cancel");
        cancel.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

            }

        });
        JPanel panelLower = new JPanel();
        panelLower.setLayout(new BoxLayout(panelLower, BoxLayout.LINE_AXIS));
        panelUpper.add(panelLower);
        panelLower.add(ok);
        panelLower.add(cancel);
        cancel.setPreferredSize(new Dimension(100, 30));
        ok.setPreferredSize(new Dimension(100, 30));
        JButton panelSides = new JButton("");
        panelSides.setBorderPainted(false);
        panelSides.setBorder(null);
        theWindow.setContentPane(panelUpper);
        theWindow.pack();
        theWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        theWindow.setResizable(true);
        theWindow.setVisible(true);
        theWindow.setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JComboBox cb = (JComboBox) e.getSource();
        theSelection = (String) cb.getSelectedItem();
    }

}
