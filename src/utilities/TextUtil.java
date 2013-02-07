package utilities;

import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import javax.swing.JOptionPane;
import errorChecking.NoTextException;



/**
 * Basically a class to hold some useful methods that
 * were programmed and seem to be used a lot.
 * Since v1.07, we have moved onto using regexes.
 * @author RehdBlob
 * @since 1.05
 * @since 2011.0902
 */
public class TextUtil {

    /**
     * This is the pattern that denotes a line of notes.
     */
    private Pattern noteLine = Pattern.compile("(()()()()()()):");

    /**
     * Uses regex to clean up an MPC text file
     * @param t The song text file to be "cleaned," that is, the
     * song file that has unnecessarily empty note lines.
     * @return The "cleaned" string.
     * @since 1.07
     * @since 2013.0205
     */
    public static String clean(String t) {
        t.replaceAll("++++++q:", ":");
        return t;
    }


    /**
     * Displays an information dialog given a title and text to
     * display.
     * @since 1.06
     * @since 2011.1127
     * @param title The title of the dialog box.
     * @param text The text to be displayed in the text box.
     * @throws NoTextException If the <b>text</b> parameter happens
     * to be <b>null</b> or equals the empty "" string.
     */
    public static void infoDialog(String title, String text) throws NoTextException
    {
        if (text == null | text.equals(""))
            throw new NoTextException();
        JOptionPane.showMessageDialog(null, text, title,
                JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * @since 1.05
     * @since 2011.1108
     * @param s The song text file that is to be "chopped" into
     * note lines.
     * @return All note lines that have data in them.
     */
    public static ArrayList<String> chop(String s) {
        ArrayList<String> result = new ArrayList<String>();
        while (s.length() != 0 & s.indexOf(':') != -1)
        {
            result.add(s.substring(0,s.indexOf(':')+1));
            s = s.substring(s.indexOf(':')+1);
            if (s.indexOf('+')==-1)
                break;
        }
        return result;
    }

    /**
     * @since 1.05
     * @since 2011.1108
     * @param read The song text file that is to be "sliced" into
     * just the note data.
     * @return The song text file with the time signature and tempo removed.
     */
    public static String slice(String read) {
        return read.substring(read.indexOf('*')+1,read.lastIndexOf(':')+1);
    }

    /**
     * @since 1.05
     * @since 2011.1108
     * @return The time signature of the song text file.
     */
    public static String getStart(String read) {
        return read.substring(0,read.indexOf('*')+1);
    }

    /**
     * @since 1.05
     * @since 2011.1108
     * @return The tempo of the song text file.
     */
    public static String getEnd(String read) {
        return read.substring(read.lastIndexOf(':')+1);
    }
}
