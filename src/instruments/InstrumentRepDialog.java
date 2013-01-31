package instruments;

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

    /**
     * The different options that one can choose for the instrument
     * to be replaced.
     */
    String [] rep = ("mario mushroom yoshi " +
            "star flower gameboy dog cat " +
            "pig swan face plane boat car " +
            "heart piranha coin shyguy boo").split("\\s");

    /**
     * The different options that one can choose for the instrument
     * to replace the original.
     */
    String [] res = ("mario mushroom yoshi " +
            "star flower gameboy dog cat " +
            "pig swan face plane boat car " +
            "heart piranha coin shyguy boo delete").split("\\s");

}
