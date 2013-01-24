package instruments;

import java.io.File;
import java.io.PrintStream;
import java.util.ArrayList;
import static utilities.TextUtil.*;


/**
 * @author RehdBlob
 * @version 1.06
 * @since 1.00
 * @since 2011.0802
 */

public class InstrumentReplacer {

    private String text  = "";
    private String start = "";
    private String end   = "";
    private ArrayList<String> pieces = new ArrayList<String>();
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
                            out+=changeTo + t.substring(1);
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
     * Splits a single MPC note line into its singular
     * instrumental components.
     * @param s The MPC note line to be converted into an ArrayList.
     */
    private ArrayList<String> separate(String s) {

        ArrayList<String> result = new ArrayList<String>();
        while (s.indexOf('+')!= -1) {
            result.add(s.substring(0,s.indexOf('+')+1));
            s = s.substring(s.indexOf('+')+1);
        }
        result.add(s);
        return result;
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
