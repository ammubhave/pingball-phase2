grammar Board;

// This puts a Java package statement at the top of the output Java files.
@header {
package pingball.parser;
}

// This adds code to the generated lexer and parser.
@members {
    /**
     * Call this method to have the lexer or parser throw a RuntimeException if
     * it encounters an error.
     */
    public void reportErrorsAsExceptions() {
        addErrorListener(new ExceptionThrowingErrorListener());
    }
    
    private static class ExceptionThrowingErrorListener extends BaseErrorListener {
        @Override
        public void syntaxError(Recognizer<?, ?> recognizer,
                Object offendingSymbol, int line, int charPositionInLine,
                String msg, RecognitionException e) {
            throw new RuntimeException(msg);
        }
    }
}

/*
 * These are the lexical rules. They define the tokens used by the lexer.
 * *** ANTLR requires tokens to be CAPITALIZED, like START_ITALIC, END_ITALIC, and TEXT.
 */
 


//COMMENT : '#' [^\r\n]* '\n' -> skip ;
COMMENT : '#' ( ~'\n' )* -> skip;
WHITESPACE : [ \t\r\n]+ -> skip ;

// Attributes
/*
STRING_NAME : 'name' ;
STRING_GRAVITY : 'gravity' ;
STRING_FRICTION1 : 'friction1' ;
STRING_FRICTION2 : 'friction2' ;
STRING_WIDTH : 'width' ;
STRING_HEIGHT : 'height' ;
STRING_TRIGGER : 'trigger' ;
STRING_ACTION : 'action' ;
STRING_ORIENTATION : 'orientation' ;
STRING_XVELOCITY : 'xVelocity' ;
STRING_YVELOCITY : 'yVelocity' ;
//STRING_X : 'x' ;
//STRING_Y : 'y' ;
STRING_KEY : 'key';
STRING_OTHERBOARD : 'otherBoard' ;
STRING_OTHERPORTAL : 'otherPortal' ;*/

EQUALS : '=' ;
NUMBER : '-'?([0-9]+'.'[0-9]*|'.'?[0-9]+) ;
NAME : [A-Za-z_][A-Za-z_0-9]* ;

/*
 * These are the parser rules. They define the structures used by the parser.
 * *** ANTLR requires grammar nonterminals to be lowercase, like html, normal, and italic.
 */
board : objectLine+ EOF ;
objectLine: boardObjectLine | ballObjectLine | squareBumperObjectLine | triangleBumperObjectLine | circleBumperObjectLine | leftFlipperObjectLine | rightFlipperObjectLine | absorberObjectLine | fireObjectLine | keyupObjectLine | keydownObjectLine | portalObjectLine ;

// object lines
boardObjectLine : 'board' attribute+ ;
ballObjectLine : 'ball' attribute+ ;
squareBumperObjectLine : 'squareBumper' attribute+ ;
triangleBumperObjectLine : 'triangleBumper' attribute+ ;
circleBumperObjectLine : 'circleBumper' attribute+ ;
leftFlipperObjectLine : 'leftFlipper' attribute+ ;
rightFlipperObjectLine: 'rightFlipper' attribute+ ;
absorberObjectLine: 'absorber' attribute+ ;
fireObjectLine: 'fire' attribute+ ;
keydownObjectLine : 'keydown' attribute+ ;
keyupObjectLine : 'keyup' attribute+ ;
portalObjectLine : 'portal' attribute+ ;

attribute : NAME EQUALS (NAME | NUMBER) ;