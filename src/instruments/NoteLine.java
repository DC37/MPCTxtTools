package instruments;

import java.text.ParseException;
import java.util.ArrayList;

import utilities.TextUtil;

/**
 * A class that denotes a line of notes on the MPC staff.
 * @author RehdBlob
 * @since 1.07
 * @since 2013.04.08
 */
public class NoteLine {

    /** THe number of notes in an MPC song. */
    private static final int NOTESIZE = 6;

    /** List of notes in this line. */
    private ArrayList<String> notes;

    /** A character (a-q) denoting what volume that this note is played at. */
    private char vol;

    /** Makes a new note line without anything in it. */
    public NoteLine() {
        notes = new ArrayList<String>();
        vol = 'q';
    }

    /**
     * Makes a new note line from a String that we assume to be a note line.
     * @param parse This is the String that we are attempting to parse.
     */
    public NoteLine(String parse) throws ParseException {
        if (parse.length() < 7) {
            notes = new ArrayList<String>();
            vol = 'q';
            return;
        }
        notes = TextUtil.dice(parse);
        vol = parse.charAt(parse.length() - 2);
        if (!(vol >= 'a' && vol <= 'q'))
            vol = 'q';
        if (notes.size() != NOTESIZE)
            throw new ParseException("Incorrect number of notes in a line!", 0);
    }

    @Override
    public String toString() {
        return "";
    }
}
