package instruments;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

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
public class InstrumentRepDialog implements ActionListener {

    /**
     * The amount of padding that we want between different components
     * in this window.
     */
    private static final int PAD = 10;

    /**
     * The different options that one can choose for the instrument
     * to be replaced.
     */
    private static String [] rep = ("mario mushroom yoshi " +
            "star flower gameboy dog cat " +
            "pig swan face plane boat car " +
            "heart piranha coin shyguy boo").split("\\s");

    /**
     * The different options that one can choose for the instrument
     * to replace the original.
     */
    private static String [] res = ("mario mushroom yoshi " +
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
        return showDialog(theBox);
    }

    /**
     * Shows the second dialog we want, which is a selection of the 19
     * instruments plus a delete option.
     * @return A String representing the instrument that we want to replace
     * the first instrument with.
     */
    static String showReplacementDialog() {
        JComboBox theBox = new JComboBox(rep);
        return showDialog(theBox);
    }

    /**
     * Shows a dialog box with the ComboBox that we've passed to it from some
     * higher-up function call.
     * @param theBox The ComboBox holding some set of options.
     * @return A String representing the selection that we've made.
     */
    private static String showDialog(JComboBox theBox) {
        JFrame theFrame = new JFrame("Instrument Replacer");
        theFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JButton ok = new JButton("Ok");
        JButton cancel = new JButton("Cancel");
        ok.setPreferredSize(new Dimension(75, 25));
        cancel.setPreferredSize(new Dimension(75, 25));

        JPanel panelLower = new JPanel();
        JPanel panelSouth = new JPanel();
        panelSouth.setLayout(new BoxLayout(panelSouth, BoxLayout.LINE_AXIS));
        JPanel panelEast = new JPanel();
        JPanel panelWest = new JPanel();

        panelLower.add(ok);
        panelLower.add(cancel);
        panelSouth.setBorder(new EmptyBorder(PAD, PAD, PAD, PAD));
        panelLower.setBorder(new EmptyBorder(PAD, PAD, PAD, PAD));
        theFrame.setContentPane(panelLower);
        theFrame.setLocationRelativeTo(null);
        theFrame.setVisible(true);
        return null;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

}
