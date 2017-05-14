grammar Calculator;
INT    : [0-9]+;
DOUBLE : [0-9]+'.'[0-9]+;
PI     : 'pi';
E      : 'e';
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
    : PI                    # ConstantPI
    | E                     # ConstantE
    | DOUBLE                # Double
    | INT                   # Int
    | ID                    # Variable
    | LPAR plusOrMinus RPAR # Braces
    ;