package Main.Listeners;

import Main.MazeData.Coords;
import Main.CustomEventManager;
import Main.EventType;
import Main.GUI.ButtonEnum;
import Main.GUI.ControlPanelComposite;
import Main.MazeData.MazeBrowse;

import javax.swing.event.MouseInputAdapter;
import java.awt.event.MouseEvent;

public class MazeMouseActionListener extends MouseInputAdapter {
    private final ControlPanelComposite controlPanelComposite;

    public MazeMouseActionListener(ControlPanelComposite controlPanelComposite) {
        this.controlPanelComposite = controlPanelComposite;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

        Coords newCoords = new Coords(e.getX() / 8, e.getY()/8);

        MazeBrowse data = MazeBrowse.getInstance();

        if(data.width() <= e.getX()/8
                || data.height() <= e.getY()/8)
            return;

        if(controlPanelComposite.isChoosingEntry())
        {
            if(data.getExit() != null)
                controlPanelComposite.setButtonState(ButtonEnum.solveButton, true);

            controlPanelComposite.setButtonState(ButtonEnum.chooseExitButton, true);
            controlPanelComposite.setButtonState(ButtonEnum.chooseFileButton, true);

            controlPanelComposite.changeChoosingEntry();

            data.setEntry(newCoords);

            CustomEventManager.getInstance().callEvent(EventType.entryChangeEvent);

        }
        else if (controlPanelComposite.isChoosingExit())
        {
            if(data.getEntry() != null)
                controlPanelComposite.setButtonState(ButtonEnum.solveButton, true);

            controlPanelComposite.setButtonState(ButtonEnum.chooseEntranceButton, true);
            controlPanelComposite.setButtonState(ButtonEnum.chooseFileButton, true);

            controlPanelComposite.changeChoosingExit();

            data.setExit(newCoords);

            CustomEventManager.getInstance().callEvent(EventType.exitChangeEvent);

        }

    }
}
