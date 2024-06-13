package Main.ActionObservers;

import Main.GUI.ButtonEnum;
import Main.GUI.ControlPanelComposite;
import Main.MazeData.MazeBrowse;

import javax.swing.*;

public class ChangeCoordsObserver extends GuiObserver {
    public ChangeCoordsObserver(ControlPanelComposite controlPanelComposite) {
        super(controlPanelComposite);
    }

    @Override
    public void call() {
        if (!SwingUtilities.isEventDispatchThread()) {
            SwingUtilities.invokeLater(this::call);
            return;
        }
        MazeBrowse data = MazeBrowse.getInstance();

        if (!controlPanelComposite.isChoosingExit() && !controlPanelComposite.isChoosingEntry()) {
            controlPanelComposite.setButtonState(ButtonEnum.chooseEntranceButton, true);
            controlPanelComposite.setButtonState(ButtonEnum.chooseExitButton, true);

            if (data.getExit() != null && data.getEntry() != null)
                controlPanelComposite.setButtonState(ButtonEnum.solveButton, true);

        }

        controlPanelComposite.repaintMazeImage();

        String exitString = data.getExit().toString();
        String entryString = data.getEntry().toString();

        if(data.getExit() == null)
            exitString = "Brak";

        if(data.getEntry() == null)
            entryString = "Brak";

        controlPanelComposite.setStatusLabel("<html>" + "<table><tr><td>Szerokość: " + data.width() + "</td><td> Wejście: " + entryString +
                "</td></tr><tr><td>Wysokość: " + data.height() + "</td><td> Wyjście: " + exitString +
                "</td></tr></table>", false);
    }
}
