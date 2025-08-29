import java.io.File;
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
}
