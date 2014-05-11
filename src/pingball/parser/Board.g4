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

// Objects/Gadgets
STRING_BOARD : 'board' ;
STRING_BALL : 'ball' ;

STRING_SQUAREBUMPER : 'squareBumper' ;
STRING_TRIANGLEBUMPER : 'triangleBumper' ; 
STRING_CIRCLEBUMPER : 'circleBumper';
STRING_LEFTFLIPPER : 'leftFlipper' ;
STRING_RIGHTFLIPPER : 'rightFlipper' ;
STRING_ABSORBER : 'absorber' ;
STRING_PORTAL : 'portal' ;
STRING_FIRE : 'fire' ;
STRING_KEYDOWN : 'keydown' ;
STRING_KEYUP : 'keyup' ;

// Attributes
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
STRING_X : 'x' ;
STRING_Y : 'y' ;
STRING_KEY : 'key';
STRING_OTHERBOARD : 'otherBoard' ;
STRING_OTHERPORTAL : 'otherPortal' ;

EQUALS : '=' ;
NUMBER : '-'?([0-9]+'.'[0-9]*|'.'?[0-9]+) ;
//INTEGER : [0-9]+ ;
//NUMBER : [0-9]+ ;
//NUMBER : ([0-9]+) | ('-'?([0-9]+.[0-9]*|.?[0-9]+)) ;
NAME : [A-Za-z_][A-Za-z_0-9]* ;

/*
 * These are the parser rules. They define the structures used by the parser.
 * *** ANTLR requires grammar nonterminals to be lowercase, like html, normal, and italic.
 */
board : objectLine+ EOF ;
objectLine: boardObjectLine | ballObjectLine | squareBumperObjectLine | triangleBumperObjectLine | circleBumperObjectLine | leftFlipperObjectLine | rightFlipperObjectLine | absorberObjectLine | fireObjectLine ;

// object lines
boardObjectLine : STRING_BOARD boardAttributes+ ;
ballObjectLine : STRING_BALL ballAttributes+ ;
squareBumperObjectLine : STRING_SQUAREBUMPER squareBumperAttributes+ ;
triangleBumperObjectLine : STRING_TRIANGLEBUMPER triangleBumperAttributes+ ;
circleBumperObjectLine : STRING_CIRCLEBUMPER circleBumperAttributes+ ;
leftFlipperObjectLine : STRING_LEFTFLIPPER leftFlipperAttributes+ ;
rightFlipperObjectLine: STRING_RIGHTFLIPPER rightFlipperAttributes+ ;
absorberObjectLine: STRING_ABSORBER absorberAttributes+ ;
fireObjectLine: STRING_FIRE fireAttributes+ ;
keydownObjectLine : STRING_KEYDOWN keydownAttributes+ ;
keyupObjectLine : STRING_KEYUP keyupAttributes+ ;
portalObjectLine : STRING_PORTAL portalAttributes+ ;

// attribute lines
boardAttributes : attributeName | attributeGravity | attributeFriction1 | attributeFriction2 ;
ballAttributes : attributeName | attributeX | attributeY | attributeXVelocity | attributeYVelocity ;
squareBumperAttributes : attributeName | attributeX | attributeY ;
triangleBumperAttributes : attributeName | attributeX | attributeY | attributeOrientation ;
circleBumperAttributes : attributeName | attributeX | attributeY ;
leftFlipperAttributes : attributeName | attributeX | attributeY | attributeOrientation ;
rightFlipperAttributes : attributeName | attributeX | attributeY | attributeOrientation ;
absorberAttributes : attributeName | attributeX |attributeY | attributeWidth | attributeHeight ;
fireAttributes : attributeName | attributeTrigger | attributeAction ;
keydownAttributes : attributeKey | attributeAction ;
keyupAttributes : attributeKey | attributeAction ;
portalAttributes : attributeName | attributeX | attributeY | attributeOtherboard | attributeOtherportal ;

// attribute
attributeName : STRING_NAME EQUALS NAME ;
attributeGravity : STRING_GRAVITY EQUALS NUMBER ;
attributeFriction1 : STRING_FRICTION1 EQUALS NUMBER ;
attributeFriction2 : STRING_FRICTION2 EQUALS NUMBER ;
attributeX : STRING_X EQUALS NUMBER ;
attributeY : STRING_Y EQUALS NUMBER ;
attributeXVelocity : STRING_XVELOCITY EQUALS NUMBER ;
attributeYVelocity : STRING_YVELOCITY EQUALS NUMBER ;
attributeOrientation : STRING_ORIENTATION EQUALS NUMBER ;
attributeTrigger : STRING_TRIGGER EQUALS NAME ;
attributeAction : STRING_ACTION EQUALS NAME ;
attributeWidth : STRING_WIDTH EQUALS NUMBER ;
attributeHeight : STRING_HEIGHT EQUALS NUMBER ;
attributeKey : STRING_KEY EQUALS NAME ;
attributeOtherboard : STRING_OTHERBOARD EQUALS NAME ;
attributeOtherportal : STRING_OTHERPORTAL EQUALS NAME ;