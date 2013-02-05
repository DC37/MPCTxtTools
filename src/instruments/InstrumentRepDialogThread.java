package instruments;

/**
 * A Thread class that checks for different events in the Instrument
 * Replacer dialog. This should handle the different events that the
 * dialog fires off. All it does is wait to be interrupted.
 * @author RehdBlob
 * @since 1.07
 * @since 2013.0205
 */
public class InstrumentRepDialogThread extends Thread {

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                break;
            }
        }
    }

}
