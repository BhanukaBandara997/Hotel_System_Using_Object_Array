package hotel_system;

/*
This program is about a Hotel System using Object array
*/


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 *
 * @author BhanukaBandara
 */

public class Queue {


		// Declaring variables as public static
		static Hotel[] hotel = new Hotel[11];
		
		public static int roomNo;
		public static String roomName;
		public static Scanner input = new Scanner(System.in);

		// Main page of the program 
		public static void main(String[] args) {
			System.out.println();
			System.out.println("       WELCOME TO HOTEL JETWING YALA");
			System.out.println("$++++++++++++++++++++++++++++++++++++$");
			System.out.println();

			// calling the method initialize
			initialise(hotel);

			// calling the method mainPage
			mainPage();

			// calling the method selections
			selections();
		}
	// Initializing the array as empty
		private static void initialise(Hotel[] hotels) {
			try{
			for (int i = 0; i < hotels.length; i++) {
				
				hotels[i]=null;
				
			}
			}catch(Exception ex){
				
				
			}
		}

		public static void mainPage() {
			System.out.println(" --- Selections--- ");
			System.out.println("=============================== ");
			System.out.println("Enter V : View Rooms --- ");
			System.out.println("Enter A : Add new Customer --- ");
			System.out.println("Enter E : Display Empty Room --- ");
			System.out.println("Enter D : Delete Customer from Room --- ");
			System.out.println("Enter F : Find Room Information --- ");
			System.out.println("Enter T : Store program details into a text file --- ");
			System.out.println("Enter L : Load Program Data to the Array --- ");
			System.out.println("Enter O : View rooms Ordered alphabetically by name --- ");
			System.out.println("Enter Q : Quit the program --- ");
		}
                
                //User selection in the mainpage 
                public static void selections() {

			boolean addAgain = true;
			while (addAgain == true) {
				System.out.print("--- Enter your selection:");
				String selected = input.next().toLowerCase();
				if (selected.equals("v")) {
					view();
				} else if (selected.equals("a") || selected.equals("A")) {
					addDetails();
				} else if (selected.equals("e") || selected.equals("E")) {
					emptyRooms();
				} else if (selected.equals("d") || selected.equals("D")) {
					deleteFile();
				} else if (selected.equals("f") || selected.equals("F")) {
					search();
				} else if (selected.equals("t") || selected.equals("T")) {
					storeInfo();
				} else if (selected.equals("l") || selected.equals("L")) {
					load();
				} else if (selected.equals("o") || selected.equals("O")) {
					displayOrderedRooms();
				} else if (selected.equals("q") || selected.equals("Q")) {
					System.exit(0);
				} else {
					System.out.println("--- Invaild Input Check The Main Menu... ---");
				}
				System.out.print("--- Do you want to continue press (Y/N): ");
				String Addagain = input.next().toLowerCase();
				if (Addagain.equals("no") || Addagain.equals("No") || Addagain.equals("NO") || Addagain.equals("n") || Addagain.equals("N")) {
					addAgain = false;
				} else if (Addagain.equals("yes") || Addagain.equals("Yes") || Addagain.equals("YES") || Addagain.equals("y") || Addagain.equals("Y")) {
					addAgain = true;
				} else {
					System.out.println("--- Your Input is Invaild... Try Again... ---");
				}
			}
		}

		// adding customer details
		public static void addDetails() {

			System.out.print("--- Enter room number 1 to 10: ---  ");
			if (input.hasNextInt()) {
				roomNo = input.nextInt();
			} else {
				System.out.println("--- Invaild input!!! Enter room number 1 to 10: ---");
				
			}
			if((roomNo>0)&& (roomNo<11)){
			System.out.print("--- Enter name for room " + roomNo + " : ");
			if (input.hasNext()) {
				roomName = input.next().toLowerCase();
			} else {
				System.out.println("--- Invaild Input!! Enter name for room " + roomNo
						+ " : ");
				System.exit(0);
			}
			Hotel htl=new Hotel(roomName);
			hotel[roomNo] = htl;
			System.out.println("Succesfully added your customer");
			}else{
				System.out.println("--- Invaild Input!!! Please enter a valid number withing the range of 1 to 10 ---");
			}
		}

