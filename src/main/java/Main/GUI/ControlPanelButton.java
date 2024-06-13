package Main.GUI;

import javax.swing.*;
import java.awt.*;

class ControlPanelButton extends JButton {

    ControlPanelButton(String name, ImageIcon icon)
    {
        super(name);
        setFont(new Font("Helvetica", Font.BOLD, 16));
        setIcon(icon);
        setForeground(new Color(8, 13, 42));
    }
}