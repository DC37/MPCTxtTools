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
 * there are 19 instruments and 20 options in the two types of dialogs
 * very inconsistent...
 * This should fix the problem.
 * @author RehdBlob
 * @since 1.07
 * @since 2013.0131
 */
public class InstrumentRepDialog {

    /** Tells us whether to continue waiting for user input. */
    private static boolean cont;

    /** Tells us whether we used the cancel button to exit from this dialog. */
    private static boolean cancelPressed;

    /** The current selection that we've made in the combobox. */
    private static String selection = null;

    /** The second selection that we'll be making in this dialog box. */
    private static String replacement = null;

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
     * Shows a single, hopefully intuitive, dialog that asks us which
     * instruments we want to replace and replace with.
     * @return A two-character String that starts with the character we
     * want to replace and the character we want to replace it with.
     */
    static String showInstrumentRepDialog() {
        cont = true;
        cancelPressed = false;
        selection = replacement = "mario";
        JComboBox theBox = new JComboBox(res);
        theBox.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                selection = (String)
                        ((JComboBox)e.getSource()).getSelectedItem();
            }

        });


        JLabel labelUpper = new JLabel("Select the instrument you want");
        JLabel labelUpperMid = new JLabel(" to replace.");

        JComboBox theSecondBox = new JComboBox(rep);
        theSecondBox.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                replacement = (String)
                        ((JComboBox)e.getSource()).getSelectedItem();
            }

        });

        JLabel labelLowerMid = new JLabel("Select the replacement");
        JLabel labelLower = new JLabel(" instrument.");
        return showDualDialog(theBox, theSecondBox,
                labelUpper, labelUpperMid, labelLowerMid, labelLower);
    }


    /**
     * Shows a dialog box with the ComboBox that we've passed to it from some
     * higher-up function call. The return String will have format ab, where
     * a is the first selection and b is the second selection.
     * @param theSelectBox The ComboBox holding some set of options.
     * @param theReplaceBox The ComboBox holding some set of options for replacement.
     * @param labelUpper The upper label above the first selection box
     * @param labelUpperMid The upper label below the first selection box
     * @param labelLowerMid The lower label above the second selection box
     * @param labelLower The lower label below the second selection box.
     * @return A String representing the selection that we've made.
     */
    private static String showDualDialog(JComboBox theSelectBox, JComboBox theReplaceBox,
            JLabel labelUpper, JLabel labelUpperMid, JLabel labelLowerMid,
            JLabel labelLower) {
        cont = true;
        JFrame theFrame = new JFrame("Instrument Replacer");
        theFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        theSelectBox.setPreferredSize(new Dimension(150, 25));
        theReplaceBox.setPreferredSize(new Dimension(150, 25));


        JButton ok = new JButton("Ok");
        ok.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                cont = false;
            }

        });
        ok.setPreferredSize(new Dimension(75, 25));

        JButton cancel = new JButton("Cancel");
        cancel.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                cancelPressed = true;
                cont = false;
            }

        });
        cancel.setPreferredSize(new Dimension(75, 25));



        JPanel okCancel = new JPanel();
        okCancel.add(ok);
        okCancel.add(cancel);


        JPanel panelNorth = new JPanel();
        panelNorth.setLayout(new BoxLayout(panelNorth, BoxLayout.Y_AXIS));

        JPanel panelSouth = new JPanel();
        panelSouth.setLayout(new FlowLayout());

        JPanel comboSelectBox = new JPanel();
        comboSelectBox.add(theSelectBox);

        JPanel comboReplaceBox = new JPanel();
        comboReplaceBox.add(theReplaceBox);


        panelNorth.add(labelUpper);
        panelNorth.add(labelUpperMid);
        panelNorth.add(comboSelectBox);
        panelNorth.add(labelLowerMid);
        panelNorth.add(labelLower);
        panelNorth.add(comboReplaceBox);

        panelSouth.add(panelNorth);
        panelSouth.add(okCancel);

        theFrame.setContentPane(panelSouth);
        theFrame.setSize(220, 210);
        theFrame.setLocationRelativeTo(null);
        theFrame.setResizable(false);
        theFrame.setVisible(true);

        while(cont) {
            try {
                Thread.sleep(1);
                if (!theFrame.isVisible()) {
                    return null;
                }
            } catch (InterruptedException e) {
                continue;
            }
        }

        theFrame.dispose();
        if (cancelPressed)
            return null;

        return selection + " " + replacement;
    }

}
