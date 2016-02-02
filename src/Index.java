import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.TreeMap;


public class Index {

	Scanner reader;
	String[] stopList= {"a","able","about","above","abst","accordance","according","accordingly","across","act","actually","added","adj","affected","affecting","affects","after","afterwards","again","against","ah","all","almost","alone","along","already","also","although","always","am","among","amongst","an","and","announce","another","any","anybody","anyhow","anymore","anyone","anything","anyway","anyways","anywhere","apparently","approximately","are","aren","arent","arise","around","as","aside","ask","asking","at","auth","available","away","awfully","b","back","be","became","because","become","becomes","becoming","been","before","beforehand","begin","beginning","beginnings","begins","behind","being","believe","below","beside","besides","between","beyond","biol","both","brief","briefly","but","by","c","ca","came","can","cannot","can't","cause","causes","certain","certainly","co","com","come","comes","contain","containing","contains","could","couldnt","d","date","did","didn't","different","do","does","doesn't","doing","done","don't","down","downwards","due","during","e","each","ed","edu","effect","eg","eight","eighty","either","else","elsewhere","end","ending","enough","especially","et","et-al","etc","even","ever","every","everybody","everyone","everything","everywhere","ex","except","f","far","few","ff","fifth","first","five","fix","followed","following","follows","for","former","formerly","forth","found","four","from","further","furthermore","g","gave","get","gets","getting","give","given","gives","giving","go","goes","gone","got","gotten","h","had","happens","hardly","has","hasn't","have","haven't","having","he","hed","hence","her","here","hereafter","hereby","herein","heres","hereupon","hers","herself","hes","hi","hid","him","himself","his","hither","home","how","howbeit","however","hundred","i","id","ie","if","i'll","im","immediate","immediately","importance","important","in","inc","indeed","index","information","instead","into","invention","inward","is","isn't","it","itd","it'll","its","itself","i've","j","just","k","keep","keeps","kept","kg","km","know","known","knows","l","largely","last","lately","later","latter","latterly","least","less","lest","let","lets","like","liked","likely","line","little","'ll","look","looking","looks","ltd","m","made","mainly","make","makes","many","may","maybe","me","mean","means","meantime","meanwhile","merely","mg","might","million","miss","ml","more","moreover","most","mostly","mr","mrs","much","mug","must","my","myself","n","na","name","namely","nay","nd","near","nearly","necessarily","necessary","need","needs","neither","never","nevertheless","new","next","nine","ninety","no","nobody","non","none","nonetheless","noone","nor","normally","nos","not","noted","nothing","now","nowhere","o","obtain","obtained","obviously","of","off","often","oh","ok","okay","old","omitted","on","once","one","ones","only","onto","or","ord","other","others","otherwise","ought","our","ours","ourselves","out","outside","over","overall","owing","own","p","page","pages","part","particular","particularly","past","per","perhaps","placed","please","plus","poorly","possible","possibly","potentially","pp","predominantly","present","previously","primarily","probably","promptly","proud","provides","put","q","que","quickly","quite","qv","r","ran","rather","rd","re","readily","really","recent","recently","ref","refs","regarding","regardless","regards","related","relatively","research","respectively","resulted","resulting","results","right","run","s","said","same","saw","say","saying","says","sec","section","see","seeing","seem","seemed","seeming","seems","seen","self","selves","sent","seven","several","shall","she","shed","she'll","shes","should","shouldn't","show","showed","shown","showns","shows","significant","significantly","similar","similarly","since","six","slightly","so","some","somebody","somehow","someone","somethan","something","sometime","sometimes","somewhat","somewhere","soon","sorry","specifically","specified","specify","specifying","still","stop","strongly","sub","substantially","successfully","such","sufficiently","suggest","sup","sure","t","take","taken","taking","tell","tends","th","than","thank","thanks","thanx","that","that'll","thats","that've","the","their","theirs","them","themselves","then","thence","there","thereafter","thereby","thered","therefore","therein","there'll","thereof","therere","theres","thereto","thereupon","there've","these","they","theyd","they'll","theyre","they've","think","this","those","thou","though","thoughh","thousand","throug","through","throughout","thru","thus","til","tip","to","together","too","took","toward","towards","tried","tries","truly","try","trying","ts","twice","two","u","un","under","unfortunately","unless","unlike","unlikely","until","unto","up","upon","ups","us","use","used","useful","usefully","usefulness","uses","using","usually","v","value","various","'ve","very","via","viz","vol","vols","vs","w","want","wants","was","wasnt","way","we","wed","welcome","we'll","went","were","werent","we've","what","whatever","what'll","whats","when","whence","whenever","where","whereafter","whereas","whereby","wherein","wheres","whereupon","wherever","whether","which","while","whim","whither","who","whod","whoever","whole","who'll","whom","whomever","whos","whose","why","widely","willing","wish","with","within","without","wont","words","world","would","wouldnt","www","x","y","yes","yet","you","youd","you'll","your","youre","yours","yourself","yourselves","you've","z","zero"};
	static HashMap<String, LinkedList<Integer>> tempDict=new HashMap<String,LinkedList<Integer>>();
	String[] docs;
	static Stemmer stemmer=new Stemmer();
	static TreeMap<String,LinkedList<Integer>> dict;

	private int size;//size of dictionary 
	static final int k=2;//partition 
	static ArrayList<Integer> kArray;//dynamic array with resize

