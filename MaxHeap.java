import java.util.*;
public class MaxHeap 
{
	private int[] heap;
	private int lastIndex;
	private boolean initialized = false;
	private static final int DEFAULT_CAPACITY = 25;
	private static final int MAX_CAPACITY = 1000;
	private int swapCounter = 0;
	
	public MaxHeap()
	{
		this(DEFAULT_CAPACITY);
	}
	public int getSwapCount()
	{
		return swapCounter;
	}
	public MaxHeap(int intialCapacity)
	{
		if(intialCapacity < DEFAULT_CAPACITY)
			intialCapacity = DEFAULT_CAPACITY;
		else
			checkCapacity(intialCapacity);
		
		//@SuppressWarnings("unchecked");
		int[] tempHeap = new int [intialCapacity + 1];
		heap = tempHeap;
		lastIndex = 0;
		initialized = true;
	}
	
	public MaxHeap(int[] entries)
	{
		this(entries.length);
		lastIndex = entries.length;
		assert initialized = true;
		
		for(int index =0; index < entries.length; index++)
			heap[index+1] = entries[index];
		
		for(int rootIndex = lastIndex /2; rootIndex > 0; rootIndex--)
			reheap(rootIndex);
	}
	public int getMax()
	{
		checkInitialization();
		int root = 0;
		if(!isEmpty())
			root = heap[1];
		return root;
	}
	public boolean isEmpty()
	{
		return lastIndex <1;
	}
	public int getSize()
	{
		return lastIndex;
	}
	public void clear()
	{
		checkInitialization();
		while(lastIndex > -1) 
		{
			heap[lastIndex] = 0;
			lastIndex--;
		}
		lastIndex = 0;
	}
	public void add(int newEntry)
	{
		checkInitialization();
		int newIndex = lastIndex +1;
		int parentIndex = newIndex/2;
		while( (parentIndex > 0) && (newEntry - heap[parentIndex]) > 0)
		{
			heap[newIndex] = heap[parentIndex];
			newIndex = parentIndex;
			parentIndex = newIndex/2;
			swapCounter++;
		}
		heap[newIndex] = newEntry;
		lastIndex++;
		ensureCapacity();
	}
	
	private void checkInitialization()
	{
		if (!initialized)
			throw new SecurityException("heap is not initialized properly.");
	}
	private void ensureCapacity()
	{
		if (lastIndex >= heap.length)
		{
			int newCapacity = 2 * (heap.length - 1);
			checkCapacity(newCapacity);
			heap = Arrays.copyOf(heap, newCapacity);
		} 
	} 
	
	private void checkCapacity(int capacity)
	{
		if (capacity > MAX_CAPACITY)
			throw new IllegalStateException("Attempt to create a bag whose " +
			"capacity exeeds allowed " +
	          "maximum of " + MAX_CAPACITY);
	}
	
	public void removeMax()
	{
		checkInitialization();
		int root;
		if(!isEmpty())
		{
			root = heap[1];
			heap[1] = heap[lastIndex];
			lastIndex--;
			reheap(1);
		}
	}
	private void reheap(int rootIndex)
	{
		boolean done = false;
		int orphan = heap[rootIndex];
		int leftChildIndex = 2*rootIndex;
		while(!done && (leftChildIndex <= lastIndex))
		{
			int largerChildIndex = leftChildIndex;
			int rightChildIndex = leftChildIndex+1;
			if((rightChildIndex <= lastIndex) && (heap[rightChildIndex] - heap[largerChildIndex] > 0))
				largerChildIndex = rightChildIndex;
			
			if(orphan - heap[largerChildIndex] < 0)
			{
				heap[rootIndex] = heap[largerChildIndex];
				rootIndex = largerChildIndex;
				leftChildIndex = 2*rootIndex;
				swapCounter++;
			}
			else
				done = true;
		}
		heap[rootIndex] = orphan;
	}
	public void print()
	{
		for(int i = 1; i <= 10; i++)
			System.out.print( heap[i] + " ");
		System.out.print("...\n");
	}
}
