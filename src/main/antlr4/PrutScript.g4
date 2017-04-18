grammar PrutScript;
/*@header {
    package nl.hvanderheijden.prutscript.antlr4;
}*/

prutScript : (block endExpr+|expression endExpr+|endExpr+)+  EOF;

expression :
	LPAREN expression RPAREN #ParenthesizedExpr
	|expression EXPONENT expression  #ExponentialExpr
    | expression operatorToken=(MULTIPLY | DIVIDE) expression  #MultiplicativeExpr
	| expression operatorToken=(ADD | SUBTRACT) expression  #AdditiveExpr
	| NUMBER #NumberExpr
	| String #StringExpr
	| identifier=Identifier #variable
	| identifier=Identifier  expression+   #methodExpr
	| identifier=Identifier '=' (expression|block)  #assignExpr
	;


LINE_COMMENT :   '#' ~[\r\n]* -> skip;



endExpr: (';'| '\r'?'\n');

block:
    identity=Identifier Identifier* endExpr* '{' endExpr* ((expression|block) endExpr+)* '}'
    ;



Identifier
    :   Letter LetterOrDigit*
    ;

fragment Letter
    :   [a-zA-Z$_] // these are the "java letters" below 0x7F
    |   // covers all characters above 0x7F which are not a surrogate
        ~[\u0000-\u007F\uD800-\uDBFF]
    |   // covers UTF-16 surrogate pairs encodings for U+10000 to U+10FFFF
        [\uD800-\uDBFF] [\uDC00-\uDFFF]
    ;

fragment LetterOrDigit
    :   [a-zA-Z0-9$_] // these are the "java letters or digits" below 0x7F
    |   // covers all characters above 0x7F which are not a surrogate
        ~[\u0000-\u007F\uD800-\uDBFF]
    |   // covers UTF-16 surrogate pairs encodings for U+10000 to U+10FFFF
        [\uD800-\uDBFF] [\uDC00-\uDFFF]
    | '.'
;

NUMBER : INT ('.' INT)?;

INT : ('0'..'9')+;

EXPONENT : '^';
MULTIPLY : '*';
DIVIDE : '/';
SUBTRACT : '-';
ADD : '+';
LPAREN : '(';
RPAREN : ')';

String
   : '"' (~ ["\\])* '"'
   ;

COMMENT
    :   '/*' .*? '*/' -> skip
    ;


//WS : [ \t\r\n] -> channel(HIDDEN);
WS: (' ' | '\t') -> skip;
NL: '\r'? '\n' -> skip;