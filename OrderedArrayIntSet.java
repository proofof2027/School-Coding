import java.util.*;

/*
 * In this implementation of IntSet, all items are stored in an ordered array.
 *
 * The effiency of each method is as follows:
 *  	add is O(n)  (to make sure it isn't a duplicate)
 *  	contains is O(log(n))
 *  	remove is O(log(n))  (to find the index of the element)
 *  	size is O(1)
 */
public class OrderedArrayIntSet implements IntSet{

	/**
    * Returns an iterator over the elements in this set.
    * The elements are returned in a particular order.
    *
    * @return  an Iterator over the elements in this set
    */
    public Iterator<Integer> iterator(){
        return new MyIterator();
    }
    
    private class MyIterator implements Iterator<Integer>{
        private int index = 0;

        public boolean hasNext(){
        return 0 <= index && index < size;
        }
        
        public Integer next(){  
        // Note: the 2 lines below are equivalent to the 1 line
        // return data[index++];
        index++;
        return data[index - 1];
        }
    }

    private int[] data;
	private int size;
    
	/**
 	* Construct an empty set.
 	*/
	public OrderedArrayIntSet() {
    	size = 0;
    	data = new int[10];
	}

	/**
 	* Adds an element to the set, ensuring no duplicate elements are added.
 	*
 	* @param element - the item to add to the set
 	* @return  - true if element was added, false if it already existed.
 	*/
	public boolean add(int element) {
    	if (size == data.length) {
        	int[] copy = data;
        	data = new int[size * 2];

        	// Copy data back into original array
        	for (int i = 0; i < size; i++) {
            	data[i] = copy[i];
        	}
    	}


    	if (contains(element)) {  // Don't add if it already exists
        	return false;
    	}

    	int i;
   	 
    	for (i = size - 1; (i >= 0 && data[i] > element); i--){
        	data[i + 1] = data[i];
    	}

    	data[i + 1] = element;

    	size++;
    	return true;
	}

	/**
 	* Returns the index of the element, -1 if the element does not exist in the set
 	*
 	* @param element - item to look for
 	* @return  - index of element or -1 if it does not exist
 	*/
	public boolean contains(int element) {
    	return (findIndex(element, 0, size - 1) != -1);
	}

	/**
 	* Removes element from set if it exists in the set already
 	*
 	* @param element - Item to remove from the set
 	* @return  - true if element was removed, false if it did not exist in the set
 	*/
	public boolean remove(int element) {
    	// See if the element is in the set.
    	int indexOfElement = findIndex(element, 0, size - 1);
   	 
    	// Return false if element not in the set.
    	if (indexOfElement == -1) return false;

    	for (int i = indexOfElement; i < size - 1; i++){
        	data[i] = data[i + 1];
    	}
    
    	size--;
    	return true;
	}

	/**
 	* @return  - the number of elements currently in the set
 	*
 	* This is NOT the same as the length of the underlying array structure
 	*/
	public int size() {
    	return size;
	}
    
	// Return index of the element, or -1 if not found.
	private int findIndex(int element, int start, int end) {
    	int mid = (start  + end) / 2;
   	 
    	if (end < start) {
        	return -1;
    	}
    	return 
    		(element == data[mid]) ? mid
    		: (element < data[mid]) ? findIndex(element, start, mid - 1)
    		: findIndex(element, mid + 1, end);
	}
}