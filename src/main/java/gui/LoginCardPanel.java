package gui;

import controller.Controller;
import exceptions.BlankFieldException;
import exceptions.UserNotFoundException;
import utils.RoundedPanel;
import utils.UIColors;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LoginCardPanel {
    private JPanel rootPanel;
    private JPanel cardPanel;
    private JPasswordField passwordField;
    private JTextField usernameField;
    private JPanel rLoginPanel;
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    private JLabel loginLabel;
    private JLabel dontHaveAnAccountLabel;
    private JLabel infoLabel;
    private JLabel welcomeLabel;

    private final Controller controller;

    // Costruttore
    public LoginCardPanel(final JPanel cardPanel, final Controller controller) {
        this.cardPanel = cardPanel;
        this.controller = controller;

        customizeComponents();
        setupDontHaveAnAccountLabelListener();
    }

    // Metodi privati
    private void customizeComponents() {
        welcomeLabel.setForeground(UIColors.NIGHT_BLUE);
        infoLabel.setForeground(UIColors.CARMINE_RED);
        usernameLabel.setForeground(Color.GRAY);
        passwordLabel.setForeground(Color.GRAY);

        rLoginPanel.setBackground(UIColors.NIGHT_BLUE);
        loginLabel.setForeground(Color.WHITE);

        dontHaveAnAccountLabel.setForeground(UIColors.HYPERLINK_BLUE);
        dontHaveAnAccountLabel.setText("<html><u>Don't have an account? Register!</u></html>");
    }

    private void createUIComponents() {
        rLoginPanel = new RoundedPanel();

        setupRLoginPanelListener();
    }

    private void setupRLoginPanelListener() {
        rLoginPanel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        rLoginPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                handleLogin();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                rLoginPanel.setBackground(UIColors.CARMINE_RED);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                rLoginPanel.setBackground(UIColors.NIGHT_BLUE);
            }
        });
    }

    private void handleLogin() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        try {
            controller.loginUser(username, password);
            callMainFrame();
        } catch (BlankFieldException | UserNotFoundException ex) {
            showErrorDialog(ex.getMessage());
        }
    }

    private void callMainFrame() {
        SwingUtilities.invokeLater(() -> {
            new MainFrame(controller).setVisible(true);
            JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(rootPanel);
            if (frame != null) frame.dispose();
        });
    }

    private void setupDontHaveAnAccountLabelListener() {
        dontHaveAnAccountLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        dontHaveAnAccountLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                CardLayout layout = (CardLayout) cardPanel.getLayout();
                layout.show(cardPanel, "register");
            }
        });
    }

    private void showErrorDialog(String message) {
        JOptionPane.showMessageDialog(
                null,
                message,
                "Login Error",
                JOptionPane.ERROR_MESSAGE
        );
    }

    // Getter & Setter
    public JPanel getRootPanel() {
        return rootPanel;
    }
}