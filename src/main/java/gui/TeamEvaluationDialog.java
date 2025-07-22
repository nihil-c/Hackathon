package gui;

import model.*;
import utils.RoundedPanel;
import utils.UIColors;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Finestra di dialogo per la valutazione di un team nell'applicazione Hackathon.IO.
 * <p>
 * Permette ai giudici di assegnare un punteggio al team e di fornire feedback sugli upload.
 * Gestisce la visualizzazione delle informazioni del team, la logica di salvataggio e la visualizzazione di messaggi informativi o di errore.
 * </p>
 */
public class TeamEvaluationDialog extends JDialog {
    private JPanel rootPanel;
    private JLabel teamNameLabel;
    private JLabel teamNameContentLabel;
    private JLabel scoreLabel;
    private JLabel maxScoreLabel;
    private JLabel uploadsLabel;
    private JLabel saveLabel;
    private JPanel rSavePanel;
    private JTextField scoreTextField;
    private JLabel uploadInfoLabel;
    private JPanel uploadsListPanel;
    private JScrollPane scrollPanel;

     /**
     * Costruttore della finestra di valutazione team.
     * @param team team da valutare
     */
    public TeamEvaluationDialog(Team team) {
        setTitle("Team Evaluation");
        setSize(600, 450);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setModal(true);
        setContentPane(rootPanel);

        customizeComponents(team);
        setupScrollPanel(team);
        setupRSavePanelListener(team);
    }

    /**
     * Personalizza i componenti grafici della finestra.
     * @param team team da valutare
     */
    private void customizeComponents(Team team) {
        teamNameLabel.setForeground(Color.GRAY);
        teamNameContentLabel.setText(team.getTeamName());
        teamNameContentLabel.setForeground(UIColors.NIGHT_BLUE);
        scoreLabel.setForeground(Color.GRAY);
        maxScoreLabel.setForeground(Color.GRAY);
        uploadsLabel.setForeground(UIColors.CARMINE_RED);
        uploadInfoLabel.setForeground(Color.GRAY);
        rSavePanel.setBackground(UIColors.NIGHT_BLUE);
        saveLabel.setForeground(Color.WHITE);
    }

