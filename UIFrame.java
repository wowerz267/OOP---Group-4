package MapProject;

import javax.swing.*;
import java.awt.*;

public class UIFrame extends JFrame {

    private JTextField emailField;
    private JButton submitButton;
    private JPanel emailPanel;
    private JPanel panelMap;
    private MapViewerPanel mapViewerPanel;

    public UIFrame() {
        setTitle("Discounted Places Finder");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 900, 700);
        getContentPane().setLayout(new BorderLayout());

        initEmailPanel();
        getContentPane().add(emailPanel, BorderLayout.NORTH);

        // Use BackgroundPanel for center
        panelMap = new BackgroundPanel();
        panelMap.setLayout(new BorderLayout());
        getContentPane().add(panelMap, BorderLayout.CENTER);
    }

    private void initEmailPanel() {
        emailPanel = new JPanel();
        JLabel emailLabel = new JLabel("Enter your usc.edu.ph email:");
        emailField = new JTextField(20);
        submitButton = new JButton("Submit");

        submitButton.addActionListener(e -> onSubmitEmail());

        emailPanel.add(emailLabel);
        emailPanel.add(emailField);
        emailPanel.add(submitButton);
    }

    private void onSubmitEmail() {
        String email = emailField.getText();
        if (!EmailValidator.isValid(email)) {
            JOptionPane.showMessageDialog(this,
                    "Please enter a valid email address.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (!EmailValidator.isUSCEmail(email)) {
            JOptionPane.showMessageDialog(this,
                    "Email must end with usc.edu.ph",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        System.setProperty("http.agent", "DiscountedPlacesFinder/1.0 (" + email + ")");

        getContentPane().remove(emailPanel);

        mapViewerPanel = new MapViewerPanel(this);
        panelMap.add(mapViewerPanel, BorderLayout.CENTER);
        mapViewerPanel.loadMap();

        getContentPane().revalidate();
        getContentPane().repaint();
    }
}