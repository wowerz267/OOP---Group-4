package MapProject;

import javax.swing.*;
import java.awt.*;

public class BackgroundPanel extends JPanel {

    private Image backgroundImage;
    private Image logoImage;

    public BackgroundPanel() {
        // Load images from resources folder (assuming resources is under src)
        backgroundImage = new ImageIcon(getClass().getResource("/resources/background.jpg")).getImage();
        logoImage = new ImageIcon(getClass().getResource("/resources/logo.jpg")).getImage();

        // Optional: check if images are loaded
        System.out.println("Background: " + getClass().getResource("/resources/background.jpg"));
        System.out.println("Logo: " + getClass().getResource("/resources/logo.jpg"));

        setLayout(new BorderLayout());
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw background stretched to fill panel
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }

        // Draw smaller logo in the upper-right corner
        if (logoImage != null) {
            int logoOriginalWidth = logoImage.getWidth(this);
            int logoOriginalHeight = logoImage.getHeight(this);

            // Resize logo to 100px width, maintain aspect ratio
            int logoWidth = 150;
            int logoHeight = (logoOriginalHeight * logoWidth) / logoOriginalWidth;

            // Upper-right coordinates with margin
            int x = getWidth() - logoWidth - 10; // 10px from right edge
            int y = 10; // 10px from top

            g.drawImage(logoImage, x, y, logoWidth, logoHeight, this);
        }
    }
}
