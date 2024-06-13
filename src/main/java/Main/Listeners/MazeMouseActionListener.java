package Main.Listeners;

import Main.MazeData.Coords;
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

            controlPanelComposite.repaintMazeImage();

            String exitString;
            String entryString = newCoords.toString();

            if(data.getExit() == null)
                exitString = "Brak";
            else
                exitString = data.getExit().toString();


            controlPanelComposite.setStatusLabel("<html>" + "<table><tr><td>Szerokość: " + data.width() + "</td><td> Wejście: " + entryString +
                    "</td></tr><tr><td>Wysokość: " + data.height() + "</td><td> Wyjście: " + exitString +
                    "</td></tr></table>", false);

        }
        else if (controlPanelComposite.isChoosingExit())
        {
            if(data.getEntry() != null)
                controlPanelComposite.setButtonState(ButtonEnum.solveButton, true);

            controlPanelComposite.setButtonState(ButtonEnum.chooseEntranceButton, true);
            controlPanelComposite.setButtonState(ButtonEnum.chooseFileButton, true);

            controlPanelComposite.changeChoosingExit();

            data.setExit(newCoords);

            controlPanelComposite.repaintMazeImage();

            String exitString = newCoords.toString();
            String entryString;

            if(data.getEntry() == null)
                entryString = "Brak";
            else
                entryString = data.getEntry().toString();

            controlPanelComposite.setStatusLabel("<html>" + "<table><tr><td>Szerokość: " + data.width() + "</td><td> Wejście: " + entryString +
                    "</td></tr><tr><td>Wysokość: " + data.height() + "</td><td> Wyjście: " + exitString +
                    "</td></tr></table>", false);

        }

    }
}
