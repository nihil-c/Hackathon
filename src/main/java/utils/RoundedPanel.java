package utils;

import javax.swing.*;
import java.awt.*;

public class RoundedPanel extends JPanel {
    private final int cornerRadius;

    public RoundedPanel() {
        super();
        this.cornerRadius = 10;
        setOpaque(false);
    }

    public RoundedPanel(int radius) {
        super();
        this.cornerRadius = radius;
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Dimension arcs = new Dimension(cornerRadius, cornerRadius);
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setColor(getBackground());
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), arcs.width, arcs.height);
        g2.dispose();
    }
}
