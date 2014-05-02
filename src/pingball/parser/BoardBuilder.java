package pingball.parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.CharBuffer;

import pingball.board.Board;

/**
 * Parses board definition into built boards.
 */
public class BoardBuilder {
	public static Board buildBoard(File source) {
	    try {
	        BufferedReader br = new BufferedReader(new FileReader(source.getPath()));
    		//FileReader reader = new FileReader(source);
	        /*String s = br.readLine();
	        while (s != null) {
	            System.out.println(s);
	            s = br.readLine();
	            
	        }*/
	        char[] chs = new char[(int)source.length()];
	        br.read(chs);
	        return BoardFactory.parse( String.copyValueOf(chs));/*;
    		CharBuffer buffer = CharBuffer.allocate((int)source.length());
    		br.read(buffer);
            String contents = buffer.toString();
    		br.close();
    		return BoardFactory.parse(contents);*/
	    }
	    catch (IOException ex){ 
	        ex.printStackTrace();
	        return null;
	    }
	}
}
