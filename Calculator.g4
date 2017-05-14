grammar Calculator;
INT    : [0-9]+;
DOUBLE : [+-]?[0-9]+'.'[0-9]+;
NUMBER : INT
       | DOUBLE;
NL     : '\n';
WS     : [ \t\r]+ -> skip;
ID     : [A-Z]+;

PLUS  : '+';
EQUAL : '=';
MINUS : '-';
MULT  : '*';
DIV   : '/';
LPAR  : '(';
RPAR  : ')';
TRANSP: '^T';
RANK  : 'rank';
MATRIX : '[''['NUMBER(','[ ]*NUMBER)*']'
(','[ ]*'['NUMBER (','[ ]*NUMBER)*']')*']';

input
    : ID EQUAL plusOrMinus EOF     # ToSetVar
    | plusOrMinus EOF              # Calculate
    ;

plusOrMinus
    : plusOrMinus PLUS multOrDiv  # Plus
    | plusOrMinus MINUS multOrDiv # Minus
    | multOrDiv                   # ToMultOrDiv
    ;

multOrDiv
    : multOrDiv MULT pow # Multiplication
    | multOrDiv DIV pow  # Division
    | pow                # ToPow
    ;

rank
    : RANK LPAR plusOrMinus RPAR  #Runk
    ;

pow
    : unaryMinus (TRANSP)? # Power
    ;


unaryMinus
    : MINUS unaryMinus # ChangeSign
    | rank             # Rang
    | atom             # ToAtom
    ;

atom
    : MATRIX                # Matrix
    | ID                    # Variable
    | LPAR plusOrMinus RPAR # Braces
    ;