package Main.GUI;

import javax.swing.*;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

final class ScrollPaneComposite {
    private MazeViewPanel internalMazeView;
    private BufferedImage mazeImage;
    private MouseListener mouseListener;
    private final JScrollPane scrollPane;

    ScrollPaneComposite(BufferedImage mazeImage)
    {
        internalMazeView = new MazeViewPanel(mazeImage);

        this.mazeImage = mazeImage;

        scrollPane = new JScrollPane(internalMazeView);
        scrollPane.setAutoscrolls(true);
    }

    JScrollPane getScrollPane() {
        return scrollPane;
    }

    void changeMazeImage(BufferedImage mazeImage)
    {
        this.mazeImage = mazeImage;
        internalMazeView = new MazeViewPanel(mazeImage);
        internalMazeView.getInternalLabel().addMouseListener(mouseListener);
        scrollPane.setViewportView(internalMazeView);
    }

    BufferedImage getMazeImage()
    {
        return mazeImage;
    }

    void revalidateView()
    {
        internalMazeView.revalidate();
        internalMazeView.repaint();
    }

    void addMouseListener(MouseListener listener)
    {
        internalMazeView.getInternalLabel().addMouseListener(listener);
        mouseListener = listener;
    }


}
