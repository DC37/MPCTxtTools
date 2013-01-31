package instruments;
import javax.swing.JOptionPane;
import errorChecking.ErrorDialogs;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Hashtable;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Replaces an instrument in Mario Paint Composer
 * with another with unprecedented speed. I intend to add
 * more functionality to this later, though this is already
 * extremely useful.
 * @author RehdBlob
 * @version 1.06
 * @since 1.00
 * @since 2011.0802
 */
public class InstrumentReplacerMain {
    public static Hashtable<String, Character> instruments =
            new Hashtable<String, Character>();

    public static void start(File fileName) {
        if(fileName==null)
            return;

        InstrumentReplacer replace = new InstrumentReplacer();
        putInstruments();

        try {
            Scanner file = new Scanner(fileName);
            String songText = file.nextLine();
            replace.setParams(songText,fileName);
            file.close();
        } catch(FileNotFoundException e) {
            ErrorDialogs.fileNotFound();
            e.printStackTrace();
            return;
        } catch(NoSuchElementException e) {
            ErrorDialogs.noText();
            e.printStackTrace();
            return;
        }

        String [] res = ("mario mushroom yoshi " +
                "star flower gameboy dog cat " +
                "pig swan face plane boat car " +
                "heart piranha coin shyguy boo").split("\\s");

        String change = (String) JOptionPane.showInputDialog(null,
                "Replace (mario, mushroom, yoshi,\n" +
                        "star, flower, gameboy, dog, cat,\n" +
                        "pig, swan, face, plane, boat, car,\n" +
                        "heart, piranha, coin, shyguy, boo):",
                        "Instrument Replacer", JOptionPane.QUESTION_MESSAGE,
                        null, res, "Mario");

        if (change == null)
            return;

        Character ch = instruments.get(change.toLowerCase());
        if (ch == null) {
            ErrorDialogs.illegalCharacter();
            return;
        }

        res = ("mario mushroom yoshi " +
                "star flower gameboy dog cat " +
                "pig swan face plane boat car " +
                "heart piranha coin shyguy boo delete").split("\\s");

        String changeTo = (String)JOptionPane.showInputDialog(null,
                "With (mario, mushroom, yoshi,\n" +
                        "star, flower, gameboy, dog, cat,\n" +
                        "pig, swan, face, plane, boat, car,\n" +
                        "heart, piranha, coin, shyguy, boo,\n" +
                        "delete):", "Instrument Replacer",
                        JOptionPane.QUESTION_MESSAGE, null, res, "Mushroom");
        if (changeTo == null)
            return;

        Character chT = instruments.get(changeTo.toLowerCase());
        if (chT == null) {
            ErrorDialogs.illegalCharacter();
            return;
        }

        try {
            replace.writeOldFile();
        }
        catch (Exception e) {
            ErrorDialogs.unwritableFile();
            e.printStackTrace();
            return;
        }
        String newFile = replace.change(ch, chT);

        try {
            PrintStream writer = new PrintStream(fileName);
            writer.print(newFile);
            writer.close();
        } catch (FileNotFoundException e) {
            ErrorDialogs.fileNotFound();
            e.printStackTrace();
            return;
        } catch (SecurityException e) {
            ErrorDialogs.unwritableFile();
            e.printStackTrace();
            return;
        } catch (Exception e) {
            ErrorDialogs.unanticipatedError();
            e.printStackTrace();
            return;
        }
        JOptionPane.showMessageDialog(null, "Complete!",
                "Instrument Replacer", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * This populates the hashtable we're using to get characters.
     * @since 1.00b
     * @since 2011.0802
     */
    private static void putInstruments() {
        String [] res = ("mario mushroom yoshi " +
                "star flower gameboy dog cat " +
                "pig swan face plane boat car " +
                "heart piranha coin shyguy boo delete").split("\\s");
        for(char ch = 'a'; ch < 'u'; ch++)
            instruments.put(res[ch-97], new Character(ch));
    }
}
