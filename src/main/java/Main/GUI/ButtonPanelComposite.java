package Main.GUI;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
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

    ButtonPanelComposite(){
        Image solveImage = loadIcon("accept.png");
        Image exitImage = loadIcon("exit.png");
        Image entryImage = loadIcon("log_in.png");
        Image folderImage = loadIcon("folder.png");
        Image writingImage = loadIcon("writing.png");

        this.controlJPanel = new JPanel();

        this.chooseFileButton = new ControlPanelButton(" Wybierz plik", new ImageIcon(folderImage));
        this.chooseExitButton = new ControlPanelButton(" Wybierz wyjście", new ImageIcon(exitImage));
        this.chooseEntranceButton = new ControlPanelButton(" Wybierz wejście", new ImageIcon(entryImage));
        this.solveButton = new ControlPanelButton(" Rozwiąż", new ImageIcon(solveImage));
        this.writeFileButton = new ControlPanelButton(" Wypisz labirynt", new ImageIcon(writingImage));

        this.fileNameLabel = new JLabel("Wybrany plik nie istnieje");
        this.fileNameLabel.setOpaque(true);
        this.fileNameLabel.setBackground(new Color(8, 13, 42));
        this.fileNameLabel.setHorizontalAlignment(0);
        this.fileNameLabel.setFont(new Font("Serif", Font.BOLD, 18));

        setGrid();
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