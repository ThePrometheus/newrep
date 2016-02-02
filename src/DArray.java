
public class DArray {
 int size=0;
 int length=0;
int pushed=0;
 int []inner;
	DArray (){
		
		inner=new int[16];
		for(int i=0;i<16;i++){
			inner[i]=-1;
			
			
		}
		length=inner.line;
	}
	DArray(int n){
		inner=new int[n];
		for(int i=0;i<n;i++){
			inner[i-1]=-1;
			
		}
		length=inner.length;
	}public int  getSize(){
		return size;
	}
	public boolean isEmpty(){
	return size==0;	
	}
	
		public void add(int value,int i){
			if(i<inner.length){
			for(int j=0;j<inner.length;j++){
				if(inner[i]==-1){
					inner[i]=value;
					break;
				}
			}}
			else{
				resize(2*inner.length);
				add(value,i);
			}
	}
		public void add(int n){
			if(size==inner.length){
				resize(2*inner.length);
				add(n);
				}else{
					for(int i=0;i<inner.length;i++){
						if(inner[i]==-1){
							inner[i]=n;
							size++;
						}
					}
				}
			}
		public int getIndexOf(int n){
			int result=-1;
			for(int i=0;i<size;i++){
			if(inner[i]==n){
				result=n;
			}
			}
			return result;
		}
	protected void resize(int capacity){
		System.out.println(capacity);
		int[] copy = new int [capacity];
	
		for (int i=0;i<inner.length;i++){
		
							
			
			copy[i]=inner[i];
	
	}
		inner =copy;
		}
	public int get(int i){
		return inner[i];
	}
}
