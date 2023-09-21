public interface NoteADT {
    void setValue(int value);
    void setLength(String length);
    void setFrequency();
    void setKeyColor();
    int getValue();
    String getLength();
    double getFrequency();
    String getKeyColor();
    String toString();
}