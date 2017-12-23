//Programmer's name:  Sha Lu 
//                    David Tu
//                    Eric Simmons
//Course:             CPSC 335
//Assignment number:  2
//Due date:           Jun 21, 2016
/**
 * An implementation of a minimum heap with handles
 */

public class Heap {

    private HeapElt[] array;
    int heapsize;
    int arraysize;

    /*
      The constructor has been set up with an initial array of size 4
      so that your doubleHeap() method will be tested.  Don't change
      this!
    */
    public Heap() {
	array = new HeapElt[4];
	heapsize = 0;
	arraysize = 4;
    }



    /*
      Exchanges that values at positions pos1 and pos2 in the heap array.
      Handles must be exchanged correctly as well.

      Running time = O(1)
    */
    private void exchange(int pos1, int pos2) {

	// PROVIDE YOUR IMPLEMENTATION HERE
		HeapElt temp = array[pos2];
		// exchange the element and call setHandle to save index
		array[pos2] = array[pos1];
		array[pos2].setHandle(pos2);
		
		array[pos1] = temp;
		array[pos1].setHandle(pos1);
		//System.out.println("swap" + array[pos2] + " and " + array[pos1]);
    }



    /*
      Doubles the size of the array.  A new array is created, the elements in
      the heap are copied to the new array, and the array data member is set
      to the new array.  Data member arraysize is set to the size of the
      new array.

      Running time = O(n)
    */
    private void doubleHeap() {

	// PROVIDE YOUR IMPLEMENTATION HERE
		// to change array size, 1 create new array, 2 copy old array to it,
		// 3 set old array length with new length, 4 put new name to old one
		int doubleSize = 2 * array.length;
		HeapElt[] newArray = new HeapElt[doubleSize];
		for (int i = 0; i < array.length; i++) {
			newArray[i] = array[i];
		}
		arraysize = newArray.length;
		array = newArray;
    }



    /*
      Fixes the heap if the value at position pos may be smaller than its
      parent.  Using exchange() to swap elements will simplify your
      implementation.  Heap elements contain records that are Comparable;
      you will need to figure out what to test and how to test it.

      Running time = O(nlgn)
    */
    public void heapifyUp(int pos) {

	// PROVIDE YOUR IMPLEMENTATION HERE
    	//pos > 0 is for ignoring root node, which doesn't need to heapify
    	//second condition translates to: array[pos] < array[parent[pos]]
		int parent = pos / 2;
		// if pos value < its parent, swap
		
		
		while (pos > 1 && array[pos].getRecord().compareTo(array[parent].getRecord()) < 0) {
			exchange(parent, pos);
			pos = parent;
			parent = pos / 2;
			//System.out.println("pos is "+pos+", parent pos is "+ parent);
		}
    }

    /*
      Fixes the heap if the value at position pos may be bigger than one of
      its children.  Using exchange() to swap elements will simplify your
      implementation.  Heap elements contain records that are Comparable;
      you will need to figure out what to test and how to test it.

      Running time = O(nlgn)
    */
    public void heapifyDown(int pos) {

	// PROVIDE YOUR IMPLEMENTATION HERE
		int leftChild = 2 * pos;
		int rightChild = 2 * pos + 1;
		int smallestPos = pos;
		// if left child value of pos < pos, set left smallest
		if (leftChild <= heapsize && array[leftChild].getRecord().compareTo(array[smallestPos].getRecord()) < 0) {
			smallestPos = leftChild;
		}
		// if right child value of pos < , set right smallest
		if (rightChild <= heapsize && array[rightChild].getRecord().compareTo(array[smallestPos].getRecord()) < 0) {
			smallestPos = rightChild;
		}
		// if smallest is not pos(current smallest), swap and keep heapifyDown 
		if (smallestPos != pos) {
			exchange(pos, smallestPos);
			heapifyDown(smallestPos);
		}
    }



    /*
      Insert inElt into the heap.  Before doing so, make sure that there is
      an open spot in the array for doing so.  If you need more space, call
      doubleHeap() before doing the insertion.

      Running time = O(lgn) (NOTE that there are a couple of different cases
      here!)
    */
    public void insert(HeapElt inElt) {

	// PROVIDE YOUR IMPLEMENTATION HERE
		if (heapsize >= arraysize - 1) {
			doubleHeap();
		}
		heapsize = heapsize + 1;
		array[heapsize] = inElt;
		//set inElt's index in array
		inElt.setHandle(heapsize);
		heapifyUp(heapsize);	 
    }



    /*
      Remove the minimum element from the heap and return it.  Restore
      the heap to heap order.  Assumes heap is not empty, and will
      cause an exception if the heap is empty.

      Running time = O(lgn)
    */
    public HeapElt removeMin() {
	// WARNING: Will fail with empty heap!

	// PROVIDE YOUR IMPLEMENTATION HERE
		if(heapsize >= 1) {
			exchange(1, heapsize);
			heapsize--;
			heapifyDown(1);
			return array[heapsize+1];
		}
		else System.exit(1); 
			return null;
    }



    /*
      Return the number of elements in the heap..

      Running time = O(1)
    */
    public int getHeapsize() {

	// PROVIDE YOUR IMPLEMENTATION HERE
    	return heapsize;
    }

 

   /*
      Print out the heap for debugging purposes.  It is recommended to 
      print both the key from the record and the handle.

      Running time = O(????)
    */
    public void printHeap() {

	// PROVIDE YOUR IMPLEMENTATION HERE
		System.out.println("value  handle");
		System.out.println("-----  ------");

		for (int i =1; i <= heapsize; i++) {
		    System.out.println("  " + array[i].getRecord().toString()
				       + "       "
				       + array[i].getHandle());
		}
		System.out.println("\n ========= End of printHeap() ========= \n");
     }

}
