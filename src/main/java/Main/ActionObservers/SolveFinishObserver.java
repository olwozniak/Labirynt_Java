package Main.ActionObservers;

import Main.GUI.ButtonEnum;
import Main.GUI.ControlPanelComposite;
import Main.MazeData.MazeBrowse;

import javax.swing.*;

public class SolveFinishObserver extends GuiObserver{
    public SolveFinishObserver(ControlPanelComposite controlPanelComposite)
    {
        super(controlPanelComposite);
    }


    @Override
    public void call() {
        if(!SwingUtilities.isEventDispatchThread())
        {
            SwingUtilities.invokeLater(this::call);
            return;
        }
        controlPanelComposite.setButtonState(ButtonEnum.solveButton, true);
        controlPanelComposite.setButtonState(ButtonEnum.chooseExitButton, true);
        controlPanelComposite.setButtonState(ButtonEnum.chooseEntranceButton, true);
        controlPanelComposite.setButtonState(ButtonEnum.chooseFileButton, true);
        controlPanelComposite.setButtonState(ButtonEnum.writeFileButton, true);

        MazeBrowse data = MazeBrowse.getInstance();

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


        if(!data.isSolved())
            controlPanelComposite.setStatusLabel("Nie udało się znaleźć wyjścia z labiryntu! Spróbuj zmienić pozycję wejścia / wyjścia.", true);
        else {
            controlPanelComposite.repaintMazeImage();
            controlPanelComposite.setStatusLabel("<html>Znaleziono rozwiązanie labiryntu!" + "<table><tr><td>Szerokość: " + data.width() + "</td><td> Wejście: " + entryString +
                    "</td></tr><tr><td>Wysokość: " + data.height() + "</td><td> Wyjście: " + exitString +
                    "</td></tr></table>", false);
        }
    }
}
