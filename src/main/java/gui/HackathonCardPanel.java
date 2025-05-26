package gui;

import controller.Controller;
import model.*;
import utils.RoundedPanel;
import utils.UIColors;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class HackathonCardPanel {
    private JPanel rootPanel;
    private JLabel hackathonLabel;
    private JLabel infoLabel;
    private JScrollPane scrollPanel;
    private JLabel overviewLabel;
    private JPanel roundedTitlePanel;
    private JLabel titleLabel;
    private JPanel roundedTitleContentPanel;
    private JPanel roundedLocationPanel;
    private JLabel locationLabel;
    private JPanel roundedLocationContentPanel;
    private JLabel titleContentLabel;
    private JLabel locationContentLabel;
    private JPanel roundedStartDatePanel;
    private JLabel startDateLabel;
    private JPanel roundedStartDateContentPanel;
    private JLabel startDateContentLabel;
    private JPanel roundedEndDatePanel;
    private JLabel endDateLabel;
    private JPanel roundedEndDateContentPanel;
    private JLabel endDateContentLabel;
    private JPanel roundedDeadlinePanel;
    private JLabel deadlineLabel;
    private JPanel roundedDeadlineContentPanel;
    private JLabel deadlineContentLabel;
    private JPanel roundedOrganizerPanel;
    private JLabel organizerLabel;
    private JPanel roundedOrganizerContentPanel;
    private JLabel organizerContentLabel;
    private JLabel problemStatementLabel;
    private JPanel roundedEditPanel;
    private JLabel editLabel;
    private JTextArea problemStatementTextArea;
    private JLabel rankingLabel;
    private JLabel publishLabel;
    private JPanel roundedPublishPanel;
    private JLabel rankingInfoLabel;
    private JPanel rankingListPanel;

    private final Controller controller;

    public HackathonCardPanel(Controller controller) {
        this.controller = controller;
    }

    public JPanel getRootPanel() {
        return rootPanel;
    }
}
