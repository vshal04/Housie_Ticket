package Miscellaneous;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HousieTicket {
	
	private static final Logger LOGGER = Logger.getLogger(HousieTicket.class.getName());


	private static int[][] generateHousieTicket(int rows, int columns) {
		int[][] ticket = new int[rows][columns];
		List<Integer>[] columnValues = new ArrayList[columns];

		for (int i = 0; i < columns; i++) {
			columnValues[i] = new ArrayList<>();
			for (int j = 1; j <= 10; j++) {
				columnValues[i].add(i * 10 + j);
			}
		}

		for (int i = 0; i < columns; i++) {
			Collections.shuffle(columnValues[i]);
		}

		for (int i = 0; i < rows; i++) {
			Set<Integer> usedNumbers = new HashSet<>();
			int cellsFilled = 0;

			while (cellsFilled <= 5) {
				int randomColumn = ThreadLocalRandom.current().nextInt(columns);
				//System.out.print(randomColumn);
				int randomIndex = ThreadLocalRandom.current().nextInt(columnValues[randomColumn].size());
				//System.out.print(randomIndex);
				int number = columnValues[randomColumn].get(randomIndex);
				//System.out.print(number);

				if (!usedNumbers.contains(number)) {
					ticket[i][randomColumn] = number;
					usedNumbers.add(number);
					cellsFilled++;
				}
			}
		}

		return ticket;
	}

	private static void printTicket(int[][] ticket) {
		System.out.println("HOUSIE TICKET: ");
		System.out.println("--------------");
		for (int[] row : ticket) {
			for (int cell : row) {
				System.out.printf("%2d | ", cell);
			}
			System.out.println();
		}
	}

	public static void main(String[] args) {
		
		 try {
	            int rows = 3;
	            int columns = 9;

	            int[][] ticket = generateHousieTicket(rows, columns);
	            printTicket(ticket);
	        } catch (IllegalArgumentException e) {
	            LOGGER.log(Level.SEVERE, e.getMessage(), e);
	        }

	}

}
