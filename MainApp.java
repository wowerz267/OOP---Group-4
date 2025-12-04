package MapProject;

import javax.swing.SwingUtilities;

public class MainApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            UIFrame frame = new UIFrame();  // must import or be in same package
            frame.setVisible(true);
        });
    }
}