import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import edu.stanford.nlp.tagger.maxent.MaxentTagger;
public class test {
public static void main(String [] args) throws Exception
{
//	Polarity pol = new Polarity();
	MaxentTagger tagger = new MaxentTagger("taggers/english-left3words-distsim.tagger");
	
	//Read the contents of the file
	BufferedReader br=null;
	StringBuilder sb = new StringBuilder();
	PrintWriter out = null;
	
	String sCurrentLine;
	 
	try {
	br = new BufferedReader(new FileReader("Hackathoninput.txt"));
	out = new PrintWriter("Hackathonoutput.txt");

	while ((sCurrentLine = br.readLine()) != null) {
		System.out.println(sCurrentLine);
 
	// The tagged string
//	String tagged = tagger.tagString(sample);
	
	String inp ="";
//String tweettext ="my samsung mobile is not awesome";
	
	//System.out.println("Input: " + sample);
	//System.out.println("Output: "+ tagged);

	
	//sb.append(sCurrentLine);
	int resultscore = Polarity.getPolarity(sCurrentLine, inp , tagger);
	
	if(resultscore == 0)
	{	
		System.out.println("neutral");
		sb.append("neutral");
	}	
	else if(resultscore >0)
	{
		//System.out.println(tweettext);
	    System.out.println("positive");
	    sb.append("positive");
	    
	}
	else{
		//System.out.println(tweettext);
		System.out.println("negative");
		sb.append("negative");
	}
	sb.append("\n");
	} // end of while
	String output = "";
	if(sb != null)
		output = sb.toString();
	System.out.println("Output string is \n"+ output);
	String updatedText = output.replaceAll("\n", System.lineSeparator());
		out.println(updatedText);
		out.close();
	}// try
	catch (Exception e){
		e.printStackTrace();
	}
	finally {
		try {
			if (br != null)
				br.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	
}
}
