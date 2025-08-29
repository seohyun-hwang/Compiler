import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class SemAn extends Parser {
    public static void runSemAn() {
        try {
            File cFile = new File("main.c"); // create a new file "main.c"
            if (cFile.createNewFile()) {
                System.out.println("\nA new file \"main.c\" has been created. Translated code will be written there.");
            }
            else {
                System.out.println("\nThe file \"main.c\" already exists. Translated code will be written there.");
            }
        } catch (IOException e) {
            System.out.println("\nERROR: the new file \"main.c\" could not be created.");
            e.printStackTrace();
        }

    }
    public static void SemAnStage1() { // determining each variable's value at each variable-usage + unrolling the while loops

    }
    /*public static void SemAnStage2() { // writing into main.c
        try (FileOutputStream document = new FileOutputStream("main.c")) {
            document.write();
            System.out.println("Compiling has finished! Check your main.c file.");
        } catch (IOException e) {
            System.out.println("There was an error writing into the main.c file.");
            System.exit(1);
        }
    }*/
}
