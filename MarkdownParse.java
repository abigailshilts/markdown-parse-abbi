// File reading code from https://howtodoinjava.com/java/io/java-read-file-to-string-examples/
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class MarkdownParse {
    public static ArrayList<String> getLinks(String markdown) {
        ArrayList<String> toReturn = new ArrayList<>();
        // find the next [, then find the ], then find the (, then take up to
        // the next )
        int currentIndex = 0;
        boolean foundParen = true;
        while(currentIndex < markdown.length() && foundParen) {
            int nextOpenBracket = markdown.indexOf("[", currentIndex);
            int nextCloseBracket = markdown.indexOf("]", nextOpenBracket);
            int openParen = markdown.indexOf("(", nextCloseBracket);
            int closeParen = markdown.indexOf(")", openParen);
            if(!markdown.substring(closeParen+1, markdown.length()).contains(")")){
                foundParen = false;
            }
            if(markdown.substring(openParen+1, closeParen).contains(".") &&
            !markdown.substring(openParen+1, closeParen).contains(" ") &&
            (nextOpenBracket == 0 ||
            !markdown.substring(nextOpenBracket - 1, nextOpenBracket).contains("!"))){
                toReturn.add(markdown.substring(openParen + 1, closeParen));
            }
            currentIndex = closeParen + 1;
            System.out.println("in loop");


        }
        return toReturn;
    }
    public static void main(String[] args) throws IOException {
		Path fileName = Path.of(args[0]);
	    String contents = Files.readString(fileName);
        ArrayList<String> links = getLinks(contents);
        System.out.println(links);
    }
}