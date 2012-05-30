package errorChecking;

import javax.swing.JOptionPane;

/**
 * @author RehdBlob
 * @version 1.06
 * @since 1.04
 * @since 2011.0809
 */
public class ErrorDialogs {
	/**
	 * A method that generates an error dialog with the 
	 * JOptionPane class.
	 * @param output The String that the error dialog is to display.
	 */
	private static void eDialog(String output) {
		JOptionPane.showMessageDialog(null, output, "Error", 
				JOptionPane.ERROR_MESSAGE);
	}
	
	/**
	 * When a FileNotFound Exception is thrown, the 
	 * Exception should be caught and this dialog displayed.
	 */
	public static void fileNotFound() {
		eDialog("File not found!");
	}
	
	/**
	 * When the user enters a non-integer value for a field that
	 * requires an integer value, this dialog should be invoked.
	 */
	public static void illegalNumber() {
		eDialog("You didn't enter an integer!");
	}
	
	/**
	 * When the enters a tempo multiplier of 1, the program
	 * will not change anything in the text file; therefore,
	 * a SingleMultiplierException thrown by that event should 
	 * invoke this dialog.
	 */
	public static void singleMultiplier() {
		eDialog("What's the point of multiplying " +
				"the tempo by 1?");
	}
	
	/**
	 * When a file cannot be written to, this dialog should be
	 * invoked.
	 */
	public static void unwritableFile() {
		eDialog("Cannot write to file");
	}
	
	/**
	 * If the user enters a non-existent instrument
	 * for the instrument replacer, this dialog should
	 * inform them to try again with their query.
	 */
	public static void illegalCharacter() {
		eDialog("That instrument doesn't exist!");
	}
	
	/**
	 * If the Mario Paint song text file to be outputted 
	 * contains no text, this dialog should be shown as a 
	 * warning that the file contains no data.
	 */
	public static void noOutput() {
		eDialog("There's no text file data to output!");
	}
	
	/**
	 * If MarioPaintSongList.txt cannot be found, the File 
	 * Splitte branch should display this dialog so the user
	 * can take corrective action.
	 */
	public static void noSongList() {
		eDialog("MarioPaintSongList.txt not found!");
	}
	
	/**
	 * If the user enters an empty string for a file name,
	 * this dialog can be invoked.
	 */
	public static void noFileName() {
		eDialog("No file name was entered!");
	}
	
	/**
	 * If the prefs folder backup fails to work, this dialog
	 * should be invoked to inform the user of the error.
	 */
	public static void notBackedUp() {
		eDialog("Files were not backed up!");
	}
	
	/**
	 * If the input text file has no data in it, then this dialog
	 * should be shown.
	 */
	public static void noText() {
		eDialog("There is no text in this file!");
	}
	
	/**
	 * A catch-all error dialog - this is meant to help users
	 * identify unanticipated errors so that later versions can
	 * catch them.
	 */
	public static void unanticipatedError() {
		eDialog("An unanticipated error occurred.\n" +
				"You may want to talk to Rehd about this one.");
	}


}
