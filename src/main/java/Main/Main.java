package Main;

import Main.MazeData.MazeBrowse;
import javax.swing.*;
import java.util.*;

class Main {
    public static void main(String[] args) {
        CustomEventManager.initialize();
        MazeBrowse.initialize();

        SwingUtilities.invokeLater(() -> startGui(args));
    }

    private static void startGui(String[] args) {
        ApplicationGUI mainGui = new ApplicationGUI();
        mainGui.start();
    }
}