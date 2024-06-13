package Main.File;

import  Main.MazeData.MazeBrowse;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class BinaryFileHeader {
    int fileId;
    byte esc;
    short columns;
    short rows;
    short entryX;
    short entryY;
    short exitX;
    short exitY;
    int counter;
    int solutionOffset;
    byte separator;
    byte wall;
    byte path;

    BinaryFileHeader(RandomAccessFile file) throws IOException {
        file.seek(0);
        fileId = Integer.reverseBytes(file.readInt());
        esc = file.readByte();
        columns = Short.reverseBytes(file.readShort());
        rows = Short.reverseBytes(file.readShort());
        entryX = Short.reverseBytes(file.readShort());
        entryY = Short.reverseBytes(file.readShort());
        exitX = Short.reverseBytes(file.readShort());
        exitY = Short.reverseBytes(file.readShort());

        file.readInt();
        file.readInt();
        file.readInt();

        counter = Integer.reverseBytes(file.readInt());
        solutionOffset = Integer.reverseBytes(file.readInt());
        separator = file.readByte();
        wall = file.readByte();
        path = file.readByte();
    }

    BinaryFileHeader(MazeBrowse data) {
        if (data.getExit() == null || data.getEntry() == null)
            throw new RuntimeException("Brak wejścia / wyjscia");

        fileId = 0x52524243;
        esc = 0x1B;
        columns = (short) data.width();
        rows = (short) data.height();
        entryX = (short) (data.getEntry().x + 1);
        entryY = (short) (data.getEntry().y + 1);
        exitX = (short) (data.getExit().x + 1);
        exitY = (short) (data.getExit().y + 1);
        counter = 0;
        solutionOffset = 0;
        separator = '#';
        wall = 'X';
        path = ' ';
    }

    byte[] toByteBuffer()
    {
        ByteBuffer buffer = ByteBuffer.allocate(40);

        buffer.order(ByteOrder.LITTLE_ENDIAN);

        buffer.putInt(fileId);
        buffer.put(esc);
        buffer.putShort(columns);
        buffer.putShort(rows);
        buffer.putShort(entryX);
        buffer.putShort(entryY);
        buffer.putShort(exitX);
        buffer.putShort(exitY);
        buffer.putInt(0);
        buffer.putInt(0);
        buffer.putInt(0);
        buffer.putInt(counter);
        buffer.putInt(solutionOffset);
        buffer.put(separator);
        buffer.put(wall);
        buffer.put(path);

        return buffer.array();
    }

    @Override
    public String toString() {
        return "BinaryFileHeader{" +
                "fileId=0x" + Integer.toHexString(fileId) +
                ", esc=0x" + Integer.toHexString(esc) +
                ", columns=" + columns +
                ", rows=" + rows +
                ", entryX=" + entryX +
                ", entryY=" + entryY +
                ", exitX=" + exitX +
                ", exitY=" + exitY +
                ", counter=" + counter +
                ", solutionOffset=" + solutionOffset +
                ", separator=" + separator +
                ", wall=" + wall +
                ", path=" + path +
                '}';
    }
}

