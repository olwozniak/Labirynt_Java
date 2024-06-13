package Main.GUI;

import javax.swing.*;
import java.awt.*;

final class StatusLabelPanel extends JPanel {
    private final JLabel panelLabel;
    StatusLabelPanel()
    {
        super();
        setLayout(new BorderLayout());

        panelLabel = new JLabel();
        panelLabel.setFont(new Font("Helvetica", Font.BOLD, 20));
        panelLabel.setHorizontalAlignment(0);
        panelLabel.setOpaque(true);
        panelLabel.setBackground(new Color(8, 13, 42));

        add(panelLabel, BorderLayout.CENTER);

        setLabel("Wybierz labirynt do rozwiązania", false);
    }

    void setLabel(String text, boolean isError) {
        if(isError)
            panelLabel.setForeground(new Color(150, 200, 200));
        else
            panelLabel.setForeground(Color.WHITE);

        panelLabel.setText(text);
    }
}