    /**
     * Imposta lo scroll e il layout del pannello upload.
     * @param team team da valutare
     */
    private void setupScrollPanel(Team team) {
        scrollPanel.setBorder(null);
        scrollPanel.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));
        scrollPanel.getHorizontalScrollBar().setPreferredSize(new Dimension(0, 0));
        scrollPanel.getVerticalScrollBar().setUnitIncrement(10);
        setupUploadsListPanel(team);
    }

    /**
     * Imposta il layout e aggiorna la lista degli upload del team.
     * @param team team da valutare
     */
    private void setupUploadsListPanel(Team team) {
        uploadsListPanel.setLayout(new BoxLayout(uploadsListPanel, BoxLayout.Y_AXIS));
        updateUploadsListPanel(team);
    }

    /**
     * Aggiorna la lista degli upload del team.
     * @param team team da valutare
     */
    public void updateUploadsListPanel(Team team) {
        uploadsListPanel.removeAll();

        for (Upload upload : team.getUploads()) {
            RoundedPanel card = createUploadCard(upload);
            uploadsListPanel.add(card, 0);
            uploadsListPanel.add(Box.createVerticalStrut(15), 1);
        }

        uploadsListPanel.revalidate();
        uploadsListPanel.repaint();
    }

    /**
     * Crea una card grafica per un upload del team.
     * @param upload upload da visualizzare
     * @return pannello grafico dell'upload
     */
    private RoundedPanel createUploadCard(Upload upload) {
        RoundedPanel card = new RoundedPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBackground(Color.WHITE);
        card.setBorderColor(UIColors.LIGHT_GRAY);
        card.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel titleLabel = new JLabel(upload.getTitle());
        JLabel urlLabel = new JLabel("URL: " + upload.getUrl());
        JLabel dateLabel = new JLabel("Date: " + upload.getCreationDate());
        String author = upload.getUploader() != null ? upload.getUploader().getUsername() : "Unknown";
        JLabel authorLabel = new JLabel("Uploader: @" + author);

        titleLabel.setForeground(UIColors.CARMINE_RED);
        titleLabel.setFont(new Font(null, Font.BOLD, 14));
        urlLabel.setForeground(Color.GRAY);
        dateLabel.setForeground(Color.GRAY);

        card.add(titleLabel);
        card.add(urlLabel);
        card.add(dateLabel);
        card.add(authorLabel);

        makeCardInteractive(card, upload);

        return card;
    }

    /**
     * Rende interattiva la card di un upload, permettendo di inserire feedback.
     * @param card pannello grafico dell'upload
     * @param upload upload associato
     */
    private void makeCardInteractive(RoundedPanel card, Upload upload) {
        card.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        card.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (upload.getFeedback() != null) {
                    showInfoDialog("Feedback", "A feedback has been provided for this upload:\n" + upload.getFeedback());
                } else {
                    JPanel feedbackPanel = new JPanel();
                    feedbackPanel.setLayout(new BorderLayout());
                    JTextArea feedbackTextArea = new JTextArea(5, 20);
                    feedbackTextArea.setLineWrap(true);
                    feedbackTextArea.setWrapStyleWord(true);
                    feedbackTextArea.setText("Type your feedback here...");
                    feedbackPanel.add(new JLabel("Feedback for " + upload.getTitle() + ":"), BorderLayout.NORTH);
                    feedbackPanel.add(new JScrollPane(feedbackTextArea), BorderLayout.CENTER);
                    feedbackPanel.setPreferredSize(new Dimension(400, 200));
                    int option = JOptionPane.showConfirmDialog(
                            TeamEvaluationDialog.this,
                            feedbackPanel,
                            "Provide Feedback",
                            JOptionPane.OK_CANCEL_OPTION,
                            JOptionPane.PLAIN_MESSAGE
                    );
                    if (option == JOptionPane.OK_OPTION) {
                        String feedback = feedbackTextArea.getText().trim();
                        if (!feedback.isBlank()) {
                            upload.setFeedback(feedback);
                            showInfoDialog("Success", "Feedback saved successfully.");
                        } else {
                            showErrorDialog("Feedback cannot be empty.");
                        }
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

    /**
     * Imposta il listener per il pannello di salvataggio del punteggio.
     * @param team team da valutare
     */
    private void setupRSavePanelListener(Team team) {
        rSavePanel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        rSavePanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                String input = scoreTextField.getText().trim();

                if (input.isBlank()) {
                    dispose();
                    return;
                }

                int oldScore = team.getScore();

                try {
                    int score = Integer.parseInt(input);

                    if (score == oldScore) {
                        dispose();
                        return;
                    }

                    if (score < 0 || score > 100) {
                        showErrorDialog("Score must be between 0 and 100.");
                    } else {
                        team.setScore(score);
                        showInfoDialog("Success", "Score saved successfully.");
                        dispose();
                    }
                } catch (NumberFormatException ex) {
                    showErrorDialog("Score must be a number between 0 and 100.");
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                rSavePanel.setBackground(UIColors.CARMINE_RED);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                rSavePanel.setBackground(UIColors.NIGHT_BLUE);
            }
        });
    }

    /**
     * Mostra una finestra informativa con titolo e messaggio.
     * @param title titolo della finestra
     * @param message messaggio da visualizzare
     */
    private void showInfoDialog(String title, String message) {
        JOptionPane.showMessageDialog(
                this,
                message,
                title,
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    /**
     * Mostra una finestra di errore con il messaggio specificato.
     * @param message messaggio di errore da visualizzare
     */
    private void showErrorDialog(String message) {
        JOptionPane.showMessageDialog(
                this,
                message,
                "Error",
                JOptionPane.ERROR_MESSAGE
        );
    }

    /**
     * Restituisce il pannello principale della finestra.
     * @return rootPanel
     */
    public JPanel getRootPanel() {
        return rootPanel;
    }

    /**
     * Inizializza i componenti grafici custom.
     */
    private void createUIComponents() {
        rSavePanel = new RoundedPanel();
    }
}

