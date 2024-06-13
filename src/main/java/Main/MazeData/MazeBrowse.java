package Main.MazeData;

import Main.GUI.ButtonEnum;
import Main.GUI.ControlPanelComposite;

import javax.swing.*;
import java.util.*;

public class MazeBrowse{

    public static final byte Route = -2; //sciezka
    public static final byte Wall = -1; //sciana
    public static final byte Path = 0; //przestrzen

    private String source; //źródło labiryntu
    private byte[][] maze; //tablica reprezentująca labirynt
    private Coords entry; //współrzędne P
    private Coords exit; //współrzędne K
    private boolean isSolved; //Czy rozwiązany?

    private static MazeBrowse instance; //jedyna instancja tej klasy

    private MazeBrowse() //konstruktor, który rezerwuje 1000x1000  na labirynt i ustawia że jest nierozwiązany
    {
        byte[][] fill = new byte[1000][1000];

        for(byte[] row : fill)
            Arrays.fill(row, (byte) 0); //labirynt uzupełniony zerami

        maze = fill;
        isSolved = false;
    }

    public static void initialize()   // inicjalizacja jednynej instancji tej klasy
    {
        instance = new MazeBrowse();
    }

    public synchronized void changeMaze(byte[][] maze, Coords entry, Coords exit, String source) { //zmienia labirynt na podstawie nowych danaych PO CO
        this.maze = maze;
        this.entry = entry;
        this.exit = exit;
        this.source = source;
        this.isSolved = false;
    }

    /**
     * czyści ścieżkę w przypadku ponownego rozwiązania PO CO
     */
    private synchronized void clearRoute()
    {
        for(byte[] arr : maze)
            for(int i = 0; i < width(); i++)
                if(arr[i] == Route)
                    arr[i] = Path;

        isSolved = false;
    }

    public static MazeBrowse getInstance() { //zwraca instancję klasy
        return instance;
    }

    public synchronized int width() { //zwraca szerokość labiryntu (liczba kolumn)
        return maze[0].length;

    }

    public synchronized int height() { //zwraca wysokość labiryntu (liczba wierszy)
        return maze.length;
    }

    public synchronized void setExit(Coords newCoords) { //nowe współrzędne  wyjścia, sprawdza czy są w granicach labiryntu
        if(newCoords.x >= width() || newCoords.y >= height()) {
            System.err.println("Error setting the exit location in the maze: " + newCoords.x + ", " + newCoords.y);
            return;
        }
        clearRoute();
        if(newCoords.equals(entry))
            entry = null;

        exit = newCoords;
    }

    public synchronized void setEntry(Coords newCoords) { //nowe współrzędne  wejścia, sprawdza czy są w granicach labiryntu
        if(newCoords.x >= width() || newCoords.y >= height()) {
            System.err.println("Error setting the exit location in the maze: " + newCoords.x + ", " + newCoords.y);
            return;
        }
        clearRoute();
        if(newCoords.equals(exit))
            exit = null;

        entry = newCoords;
    }

    public synchronized byte[][] getMaze() { //zwraca aktualny stan labiryntu
        return maze;
    }

    public synchronized Coords getEntry() { //zwraca aktualne wspł. wejścia
        return entry;
    }

    public synchronized Coords getExit() {//zwraca aktualne wspł. wyjścia
        return exit;
    }

    public synchronized String getSource() //zwraca aktualny plik źródłowy labiryntu
    {
        return source;
    }

    public synchronized void solve(ControlPanelComposite controlPanelComposite) //rozpoczyna rozwiązywanie w nowym wątku
    {
        if(entry == null || exit == null)
            throw new RuntimeException("Cannot start solving while entrance / exit is null!");

        Thread thread = new Thread(() -> insideSolve(controlPanelComposite));
        thread.start();
    }

