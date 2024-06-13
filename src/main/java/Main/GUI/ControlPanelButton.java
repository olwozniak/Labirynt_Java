package Main.GUI;

import javax.swing.*;
import java.awt.*;

class ControlPanelButton extends JButton {

    ControlPanelButton(String name, ImageIcon icon)
    {
        super(name);
        setFont(new Font("Serif", Font.PLAIN, 16));
        setIcon(icon);
        setForeground(Color.WHITE);
    }
}