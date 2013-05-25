package fileSplitter;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import javax.swing.JOptionPane;
import errorChecking.*;


/**
 * A class that increases the tempo of a 
 * MPC file by x times and that creates the 
 * necessary pieces for the increase.
 * @author RehdBlob
 * @version 1.06
 * @since 1.00a
 * @since 2011.0802
 */
public class FileSplitterMain {
	
	public static void start(File fileName) {
		try {
			if(fileName==null)
				return;
			FileSplitter split = new FileSplitter(fileName);
		
			try {
				split.getMultiplier();
				} catch (NumberFormatException e) {
					ErrorDialogs.illegalNumber();
					e.printStackTrace();
					return;
				} catch (SingleMultiplierException e) {
					ErrorDialogs.singleMultiplier();
					e.printStackTrace();
					return;
				} catch (NoEntryException e) {
					e.printStackTrace();
					return;
				} catch (CancelException c) {
					return;
				} catch (Exception e) {
					ErrorDialogs.unanticipatedError();
					e.printStackTrace();
					return;
				}
			
			try {
					split.writeOldFile();
				} catch (Exception e) {
					ErrorDialogs.unwritableFile();
					e.printStackTrace();
					return;
				}
			
			try	{
					split.writeNewFiles();
				} catch (NoOutputException e) {
					ErrorDialogs.noOutput();
					e.printStackTrace();
					return;
				} catch (NoSongListException e) {
					ErrorDialogs.noSongList();
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
		} catch (FileNotFoundException e) {
			ErrorDialogs.fileNotFound();
			e.printStackTrace();
			return;
		} catch (NoSuchElementException e)	{
			ErrorDialogs.noText();
			e.printStackTrace();
			return;
		} catch (Exception e) {
			ErrorDialogs.unanticipatedError();
			e.printStackTrace();
			return;
		}
		
		JOptionPane.showMessageDialog(null, "Complete!",
				"File Splitter", JOptionPane.INFORMATION_MESSAGE);

		
	}

}
