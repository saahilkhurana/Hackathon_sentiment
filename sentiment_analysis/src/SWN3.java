import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.Vector;


public class SWN3 {
	public String pathToSWN = "SentiWordNet_3.0.0_20130122.txt";
	   //public String pathToSWN ="tester.txt";
		private HashMap <String,Double> _dict;
		public Double score (String testword, String type)
		{
			System.out.println("test word :"+testword+"type: "+type);
			double _score = 0.0;		
			int flag =0;
			 _dict = new HashMap<String, Double>();
			HashMap<String, Vector<Double>> _temp = new HashMap<String, Vector<Double>>();
			try{
				BufferedReader csv = new BufferedReader(new FileReader(pathToSWN));
				String line = "";
				while((line = csv.readLine())!= null )
				{
					// System.out.println("Im in while loop");
					String[] data = line.split("\t");
					Double score = Double.parseDouble(data[2])-Double.parseDouble(data[3]);
					//System.out.println("intermediate score is  "+score);
					String words[] = data[4].split(" ");
					for(String w:words)
					{
						//System.out.println(" the words array is:"+w);
						String []w_n = w.split("#");
						if(w_n[0].equals(testword) && data[0].equals(type))
						{
							System.out.println("test word is "+testword+" type is "+type);
							w_n[0] +="#"+data[0];  // word followed by the type say pathetic#a
							int index = Integer.parseInt(w_n[1])-1;
							if(_temp.containsKey(w_n[0]))
							{
								Vector <Double> v = _temp.get(w_n[0]);
								if(index > v.size())
									for(int i= v.size() ; i<index ; i++)
										v.add(0.0);
								v.add(index, score);
								_temp.put(w_n[0], v);
							}
							else
							{
								//System.out.println("in else");
								Vector <Double> v =new Vector<Double>();
								for(int i=0;i<index;i++)
									v.add(0.0);
								v.add(index,score);
								
							//	for(int i=0;i<=index;i++)
							//	System.out.println("vector is:"+v.get(i));
								
								_temp.put(w_n[0], v);
							}
							flag = 1;
							break;
						}
					}
					if(flag ==1)
						break;
				}
				Set <String> temp = _temp.keySet();
				for(Iterator<String> iterator = temp.iterator(); iterator.hasNext();)
				{
					String word = (String) iterator.next();
					Vector <Double> v = _temp.get(word);
					_score = 0.0;
					double sum = 0.0;
					for(int i=0;i<v.size();i++)
					{
						_score += ((double)1/(double)(i+1))*v.get(i);
						//System.out.println("_score in SWN3 is"+_score);
					}
					for(int i=1;i<= v.size();i++)
					{
						sum += (double)1/(double)i;
					//	System.out.println("_sum in SWN3 is"+sum);
					}	
					_score /= sum;
					System.out.println("the score after dividing by sum is "+_score);
					_dict.put(word, _score);
				}
			}
			catch(Exception e)
			{
				
			}
			return _score;
		}
	}

