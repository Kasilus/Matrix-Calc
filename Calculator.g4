grammar Calculator;
INT    : [+-]?[0-9]+;
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
    : plusOrMinus PLUS mult        # Plus
    | plusOrMinus MINUS mult       # Minus
    | mult                         # ToMult
    ;

mult
    : mult MULT transponation      # Multiplication
    | transponation                # ToTransponation
    ;

rank
    : RANK LPAR plusOrMinus RPAR   #Runk
    ;

transponation
    : unaryMinus (TRANSP)?         # Power
    ;


unaryMinus
    : MINUS unaryMinus             # ChangeSign
    | rank                         # Rang
    | atom                         # ToAtom
    ;

atom
    : MATRIX                       # Matrix
    | ID                           # Variable
    | LPAR plusOrMinus RPAR        # Braces
    ;