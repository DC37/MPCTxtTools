package instruments;

import java.io.File;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

import utilities.TextUtil;

import static utilities.TextUtil.*;


/**
 * This is the class that takes care of replacing instruments in
 * an MPC Text file.
 * @author RehdBlob
 * @since 1.00
 * @since 2011.0802
 */

public class InstrumentReplacer {

    /** This is the song data. */
    private String text  = "";

    /** This is the header for the song. It tends to be the time signature. */
    private String start = "";

    /** This is the footer for the song. It tends to be the tempo. */
    private String end   = "";

    /** This is the song data, in pieces. */
    private ArrayList<String> pieces = new ArrayList<String>();

    /** This is the file that we're writing to. */
    private File file  = null;

    /**
     * Initializes the Instrument Replacer's <b>text</b>, <b>start</b>,
     * <b>end</b>, <b>pieces</b>, and <b>file</b> fields given the
     * parameters.
     * @param read The raw MPC text file data given to this class
     * by another method.
     * @param fileName The name of the file that was opened.
     */
    public void setParams(String read, File fileName) {
        text   = clean(read);
        start  = getStart(read);
        end    = getEnd(read);
        pieces = chop(slice(text));
        file   = fileName;
    }

    /**
     * @param change The char that the method is to find and replace.
     * @param changeTo The char that the method is to replace <b>change</b>
     * with.
     * @return The full MPC raw song data that is to be written
     * into the file, with the correct instruments replaced.
     */
    public String change(char change, char changeTo) {

        String out = start;
        boolean delete = (changeTo == 't');
        for (String s : pieces) {
            if (s.indexOf(change) != -1) {
                int i = 0;
                ArrayList<String> beatPart = separate(s);
                for(String t : beatPart) {
                    i++;
                    if (i == 7)	{
                        i = 0;
                        out+=t;
                        break;
                    }
                    else if (t.charAt(0) == change)	{
                        if (delete)
                            out += "+";
                        else
                            out += changeTo + t.substring(1);
                    } else {
                        out+= t;
                    }
                }
            } else 	{
                out+=s;
            }
        }
        out += end;
        out = clean(out);
        return out;
    }

    /**
     * Uses a newer schema to replace all of one instrument with another.
     * @param change The character to change.
     * @param changeTo The character to change to.
     * @return The full MPC raw song data that is to be written
     * into the file, with the correct instruments replaced.
     */
    public String changeNew(char change, char changeTo) {
        String out = start;
        boolean delete = (changeTo == 't');
        for (String s : pieces) {
            StringBuilder st = new StringBuilder();
            ArrayList<String> notes = TextUtil.dice(s);
            for (String inst : notes) {
                char [] theStr = inst.toCharArray();
                if (theStr[0] == change)
                    theStr[0] = changeTo;
                st.append(theStr);
                st.append("+");
            }
        }
        return out;
    }

    /**
     * Backs up the original text file to a file with "old" appended
     * to the end of the file name.
     * @since 1.00a
     * @since 2011.0802
     * @throws Exception If the PrintStream cannot write to the file.
     */
    public void writeOldFile() throws Exception	{
        String fName = file.getName();
        PrintStream writer = new PrintStream(
                fName.substring(0,fName.indexOf(']'))
                + "old]MarioPaint.txt");
        writer.print(text);
        writer.close();
    }

}
