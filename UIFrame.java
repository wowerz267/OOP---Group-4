package MapProject;

import javax.swing.*;
import java.awt.*;

public class UIFrame extends JFrame {


    private JTextField emailField;
    private JButton submitButton;
    private JPanel emailPanel;
    

    private BackgroundPanel panelMap;
    private MapViewerPanel mapViewerPanel;

   
    private final EmailValidator generalValidator = new GeneralEmailValidator();
    private final EmailValidator uscValidator = new USCEmailValidator();

    // Constructor: Sets up the main window when program starts
    public UIFrame() {
        
        setTitle("CarolinMap");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        setBounds(100, 100, 900, 700); 
        getContentPane().setLayout(new BorderLayout()); 

        // Create and add email input panel at the top
        initEmailPanel();
        getContentPane().add(emailPanel, BorderLayout.NORTH);

        // Create background panel in center (will hold map later)
        panelMap = new BackgroundPanel();
        panelMap.setLayout(new BorderLayout());
        getContentPane().add(panelMap, BorderLayout.CENTER);
    }
    
    // Creates the email input interface (label, text field, button)
    private void initEmailPanel() {
        emailPanel = new JPanel();
        
        // Create components
        JLabel emailLabel = new JLabel("Enter your usc.edu.ph email:");
        emailField = new JTextField(20);  // Text box for email input
        submitButton = new JButton("Submit");

        // When button is clicked, call onSubmitEmail()
        submitButton.addActionListener(e -> onSubmitEmail());

        // Add all components to panel
        emailPanel.add(emailLabel);
        emailPanel.add(emailField);
        emailPanel.add(submitButton);
    }

    // Validates email and transitions to map screen if valid
    private void onSubmitEmail() {

        String email = emailField.getText();

        // Check #1: Is it a valid email format?
        if (!generalValidator.validate(email)) {
            JOptionPane.showMessageDialog(this,
                    "Please enter a valid email address.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;  // Stop here if invalid
        }

        // Check #2: Does it end with "usc.edu.ph"?
        if (!uscValidator.validate(email)) {
            JOptionPane.showMessageDialog(this,
                    "Email must end with usc.edu.ph",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;  // Stop here if not USC email
        }

        // Email is valid! Now transition to map screen:
        
        // Set app identifier for internet requests
        System.setProperty("http.agent", "CarolinMap/1.0 (" + email + ")");

        // Remove email panel (no longer needed)
        getContentPane().remove(emailPanel);

        // Create and add map viewer
        mapViewerPanel = new MapViewerPanel(this);
        panelMap.add(mapViewerPanel, BorderLayout.CENTER);
        mapViewerPanel.loadMap();  // Load the actual map

        // Refresh the display to show changes
        getContentPane().revalidate();
        getContentPane().repaint();    
    }
}
