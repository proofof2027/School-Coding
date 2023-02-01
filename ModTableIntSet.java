import java.util.*;


/*
 * In this implementation of IntSet, all items are stored in an ordered array.
 *
 * The effiency of each method is as follows:
 *  	add is O(1)  
 *  	contains is O(1)
 *  	remove is O(1)
 *  	size is O(1)
 */
class ModTableIntSet implements IntSet{

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
        return bucketArray[index - 1];
        }
    }

	private int size;
    public TreeIntSet[] bucketArray;
    
	/**
 	* Construct an empty set.
 	*/
	public ModTableIntSet(int modulus) {
    	size = 0;
    	bucketArray = new TreeIntSet[modulus];
    	for (int i = 0; i < modulus; i++){
    		bucketArray[i] = new TreeIntSet();
    	}
	}

	/**
 	* Adds an element to the set, ensuring no duplicate elements are added.
 	*
 	* @param element - the item to add to the set
 	* @return  - true if element was added, false if it already existed.
 	*/
	public boolean add(int element) {
    	if (contains(element)){
    		return false;
    	}
    	bucketArray[correctMod(element)].add(element);
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
    	return bucketArray[correctMod(element)].contains(element);
	}

	/**
 	* Removes element from set if it exists in the set already
 	*
 	* @param element - Item to remove from the set
 	* @return  - true if element was removed, false if it did not exist in the set
 	*/
	public boolean remove(int element) {

    	// Return false if element not in the set.
    	if (!bucketArray[correctMod(element)].remove(element)) return false;

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

	private int correctMod(int num){
		return (num < 0) ? num % bucketArray.length + bucketArray.length : num % bucketArray.length;
	}
}
