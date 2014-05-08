grammar Board;

// This puts a Java package statement at the top of the output Java files.
@header {
package parser;
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
WHITESPACE : [ \t\r\n'=']+ -> skip;
NAME: [A-Za-z_][A-Za-z_0-9]*;
NUMBER: ('-')?([0-9]+'.'[0-9]*|'.'?[0-9]+);
COMMENT :  '#'~[\r\n]* -> skip;

/*
 * These are the parser rules. They define the structures used by the parser.
 * *** ANTLR requires grammar nonterminals to be lowercase, like html, normal, and italic.
 */
gadget: bumper | flipper |absorber;
board:'board' ('name'  NAME)? gravity? friction1? friction2? ball* gadget* trigger* EOF;
gravity: 'gravity'  NUMBER;
friction1: 'friction1'  NUMBER;
friction2: 'friction2' NUMBER;
ball:'ball' 'name'  NAME 'x'  NUMBER 'y'  NUMBER 'xVelocity'  NUMBER 'yVelocity'  NUMBER;
sBumper:'squareBumper' 'name'  NAME 'x'  NUMBER 'y'  NUMBER;
cBumper:'circleBumper' 'name'  NAME 'x'  NUMBER 'y'  NUMBER;
tBumper:'triangleBumper' 'name'  NAME 'x'  NUMBER 'y'  NUMBER 'orientation'  NUMBER;
bumper: sBumper | cBumper | tBumper;
flipper: NAME 'name'  NAME 'x'  NUMBER 'y'  NUMBER 'orientation'  NUMBER;
absorber: 'absorber' 'name'  NAME 'x'  NUMBER 'y'  NUMBER 'width'  NUMBER 'height'  NUMBER;
trigger: 'fire' 'trigger'  NAME 'action'  NAME;