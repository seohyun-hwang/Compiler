import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Lexer extends Main {
    static boolean isStatementOpen = false;
    static boolean isFirstKeyword = false;
    static boolean isArgOpen = false;
    static boolean isLoopOpen = false;

    static boolean isIntnewDeclared = false;
    static boolean isBoolnewDeclared = false;
    static boolean isIntdefDeclared = false;
    static boolean isBooldefDeclared = false;
    static boolean isWhileFalseDeclared = false;
    static boolean isWhileTrueDeclared = false;
    static boolean isPrintDeclared = false;

    public static void runLexer() {
        ArrayList<Character> word = new ArrayList<>();

        //read through .shcl file --> tokenize
        try {
            int lineCount = 1;

            File shclFile = new File("main.shcl");
            Scanner scanner = new Scanner(shclFile); // instructs a scanner to read through the .shcl file
            scanner.useDelimiter(""); // scanner-delimiter set to an empty String
            while (scanner.hasNext()) {
                int charCount = 0;
                char ch = scanner.next().charAt(charCount); // going through file character-by-character
                System.out.print(ch);

                if (shouldLexerCrash) {
                    break;
                }
                else if (ch == '\n') { // skip newlines
                    lineCount++;
                    continue;
                }
                else if (ch == ' ') { // skip whitespaces
                    continue;
                }
                if (!isStatementOpen) {
                    if (ch == '#') { // # marks the beginning of a statement
                        isStatementOpen = true;
                        isFirstKeyword = true;
                        word.add(ch);
                    }
                }
                else {
                    if (ch == ';') {
                        isStatementOpen = false;
                        isArgOpen = false;
                        tokSeq.add("$T1");
                        word.clear();
                        continue;
                    }
                    executeWhileStatementOpen(ch, word, lineCount);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("ERROR: FileNotFound");
            e.printStackTrace();
        }

        System.out.println();
        for (int i = 0; i < tokSeq.size(); i++) {
            System.out.print(tokSeq.get(i) + " - ");
        }
    }
    public static void executeWhileStatementOpen(char ch, ArrayList<Character> word, int lineCount) {
        word.add(ch);

        if (!isFirstKeyword) {
            if (isIntnewDeclared) { // FK1
                if (ch == '=') {
                    syntaxError(lineCount);
                }
                else if (ch == ',') {
                    isIntnewDeclared = false;
                    tokSeq.add(wordToString(word, 1));
                }
            }
            else if (isBoolnewDeclared) { // FK2
                if (ch == '=') {
                    syntaxError(lineCount);
                }
                else if (ch == ',') {
                    isBoolnewDeclared = false;
                    tokSeq.add(wordToString(word, 1));
                }
            }
            else if (isIntdefDeclared) { // FK3
                if (ch == '=') {
                    isArgOpen = true;
                    tokSeq.add(wordToString(word, 1));
                    tokSeq.add("$S1");
                    word.clear();
                }
                else if (ch == ',' && isArgOpen) {
                    isIntdefDeclared = false;
                    isArgOpen = false;
                }
                else if (ch == '+') {
                    tokSeq.add("$UV1");
                    tokSeq.add(wordToString(word, 1));
                    tokSeq.add("$ADD");
                    word.clear();
                }
                else if (ch == '-') {
                    tokSeq.add("$UV1");
                    tokSeq.add(wordToString(word, 1));
                    tokSeq.add("$SUBT");
                    word.clear();
                }
                else if (ch == '*') {
                    tokSeq.add("$UV1");
                    tokSeq.add(wordToString(word, 1));
                    tokSeq.add("$MULT");
                    word.clear();
                }
                else if (ch == '/') {
                    tokSeq.add("$UV1");
                    tokSeq.add(wordToString(word, 1));
                    tokSeq.add("$DIV");
                    word.clear();
                }
            }
            else if (isBooldefDeclared) { // FK4
                if (ch == '=') {
                    isArgOpen = true;
                    tokSeq.add(wordToString(word, 1));
                    tokSeq.add("$S1");
                    word.clear();
                }
                else if (ch == ',' && isArgOpen) {
                    isBooldefDeclared = false;
                    isArgOpen = false;
                }
                else if (ch == ':') {
                    tokSeq.add("$UV2");
                    tokSeq.add(wordToString(word, 1));
                    tokSeq.add("$EQCOND"); // conditional equality
                    word.clear();
                }
                else if (ch == '<') {
                    tokSeq.add("$UV2");
                    tokSeq.add(wordToString(word, 1));
                    tokSeq.add("$LESSER"); // lesser than
                    word.clear();
                }
                else if (ch == '>') {
                    tokSeq.add("$UV2");
                    tokSeq.add(wordToString(word, 1));
                    tokSeq.add("$GREATER"); // greater than
                    word.clear();
                }
            }
            else if (isWhileFalseDeclared) { // FK5
                if (ch == '{') {
                    isLoopOpen = true;
                    tokSeq.add(wordToString(word, 1));
                    tokSeq.add("$S2");
                }
                else if (ch == '}') {
                    isLoopOpen = false;
                    tokSeq.add("$T2");
                }
            }
            else if (isWhileTrueDeclared) {  // FK6
                if (ch == '{') {
                    isLoopOpen = true;
                    tokSeq.add(wordToString(word, 1));
                    tokSeq.add("$S2");
                }
                else if (ch == '}') {
                    isLoopOpen = false;
                    tokSeq.add("$T2");
                }
            }
            else if (isPrintDeclared) { // FK7
                if (ch == ',') {
                    isPrintDeclared = false;
                    tokSeq.add(wordToString(word, 1));
                }
            }
        }
        else if (wordToString(word, 0).equalsIgnoreCase("#intnew,")) { // integer-variable declaration
            word.clear();
            isFirstKeyword = false;
            isIntnewDeclared = true;
            tokSeq.add("$FK1");
            //tokSeq.add("FK_INTNEW");
        }
        else if (wordToString(word, 0).equalsIgnoreCase("#boolnew,")) { // boolean-variable declaration
            word.clear();
            isFirstKeyword = false;
            isBoolnewDeclared = true;
            tokSeq.add("$FK2");
            //tokSeq.add("FK_BOOLNEW");
        }
        else if (wordToString(word, 0).equalsIgnoreCase("#intdef,")) { // boolean-variable declaration
            word.clear();
            isFirstKeyword = false;
            isIntdefDeclared = true;
            tokSeq.add("$FK3");
            //tokSeq.add("FK_INTCHANGE");
        }
        else if (wordToString(word, 0).equalsIgnoreCase("#booldef,")) { // boolean-variable declaration
            word.clear();
            isFirstKeyword = false;
            isBooldefDeclared = true;
            tokSeq.add("$FK4");
            //tokSeq.add("FK_BOOLCHANGE");
        }
        else if (wordToString(word, 0).equalsIgnoreCase("#while0,")) { // while-false loop declaration
            word.clear();
            isFirstKeyword = false;
            isWhileFalseDeclared = true;
            tokSeq.add("$FK5");
            //tokSeq.add("FK_WHILE0");
        }
        else if (wordToString(word, 0).equalsIgnoreCase("#while1,")) { // while-true loop declaration
            word.clear();
            isFirstKeyword = false;
            isWhileTrueDeclared = true;
            tokSeq.add("$FK6");
            //tokSeq.add("FK_WHILE1");
        }
        else if (wordToString(word, 0).equalsIgnoreCase("#println,")) { // while-false loop declaration
            word.clear();
            isFirstKeyword = false;
            isPrintDeclared = true;
            tokSeq.add("$FK7");
            //tokSeq.add("FK_PRINTLN");
        }
    }

    public static String wordToString(ArrayList<Character> word, int n) { // converting the Character-ArrayList "word" to a String value
        char[] charArray = new char[word.size() - n]; // converting the Character-ArrayList "word" to a char-array "charArray"
        for (int i = 0; i < word.size() - n; i++) { // filling the char-array "charArray" with elements of the Character-ArrayList "word"
            charArray[i] = word.get(i);
        }
        return new String(charArray); // converting the char-array "charArray" to a String value
    }
    public static void syntaxError(int lineCount) {
        System.out.println("\nERROR: SyntaxError: ya messed up the syntax! check line " + lineCount + ".");
    }
    public static void characterError(int lineCount) {
        System.out.println("\nERROR: CharacterError: you used a character you're not supposed to use! check line " + lineCount + ".");
    }
}