    private synchronized void insideSolve(ControlPanelComposite controlPanelComposite)
    {

        int[][] intMaze = new int[maze.length][maze[0].length];

        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[i].length; j++) {
                if(maze[i][j] == 0 || (entry.x == j && entry.y == i) || (exit.x == j && exit.y == i))
                    intMaze[i][j] = Integer.MAX_VALUE;
                else
                    intMaze[i][j] = -1;
            }
        }

        boolean solveFlag = false;

        DJQueue djQueue = new DJQueue();

        djQueue.addToQueue(entry, 0);
        intMaze[entry.y][entry.x] = 0;

        while(!djQueue.isEmpty())
        {
            int currLen = djQueue.peekLength();
            Coords currCoords = djQueue.popCoords();

            if(currCoords.equals(exit))
            {
                solveFlag = true;
                break;
            }

            int x = currCoords.x;
            int y = currCoords.y;

            if(x > 0){
                if(intMaze[y][x-1] > currLen + 1) {
                    djQueue.addToQueue(new Coords(x - 1, y), currLen + 1);
                    intMaze[y][x-1] = currLen + 1;
                }
            }
            if(x < width() - 1){
                if(intMaze[y][x+1] > currLen + 1) {
                    djQueue.addToQueue(new Coords(x + 1, y), currLen + 1);
                    intMaze[y][x+1] = currLen + 1;
                }
            }
            if(y > 0){
                if(intMaze[y-1][x] > currLen + 1) {
                    djQueue.addToQueue(new Coords(x, y - 1), currLen + 1);
                    intMaze[y-1][x] = currLen + 1;
                }
            }
            if(y < height() - 1){
                if(intMaze[y+1][x] > currLen + 1) {
                    djQueue.addToQueue(new Coords(x, y + 1), currLen + 1);
                    intMaze[y+1][x] = currLen + 1;
                }
            }
        }

        if(!solveFlag) {
            controlPanelComposite.setStatusLabel("Nie udało się znaleźć wyjścia z labiryntu! Spróbuj zmienić pozycję wejścia / wyjścia.", true);
            return;
        }
        Coords currCoords = exit;
        int currLen = intMaze[exit.y][exit.x];

        while(currLen > 1)
        {
            currLen--;

            int x = currCoords.x;
            int y = currCoords.y;

            if(x > 0){
                if(intMaze[y][x-1] == currLen) {
                    maze[y][x-1] = -2;
                    currCoords = new Coords(x-1, y);
                    continue;
                }
            }
            if(x < width() - 1){
                if(intMaze[y][x+1] == currLen) {
                    maze[y][x+1] = -2;
                    currCoords = new Coords(x+1, y);
                    continue;
                }
            }
            if(y > 0){
                if(intMaze[y-1][x] == currLen) {
                    maze[y-1][x] = -2;
                    currCoords = new Coords(x, y-1);
                    continue;
                }
            }
            if(y < height() - 1){
                if(intMaze[y+1][x] == currLen) {
                    maze[y+1][x] = -2;
                    currCoords = new Coords(x, y+1);
                    continue;
                }
            }
            throw new RuntimeException("Cannot backtrack a route after finding a solution! THIS SHOULD NOT HAPPEN AND MEANS THERE IS AN ERROR IN THE ALGORITHM");

        }

        isSolved = true;
        SwingUtilities.invokeLater(() -> {
            controlPanelComposite.setButtonState(ButtonEnum.solveButton, true);
            controlPanelComposite.setButtonState(ButtonEnum.chooseExitButton, true);
            controlPanelComposite.setButtonState(ButtonEnum.chooseEntranceButton, true);
            controlPanelComposite.setButtonState(ButtonEnum.chooseFileButton, true);
            controlPanelComposite.setButtonState(ButtonEnum.writeFileButton, true);

            String exitString;
            String entryString;

            if(getExit() == null)
                exitString = "Brak";
            else
                exitString = getExit().toString();

            if(getEntry() == null)
                entryString = "Brak";
            else
                entryString = getEntry().toString();

            controlPanelComposite.repaintMazeImage();
            controlPanelComposite.setStatusLabel("<html>Znaleziono rozwiązanie labiryntu!" + "<table><tr><td>Szerokość: " + width() + "</td><td> Wejście: " + entryString +
                        "</td></tr><tr><td>Wysokość: " + height() + "</td><td> Wyjście: " + exitString +
                        "</td></tr></table>", false);
        });

    }

    public boolean isSolved() {
        return isSolved;
    }

    public void setSolved(boolean solveFlag) {
        isSolved = solveFlag;
    }
}
