import java.util.Arrays;

public class PQ<T extends PQEntry<T,Y>, Y extends PQKey<Y>> {
	private T[] heap;
	private int size1;
	public void insert(T flight) {
		// Ensure flight is not null
		if (flight == null) throw new IllegalArgumentException();
		// Check available space
		if (size1 == heap.length-1){
			T[] heap1=(T[])(  new PQEntry[heap.length+20]);
			heap1=Arrays.copyOf(heap, heap.length);
			heap=heap1;
			
		}
		// Place object at the next available position

		flight.getKey().setPos(++size1);
		heap[size1] = flight;

		// Let the newly added object swim
		swim(size1);

	}
	public T Max() {
		if(isEmpty()) throw new IllegalStateException("Ôhe PQ is empty");
		return heap[1];
	}
	public T getMax() {
		// Ensure not empty
		if (isEmpty()) throw new IllegalStateException();
		// Keep a reference to the root object
		T flight = heap[1];
		// Replace root object with the one at rightmost leaf
		if (size1 > 1) heap[1] = heap[size1];
		// Dispose the rightmost leaf
		heap[size1--] = null;
		// Sink the new root element
		sink(1);
		flight.getKey().setPos(-1);
		// Return the object removed
		return flight;
	}
	private void swim(int i) {
		while (i > 1) {  //if i root (i==1) return
			int p = i/2;  //find parent
			int result =heap[i].compareTo(heap[p]);  //compare parent with child
			if (result <= 0)return;
			swap(i, p);                 //else swap and i=p
			i = p;

		}

	}
	private void sink(int i){
		int left = 2*i, right = left+1, max = left;
		// If 2*i >= size1, node i is a leaf
		while (left <= size1) {
			// Determine the largest children of node i
			if (right <= size1) {
				max = heap[left].compareTo(heap[right]) < 0 ? right : left;
			}
			// If the heap condition holds, stop. Else swap and go on.
			if (heap[i].compareTo(heap[max]) >= 0) return;
			swap(i, max);
			i = max; left = 2*i; right = left+1; max = left;
		}
	}
	private void swap(int i, int j) {
		T tmp = heap[i];
		heap[i] = heap[j];
		heap[j] = tmp;
		int temp= heap[i].getKey().getPos();
		heap[i].getKey().setPos(heap[j].getKey().getPos());
		heap[j].getKey().setPos(temp);

	}
	public int size() {
		return size1;
	}
	public	void updateKey(T x, Y k){
		int pos=x.getKey().getPos();
		Y temp= x.getKey();

		x.setKey(k);
		if((x.getKey().compareTo(temp)<0)){
			sink(pos);
		}
		else{swim(pos);}
	}





	public T remove(Y k) {

		if(k.getPos()==-1){return null;}
		T f=heap[k.getPos()];
		Y knew= k.pushToTop();
		swim(knew.getPos());
		return getMax();
		

	}



	public boolean isEmpty(){
		return size1 == 0;
	}

	@SuppressWarnings("unchecked")
	public PQ(int capacity) {
		if (capacity < 1) throw new IllegalArgumentException();
		this.heap = (T[]) new PQEntry[capacity+1];
		this.size1 = 0;

	}



}
