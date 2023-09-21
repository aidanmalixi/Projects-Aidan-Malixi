public class NoteDataStructureClass implements NoteADT {
    private String length;
    private int value;
    private double frequency;
    private String keyColor;

    public NoteDataStructureClass() {
        setLength("Quarter note");
        setValue(-9); 
        setFrequency();
        setKeyColor();
    }

    public NoteDataStructureClass(String length, int value) {
        setLength(length);
        setValue(value);
        setFrequency();
        setKeyColor();
    }

    public NoteDataStructureClass(NoteDataStructureClass other) {
        this.length = other.length;
        this.value = other.value;
        this.frequency = other.frequency;
        this.keyColor = other.keyColor;
    }

    public void setValue(int value) {
        this.value = value;
        setFrequency();
        setKeyColor();
    }

    public void setLength(String length) {
        this.length = length;
    }

    public void setFrequency() {
        this.frequency = 440.0 * Math.pow(2, value / 12.0);
    }

    public void setKeyColor() {
        int noteIndex = (value + 9) % 12; // Map value to note index
        this.keyColor = (noteIndex == 1 || noteIndex == 3 || noteIndex == 6 || noteIndex == 8 || noteIndex == 10) ? "Black key (sharp)" : "Natural key";
    }

    public int getValue() {
        return value;
    }

    public String getLength() {
        return length;
    }

    public double getFrequency() {
        return frequency;
    }

    public String getKeyColor() {
        return keyColor;
    }

    public String toString() {
        return getNoteLetter() + "\nLength: " + length + "\nValue: " + value + "\n" + keyColor + "\n" + frequency + " Hz";
    }

    private String getNoteLetter() {
        String[] noteLetters = {"C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B"};
        int noteIndex = (value + 9) % 12; // Map value to note index
        return noteLetters[noteIndex];
    }
}
