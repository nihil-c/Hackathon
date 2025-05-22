package gui;

import controller.Controller;
import exceptions.AlreadyOrganizingAnotherEventException;
import exceptions.AlreadyRegisteredToHackathonException;
import exceptions.OrganizerSelfRegistrationException;
import model.Hackathon;
import model.Role;
import model.User;
import utils.RoundedPanel;
import utils.UIColors;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;

public class DashboardCardPanel {
    private JPanel rootPanel;
    private JLabel dashboardLabel;
    private JLabel welcomeLabel;
    private JPanel roundedAddPanel;
    private JLabel addLabel;
    private JScrollPane scrollPanel;
    private JPanel eventListPanel;
    private JLabel infoLabel;
    private JLabel openEventsLabel;

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
        eventListPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
    }

    private void populateEventListPanel() {
        if (controller.getHackathons().isEmpty()) {
            infoLabel.setVisible(true);
        } else {
            infoLabel.setVisible(false);
            for (Hackathon hackathon : controller.getHackathons()) {
                RoundedPanel card = createEventCard(hackathon);
                eventListPanel.add(card, 0);
                eventListPanel.add(Box.createVerticalStrut(15), 1);
            }
        }
    }

    private RoundedPanel createEventCard(Hackathon hackathon) {
        RoundedPanel card = new RoundedPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel titleLabel = new JLabel(hackathon.getTitle());
        JLabel locationLabel = new JLabel("Location: " + hackathon.getLocation());
        JLabel startDateLabel = new JLabel("Start Date: " + hackathon.getStartDate());
        JLabel endDateLabel = new JLabel("End Date: " + hackathon.getEndDate());
        JLabel registrationDeadlineLabel = new JLabel("Registration deadline: " + hackathon.getStartDate().minusDays(2));
        JLabel organizerLabel = new JLabel("Organizer: " + hackathon.getOrganizer().getUsername());
        JLabel creationDateLabel = new JLabel("Creation Date: " + LocalDate.now());

        titleLabel.setForeground(UIColors.CARMINE_RED);
        titleLabel.setFont(new Font(null, Font.BOLD, 14));
        creationDateLabel.setForeground(Color.GRAY);

        card.add(titleLabel);
        card.add(Box.createVerticalStrut(5));
        card.add(locationLabel);
        card.add(startDateLabel);
        card.add(endDateLabel);
        card.add(registrationDeadlineLabel);
        card.add(organizerLabel);
        card.add(Box.createVerticalStrut(5));
        card.add(creationDateLabel);

        makeCardInteractive(card, hackathon);

        return card;
    }

    private void makeCardInteractive(RoundedPanel card, Hackathon hackathon) {
        card.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int result = JOptionPane.showConfirmDialog(
                        null,
                        "Do you want to register to this hackathon?",
                        "Register",
                        JOptionPane.OK_CANCEL_OPTION
                );

                if (result == JOptionPane.OK_OPTION) {
                    User currentUser = controller.getCurrentUser();
                    try {
                        currentUser.registerToHackathon(hackathon);
                        controller.assignRoleToCurrentUser("participant");
                        currentUser.setRegisteredHackathon(hackathon);
                        JOptionPane.showMessageDialog(null, "Successfully registered!");
                    } catch (AlreadyOrganizingAnotherEventException ex) {
                        JOptionPane.showMessageDialog(
                                null,
                                "You are already organizing another event.",
                                "Error",
                                JOptionPane.ERROR_MESSAGE
                        );
                    } catch (OrganizerSelfRegistrationException ex) {
                        JOptionPane.showMessageDialog(
                                null,
                                "You cannot register to an event you created.",
                                "Error",
                                JOptionPane.ERROR_MESSAGE
                        );
                    } catch (AlreadyRegisteredToHackathonException ex) {
                        JOptionPane.showMessageDialog(
                                null,
                                "You are already registered to this hackathon.",
                                "Error",
                                JOptionPane.ERROR_MESSAGE
                        );
                    }
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                card.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                card.setBackground(UIColors.LIGHT_GRAY);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                card.setCursor(Cursor.getDefaultCursor());
                card.setBackground(Color.WHITE);
            }
        });
    }

    private void customizeComponents() {
        dashboardLabel.setForeground(UIColors.NIGHT_BLUE);
        welcomeLabel.setText("Welcome, @" + controller.getCurrentUser().getUsername() + "!");
        welcomeLabel.setForeground(Color.GRAY);
        openEventsLabel.setForeground(UIColors.CARMINE_RED);
        addLabel.setForeground(Color.WHITE);
        infoLabel.setForeground(Color.GRAY);
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
            public void mousePressed(MouseEvent e) {
                Role currentUserRole = controller.getCurrentUser().getRoleInstance();

                if (currentUserRole == null) {
                    JTextField titleField = new JTextField();
                    JTextField locationField = new JTextField();
                    JTextField startDateField = new JTextField(LocalDate.now().toString());
                    JTextField endDateField = new JTextField(LocalDate.now().toString());

                    Dimension maxSize = new Dimension(Integer.MAX_VALUE, 30);
                    titleField.setMaximumSize(maxSize);
                    locationField.setMaximumSize(maxSize);
                    startDateField.setMaximumSize(maxSize);
                    endDateField.setMaximumSize(maxSize);

                    JPanel panel = new JPanel();
                    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

                    panel.add(new JLabel("Title:")).setForeground(Color.GRAY);
                    panel.add(titleField);
                    panel.add(Box.createVerticalStrut(10));

                    panel.add(new JLabel("Location:")).setForeground(Color.GRAY);
                    panel.add(locationField);
                    panel.add(Box.createVerticalStrut(10));

                    panel.add(new JLabel("Start Date (YYYY-MM-DD):")).setForeground(Color.GRAY);
                    panel.add(startDateField);
                    panel.add(Box.createVerticalStrut(10));

                    panel.add(new JLabel("End Date (YYYY-MM-DD):")).setForeground(Color.GRAY);
                    panel.add(endDateField);
                    panel.add(Box.createVerticalStrut(10));

                    panel.add(new JLabel("By pressing OK you will become the event Organizer.")).setForeground(UIColors.CARMINE_RED);
                    panel.add(new JLabel("Organizers cannot join or manage more than one event.")).setForeground(UIColors.CARMINE_RED);

                    int result = JOptionPane.showConfirmDialog(
                            null,
                            panel,
                            "Add Hackathon",
                            JOptionPane.OK_CANCEL_OPTION,
                            JOptionPane.PLAIN_MESSAGE
                    );

                    if (result == JOptionPane.OK_OPTION) {
                        try {
                            User currentUser = controller.getCurrentUser();
                            controller.assignRoleToCurrentUser("organizer");

                            String title = titleField.getText();
                            String location = locationField.getText();
                            LocalDate startDate = LocalDate.parse(startDateField.getText());
                            LocalDate endDate = LocalDate.parse(endDateField.getText());

                            Hackathon newHackathon = currentUser.createHackathon(title, location, startDate, endDate);
                            currentUser.setRegisteredHackathon(newHackathon);
                            controller.addHackathonToList(newHackathon);

                            updateEventListPanel();
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(
                                    null,
                                    "Invalid input: " + ex.getMessage(),
                                    "Error",
                                    JOptionPane.ERROR_MESSAGE
                            );
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(
                            null,
                            "Cannot create a new event:\nyou're already participating in a hackathon.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE
                    );
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