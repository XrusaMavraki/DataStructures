
import java.util.*;

public class QueueSimulation {
	static final int M = 3;
	static IntQueueImpl[] fifo = new IntQueueImpl[M];
	private int sum = 0;
	private int totalPeople = 0;
	private int max = 0;

	public QueueSimulation() {
		for (int i = 0; i < M; i++) {
			IntQueueImpl obj = new IntQueueImpl();
			fifo[i] = obj;
		}
	}

	public static void main(String[] args) {

		QueueSimulation qs = new QueueSimulation();
		Scanner input = new Scanner(System.in);
		System.out.println("Write in an order how many people come and when. For example 0013 means 2 people come in time 0 1 in time 1 and 1 in time 3");

		String buffer = input.nextLine();
		input.close();
		String[] inputValues= buffer.split(" ");
		try{
			int previousNumber=Integer.parseInt(inputValues[0]); // the previous number will check if the input is correctly entered
			for(String str: inputValues){
				if (Integer.parseInt(str)<previousNumber){
					System.out.println("the  input you gave is wrong.The times you entered are not in order. Please try again");
					return;
				}
				previousNumber=Integer.parseInt(str);
			}
		}catch (NumberFormatException e){
			System.out.println("The input you gave is not a number ");
			return; 
		}

		int counter = 1;
		int a = Integer.parseInt(inputValues[0]);
		for (int i=1;i<inputValues.length;i++) {
			int currentNumber=Integer.parseInt(inputValues[i]);
			if (currentNumber == a) {
				counter++;
			} 

			else {
				qs.removeFromQueue(currentNumber);
				int timereturn = qs.addtoQueue(counter, a);
				if (timereturn > qs.max) {
					qs.max = timereturn;
				}
				qs.totalPeople += counter;
				counter = 1;
				a = currentNumber;
			}
		}
		qs.totalPeople += counter;
		int timereturn = qs.addtoQueue(counter, a);
		if (timereturn > qs.max) {
			qs.max = timereturn;
		}
		qs.printInfo();
	}

	public int addtoQueue(int counter, int when) {
		int time = 0;
		for (int j = 0; j < counter; j++) {
			String s = "0 ";
			int min = fifo[0].size();
			for (int i = 1; i < M; i++) {
				if (min > fifo[i].size()) {
					min = fifo[i].size();
					s = i + " ";
				} else if (min == fifo[i].size()) {
					s += i + " ";
				}
			}

			String[] st = s.trim().split(" ");
			int i;
			if (st.length == 1) {
				i = Integer.parseInt(st[0]);

			} else {

				Random ran = new Random();
				i = Integer.parseInt(st[ran.nextInt(st.length - 1)]);

			}
			int lastPersonLeaves;
			if (fifo[i].isEmpty()) {
				lastPersonLeaves = when;
			} else {
				lastPersonLeaves = (fifo[i].size() - 1) * 4 + fifo[i].peek();
			}

			time = lastPersonLeaves + 4;

			sum += time - when;
			fifo[i].put(time);

			
		}
		return time;
	}

	private void removeFromQueue(int time) {
		for (int i = 0; i < M; i++) {

			while(!fifo[i].isEmpty() && fifo[i].peek()<=time){
						fifo[i].get();
				

			}
		}
	}

	private void printInfo() {
		System.out.println("Finished all clients at time t = " + max);
		double waitingTime = (double) sum / totalPeople;
		System.out.println("Average waiting time = " + waitingTime);
	}

}
