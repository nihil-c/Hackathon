package gui;

import controller.*;
import model.*;
import utils.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;

public class DashboardCardPanel {
    private JPanel rootPanel;
    private JPanel dashboardPanel;
    private JLabel dashboardLabel;
    private JLabel welcomeLabel;
    private JPanel roundedAddPanel;
    private JLabel addLabel;
    private JScrollPane scrollPanel;
    private JPanel eventListPanel;
    private JLabel infoLabel;

    private final Controller controller;

    public DashboardCardPanel(Controller controller) {
        this.controller = controller;

        setupScrollPanel();
        populateEventListPanel();
        customizeComponents();
    }

    private void setupScrollPanel() {
        scrollPanel.setBorder(null);
        scrollPanel.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));
        scrollPanel.getHorizontalScrollBar().setPreferredSize(new Dimension(0, 0));
        scrollPanel.getVerticalScrollBar().setUnitIncrement(10);

        eventListPanel.setLayout(new BoxLayout(eventListPanel, BoxLayout.Y_AXIS));
        eventListPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
    }

    private void populateEventListPanel() {
        if (controller.getHackathons().isEmpty()) {
            infoLabel.setVisible(true);
        } else {
            infoLabel.setVisible(false);

            for (Hackathon hackathon : controller.getHackathons()) {
                RoundedPanel card = createEventCard(
                        hackathon.getTitle(),
                        hackathon.getStartDate(),
                        hackathon.getEndDate(),
                        hackathon.getOrganizer().getUsername()
                );

                eventListPanel.add(card, 0);
                eventListPanel.add(Box.createVerticalStrut(15), 1);
            }
        }
    }

    private RoundedPanel createEventCard(String name, LocalDate startDate, LocalDate endDate, String organizer) {
        RoundedPanel card = new RoundedPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel titleLabel = new JLabel(name);
        JLabel startDateLabel = new JLabel("Start Date: " + startDate);
        JLabel endDateLabel = new JLabel("End Date: " + endDate);
        JLabel orgLabel = new JLabel("Organizer: " + organizer);

        titleLabel.setForeground(UIColors.CARMINE_RED);
        titleLabel.setFont(new Font(null, Font.BOLD, 14));

        card.add(titleLabel);
        card.add(Box.createVerticalStrut(5));
        card.add(startDateLabel);
        card.add(endDateLabel);
        card.add(orgLabel);

        return card;
    }

    private void customizeComponents() {
        dashboardLabel.setForeground(UIColors.NIGHT_BLUE);
        welcomeLabel.setText("Welcome, " + controller.getCurrentUser().getUsername() + "!");
        welcomeLabel.setForeground(Color.GRAY);
        addLabel.setForeground(Color.WHITE);
        infoLabel.setForeground(UIColors.CARMINE_RED);
    }

    private void createUIComponents() {
        roundedAddPanel = new RoundedPanel();

        setupAddPanel();
    }

    private void setupAddPanel() {
        roundedAddPanel.setBackground(UIColors.NIGHT_BLUE);
        roundedAddPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                roundedAddPanel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                roundedAddPanel.setBackground(UIColors.CARMINE_RED);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                roundedAddPanel.setCursor(Cursor.getDefaultCursor());
                roundedAddPanel.setBackground(UIColors.NIGHT_BLUE);
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                JTextField titleField = new JTextField();
                JTextField startDateField = new JTextField("YYYY-MM-DD");
                JTextField endDateField = new JTextField("YYYY-MM-DD");


                JPanel panel = new JPanel(new GridLayout(0, 1));
                panel.add(new JLabel("Title:")).setForeground(Color.GRAY);
                panel.add(titleField);
                panel.add(new JLabel("Start Date:")).setForeground(Color.GRAY);
                panel.add(startDateField);
                panel.add(new JLabel("End Date:")).setForeground(Color.GRAY);
                panel.add(endDateField);

                int result = JOptionPane.showConfirmDialog(
                        null,
                        panel,
                        "Add Hackathon",
                        JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.PLAIN_MESSAGE);

                if (result == JOptionPane.OK_OPTION) {
                    try {
                        controller.changeUserRole("organizer");

                        String title = titleField.getText().trim();
                        LocalDate startDate = LocalDate.parse(startDateField.getText().trim());
                        LocalDate endDate = LocalDate.parse(endDateField.getText().trim());

                        Hackathon newHackathon = new Hackathon(
                                title,
                                startDate,
                                endDate,
                                (Organizer) controller.getCurrentUser()
                        );

                        controller.addHackathon(newHackathon);

                        updateEventListPanel();
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Invalid input: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }

        });
    }

    private void updateEventListPanel() {
        eventListPanel.removeAll();
        populateEventListPanel();
        eventListPanel.revalidate();
        eventListPanel.repaint();
    }

    public JPanel getRootPanel() {
        return rootPanel;
    }
}