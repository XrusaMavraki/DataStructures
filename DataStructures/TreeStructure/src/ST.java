import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.StringTokenizer;

//Χρυσάνθη Τσαμπίκα Μαυράκη 3130128 
//Ελευθέριος Χατζηαράπης     3130225 
public class ST {

	private class TreeNode{
		WordFreq item; //
		TreeNode l=null ;// pointer to left subtree
		TreeNode r=null; // pointer to right subtree
		private int N=0; //number of nodes in the subtree starting at this TreeNode 

		public TreeNode(WordFreq item){
			this.item=item;
		}
		public TreeNode(WordFreq item,TreeNode l,TreeNode r){
			this(item);
			setLeft(l);
			setRight(r);
		}
		public String toString(){
			return item.toString();
		}
		public void setLeft(TreeNode l){
			this.l=l;
		}
		public void setRight(TreeNode r){
			this.r=r;
		}
		private int calcN(TreeNode h){


			if(h==null) return 0;

			return 1+ calcN(h.l) + calcN(h.r);
		}
		public int getN(){
			this.N=calcN(this);
			return N;
		}

	}

	public ST() {}
	public ST(ST src) {
		head = copyNodes(src.head);
	}
	
	private TreeNode copyNodes(TreeNode root) {
		if (root == null) return null;
		TreeNode leftChildCopy = copyNodes(root.l);
		TreeNode rightChildCopy = copyNodes(root.r);
		return new TreeNode(root.item, leftChildCopy, rightChildCopy);
	}
	
	private TreeNode head; //ρίζα στο δέντρο
	private StringDoubleEndedQueueImpl stopwords = new StringDoubleEndedQueueImpl(); // λίστα µε τα stopwords

	WordFreq search(String w) {
		WordFreq f= findRecursive(head,w);

		if(f==null)return null;
		if(f.getfrequency()>(int)getMeanfrequency()){
			remove(f.key());

			insert(f);
			return f;
		}
		return f;


	}


	private WordFreq findRecursive(TreeNode p, String element){
		if (p == null)
			return null;
		int result= element.compareTo( p.item.key());
		if (result == 0)
			return p.item;
		if (result<0)
			return findRecursive(p.l, element);
		else
			return findRecursive(p.r, element);
	}

	private int calcFrequency(TreeNode h){
		if (h==null )return 0;
		return h.item.getfrequency() + calcFrequency(h.l)+ calcFrequency(h.r);
	}

	void insert(WordFreq item){

		head = insertR(head, item); 
	}

	private TreeNode insertR(TreeNode h, WordFreq x) {
		if (h == null) return new TreeNode(x);
		if ((x.key().compareTo( h.item.key())<0))
			h.l = insertR(h.l, x);
		else h.r = insertR(h.r, x);
		return h; 
	}

	void update(String w){
		WordFreq f=search(w);
		if(f==null){
			WordFreq fr= new WordFreq(w,1);
			insert(fr);

		}
		else
		{
			int fr= f.getfrequency();
			f.setfrequency(++fr);
		}



	}
	void remove(String w) {
		head = rem(w, head);
	}


	private TreeNode rem(String w, TreeNode temp){

		if(temp==null) return null;
		if(temp.item.key().compareTo(w)<0){
			temp.r = rem(w, temp.r);
		}
		else if(temp.item.key().compareTo(w)>0){
			temp.l = rem(w, temp.l);
		}
		else if(temp.item.key().compareTo(w)==0){

			temp = joinLR(temp.l, temp.r);
		}
		return temp;

	}
	private TreeNode joinLR(TreeNode left, TreeNode right) {
		if (right == null) return left;
		right = partR(right, 0); //διαµέριση µε k=0
		right.l = left; //το a θα γίνει το αριστερό υποδέντρο του b
		return right; 
	} 

	private TreeNode partR(TreeNode h, int k) {// µε όρισµα k πάει το (k+1)-οστό στη ρίζα
		int t = (h.l == null) ? 0 : h.l.getN();
		if (t > k) {
			h.l = partR(h.l, k);
			h = rotR(h); 
		}
		if (t < k) {
			h.r = partR(h.r, k-t-1);
			h = rotL(h); 
		}
		return h; 
	} 

	private TreeNode rotR(TreeNode h) {
		TreeNode x = h.l; 
		h.l = x.r; 
		x.r = h; 
		return x; 
	}
	private TreeNode rotL(TreeNode h) {
		TreeNode x = h.r;
		h.r = x.l; 
		x.l = h;
		return x; 
	} 

	void load(String filename){
		File file = new File(filename);
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(file));
			String line = null;
			while((line = reader.readLine()) != null){
				String[] words = line.split(" ");
				for (String word : words) {
					word = word.toLowerCase();
					if (stopwords.contains(word)) continue;
					if (containsNumber(word)) continue;
					word = removePunctuation(word);
					if (word.equals("")) continue;
					update(word);
				}
			}

		} catch (IOException e){
			e.printStackTrace();
		}

	}
	
	// checks if the word contains any Number
	// returns true if it does
	private boolean containsNumber(String word) {
		for (Character ch : word.toCharArray()) {
			if (Character.isDigit(ch)) return true;
		}
		return false;
	}
	
	// removes punctuation except apostrophi
	private String removePunctuation(String word) {
		String nWord = "";
		for (Character ch : word.toCharArray()) {
			if (!Character.isLetter(ch) && ch != '\'') continue;
			nWord = nWord + ch;
		}
		return nWord;
	}

	int getTotalWords(){
		return countT(head);
	}

	private int countT(TreeNode h) {
		if(h == null) return 0;
		return h.item.getfrequency() + countT(h.l) + countT(h.r);
	}
	int getDistinctWords(){
		return head.getN();
	}
	public int getFrequency(String w){
		WordFreq f= search(w);
		if(f==null)return 0;
		else return f.getfrequency();

	}
	WordFreq getMaximumfrequency(){
		return traverseR(head);
	}
	private  WordFreq traverseR(TreeNode h) {
		if (h == null) return null;
		WordFreq max = h.item;
		
		WordFreq maxLeft = traverseR(h.l);
		WordFreq maxRight = traverseR(h.r);
		
		if (maxLeft != null && maxLeft.getfrequency() > max.getfrequency()) max = maxLeft;
		if (maxRight != null && maxRight.getfrequency() > max.getfrequency()) max = maxRight;
		
		return max;
	}
	//void traverse() {return traverseR(head); } 


	double getMeanfrequency(){
		int fr= calcFrequency(head);
		head.getN();
		return (double)(fr/head.N);
	}

	void addStopWord(String w){
		stopwords.addFirst(w);
	}
	void removeStopWord(String w){
		stopwords.remove(w);
	}
	void printΤreeAlphabetically(PrintStream stream){
		inorder(head,stream);

	}
	void printΤreeByfrequency(PrintStream stream){
		ST temp = new ST(this);
		while(temp.head!=null){
			WordFreq fr=temp.getMaximumfrequency();
			stream.println(fr);
			temp.remove(fr.key());
		}
	}

	private void inorder(TreeNode node,PrintStream stream) {
		if (node==null)
			return;
		inorder(node.l,stream);
		stream.println(node.toString()); 
		inorder(node.r,stream);
	}

}
