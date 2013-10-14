package edu.buffalo.cse.ir.wikiindexer.indexer;

 class Sort {
	public Integer[] theArray; // ref to array theArray
	public String[] theArray2;
	public int nElems;
	public void quickSort() {
		recQuickSort(0, nElems - 1);
	}
	public void recQuickSort(int left, int right) {
		if (right - left <= 0) // if size <= 1,
			return; // already sorted
		else // size is 2 or larger
		{
			long pivot = theArray[right]; // rightmost item // partition range
			int partition = partitionIt(left, right, pivot);
			recQuickSort(left, partition - 1); // sort left side
			recQuickSort(partition + 1, right); // sort right side
		}
	}

	public int partitionIt(int left, int right, long pivot) {
		int leftPtr = left - 1; // left (after ++)
		int rightPtr = right; // right-1 (after --)
		while (true) { // find bigger item
			while (theArray[++leftPtr] < pivot)
				; // (nop) // find smaller item
			while (rightPtr > 0 && theArray[--rightPtr] > pivot)
				; // (nop)

			if (leftPtr >= rightPtr) // if pointers cross,
				break; // partition done
			else
				// not crossed, so
				swap(leftPtr, rightPtr); // swap elements
		} // end while(true)
		swap(leftPtr, right); // restore pivot
		return leftPtr; // return pivot location
	}

	public void swap(int dex1, int dex2) // swap two elements
	{
		int temp = theArray[dex1]; // A into temp
		theArray[dex1] = theArray[dex2]; // B into A
		theArray[dex2] = temp; // temp into B
		
		String stemp = theArray2[dex1]; // A into temp
		theArray2[dex1] = theArray2[dex2]; // B into A
		theArray2[dex2] = stemp; // temp into B
	}
	
	public static void main(String[] args) throws Exception {
		Sort s = new Sort();
		s.nElems = 4;
		s.theArray = new Integer[s.nElems];
		s.theArray[0] = 3;
		s.theArray[1] = 2;
		s.theArray[2] = 4;
		s.theArray[3] = 1;
		s.theArray2 = new String[s.nElems];
		s.theArray2[0] = "3";
		s.theArray2[1] = "2";
		s.theArray2[2] = "4";
		s.theArray2[3] = "1";
		
		s.quickSort();
		System.out.println(s.theArray[3]);
		System.out.println(s.theArray2[3]);
	}
}
