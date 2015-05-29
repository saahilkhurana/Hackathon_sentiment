import edu.stanford.nlp.tagger.maxent.MaxentTagger;
import java.io.IOException;

public class TagText {
	public String stringtagger(String sample, MaxentTagger tagger)throws IOException,
	ClassNotFoundException
	{
	  String tagged = tagger.tagString(sample);
	  return tagged;
	}
}
