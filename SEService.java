package SearchEngine;


import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import javax.swing.JTextArea; 

public class SEService {
	public static String matchingFile(String filename, String regex) {
		String temp = filename;
		
		Pattern p = Pattern.compile(regex);
		Matcher m;
		m = p.matcher(temp);
	    if(m.matches()) {
	    	return temp;
	    }
	    return null;
	}
	
	public static void matchingText(String str, String regex, JTextArea text) {
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(str);
		while(m.find()) {
			if(m.group().length()!=0) {
				SEService.printToTextArea(text, str);
			}
		}
	}
	
	public static void printToTextArea(JTextArea text, String str) {
		text.append(str);
	}
	
	public static boolean regexCheck(String regex) {
		boolean state=false;
	    PatternSyntaxException exc = null;
	    try {
	        Pattern.compile(regex);
	    } catch (PatternSyntaxException e) {
	        exc = e;
	    }
	    if (exc != null) {
	        state=false;
	        return state;
	    } else {
	        state=true;
	        return state;
	    }
	}
	
	public static int sizeOfMatchingFiles(String[] filename, String regex) {
		int size=0;
		
		Pattern p = Pattern.compile(regex);
		Matcher m;
		for(int i=0; i<filename.length;i++) {
		    m = p.matcher(filename[i]);
		    if(m.matches()) {
		    	size++;
		    }
		}
		return size;
	}
}
