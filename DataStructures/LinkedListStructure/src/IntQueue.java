

import java.io.PrintStream;
import java.util.NoSuchElementException;

/**
 * Interface for a FIFO queue that handles integers with Javadoc-type comments
 */
public interface IntQueue {
	/**
	 * Check if the queue is empty
	 * @return true if the queue is empty
	 */
	public boolean isEmpty();

	/**
	 * Insert an integer to the queue
	 * @param item the element to be inserted
	 */
	public void put(int item);

	/**
	 * Remove and return the oldest item of the queue
	 * @return The item removed
	 * @exception NoSuchElementException if the queue is empty
	 */
	public int get() throws NoSuchElementException;

	/**
	 * Return without removing the oldest item of the queue
	 * @return The oldest item of the queue
	 * @exception NoSuchElementException if the queue is empty
	 */
	public int peek() throws NoSuchElementException;

	/**
	 * Print the elements of the queue, starting from the oldest item, to the
	 * print stream given as argument. For example, pass System.out as parameter
	 * for standard output
	 * @param stream The printstream where we want to print
	 */
	public void printQueue(PrintStream stream);

	/**
	 * Return the size of the queue, 0 if it is empty
	 * @return Number of elements in the queue
	 */
	public int size();
}
