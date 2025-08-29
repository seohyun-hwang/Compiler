<b>Description:</b> Simple compiler with the goal to read a foreign `.shcl` file (Seohyun Hwang's compiled language), then to translate its contents into C code and write results into a separate `.c` file; the compiler is written as a Java program to take advantage of OOP.<br>
• Currently implemented: tokenization, variable-collision check, nondeclared variable-usage check<br>
• All source code is found within the `src` folder (incl. `Main.java`, `Lexer.java`, `Parser.java`, `SemAn.java`).<br>
• Sample source code in the new programming language is located in `main.shcl`.<br>
<br><br>

7 kinds of statements:
1. #intnew,varname,;
2. #boolnew,varname,;
3. #intdef,varname=arithmeticArgument,;
4. #booldef,varname=conditionalArgument,;
5. #while0,varname{statements};
6. #while1,varname{statements};
7. #print,varname,;

`#` begins a statement.
`;` ends a statement.

Token naming:
1. `#intnew,` --> $FK1
2. `#boolnew,` --> $FK2
3. `#intdef,` --> $FK3
4. `#booldef,` --> $FK4
5. `#while0,` --> $FK5
6. `#while1,` --> $FK6
7. `#print,` --> $FK7
8. Variable in argument of FK3 --> $UV1
9. Variable in argument of FK4 --> $UV2
10. Numeric-literal in FK1 --> $NL1
11. Numeric-literal in FK2 --> $NL2
12. Numeric-literal in FK3 --> $NL3
13. Numeric-literal in FK4 --> $NL4
14. Boolean false --> $B0
15. Boolean true --> $B1
16. `+` (addition operator) --> $ADD+
17. `-` (subtraction operator) --> $SUBT-
18. `*` (multiplication operator) --> $MULT*
19. `/` (division operator) --> $DIV/
20. `:` (conditional equality) --> $CEQ:
21. `<` (lesser than) --> $LESSER<
22. `>` (greater than) --> $GREATER>
23. `=` --> $S1 (start of arithmetic argument)
24. `{` --> $S2 (start of loop)
25. `;` --> $T1 (end of arithmetic argument)
26. `}` --> $T2 (end of loop)

Abbreviation meanings:
1. FK: first keyword
2. S: starting-point
3. UV: use-variable
4. NL: numeric-literal
5. T: terminal

Additional rules: 
1. Variable-names are case-sensitive; the rest is case-insensitive.
2. The lexer ignores whitespaces ` `, newlines `\n`, and any text outside statement markers `#` and `;` (perhaps within loop markers `{` and `}`).

