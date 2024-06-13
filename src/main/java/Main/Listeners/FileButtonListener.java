package Main.Listeners;

import Main.File.MazeFileReader;
import Main.GUI.ButtonEnum;
import Main.GUI.ControlPanelComposite;
import Main.MazeData.MazeBrowse;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

class FileButtonListener implements ActionListener {
    private final ControlPanelComposite controlPanelComposite;

    FileButtonListener(ControlPanelComposite controlPanelComposite) {
        this.controlPanelComposite = controlPanelComposite;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser fileChooser = new JFileChooser(new File("."));

        int returnVal = fileChooser.showOpenDialog(controlPanelComposite.getJScrollPane());

        File currentFile;

        if(returnVal == JFileChooser.APPROVE_OPTION)
            currentFile = fileChooser.getSelectedFile();
        else if(returnVal == JFileChooser.ERROR_OPTION) {
            controlPanelComposite.setStatusLabel("Błąd przy otwieraniu pliku!", true);
            return;
        }
        else {
            return;
        }

        try {
            if(MazeFileReader.isFileBinary(currentFile)) {
                MazeFileReader.readBinToMazeData(currentFile);
            }
            else {
                MazeFileReader.readTxtToMazeData(currentFile);
            }

            MazeBrowse data = MazeBrowse.getInstance();

            controlPanelComposite.getFilenameLabel().setText("Źródło: " + currentFile.getName());

            controlPanelComposite.changeMazeImage();

            String exitString;
            String entryString;

            if(data.getExit() == null)
                exitString = "Brak";
            else
                exitString = data.getExit().toString();

            if(data.getEntry() == null)
                entryString = "Brak";
            else
                entryString = data.getEntry().toString();

            controlPanelComposite.setStatusLabel("<html>Odczytano labirynt z: " + data.getSource() + "<br/>" +
                    "<table><tr><td>Szerokość: " + data.width() + "</td><td> Wejście: " + entryString +
                    "</td></tr><tr><td>Wysokość: " + data.height() + "</td><td> Wyjście: " + exitString +
                    "</td></tr></table>", false);

            controlPanelComposite.setButtonState(ButtonEnum.chooseEntranceButton, true);
            controlPanelComposite.setButtonState(ButtonEnum.chooseExitButton, true);
            controlPanelComposite.setButtonState(ButtonEnum.writeFileButton, true);

            controlPanelComposite.setButtonState(ButtonEnum.solveButton, data.getExit() != null && data.getEntry() != null);

        } catch (IOException ex) {
            controlPanelComposite.setStatusLabel("Błąd IO podczas czytania z pliku: " + ex.getClass().getName(), true);
        }

    }
}
