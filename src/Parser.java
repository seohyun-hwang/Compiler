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
        parsingStage3();

    }
    public static void parsingStage1() { // crash if there are duplicate new-variable names.
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
                    System.out.println("\nPARSING ERROR: duplicate new-variable names! Duplicate of a new-int variable.");
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
                    System.out.println("\nPARSING ERROR: duplicate new-variable names! Duplicate of a new-bool variable.");
                    System.exit(1);
                }
                varMatchCountInt = 0;
                varMatchCountBool = 0;
            }
        }
    }
    public static void parsingStage2() { // checks whether def-variable names all match with new-variable names + checks that bool-use-variables exist neither in int-def nor in bool-def declarations.
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
                    System.out.println("\nPARSING ERROR: an int-def variable does not match any of the new-int-variable names!");
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
                    System.out.println("\nPARSING ERROR: a bool-def variable does not match any of the new-bool-variable names!");
                    System.exit(1);
                }
            }
            else if (tokSeq.get(i).equals("$FK5") || tokSeq.get(i).equals("$FK6") || tokSeq.get(i).equals("$FK7")) {
                boolean variableIncluded = false;
                for (int j = 0; j < varNamesInt.size(); j++) {
                    if (tokSeq.get(i + 1).equals(varNamesInt.get(j))) {
                        variableIncluded = true;
                        break;
                    }
                }
                if (!variableIncluded) {
                    for (int j = 0; j < varNamesBool.size(); j++) {
                        if (tokSeq.get(i + 1).equals(varNamesBool.get(j))) {
                            variableIncluded = true;
                            break;
                        }
                    }
                    if (!variableIncluded) {
                        if (tokSeq.get(i).equals("$FK5")) {
                            System.out.println("\nPARSING ERROR: a while0 use-variable does not match any of the new-variable names!");
                        }
                        else if (tokSeq.get(i).equals("$FK6")) {
                            System.out.println("\nPARSING ERROR: a while1 use-variable does not match any of the new-variable names!");
                        }
                        if (tokSeq.get(i).equals("$FK7")) {
                            System.out.println("\nPARSING ERROR: a println use-variable does not match any of the new-variable names!");
                        }
                        System.exit(1);
                    }
                }
            }
            else if (tokSeq.get(i).equals("$UV1")) {
                boolean variableIncluded = false;
                for (int j = 0; j < varNamesBool.size(); j++) {
                    if (tokSeq.get(i + 1).equals(varNamesBool.get(j))) {
                        variableIncluded = true;
                        break;
                    }
                }
                if (variableIncluded) {
                    System.out.println("PARSING ERROR: found a boolean-use-variable name in an int-def-variable argument!");
                    System.exit(1);
                }
                else {
                    for (int j = 0; j < varNamesInt.size(); j++) {
                        if (tokSeq.get(i + 1).equals(varNamesInt.get(j))) {
                            variableIncluded = true;
                            break;
                        }
                    }
                    if (!variableIncluded) {
                        System.out.println("\nPARSING ERROR: a use-variable does not match any of the new-variable names!");
                        System.exit(1);
                    }
                }
            }
            else if (tokSeq.get(i).equals("$UV2")) {
                boolean variableIncluded = false;
                for (int j = 0; j < varNamesBool.size(); j++) {
                    if (tokSeq.get(i + 1).equals(varNamesBool.get(j))) {
                        variableIncluded = true;
                        break;
                    }
                }
                if (variableIncluded) {
                    System.out.println("PARSING ERROR: found a bool-use-variable name in a bool-def-variable argument!");
                    System.exit(1);
                }
                else {
                    for (int j = 0; j < varNamesInt.size(); j++) {
                        if (tokSeq.get(i + 1).equals(varNamesInt.get(j))) {
                            variableIncluded = true;
                            break;
                        }
                    }
                    if (!variableIncluded) {
                        System.out.println("\nPARSING ERROR: a use-variable does not match any of the new-variable names!");
                        System.exit(1);
                    }
                }
            }
        }
    }
    public static void parsingStage3() { // crash if there is an empty while loop or if there is a conditional argument of a bool-def declaration containing more than one type of conditional operator.
        boolean enteredFK4 = false;
        int condOpCount = 0;
        for (int i = 0; i < tokSeq.size(); i++) {
            if (tokSeq.get(i).equals("$FK4")) {
                enteredFK4 = true;
            }
            else if (tokSeq.get(i).equals("$GREATER>") || tokSeq.get(i).equals("$LESSER<") || tokSeq.get(i).equals("$CEQ:")) {
                condOpCount++;
            }
            else if (tokSeq.get(i).equals("$T1") && enteredFK4) {
                enteredFK4 = false;
                if (condOpCount > 1) {
                    System.out.println("PARSING ERROR: you entered more than one conditional operator in the same bool-var-def declaration.");
                    System.exit(1);
                }
                condOpCount = 0;
            }
            else if (tokSeq.get(i).equals("$S2")) { // checks whether a while loop is empty
                if (tokSeq.get(i + 1).equals("$T2")) {
                    System.out.println("PARSING ERROR: your while loop is empty!");
                    System.exit(1);
                }
            }
        }
    }
    public static void parsingStage4() { // PEMDAS hierarchical ordering of arithmetic operations/operands + final reordering of tokSeq to tokSeq2

    }

 }