		// view empty and occupied rooms and empty rooms
		public static void view() {
			for (int i = 1; i < hotel.length; i++) {
				if (hotel[i]==null) {
					System.out.println("--- Room " + (i) + " is Empty ---");
				} else {
					System.out.println("--- Room " + (i) + " is occupied by "
							+ hotel[i].getName());
				}
			}
		}
                
                public static void displayOrderedRooms() {
			orderRooms(hotel);
			for (int i = 0; i < hotel.length; i++) {
				if (hotel[i]!=null) {
					System.out.println("--- Room Name is: " + hotel[i].getName());
				}
				continue;
			}
		}

		// displaying empty rooms
		public static void emptyRooms() {
			for (int i = 1; i < hotel.length; i++) {
				if (hotel[i]==null) {
					System.out.println("--- Room " + (i) + " is Empty ---");
				}
			}
		}

		// delete files that customer ask to delete
		public static void deleteFile() {
			try {
				System.out.println("--- Enter room number: ");
				roomNo = input.nextInt();

				if (roomNo < hotel.length) {
					hotel[roomNo]=null;
					System.out.println("--- File successfully deleted!!");
				}
			} catch (Exception e) {
				System.out.println("--- Room Info.txt not found --- ");
			}
		}

		// search for rooms information
		public static void search() {
			System.out.print("--- Enter the Room Name: ");
			String answer = input.next().toLowerCase();
			
	
				for (int i = 0;i < hotel.length; i++) {
				 if(hotel[i]!=null){
					if (hotel[i].getName().equals(answer)) {
						System.out.println("--- The customer "+answer+" is occupying room no."+i);
					} 
				}
				 continue;
				}
				
			
			
		}


		// Store rooms information in Room Info.txt text file
		public static void storeInfo() {
			try {
				File rooms = new File("Room Info.txt");
				rooms.createNewFile();

				FileWriter roomInfo = new FileWriter(rooms, true);

				BufferedWriter bw = new BufferedWriter(roomInfo);

				bw.write("Room Number \tRoom Name");
				bw.newLine();
				try{
				for (int x = 0; x < hotel.length; x++) {
					if(hotel[x]!=null){
					bw.write("\n" + x + "\t\t" + hotel[x].getName());
					bw.newLine();
					}
					continue;
				}
				}catch(Exception ex){
					System.out.println(ex);
					
				}

				bw.flush();

				bw.close();

				System.out.println("--- Successfully added the details ---");

			} catch (IOException e) {
				System.out.println("--- File Not Found Please Check Customer File --- ");
			}
		}

		// load details in text file to an array structure
		public static void load() {

			// BufferedReader br = new BufferedReader(reader);

			BufferedReader bufferreader = null;

			try {
				// FileReader bra = new FileReader("res/database.txt");
				bufferreader = new BufferedReader(new InputStreamReader(
						new FileInputStream("Room Info.txt")));
				String a = "\t" + "\t";
				int number = 0;
				String Name = "";

				bufferreader.readLine();
				bufferreader.readLine();

				for (String line = bufferreader.readLine(); line != null; line = bufferreader
						.readLine()) {
					StringTokenizer stk = new StringTokenizer(line, a);

					while (stk.hasMoreElements()) {
						number = Integer.valueOf((String) stk.nextElement());
						Name = (String) stk.nextElement();

						if (hotel.length > number) {
							hotel[number].setName(Name);
						}
					}
				}

			} catch (Exception e) {

				System.err.println("--- Data not found ---");

			} finally {
				try {
					bufferreader.close();
				} catch (IOException e) {
				}
			}
		}

		// Displaying rooms according to alphabetical order
		public static void orderRooms(Hotel array[]) {
			boolean b = true;
			String tmp;

			while (b) {
				b = false;
				for(int i=0;i<array.length;i++){
					if(array[i]!=null){
						tmp=array[i].getName();
					for(int j=1;j<array.length-1;j++){
						if(array[j]!=null){
							if(array[i].getName().equals(array[j].getName())){
								continue;
							}
							int x=tmp.compareTo(array[j].getName());
							if(x<0){
								tmp=array[j].getName();
								array[j].setName(array[i].getName());
								array[i].setName(tmp);
							}
						}
						continue;
					}
				}
					continue;
				}
			}
		}
		
	

	}

