package instruments;

import java.io.File;
import java.io.PrintStream;
import java.text.ParseException;
import java.util.ArrayList;

import errorChecking.FormatException;
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
    private File file;

    /** This means that there was some error in parsing a note line. */
    @SuppressWarnings("unused")
    private boolean error;

    /**
     * Initializes the Instrument Replacer's <b>text</b>, <b>start</b>,
     * <b>end</b>, <b>pieces</b>, and <b>file</b> fields given the
     * parameters.
     * @param read The raw MPC text file data given to this class
     * by another method.
     * @param fileName The name of the file that was opened.
     * @throws FormatException If there is some issue with the parsing.
     */
    public void setParams(String read, File fileName) throws FormatException {
        try {
            text   = clean(read);
            start  = getStart(read);
            end    = getEnd(read);
            pieces = chop(slice(text));
            file   = fileName;
        } catch (IndexOutOfBoundsException e) {
            throw new FormatException();
        }
    }

    /**
     * Uses a newer schema to replace all of one instrument with another. This
     * method takes advantage of my newish knowledge in regex parsing.
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
            try {
                NoteLine n = new NoteLine(s);
                ArrayList<String> notes = n.notes();
                for (int i = 0; i < notes.size(); i++) {
                    String str = notes.get(i);
                    if (str.length() > 0) {
                        if (delete && str.charAt(0) == change)
                            notes.set(i, "");
                        else if (str.charAt(0) == change)
                            notes.set(i, "" + changeTo + str.substring(1));
                    }
                }
                st.append(n.toString());
                out += st.toString();
            } catch (ParseException e) {
                st.append(":");
                error = true;
            }
        }
        out += end;
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
        fName = fName.substring(0,fName.indexOf(']'));
        fName = checkOldExists(fName);
        PrintStream writer = new PrintStream(fName);
        writer.print(text);
        writer.close();
    }

    /**
     * Checks whether a file that we are trying to write to back up
     * already exists.
     * @param name The filename that we are trying to check.
     * @return A valid filename that we can back up to.
     */
    private String checkOldExists(String name) {
        File f = new File(name + "old1]MarioPaint.txt");
        int counter = 2;
        while (f.exists()) {
            f = new File(name + "old" + counter + "]MarioPaint.txt");
            counter++;
        }
        return f.getName();
    }

}
