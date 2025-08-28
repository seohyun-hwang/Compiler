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
9.  variable in FK4, FK5, or FK6 --> $UV
10. numeric literal in FK5 or FK6 --> $NL
11. `=` --> $S1 (start of arithmetic argument)
12. `{` --> $S2 (start of loop)
13. `;` --> $T1 (end of arithmetic argument)
14. `}` --> $T2 (end of loop)

Abbreviation meanings:
1. FK: first keyword
2. S: starting-point
3. UV: use-variable
4. NL: numeric-literal
5. T: terminal

Additional rules: 
1. Variable-names are case-sensitive; the rest is case-insensitive.
2. The lexer ignores whitespaces ` `, newlines `\n`, and any text outside statement markers `#` and `;` (perhaps within loop markers `{` and `}`).

