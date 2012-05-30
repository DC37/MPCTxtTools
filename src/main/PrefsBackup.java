package main;

import java.io.File;
import javax.swing.JOptionPane;
import org.apache.commons.io.FileUtils;

/**
 * Backs up the prefs folder; basically
 * copies everything that the folder the program is 
 * in to a folder called "PrefsBackup."
 * @author RehdBlob
 * @since 1.05
 * @since 2011.0809
 * @version 1.06
 */
public class PrefsBackup {
	public static void backup() throws Exception {
		String s = new File(".").getAbsolutePath();
		FileUtils.copyDirectory(
				new File(s), new File(s+"\\PrefsBackup"), true);
		JOptionPane.showMessageDialog(null, "Complete!",
				"Prefs Folder Backup", JOptionPane.INFORMATION_MESSAGE);
	}
}
