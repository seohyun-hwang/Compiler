import java.util.ArrayList;

public class Parser extends Lexer {
    static ArrayList<String> tokSeq2 = new ArrayList<>();
    static ArrayList<String> varNamesInt = new ArrayList<>();
    static ArrayList<String> varNamesBool = new ArrayList<>();

    public static void runParser() {
        for (int i = 0; i < tokSeq.size(); i++) { // scans the ArrayList "tokSeq"
            if (shouldParserCrash) {
                break;
            }
            else if (tokSeq.get(i).equals("$FK1")) {
                varNamesInt.add(tokSeq.get(i + 1));
            }
            else if (tokSeq.get(i).equals("$FK2")) {
                varNamesBool.add(tokSeq.get(i + 1));
            }
            else if (tokSeq.get(i).equals("$FK3")) {

            }
            else if (tokSeq.get(i).equals("$FK4")) {

            }
            else if (tokSeq.get(i).equals("$FK5")) {

            }
            else if (tokSeq.get(i).equals("$FK6")) {
                tokSeq2.add("$FK7");
            }
        }
        parsingStage1();
        parsingStage2();
    }
    public static void parsingStage1() { // crash if there are duplicate new-variable names
        int varMatchCountInt = 0;
        int varMatchCountBool = 0;

        for (int i = 0; i < tokSeq.size(); i++) { // scans the ArrayList "tokSeq"
            if (tokSeq.get(i).equals("$FK1")) {
                for (int j = 0; j < varNamesInt.size(); j++) {
                    if (tokSeq.get(i + 1).equals(varNamesInt.get(j))) {
                        varMatchCountInt++;
                    }
                    if (tokSeq.get(i + 1).equals(varNamesBool.get(j))) {
                        varMatchCountBool++;
                    }
                }
                if (varMatchCountInt >= 2 || (varMatchCountInt >= 1 && varMatchCountBool >= 1)) {
                    System.out.println("\nERROR: duplicate new-variable names! Duplicate of a new-int variable.");
                    System.exit(1);
                }
                varMatchCountInt = 0;
                varMatchCountBool = 0;
            }
            else if (tokSeq.get(i).equals("$FK2")) {
                for (int j = 0; j < varNamesBool.size(); j++) {
                    if (tokSeq.get(i + 1).equals(varNamesBool.get(j))) {
                        varMatchCountBool++;
                    }
                    if (tokSeq.get(i + 1).equals(varNamesInt.get(j))) {
                        varMatchCountInt++;
                    }
                }
                if (varMatchCountBool >= 2 || (varMatchCountInt >= 1 && varMatchCountBool >= 1)) {
                    System.out.println("\nERROR: duplicate new-variable names! Duplicate of a new-bool variable.");
                    System.exit(1);
                }
                varMatchCountInt = 0;
                varMatchCountBool = 0;
            }
        }
    }
    public static void parsingStage2() { // checks whether def-variable and use-variable names all match with new-variable names
        for (int i = 0; i < tokSeq.size(); i++) { // scans the ArrayList "tokSeq"
            if (tokSeq.get(i).equals("$FK3")) {
                boolean variableIncluded = false;
                for (int j = 0; j < varNamesInt.size(); j++) {
                    if (tokSeq.get(i + 1).equals(varNamesInt.get(j))) {
                        variableIncluded = true;
                        break;
                    }
                }
                if (!variableIncluded) {
                    System.out.println("\nERROR: an int-def variable does not match any of the new-int-variable names!");
                    System.exit(1);
                }
            }
            else if (tokSeq.get(i).equals("$FK4")) {
                boolean variableIncluded = false;
                for (int j = 0; j < varNamesBool.size(); j++) {
                    if (tokSeq.get(i + 1).equals(varNamesBool.get(j))) {
                        variableIncluded = true;
                        break;
                    }
                }
                if (!variableIncluded) {
                    System.out.println("\nERROR: an bool-def variable does not match any of the new-bool-variable names!");
                    System.exit(1);
                }
            }
            else if (tokSeq.get(i).equals("$UV1")) {
                boolean variableIncluded = true;
                for (int j = 0; j < varNamesInt.size(); j++) {
                    if (tokSeq.get(i + 1).equals(varNamesInt.get(j))) {
                        variableIncluded = false;
                        break;
                    }
                }
                if (!variableIncluded) {
                    shouldParserCrash = true;
                    break;
                }
            }
            else if (tokSeq.get(i).equals("$UV2")) {
                boolean variableIncluded = true;
                for (int j = 0; j < varNamesBool.size(); j++) {
                    if (tokSeq.get(i + 1).equals(varNamesBool.get(j))) {
                        variableIncluded = false;
                        break;
                    }
                }
                if (!variableIncluded) {
                    shouldParserCrash = true;
                    break;
                }
            }
        }
    }
    public static void parsingStage3() {

    }
}
