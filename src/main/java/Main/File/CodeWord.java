package Main.File;

public class CodeWord {
    byte separator;
    byte value;
    short counter;

    public CodeWord(byte separator, byte value, byte counter) {
        this.separator = separator;
        this.value = value;
        this.counter = (short) (counter & 0xFF);
    }
}
