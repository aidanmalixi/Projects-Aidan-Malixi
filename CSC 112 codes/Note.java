public class Note {
    private String length;
    private int value;

    public Note() {
        this.length = "Quarter note";
        this.value = -9;
    }

    public Note(String length, int value) {
        this.length = length;
        this.value = value;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getLength() {
        return length;
    }


    public int getValue() {
        return value;
    }
    
    public String getLetter() {
            String[] Letter = {"C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B"};
            int index = (value + 9) % 12;
            return Letter[index];
        }
    public boolean isNaturalNote() {
        int noteIndex = (value + 9) % 12; // Map value to note index
        return noteIndex != 1 && noteIndex != 3 && noteIndex != 6 && noteIndex != 8 && noteIndex != 10;
    }

     public double getFrequency() {
        double exponent = (double) value / 12.0;
        return 440.0 * Math.pow(2.0, exponent);
    }

    // Convert the note to a string representation
    public String toString() {
        String noteType = isNaturalNote() ? "Natural" : "Sharp";
        return getLetter() + "\nLength: " + length + "\nValue: " + value + "\n" + noteType + " key\nFrequency: " + getFrequency() + " Hz";
    }

    public static void main(String[] args) { 

        Note note1 = new Note(); 
        Note note2 = new Note("Eighth note", -6);
        System.out.println(note1.toString());
        System.out.println(note2.toString());
    }
}