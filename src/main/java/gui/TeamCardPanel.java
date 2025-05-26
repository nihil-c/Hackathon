package gui;

import controller.Controller;
import utils.RoundedPanel;
import utils.UIColors;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TeamCardPanel {
    private JPanel rootPanel;
    private JLabel teamLabel;
    private JScrollPane scrollPanel;
    private JLabel infoLabel;
    private JLabel createTeamLabel;
    private JLabel joinTeamLabel;
    private JPanel roundedJoinTeamPanel;
    private JPanel roundedCreateTeamPanel;
    private JLabel membersLabel;
    private JLabel uploadsLabel;
    private JLabel addLabel;
    private JPanel roundedAddPanel;
    private JLabel uploadsInfoLabel;
    private JLabel membersInfoLabel;
    private JScrollPane membersScrollPanel;
    private JPanel membersListPanel;

    private final Controller controller;

    public TeamCardPanel(Controller controller) {
        this.controller = controller;
    }

    public JPanel getRootPanel() {
        return rootPanel;
    }
}
