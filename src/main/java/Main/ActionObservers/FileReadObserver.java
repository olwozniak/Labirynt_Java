package Main.ActionObservers;

import Main.GUI.ButtonEnum;
import Main.GUI.ControlPanelComposite;
import Main.MazeData.MazeBrowse;

import javax.swing.*;

public class FileReadObserver extends GuiObserver {

    public FileReadObserver(ControlPanelComposite controlPanelComposite) {
        super(controlPanelComposite);
    }

    @Override
    public void call() {
        if (!SwingUtilities.isEventDispatchThread()) {
            SwingUtilities.invokeLater(this::call);
            return;
        }
        MazeBrowse data = MazeBrowse.getInstance();

        controlPanelComposite.getFilenameLabel().setText("Źródło: " + data.getSource());

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
    }
}
