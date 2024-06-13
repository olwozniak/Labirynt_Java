package Main.Listeners;

import Main.GUI.ButtonEnum;
import Main.GUI.ControlPanelComposite;
import Main.MazeData.MazeBrowse;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SolveButtonListener implements ActionListener {
    private final ControlPanelComposite controlPanelComposite;

    public SolveButtonListener(ControlPanelComposite controlPanelComposite)
    {
        this.controlPanelComposite = controlPanelComposite;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        MazeBrowse.getInstance().solve(controlPanelComposite);
        controlPanelComposite.setButtonState(ButtonEnum.solveButton, false);
        controlPanelComposite.setButtonState(ButtonEnum.chooseExitButton, false);
        controlPanelComposite.setButtonState(ButtonEnum.chooseEntranceButton, false);
        controlPanelComposite.setButtonState(ButtonEnum.chooseFileButton, false);
        controlPanelComposite.setButtonState(ButtonEnum.writeFileButton, false);

        controlPanelComposite.setStatusLabel("Rozpoczęto rozwiązywanie labiryntu", false);
    }
}
