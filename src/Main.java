import java.util.ArrayList;

public class Main {
    static ArrayList<String> tokSeq = new ArrayList<>();

    static boolean shouldLexerCrash = false;
    static boolean shouldParserCrash = false;
    static boolean shouldSemAnCrash = false;

    public static void main(String[] args) {
        System.out.println("Welcome to my partially-completed compiler (lexer + parser"/* + semantic-analyzer*/ + ")!\n\n\n.shcl Source code below:");
        long startTime = System.nanoTime(); // starts a 'stopwatch'

        Lexer.runLexer(); // runs contends of the class "Lexer" (Lexical analysis)
        System.out.println("\nTokenization successful! Time elapsed in total: ca. " + (System.nanoTime() - startTime) + " nanoseconds.");
        Parser.runParser(); // runs contents of the class "Parser" (Syntactic analysis)
        System.out.println("\nParsing successful! Time elapsed in total: ca. " + (System.nanoTime() - startTime) + " nanoseconds.");
        SemAn.runSemAn(); // runs contents of the class "SemAn" (Semantic analysis)
        //System.out.println("\nFinal translation successful! Time elapsed in total: ca. " + (System.nanoTime() - startTime) + " nanoseconds.");
    }
}
