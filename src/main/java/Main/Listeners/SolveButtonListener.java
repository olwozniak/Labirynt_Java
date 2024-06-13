package Main.Listeners;

import Main.CustomEventManager;
import Main.EventType;
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
        MazeBrowse.getInstance().solve();
        CustomEventManager.getInstance().callEvent(EventType.solveBeginEvent);
    }
}
