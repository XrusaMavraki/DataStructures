
import java.io.PrintStream;
import java.util.NoSuchElementException;

/**
 * Defines the methods for a Double-ended Queue that handles integers
 */
public interface IntDoubleEndedQueue {
	/**
	 * Check if the queue is empty
	 * @return true if the queue is empty
	 */
	public boolean isEmpty();

	/**
	 * Insert an integer at the front of the queue
	 * @param item the element to be inserted
	 */
	public void addFirst(int item);

	/**
	 * Remove and return an integer from the front of the queue
	 * @return The item removed
	 * @exception NoSuchElementException if the queue is empty
	 */
	public int removeFirst() throws NoSuchElementException;

	/**
	 * Insert an integer at the end of the queue
	 * @param item the element to be inserted
	 */
	public void addLast(int item);

	/**
	 * Remove and return an integer from the end of the queue
	 * @return The item removed
	 * @exception NoSuchElementException if the queue is empty
	 */
	public int removeLast() throws NoSuchElementException;

	/**
	 * Return without removing the first item in the queue
	 * @return The item at the front of the queue
	 * @exception NoSuchElementException if the queue is empty
	 */
	public int getFirst() throws NoSuchElementException;

	/**
	 * Return without removing the last item in the queue
	 * @return The item at the end of the queue
	 * @exception NoSuchElementException if the queue is empty
	 */
	public int getLast() throws NoSuchElementException;

	/**
	 * Print the elements of the queue, starting from the front, to the print
	 * stream given as argument. For example, to print the elements to the
	 * standard output, pass System.out as parameter. E.g. printQueue(System.out);
	 * @param stream The printstream where we want to print
	 */
	public void printQueue(PrintStream stream);

	/**
	 * Return the size of the queue, 0 if it is empty
	 * @return Number of elements in the queue
	 */
	public int size();
}
