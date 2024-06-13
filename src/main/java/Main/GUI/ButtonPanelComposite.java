package Main.GUI;

import Main.Listeners.ChooseExitButtonListener;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.Objects;

public class ButtonPanelComposite {
    private final JPanel controlJPanel;
    private final ControlPanelButton solveButton;
    private final ControlPanelButton chooseEntranceButton;
    private final ControlPanelButton chooseExitButton;
    private final ControlPanelButton chooseFileButton;
    private final ControlPanelButton writeFileButton;
    private final JLabel fileNameLabel;

    private Image loadIcon(String path) {
        try {
            return ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResource(path)));
        } catch (Exception e) {
            throw new RuntimeException("Błąd wczytywanie pliku: " + path, e);
        }
    }

    private Image scaleImage(Image image, int width, int height) {
        BufferedImage scaledImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics2D = scaledImage.createGraphics();
        graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        graphics2D.drawImage(image, 0, 0, width, height, null);
        graphics2D.dispose();
        return scaledImage;
    }

    ButtonPanelComposite(){
        Image solveImage = loadIcon("accept.png");
        Image exitImage = loadIcon("exit.png");
        Image entryImage = loadIcon("entrance.png");
        Image labiryntImage = loadIcon("labirynt.png");
        Image writingImage = loadIcon("writing.png");

        solveImage = scaleImage(solveImage, 20, 20);
        exitImage = scaleImage(exitImage, 20, 20);
        entryImage = scaleImage(entryImage, 20, 20);
        labiryntImage = scaleImage(labiryntImage, 20, 20);
        writingImage = scaleImage(writingImage, 20, 20);

        this.controlJPanel = new JPanel();
        this.controlJPanel.setBackground(new Color(150, 200, 200));

        this.chooseFileButton = new ControlPanelButton(" WYBIERZ LABIRYNT", new ImageIcon(labiryntImage));
        this.chooseExitButton = new ControlPanelButton(" WYBIERZ WYJŚCIE", new ImageIcon(exitImage));
        this.chooseEntranceButton = new ControlPanelButton(" WYBIERZ WEJŚCIE", new ImageIcon(entryImage));
        this.solveButton = new ControlPanelButton(" ROZWIĄŻ", new ImageIcon(solveImage));
        this.writeFileButton = new ControlPanelButton(" WYPISZ LABIRYNT", new ImageIcon(writingImage));

        setBackgroundColor(chooseFileButton);
        setBackgroundColor(chooseExitButton);
        setBackgroundColor(chooseEntranceButton);
        setBackgroundColor(solveButton);
        setBackgroundColor(writeFileButton);

        this.fileNameLabel = new JLabel("Wybrany plik nie istnieje");
        this.fileNameLabel.setOpaque(true);
        this.fileNameLabel.setBackground(new Color(8, 13, 42));
        this.fileNameLabel.setHorizontalAlignment(0);
        this.fileNameLabel.setFont(new Font("Helvetica", Font.BOLD, 18));
        this.fileNameLabel.setForeground(Color.WHITE);

        setGrid();
    }

    private void setBackgroundColor(JButton button) {
        button.setBackground(new Color(8, 13, 42));
    }

    private void setGrid()
    {
        this.controlJPanel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.weightx = 1;
        gbc.weighty = 0.2;
        gbc.gridwidth = 2;
        gbc.gridy = 0; // Wiersz 1
        gbc.gridx = 0;

        this.controlJPanel.add(this.fileNameLabel, gbc);

        gbc.gridy++; //Wiersz 2
        gbc.weightx = 0.5;
        gbc.gridwidth = 1;

        this.controlJPanel.add(this.chooseFileButton, gbc);

        gbc.gridx = 1;

        this.controlJPanel.add(this.writeFileButton, gbc);

        gbc.gridx = 0;
        gbc.gridwidth = 2;
        gbc.weightx = 1;
        gbc.gridy++; //Wiersz 3

        this.controlJPanel.add(this.chooseEntranceButton, gbc);

        gbc.gridy++; //Wiersz 4

        this.controlJPanel.add(this.chooseExitButton, gbc);

        gbc.gridy++; //Wiersz 5

        this.controlJPanel.add(this.solveButton, gbc);
    }

    JPanel getControlJPanel() {
        return controlJPanel;
    }

    void setButtonState(ButtonEnum button, boolean state)
    {
        switch(button)
        {
            case solveButton -> this.solveButton.setEnabled(state);
            case chooseEntranceButton -> this.chooseEntranceButton.setEnabled(state);
            case chooseExitButton -> this.chooseExitButton.setEnabled(state);
            case chooseFileButton -> this.chooseFileButton.setEnabled(state);
            case writeFileButton -> this.writeFileButton.setEnabled(state);
        }
    }

    void setButtonListener(ButtonEnum button, ActionListener listener)
    {
        switch(button)
        {
            case solveButton -> this.solveButton.addActionListener(listener);
            case chooseEntranceButton -> this.chooseEntranceButton.addActionListener(listener);
            case chooseExitButton -> this.chooseExitButton.addActionListener(listener);
            case chooseFileButton -> this.chooseFileButton.addActionListener(listener);
            case writeFileButton -> this.writeFileButton.addActionListener(listener);
        }
    }

    JLabel getFilenameLabel() {
        return fileNameLabel;
    }
}