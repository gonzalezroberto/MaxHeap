import java.util.*;
public class main 
{
	public static void main(String[] args)
	{
		Scanner input = new Scanner(System.in);
		String menu = "Please select how to test the program:\n" + 
				"(1) 20 sets of 100 randomly generated ints\n" + 
				"(2) Fixed int values 1-100\n" + 
				"Enter choice: ";
		System.out.println(menu);
		String choice = input.nextLine();
		int swapCountOptimal = 0, swapCountSeries = 0;
		if(choice.equals("1"))
		{
			for(int row = 0; row < 20; row++) {
				int[] temp = new int[100];
				MaxHeap firstHeap = new MaxHeap(100); //series of insertions
				for(int col = 0; col < 100; col++) 
				{
					int newItem = (int) (Math.random() * 100);
					while(isDuplicate(temp, newItem, col))
						newItem = (int) (Math.random() * 100);
					temp[col] = newItem;
					firstHeap.add(newItem);
				}
				MaxHeap secondHeap = new MaxHeap(temp); // optimal method
				swapCountOptimal += secondHeap.getSwapCount(); 
				swapCountSeries += firstHeap.getSwapCount();
			}
			System.out.println("Average swaps for Series of insertions: " + swapCountSeries/20);
			System.out.println("Average swaps for optimal method: " + swapCountOptimal/20);
		}
		else if(choice.equals("2"))
		{
			int[] fixed = new int[100];
			MaxHeap fixedHeap = new MaxHeap(100);
			for(int i = 0; i < 100; i++) {
				fixed[i] = i+1;
				fixedHeap.add(i+1);
			}
			swapCountSeries = fixedHeap.getSwapCount();
			
			System.out.print("\nHeap built using series of insertions: ");
			fixedHeap.print();
			System.out.println("Number of swaps: " + swapCountSeries);
			for(int i = 0; i < 10; i++)
				fixedHeap.removeMax();
			System.out.print("Heap after 10 removals: ");
			fixedHeap.print();
			fixedHeap = new MaxHeap(fixed);
			System.out.print("\nHeap built using optimal method: ");
			fixedHeap.print();
			swapCountOptimal = fixedHeap.getSwapCount();
			System.out.println("Number of swaps: " + swapCountOptimal);
			for(int i = 0; i < 10; i++)
				fixedHeap.removeMax();
			System.out.print("Heap after 10 removals: ");
			fixedHeap.print();
		}
		else
			System.out.println("Invalid Input");
	}
	public static boolean isDuplicate(int [] database, int searchItem, int lastIndex)
	{
		Boolean found = false;
		for(int index = 0; index < lastIndex; index++)
			if(database[index] == searchItem)
				found = true;
		
		return found;
	}
}
