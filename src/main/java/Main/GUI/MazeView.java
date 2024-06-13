package Main.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

class MazeViewPanel extends JPanel {
    private final JLabel internalLabel;

    MazeViewPanel(BufferedImage mazeImage)
    {
        super();
        setLayout(new GridBagLayout());
        internalLabel = new JLabel(new ImageIcon(mazeImage));
        internalLabel.setSize(new Dimension(mazeImage.getWidth(), mazeImage.getHeight()));
        add(internalLabel);
    }

    public JLabel getInternalLabel() {
        return internalLabel;
    }
}
