package Main.Listeners;

import Main.CustomEventManager;
import Main.EventType;
import Main.File.MazeFileReader;
import Main.GUI.ControlPanelComposite;

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
            CustomEventManager.getInstance().callEvent(EventType.fileReadEvent);

        } catch (IOException ex) {
            controlPanelComposite.setStatusLabel("Błąd IO podczas czytania z pliku: " + ex.getClass().getName(), true);
        }

    }
}
