package Main.MazeData;

import java.util.AbstractMap;
import java.util.LinkedList;
import java.util.Map;

public class DJQueue extends LinkedList<Map.Entry<Coords, Integer>> {
    public DJQueue()
    {
        super();
    }

    public void addToQueue(Coords crd, int length)
    {
        Map.Entry<Coords, Integer> newEntry = new AbstractMap.SimpleEntry<>(crd, length);

        add(newEntry);
    }

    public Integer peekLength()
    {
        if(isEmpty())
            return null;

        return peek().getValue();
    }

    public Coords popCoords()
    {
        if(isEmpty())
            return null;

        return remove().getKey();
    }
}
