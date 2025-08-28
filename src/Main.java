import java.util.ArrayList;

public class Main {
    static ArrayList<String> tokSeq = new ArrayList<>();

    static boolean shouldLexerCrash = false;
    static boolean shouldParserCrash = false;
    static boolean shouldSemAnCrash = false;

    public static void main(String[] args) {
        System.out.println("Welcome to my partially-completed compiler (lexer"/* + parser + semantic-analyzer*/ + ")!");
        long startTime = System.nanoTime(); // starts a 'stopwatch'

        Lexer.runLexer(); // runs contends of the class "Lexer"
        Parser.runParser(); // runs contents of the class "Parser"
        SemAn.runSemAn(); // runs contents of the class "SemAn"

        System.out.println("\nTime elapsed: ca. " + (System.nanoTime() - startTime) + " nanoseconds.");
        // ends 'stopwatch' and prints result
    }
}
