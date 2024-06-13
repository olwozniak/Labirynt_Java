package Main.MazeData;

public class Coords{
    public int x;
    public int y;

    public Coords(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Coords c)
            return c.x == x && c.y == y;

        return false;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

}