	static String res;
	public Index(){}
	static String getRes(){
		if(res!=null){
			return res;
		}else{
			throw new NullPointerException("Not initialized dict");
		}
	}
	public void createIndex(String[] args){
		String word="";//used for a scanned token
		String term="";
		File f;
		docs=new String[args.length];
		docs=args;
		for(int i=0;i<docs.length;i++){
			f=new File(docs[i]);
			try {
				reader=new Scanner(f);
				while (reader.hasNext()){
					word=reader.next().toLowerCase();
					if(!removeStopWords(stopList,word)){
						term=word;
						term = term.replaceAll("[^a-zA-Zà-ÿÀ-ß\\s]", "");
						if(stemming(term)!=null){
							word=stemming(term);}
						if(term==null)throw new NullPointerException("Term is NULL");
						//if(temptemptempDict==null)throw new NullPointerException("temptemptempDict is NULL");
						if(tempDict==null)tempDict=new HashMap<String,LinkedList<Integer>>();
						if(tempDict.containsKey(term)){
							if(!tempDict.get(term).contains(i)){
								tempDict.get(term).add(i);}

						}else{
							LinkedList<Integer> list=new LinkedList<Integer>();
							list.add(i);
							tempDict.put(term,list);
							size++;

						}}}


			} catch (FileNotFoundException e) {
				System.err.println("File not found!!");
			}}

		dict=new TreeMap<String,LinkedList<Integer>>(tempDict);
		
		
		
		printEncoded(dict);

	}



	public static HashMap<String,LinkedList<Integer>> getTermDoc(){
		return tempDict;

	}
	private static String stemming(String word){
		for(int i=0;i<word.length();i++){
			stemmer.add(word.charAt(i));//create char[] for stemming;


		}stemmer.stem();
		String result=stemmer.toString();
		return result;
	}


	/*private static String encodeString(TreeMap<String,LinkedList<Integer>> dict){
		kArray=new DArray();
		resultTermString="";
		
		int i=0;
		for(String s:dict.keySet()){

			resultTermString+=s.length()+s;
			if(i%k==0){
				kArray.add(resultTermString.length()-s.length()-1);
			}

			i++;


		}
		return resultTermString;
	}*/
	

	public static  String query(String query,String tempDict){
		String result="word is absent";
		String cmp="";
		boolean guessed=false;
		int valueOfLength;
		
		for(int i=0;i<kArray.size();i++){
			//System.out.println("k:"+kArray.get(i));
			cmp="";//cmp used each time to compare two strings:query and read;
			valueOfLength=Character.getNumericValue(tempDict.charAt(kArray.get(i)));
			//System.out.println("value of length"+valueOfLength);
			if(valueOfLength==0){
				i++;
				continue;
			}
			for(int j=0;j<valueOfLength;j++){
				cmp+=tempDict.charAt(kArray.get(i)+j+1);
				//System.out.println(cmp);
				//cmp+=result.charAt(j);
			}if (cmp.compareTo(query)>0&& guessed==false){
				guessed=true;

				return  blockSearch(query,tempDict,i-1);
				
			}if(cmp.compareTo(query)==0){
				return cmp;
			}
		}
		return result;

	}

	public static String   blockSearch(String query,String tempDict,int pos){//search in block with k size using starting position as a cursor and value of length for reading each string
		
		String result="";
int valueOfLength;
		String cmp="";
		for(int i=0;i<k;i++){//for all in that block

			valueOfLength=Character.getNumericValue(tempDict.charAt(pos));//read numvalue of length of string
		if(Character.getNumericValue(tempDict.charAt(pos+1))<10){//if value more than 10 but less than 100;
		valueOfLength+=	Character.getNumericValue(tempDict.charAt(pos+1));
				pos++;//shift start position by one;
		}
			pos++;

			for(int j=pos;j<pos+valueOfLength;j++){//for an interval[pos.....pos+valueOfLenght);
				cmp+=tempDict.charAt(j);


			}if(cmp.compareTo(query)==0){result+=cmp;return result;}else{
				cmp="";
			}
			pos+=valueOfLength;

		}
		return result;
	}

	private static boolean removeStopWords(String [] stoplist,String word){
		for(int i=0;i<stoplist.length;i++){
			if(stoplist[i].equals(word))
				return true;
		}return false;}
	private void printEncoded(TreeMap<String,LinkedList<Integer>> dict){

	
		FileOutputStream fop = null;
		File file;
		kArray=new ArrayList<Integer>();
		 String resultTermString="";
		
		int i=0;
		for(String s:dict.keySet()){

			resultTermString+=s.length()+s;
			if(i%k==0){
				kArray.add(resultTermString.length()-s.length()-1);
				//System.out.println(resultTermString.length()-s.length()-1);
			}
			for(int j:dict.get(s)){
				resultTermString+=j+",";
			}
			
			i++;

		}
		res=resultTermString;
			

		
		

		try {
			long valueOfDict;
			file = new File("d:/ir6.txt");
			fop = new FileOutputStream(file);
			if (!file.exists()) {
				file.createNewFile();
			}byte[] contentInBytes = resultTermString.getBytes();

			fop.write(contentInBytes);
			valueOfDict=file.length();
			fop.flush();
			fop.close();
			System.out.println("Space of tempDictionary"+valueOfDict);



		}catch(IOException e ){

		}
		finally{};
	}

}









