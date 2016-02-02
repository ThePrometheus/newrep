import java.io.File;
import java.util.LinkedList;
import java.util.List;


public class Tester {
public static void main(String[] args){
	String foldername="D:/Akunin_[tfile.ru]/Æàíðû";
	File folder= new File(foldername);
	 File files[]=folder.listFiles();
	 String[] filenames= new String[files.length];
	 for(int i=0;i<files.length;i++){
		 filenames[i]=foldername+"\\"+files[i].getName();
	 }
	 Index index=new Index();
	 for(int i=0;i<filenames.length;i++){
		System.out.println(filenames[i]); 
	 }
	 String query="sky";
	 index.createIndex(filenames);
	System.out.println("result:"+ index.query(query, index.getRes()));
	
	

}
}