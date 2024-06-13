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
        panelLabel.setFont(new Font("Serif", Font.PLAIN, 22));
        panelLabel.setHorizontalAlignment(0); //centered
        panelLabel.setOpaque(true);
        panelLabel.setBackground(new Color(20, 12, 40));

        add(panelLabel, BorderLayout.CENTER);

        setLabel("Wybierz labirynt do rozwiązania", false);
    }

    void setLabel(String text, boolean isError) {
        if(isError)
            panelLabel.setForeground(new Color(110, 24, 25));
        else
            panelLabel.setForeground(new Color(30, 109, 112));

        panelLabel.setText(text);
    }
}