import java.io.*;

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

        SemAnStage2();
    }
    /*public static void SemAnStage1() { // determining each variable's value at each variable-usage + unrolling the while loops

    }*/

    public static void SemAnStage2() { // writing into main.c
        try (BufferedWriter writer = new BufferedWriter((new FileWriter("main.c")))) {
            writer.write("#include <stdio.h>\n\nint main() {\n\n    "); // opening of the C file
            //writer.write();
            writer.write("return 0;\n}"); // conclusion of the C file
        }
        /*try (FileOutputStream file = new FileOutputStream("main.c")) {
            file.getChannel().truncate(0); // clears the entire file first
            file.write();
        }*/ catch (IOException e) {
            System.out.println("There was an error writing into the main.c file.");
            System.exit(1);
        }
        System.out.println("Compiling has finished! Check your main.c file.");
    }
}
