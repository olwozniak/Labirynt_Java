package Main.GUI;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;


public final class ControlPanelComposite {
    private final ButtonPanelComposite buttonPanelComposite;

    private final ScrollPaneComposite mazeViewPanelComposite;

    private final StatusLabelPanel statusLabelPanel;

    private boolean isChoosingExit;
    private boolean isChoosingEntry;

    public ControlPanelComposite()
    {
        isChoosingEntry = false;
        isChoosingExit = false;
        this.statusLabelPanel = new StatusLabelPanel();
        this.buttonPanelComposite = new ButtonPanelComposite();

        setButtonState(ButtonEnum.solveButton, false);
        setButtonState(ButtonEnum.chooseExitButton, false);
        setButtonState(ButtonEnum.chooseEntranceButton, false);
        setButtonState(ButtonEnum.writeFileButton, false);

        BufferedImage tempImage = MazeToImageConverter.getImageFromMaze();

        this.mazeViewPanelComposite = new ScrollPaneComposite(tempImage);

    }

    public JPanel getButtonPanel() {
        return buttonPanelComposite.getControlJPanel();
    }

    public JPanel getStatusLabelJPanel() {
        return statusLabelPanel;
    }

    public JScrollPane getJScrollPane() {
        return mazeViewPanelComposite.getScrollPane();
    }

    public JLabel getFilenameLabel()
    {
        return buttonPanelComposite.getFilenameLabel();
    }

    public void setStatusLabel(String text, boolean IsError)
    {
        statusLabelPanel.setLabel(text, IsError);
    }

    public void setButtonActionListener(ButtonEnum button, ActionListener listener)
    {
        buttonPanelComposite.setButtonListener(button, listener);
    }

    public void setButtonState(ButtonEnum button, boolean state)
    {
        buttonPanelComposite.setButtonState(button, state);
    }

    public void changeMazeImage()
    {
        BufferedImage tempImage = MazeToImageConverter.getImageFromMaze();
        mazeViewPanelComposite.changeMazeImage(tempImage);
    }

    public void repaintMazeImage()
    {
        MazeToImageConverter.paintToImage(mazeViewPanelComposite.getMazeImage());
        mazeViewPanelComposite.revalidateView();
    }

    public boolean isChoosingExit() {
        return isChoosingExit;
    }

    public void changeChoosingExit() {
        isChoosingExit = !isChoosingExit;
    }

    public boolean isChoosingEntry() {
        return isChoosingEntry;
    }

    public void changeChoosingEntry() {
        isChoosingEntry = !isChoosingEntry;
    }

    public void addMouseListener(MouseListener listener)
    {
        mazeViewPanelComposite.addMouseListener(listener);
    }

}