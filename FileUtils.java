import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class FileUtils {
    static void openFile(File f) {
        try {
            if (Desktop.isDesktopSupported()) {
                Desktop.getDesktop().open(f);
            } else {
                JOptionPane.showMessageDialog(null, "Desktop open not supported on this system.");
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Cannot open file: " + ex.getMessage());
        }
    }
}
