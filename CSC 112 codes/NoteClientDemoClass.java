public class NoteClientDemoClass {
    public static void main(String[] args) {
        NoteADT note1 = new NoteDataStructureClass();
        NoteADT note2 = new NoteDataStructureClass();
        NoteADT note3 = new NoteDataStructureClass("Eighth note", 1); 
        NoteADT note4 = new NoteDataStructureClass("Quarter note", -5);
        
        System.out.println("Note 1,2: (default constructors)");
        System.out.println(note1);
        System.out.println(note2);
        
        System.out.println("Note 3,4: (overloaded constructors)");
        System.out.println(note3);
        System.out.println(note4);
    }
}