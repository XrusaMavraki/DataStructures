
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import java.io.IOException;

import java.util.Scanner;

public class FlightSchedule {
	private static String FlightListNumber;
	private static int FlightListNum;
	private static int LastListNum=0;
	private static String FlightCondition;
	private static int FlightKey;
	private static String FlightD_A;
	private static String FlightTime;
	private static int FlightT;
	
	private static PQ<Flight, KeyFlight> pq = new PQ<Flight, KeyFlight>(5);

	public static void main(String[] args) {

		KeyFlight.initMethod();

		Scanner in = new Scanner(System.in);
		System.out.print("Please give me the name of the flight schedule: ");
		String filename = in.next() + ".txt";
		in.close();
		try {
			Scanner reader = new Scanner(new FileInputStream(filename));
			getData(reader);
		} catch (FileNotFoundException e) {
			System.out.println("Couldn't find the file!!!!");
		} catch (IOException e) {
			System.out.println("Couldn't get the data from the file!!!!");
		}
	}

	public static void getData(Scanner reader) throws IOException {

		while (reader.hasNext()) {
			Flight f = null;
			FlightListNumber = reader.next();
			int hour = Integer.parseInt(FlightListNumber.substring(0, 2));
			int minute = Integer.parseInt(FlightListNumber.substring(2));
			if (hour > 23 || minute > 59){
				System.out.println("Wrong time format");
				System.exit(1);
			}
			FlightListNum=Integer.parseInt(FlightListNumber);
			FlightCondition = reader.next();
			FlightKey = reader.nextInt();

			if (!FlightCondition.equals("cancel")) {

				FlightD_A = reader.next();
				FlightTime = reader.next();
				hour = Integer.parseInt(FlightTime.substring(0, 2));
				minute = Integer.parseInt(FlightTime.substring(2));
				if (hour > 23 || minute > 59) {
					System.out.println("Wrong time format");
					System.exit(1);
				}
				FlightT=Integer.parseInt(FlightTime);
				if (FlightD_A.equals("D")) {
					f = new Flight(true, KeyFlight.getKeyFlight(FlightKey));
				} else {
					f = new Flight(false, KeyFlight.getKeyFlight(FlightKey));
				}
				f.getKey().setTime(FlightT);
			} else {
				f = new Flight(KeyFlight.getKeyFlight(FlightKey));
			}
			while((!pq.isEmpty())&&(FlightListNum>=pq.Max().getKey().getTime())){
				System.out.println(pq.getMax());
			}
			switch (FlightCondition) {
			case "insert": {
				if (FlightListNum > f.getKey().getTime()) {
					System.out.println("The time of entry is wrong. flight should have happened already");
					break;
				}
				pq.insert(f);
				break;
			}
			case "update": {
				if (pq.isEmpty()) {
					throw new IllegalStateException();
				}
				f.getKey().setTime(FlightT);
				pq.updateKey(f, f.getKey());
				break;
			}
			case "cancel": {
				if (pq.isEmpty()) {
					throw new IllegalStateException();
				}
				pq.remove(f.getKey());
				break;
			}
			}

			

		}
		reader.close();
		while(!pq.isEmpty()) {
			System.out.println(pq.getMax());
		}
	}
}
