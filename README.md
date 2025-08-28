<b>Description:</b> Simple compiler with the goal to read a foreign file, then to translate its contents into C code and write results into a separate .c file; the compiler is written as a Java program to take advantage of OOP.
• Currently implemented: tokenization, variable-collision check, nondeclared variable-usage check
<br><br>

7 kinds of statements:
1. #intnew,varname,;
2. #boolnew,varname,;
3. #intdef,varname=arithmeticArgument;
4. #booldef,varname=conditionalArgument;
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
8. integer variable in FK statements 5-7 --> $UV1
9. boolean variable in FK statements 5-7 --> $UV2
10. `=` --> $S1 (start of arithmetic argument)
11. `{` --> $S2 (start of loop)
12. `;` --> $T1 (end of arithmetic argument)
13. `}` --> $T2 (end of loop)

Abbreviation meanings:
1. FK: first keyword
2. UV: used variable
3. S: starting-point
4. T: terminal

Additional rules: 
1. Variable-names are case-sensitive; the rest is case-insensitive.
2. The lexer skips whitespaces ` `, newlines `\n`, and anything outside statement markers `#` and `;` (perhaps within loop markers `{` and `}`).
