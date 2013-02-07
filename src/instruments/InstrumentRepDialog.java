package instruments;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * This is the custom window class for the Instrument Replacer.
 * For some reason, JOptionPane likes to make things into a JList
 * instead of a JComboBox after there are >= 20 items; unfortunately
 * there are 19 instruments and 20 options in the two types of dialogs -
 * very inconsistent...
 * This should fix the problem.
 * @author RehdBlob
 * @since 1.07
 * @since 2013.0131
 */
public class InstrumentRepDialog {

    /** Tells us whether to continue waiting for user input. */
    private static boolean cont;

    /** The current selection that we've made in the combobox. */
    private static String selection = null;

    /**
     * The different options that one can choose for the instrument
     * to be replaced.
     */
    private static String [] res = ("mario mushroom yoshi " +
            "star flower gameboy dog cat " +
            "pig swan face plane boat car " +
            "heart piranha coin shyguy boo").split("\\s");

    /**
     * The different options that one can choose for the instrument
     * to replace the original.
     */
    private static String [] rep = ("mario mushroom yoshi " +
            "star flower gameboy dog cat " +
            "pig swan face plane boat car " +
            "heart piranha coin shyguy boo delete").split("\\s");

    /**
     * Shows the first dialog we want, which is a selection of the 19
     * instruments.
     * @return A String representing the instrument that we selected.
     */
    static String showInstrumentDialog() {
        JComboBox theBox = new JComboBox(res);
        JLabel labelUpper = new JLabel("Select the instrument you want");
        JLabel labelLower = new JLabel(" to replace.");
        return showDialog(theBox, labelUpper, labelLower);
    }

    /**
     * Shows the second dialog we want, which is a selection of the 19
     * instruments plus a delete option.
     * @param The String representation of the instrument that we are
     * replacing.
     * @return A String representing the instrument that we want to replace
     * the first instrument with.
     */
    static String showReplacementDialog(String inst) {
        JComboBox theBox = new JComboBox(rep);
        JLabel labelUpper = new JLabel("Select the instrument you want");
        JLabel labelLower = new JLabel(" to replace "
                + "the " + inst +  " with.");
        return showDialog(theBox, labelUpper, labelLower);
    }

    /**
     * Shows a dialog box with the ComboBox that we've passed to it from some
     * higher-up function call.
     * @param theBox The ComboBox holding some set of options.
     * @return A String representing the selection that we've made.
     */
    private static String showDialog(JComboBox theBox,
            JLabel labelUpper, JLabel labelLower) {
        cont = true;
        JFrame theFrame = new JFrame("Instrument Replacer");
        theFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        theBox.setPreferredSize(new Dimension(150, 25));
        JButton ok = new JButton("Ok");
        JButton cancel = new JButton("Cancel");
        ok.setPreferredSize(new Dimension(75, 25));
        cancel.setPreferredSize(new Dimension(75, 25));

        JPanel theButtons = new JPanel();
        JPanel panelUnder = new JPanel();
        panelUnder.setLayout(new FlowLayout());
        JPanel comboBox = new JPanel();

        JPanel panelNorth = new JPanel();
        panelNorth.setLayout(new BoxLayout(panelNorth, BoxLayout.Y_AXIS));

        ok.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                cont = false;
            }

        });

        cancel.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                selection = null;
                cont = false;
            }

        });

        theBox.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                selection = (String)
                        ((JComboBox)e.getSource()).getSelectedItem();
            }

        });

        comboBox.add(theBox);
        theButtons.add(ok);
        theButtons.add(cancel);


        panelNorth.add(labelUpper);
        panelNorth.add(labelLower);
        panelUnder.add(panelNorth);
        panelUnder.add(comboBox);
        panelUnder.add(theButtons);

        theFrame.setContentPane(panelUnder);
        theFrame.setSize(220, 160);
        theFrame.setLocationRelativeTo(null);
        theFrame.setResizable(false);
        theFrame.setVisible(true);

        while(cont) {
            try {
                Thread.sleep(1);
                if (!theFrame.isVisible()) {
                    break;
                }
            } catch (InterruptedException e) {
                continue;
            }
        }

        String theSelection = selection;
        selection = "mario";
        theFrame.dispose();
        return theSelection;
    }

}
