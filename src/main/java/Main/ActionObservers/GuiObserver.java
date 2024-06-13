package Main.ActionObservers;

import Main.GUI.ControlPanelComposite;

abstract class GuiObserver implements ActionObserver {
    final ControlPanelComposite controlPanelComposite;
    public GuiObserver(ControlPanelComposite controlPanelComposite)
    {
        this.controlPanelComposite = controlPanelComposite;
    }
}
