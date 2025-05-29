package gui;

import controller.Controller;
import exceptions.*;
import model.*;
import utils.RoundedPanel;
import utils.UIColors;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;

public class DashboardCardPanel {
    // Attributi
    private JPanel rootPanel;
    private JLabel dashboardLabel;
    private JLabel welcomeLabel;
    private JPanel rAddPanel;
    private JLabel addLabel;
    private JScrollPane scrollPanel;
    private JPanel eventListPanel;
    private JLabel infoLabel;
    private JLabel openEventsLabel;
    private JLabel emailLabel;

    private final Controller controller;

    // Costruttore
    public DashboardCardPanel(Controller controller) {
        this.controller = controller;

        customizeComponents();
        setupScrollPanel();
        populateEventListPanel();
    }

    // Metodi privati
    private void customizeComponents() {
        dashboardLabel.setForeground(UIColors.NIGHT_BLUE);
        welcomeLabel.setForeground(UIColors.CARMINE_RED);
        welcomeLabel.setText("Welcome, @" + controller.getCurrentUser().getUsername() + "!");
        emailLabel.setForeground(Color.GRAY);
        emailLabel.setText("E-mail: " + controller.getCurrentUser().getEmail());
        openEventsLabel.setForeground(UIColors.CARMINE_RED);
        rAddPanel.setBackground(UIColors.NIGHT_BLUE);
        addLabel.setForeground(Color.WHITE);
        infoLabel.setForeground(Color.GRAY);

        SwingUtilities.invokeLater(() -> scrollPanel.getVerticalScrollBar().setValue(0));
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
            for (Hackathon h : controller.getHackathons()) {
                RoundedPanel card = createEventCard(h);
                eventListPanel.add(card, 0);
                eventListPanel.add(Box.createVerticalStrut(15), 1);
            }
        }
    }

    private RoundedPanel createEventCard(Hackathon hackathon) {
        RoundedPanel card = new RoundedPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBackground(Color.WHITE);
        card.setBorderColor(UIColors.LIGHT_GRAY);
        card.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        String isOpenString = hackathon.isOpen() ? "\uD83D\uDFE2" : "\uD83D\uDD34";

        JLabel titleLabel = new JLabel(hackathon.getTitle() + " " + isOpenString);
        JLabel locationLabel = new JLabel("Location: " + hackathon.getLocation());
        JLabel startDateLabel = new JLabel("Start Date: " + hackathon.getStartDate());
        JLabel endDateLabel = new JLabel("End Date: " + hackathon.getEndDate());
        JLabel registrationDeadlineLabel = new JLabel("Registration deadline: " + hackathon.getStartDate().minusDays(2));
        JLabel maxParticipants = new JLabel("Max number of participants: " + hackathon.getMaxParticipants());
        JLabel maxTeamSize = new JLabel("Max team size: " + hackathon.getMaxTeamSize());
        JLabel organizerLabel = new JLabel("Organizer: @" + hackathon.getOrganizer().getUsername());
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
        card.add(maxParticipants);
        card.add(maxTeamSize);
        card.add(organizerLabel);
        card.add(Box.createVerticalStrut(5));
        card.add(creationDateLabel);

        makeCardInteractive(card, hackathon);

        return card;
    }

    private void makeCardInteractive(RoundedPanel card, Hackathon hackathon) {
        card.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

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
                    try {
                        controller.registerUserToHackathon(hackathon, controller.getCurrentUser());

                        JOptionPane.showMessageDialog(
                                null,
                                "Registration completed!"
                        );
                    } catch (MaxLimitReachedException | CannotRegisterToEventException ex) {
                        showErrorDialog(ex.getMessage());
                    } catch (UserIsAParticipantException ex) {
                        showErrorDialog("You are already registered for the event \"" + controller.getCurrentUser().getHackathon().getTitle() + "\" as Participant.");
                    } catch (UserIsAnOrganizerException ex) {
                        showErrorDialog("You are already registered for the event \"" + controller.getCurrentUser().getHackathon().getTitle() + "\" as Organizer.");
                    } catch (UserIsAJudgeException ex) {
                        showErrorDialog("You are already registered for the event \"" + controller.getCurrentUser().getHackathon().getTitle() + "\" as Judge.");
                    }
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                card.setBackground(UIColors.LIGHT_GRAY);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                card.setBackground(Color.WHITE);
            }
        });
    }

    private void createUIComponents() {
        rAddPanel = new RoundedPanel();

        setupAddPanelListener();
    }

    private void setupAddPanelListener() {
        rAddPanel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        rAddPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                User currentUser = controller.getCurrentUser();
                Role currentUserRole = currentUser.getRole();

                if (currentUserRole instanceof ParticipantRole) {
                    showErrorDialog("You cannot create a new event while being a Participant.");
                } else if (currentUserRole instanceof OrganizerRole) {
                    showErrorDialog("You cannot create a new event while being an Organizer.");
                } else if (currentUserRole instanceof JudgeRole) {
                    showErrorDialog("You cannot create a new event while being a Judge.");
                } else {
                    try {
                        JTextField titleField = new JTextField();
                        JTextField locationField = new JTextField();
                        JTextField startDateField = new JTextField(LocalDate.now().plusDays(3).toString());
                        JTextField endDateField = new JTextField(LocalDate.now().plusDays(4).toString());
                        JTextField maxParticipantsField = new JTextField("1");
                        JTextField maxTeamSizeField = new JTextField("1");

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

                        panel.add(new JLabel("Max number of participants:")).setForeground(Color.GRAY);
                        panel.add(maxParticipantsField);
                        panel.add(Box.createVerticalStrut(10));

                        panel.add(new JLabel("Max team size:")).setForeground(Color.GRAY);
                        panel.add(maxTeamSizeField);
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
                            String title = titleField.getText();
                            String location = locationField.getText();
                            LocalDate startDate = LocalDate.parse(startDateField.getText());
                            LocalDate endDate = LocalDate.parse(endDateField.getText());
                            String maxParticipants = maxParticipantsField.getText();
                            String maxTeamSize = maxTeamSizeField.getText();

                            if (title.isBlank() || location.isBlank() || startDate.toString().isBlank() ||
                                    endDate.toString().isBlank() || maxParticipants.isBlank() || maxTeamSize.isBlank()) {
                                throw new BlankFieldException();
                            }

                            OrganizerRole organizerRole = new OrganizerRole();

                            Hackathon newHackathon = organizerRole.createHackathon(
                                    title,
                                    location,
                                    startDate,
                                    endDate,
                                    Integer.parseInt(maxParticipants),
                                    Integer.parseInt(maxTeamSize),
                                    currentUser
                            );

                            controller.addHackathon(newHackathon);
                            currentUser.setRole(organizerRole);

                            updateEventListPanel();
                        }
                    } catch (BlankFieldException | InvalidTimeWindowException | InvalidIntegerParameterException ex) {
                        showErrorDialog(ex.getMessage());
                    }
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                rAddPanel.setBackground(UIColors.CARMINE_RED);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                rAddPanel.setBackground(UIColors.NIGHT_BLUE);
            }
        });
    }

    private void updateEventListPanel() {
        eventListPanel.removeAll();
        populateEventListPanel();
        eventListPanel.revalidate();
        eventListPanel.repaint();
    }

    private void showErrorDialog(String message) {
        JOptionPane.showMessageDialog(
                null,
                message,
                "Error",
                JOptionPane.ERROR_MESSAGE
        );
    }

    // Getter & Setter
    public JPanel getRootPanel() {
        return rootPanel;
    }
}