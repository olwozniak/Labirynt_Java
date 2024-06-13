package Main.Listeners;

import Main.GUI.ControlPanelComposite;
import Main.File.MazeFileWriter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class WriteFileButtonListener implements ActionListener {
    private final ControlPanelComposite controlPanelComposite;

    public WriteFileButtonListener(ControlPanelComposite controlPanelComposite) {
        this.controlPanelComposite = controlPanelComposite;
    }


    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        Object[] options = {"Tekstowy", "Binarny", "Anuluj"};

        int choice = JOptionPane.showOptionDialog(
                null,
                "Rodzaj pliku",
                "Zapisz labirynt",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[2]
        );

        if(choice == JOptionPane.YES_OPTION || choice == JOptionPane.NO_OPTION) {
            JFileChooser chooser = new JFileChooser(new File("."));

            int returnVal = chooser.showSaveDialog(null);

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                try {
                    if(choice == JOptionPane.YES_OPTION)
                        MazeFileWriter.writeMazeToTxt(chooser.getSelectedFile());
                    else
                        try
                        {
                            MazeFileWriter.writeMazeToBin(chooser.getSelectedFile());
                        }
                        catch(RuntimeException e)
                        {
                            controlPanelComposite.setStatusLabel("Nie można wypisać labiryntu do pliku binarnego bez wyjścia / wejścia", true);
                        }
                    controlPanelComposite.setStatusLabel("Wypisano labirynt do pliku: " + chooser.getSelectedFile().getName(), false);
                }
                catch (IOException e) {
                    controlPanelComposite.setStatusLabel("Błąd przy wypisywaniu pliku: " + e.getClass().getName(), true);
                }
            }
        }
    }
}
