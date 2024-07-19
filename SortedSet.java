/*  Student information for assignment:
 *
 *  On my honor, Dominic Paruolo,
 *  this programming assignment is my own work
 *  and I have not provided this code to any other student.
 *
 *  Number of slip days used: 2
 *
 *  Student 1 (Student whose Canvas account is being used)
 *  UTEID: dmp3588
 *  email address: dominicparuolo78@gmail.com
 *  TA name: Nidhi
 *
 */

import java.util.Iterator;
import java.util.ArrayList;

/**
 * In this implementation of the ISet interface the elements in the Set are
 * maintained in ascending order.
 *
 * The data type for E must be a type that implements Comparable.
 *
 * Implement methods that were not implemented in AbstractSet
 * and override methods that can be done more efficiently. An ArrayList must
 * be used as the internal storage container. For methods involving two set,
 * if that method can be done more efficiently if the other set is also a
 * SortedSet, then do so.
 */
public class SortedSet<E extends Comparable<? super E>> extends AbstractSet<E> {

    private ArrayList<E> myCon;

    /**
     * O(1)
     * Create an empty SortedSet.
     */
    public SortedSet() {
    	//instantiates internal ArrayList
    	myCon = new ArrayList<>();
    }

    /**
     * O(NlogN)
     * Create a copy of other that is sorted.<br>
     * @param other != null
     */
    public SortedSet(ISet<E> other) {
    	if (other == null) {
    		throw new IllegalArgumentException("Violation of precondition: SortedSet."
    				+ " Parameter must not be null.");
    	}
    	myCon = new ArrayList<>();
    	ArrayList<E> tempList = new ArrayList<>();
    	//initially adds all elements of other
    	for (E data: other) {
    		myCon.add(data);
    		tempList.add(data);
    	}
    	
    	//sorts the set
    	sort(myCon, tempList, 0, other.size() - 1);
    }
    
    
    /**
     * Merge sort code from slide
     * O(NlogN)
     * Helper method that breaks a list of data down into smaller lists of length one or zero
     * so that they are each then sorted. They are then merged together with another helper method
     * merge, called in this method.
     * @param data, the data to be sorted.
     * @param temp, a temporary list to store future data.
     * @param low, the bottom index of a current list.
     * @param high, the max index of a current list.
     */
    private void sort(ArrayList<E> data, ArrayList<E> temp,
    		int low, int high) {
    	//splits lists into parts until each list has one or zero length
    	if(low < high) {
    		int center = (low + high) / 2;
    		sort(data, temp, low, center);
    		sort(data, temp, center + 1, high);
    		//merges the two previous sorts together
    		merge(data, temp, low, center + 1, high);
    	}
    }
	
	
	/**
	 * Merge sort code from slide.
	 * O(logN)
	 * Helper method for sort that rebuilds the elements of the list in sorted order.
	 * @param data, the data to be sorted.
	 * @param temp, the temporary holder for the sorted list.
	 * @param leftPos, the current index of the "left" list.
	 * @param rightPos, the current index of the "right" list.
	 * @param rightEnd, the last index of the "right" list.
	 */
	private void merge(ArrayList<E> data, ArrayList<E> temp,
			int leftPos, int rightPos, int rightEnd) {
		int leftEnd = rightPos - 1;
		int tempPos = leftPos;
		int numElements = rightEnd - leftPos + 1;
		//main loop
		while(leftPos <= leftEnd && rightPos <= rightEnd){
			if( data.get(leftPos).compareTo(data.get(rightPos)) <= 0) {
				temp.set(tempPos, data.get(leftPos));
				leftPos++;
			} else {
				temp.set(tempPos, data.get(rightPos));
				rightPos++;
			}
			tempPos++;
		}
		//copy rest of left half
		while (leftPos <= leftEnd) {
			temp.set(tempPos, data.get(leftPos));
			tempPos++;
			leftPos++;
		}
		//copy rest of right half
		while (rightPos <= rightEnd) {
			temp.set(tempPos, data.get(rightPos));
			tempPos++;
			rightPos++;
		}
		//Copy temp back into data
		for (int i = 0; i < numElements; i++, rightEnd--)
			data.set(rightEnd, temp.get(rightEnd));
	}
    
    
    

    /**
     * O(1)
     * Return the smallest element in this SortedSet.
     * <br> pre: size() != 0
     * @return the smallest element in this SortedSet.
     */
    public E min() {
    	if (size() == 0) {
    		throw new IllegalArgumentException("Violation of precondition: min."
    				+ " Size of set must not be zero.");
    	}
    	//gets the first element
    	return myCon.get(0);
    }

    /**
     * O(1)
     * Return the largest element in this SortedSet.
     * <br> pre: size() != 0
     * @return the largest element in this SortedSet.
     */
    public E max() {
    	if (size() == 0) {
    		throw new IllegalArgumentException("Violation of precondition: max."
    				+ " Size of set must not be zero.");
    	}
    	//gets the last element
    	return myCon.get(myCon.size() - 1);
    }

    
    /**
     * O(N)
     * Add an item to this set.
     * <br> item != null
     * @param item the item to be added to this set. item may not equal null.
     * @return true if this set changed as a result of this operation, 
     * false otherwise.
     */
	public boolean add(E item) {
		if (item == null) {
    		throw new IllegalArgumentException("Violation of precondition: add."
    				+ " Parameter must not be null.");
    	}
		//finds index for new item to go
		int index = 0;
		boolean foundIndex = false;
		//finds if already present or new index to be placed
		while (index < myCon.size() && !foundIndex) {
			int compare = myCon.get(index).compareTo(item);
			if (compare == 0) { //if already present
				return false;
			} else if (compare > 0) { //found new index
				foundIndex = true;
			} else { //not present and not new index
				index++;
			}
		}
		//adds in correct index
		myCon.add(index, item);
		return true;
	}
	
	
    /**
     * O(N) if otherSet is a SortedSet.
     * O(N^2) if otherSet is not a SortedSet.
     * A union operation. Add all items of otherSet that 
     * are not already present in this set to this set.
     * @param otherSet != null
     * @return true if this set changed as a result of this operation, 
     * false otherwise.
     */
   public boolean addAll(ISet<E> otherSet) {
	   if (otherSet == null) {
		   throw new IllegalArgumentException("Violation of precondition: addAll."
				   + " Parameter must not be null.");
	   }
	   //if its a SortedSet
	   if (otherSet instanceof SortedSet<?>) {
		   ArrayList<E> newVals = addAllHelper(((SortedSet<E>) otherSet).myCon);
		   //finds if any new values were added and adds new values
		   boolean changed = myCon.equals(newVals);
		   //updates this set
		   myCon = newVals;
		   return changed;
	   }
	   //if its not a SortedSet
	   boolean changed = false;
	   for (E otherData: otherSet) {
		   //adds if not already present
		   if (add(otherData)) {
			   //shows if any values have been added
			   changed = true;
		   }
	   }
	   return changed;
   }

   
   /**
    * O(N)
    * Helper method for addAll and union that adds any new values from the given set to this set.
    * @param newVals, the ArrayList of all elements from both sets.
    * @param otherVals, the ArratList containing the elements of the other given set.
    * @return a new ArrayList containing all values from this set and the other set.
    */
   private ArrayList<E> addAllHelper(ArrayList<E> otherVals){
	   ArrayList<E> newVals = new ArrayList<>();
	   int thisIndex = 0;
	   int otherIndex = 0;
	   //checks both sets for their unique values
	   while (thisIndex < myCon.size() && otherIndex < otherVals.size()) {
		   //if the sets share a value
		   if (myCon.get(thisIndex).compareTo(otherVals.get(otherIndex)) == 0) {
			   newVals.add(myCon.get(thisIndex));
			   thisIndex++;
			   otherIndex++;
		       //if this sets current value is less than the other sets current value
		   } else if (myCon.get(thisIndex).compareTo(otherVals.get(otherIndex)) < 0) {
			   newVals.add(myCon.get(thisIndex));
			   thisIndex++;
			   //if the other sets current value is less than this sets current value
		   } else {
			   newVals.add(otherVals.get(otherIndex));
			   otherIndex++;
		   }
	   }
	   //if there are any remaining values in this set
	   while (thisIndex < myCon.size()) {
		   newVals.add(myCon.get(thisIndex));
		   thisIndex++;
	   }
	   //if there are any remaining values in the other set
	   while (otherIndex < otherVals.size()) {
		   newVals.add(otherVals.get(otherIndex));
		   otherIndex++;
	   }
	   //the completed list of all values
	   return newVals;
   }
   
   
   /**
    * O(logN)
    * Determine if item is in this set. 
    * <br>pre: item != null
    * @param item element whose presence is being tested. 
    * Item may not equal null.
    * @return true if this set contains the specified item, false otherwise.
    */
   public boolean contains(E item) {
	   if (item == null) {
		   throw new IllegalArgumentException("Violation of precondition: contains."
				   + " Parameter must not be null.");
	   }
	   int low = 0;
	   int high = myCon.size() - 1;
	   int mid = 0;
	   //binary searches for item utilizing some code from the slides
	   while (low <= high) {
		   //gets the middle index of current available values
		   mid = low + ((high - low) / 2);
		   //compares current middle item to target value
		   int comparrison = myCon.get(mid).compareTo(item);
		   //if target value is already in set
		   if (comparrison == 0) {
			   return true;
		   } else if (comparrison < 0) { //if middle value is too low
			   low = mid + 1;
		   } else { //if middle value is too high
			   high = mid - 1;
		   }
	   }
	   //if item was not found in this set
	   return false;
   }
   
   
   /**
    * O(N) if otherSet is a SortedSet.
    * O(N^2) if otherSet is not a SortedSet.
    * Determine if all of the elements of otherSet are in this set.
    * <br> pre: otherSet != null
    * @param otherSet != null
    * @return true if this set contains all of the elements in otherSet, 
    * false otherwise.
    */
   public boolean containsAll(ISet<E> otherSet) {
	   if (otherSet == null) {
		   throw new IllegalArgumentException("Violation of precondition: containsAll."
				   + " Parameter must not be null.");
	   }
	   //ensures other set doesn't have greater size than this set
	   if (otherSet.size() > size()) {
		   return false;
	   }
	   //checks if otherSet is a SortedSet
	   if (otherSet instanceof SortedSet<?>) {
		   SortedSet<E> otherSorted = (SortedSet<E>) otherSet;
		   int otherIndex = 0;
		   //checks if this set has every value from other set
		   for (int i = 0; i < myCon.size() && otherIndex < otherSorted.size(); i++) {
			   //if this set has current value from other set
			   if (myCon.get(i).equals(otherSorted.myCon.get(otherIndex))) {
				   otherIndex++;
			   }
		   }
		   //means that there are still elements in the other set that were not
		   //found in this set
		   if (otherIndex != otherSorted.size()) {
			   return false;
		   }
		   return true;
	   }
	   //if the other set is not a sorted set
	   for (E data: otherSet) {
		   boolean found = false;
		   //searches for each value from other set in this set
		   for (int i = 0; i < myCon.size() && !found; i++) {
			   //the value has been found in this set
			   if (data.equals(myCon.get(i))) {
				   found = true;
			   }
		   }
		   //a value was never found in this set
		   if (!found) {
			   return false;
		   }
	   }
	   return true;
   }
   
   
   /**
    * O(N) if otherSet is a SortedSet.
    * O(N^2) if otherSet is not a SortedSet.
    * Create a new set that is the difference of this set and otherSet. 
    * Return an ISet of elements that are in this Set but not in otherSet. 
    * Also called the relative complement. 
    * <br>Example: If ISet A contains [X, Y, Z] and ISet B contains [W, Z] 
    * then A.difference(B) would return an ISet with elements [X, Y] while
    * B.difference(A) would return an ISet with elements [W]. 
    * <br>pre: otherSet != null
    * <br>post: returns a set that is the difference of this set and otherSet.
    * Neither this set or otherSet are altered as a result of this operation.
    * @param otherSet != null
    * @return a set that is the difference of this set and otherSet
    */
	public ISet<E> difference(ISet<E> otherSet) {
		if (otherSet == null) {
			throw new IllegalArgumentException("Violation of precondition: difference."
					+ " Parameter must not be null.");
		}
		//checks if otherSet is a SortedSet
		if (otherSet instanceof SortedSet<?>) {
			//gets a new SortedSet with the difference between this set and the other set
			return diffHelper(((SortedSet<E>) otherSet).myCon);
		}
		//if the other set is not a SortedSet
		SortedSet<E> newSet = new SortedSet<>();
		for (E data: myCon) {
			//only adds if other set doesn't have the item
			if (!otherSet.contains(data)) {
				newSet.myCon.add(data);
			}
		}
		return newSet;
	}

	
	/**
	 * O(N)
	 * Helper method for difference that finds the difference between two sets and returns it.
	 * @param otherVals, the values of the other set.
	 * @return a SortedSet with the difference between this set and the given set.
	 */
	private SortedSet<E> diffHelper(ArrayList<E> otherVals){
		SortedSet<E> newSet = new SortedSet<>();
		int thisIndex = 0;
		int otherIndex = 0;
		//checks both sets to find values in only this set and not the other set
		while (thisIndex < myCon.size() && otherIndex < otherVals.size()) {
			int comparison = myCon.get(thisIndex).compareTo(otherVals.get(otherIndex));
			//if this sets current value is less than the other sets current value it can be added
			if (comparison < 0) {
				newSet.myCon.add(myCon.get(thisIndex));
				thisIndex++;
				//if the sets both have the same current value it is skipped
			} else if (comparison == 0) {
				thisIndex++;
				otherIndex++;
			} else { //if this sets current value is greater than the other sets current value
				otherIndex++;
			}
		}
		//adds all remaining values from this set
		while (thisIndex < myCon.size()) {
			newSet.myCon.add(myCon.get(thisIndex));
			thisIndex++;
		}
		//the completed difference set
		return newSet;
	}
	
	
    /**
     * O(N) if other is a SortedSet.
     * O(N^2) if other is not a SortedSet.
     * Determine if this set is equal to other.
     * Two sets are equal if they have exactly the same elements.
     * The order of the elements does not matter.
     * <br>pre: none
     * @param other the object to compare to this set 
     * @return true if other is a Set and has the same elements as this set
     */
	public boolean equals(Object other) {
		//ensures other is an ISet
		if (other instanceof ISet<?>) {
			ISet<?> otherISet = (ISet<?>) other;
			//ensures both sets have the same size
			if (otherISet.size() == size()) {
				Iterator<?> otherIt = otherISet.iterator();
				//checks if other is a SortedSet
				if (otherISet instanceof SortedSet<?>) {
					int thisIndex = 0;
					//checks values in the same index of both sets
					while (otherIt.hasNext()) {
						//if two values in the same index are not equal across both sets
						if (!otherIt.next().equals(myCon.get(thisIndex))) {
							return false;
						}
						thisIndex++;
					}
					return true;
				}
				//if other is not a SortedSet
				boolean found = false;
				//ensures both sets have same elements
				for (E data: this) {
					while(otherIt.hasNext() && !found) {
						//finds if two elements are equal
						if (otherIt.next().equals(data)) {
							found = true;
						}
					}
					//if an element is not in both sets
					if (!found) {
						return false;
					}
				}
				return true;
			}
		}
		return false;
	}
	
	
    /**
     * O(N) if otherSet is a SortedSet.
     * O(N^2) if otherSet is not a SortedSet.
     * create a new set that is the intersection of this set and otherSet.
     * <br>pre: otherSet != null<br>
     * <br>post: returns a set that is the intersection of this set 
     * and otherSet.
     * Neither this set or otherSet are altered as a result of this operation.
     * <br> pre: otherSet != null
     * @param otherSet != null
     * @return a set that is the intersection of this set and otherSet
     */
	public ISet<E> intersection(ISet<E> otherSet) {
		if (otherSet == null) {
			throw new IllegalArgumentException("Violation of precondition: intersection."
					+ " Parameter must not be null.");
		}
		SortedSet<E> newSet = new SortedSet<>();
		//checks if otherSet is a SortedSet
		if (otherSet instanceof SortedSet<?>) {
			SortedSet<E> otherSorted = (SortedSet<E>) otherSet;
			int thisIndex = 0;
			int otherIndex = 0;
			//checks both sets for values only in both sets
			while (thisIndex < size() && otherIndex < otherSorted.size()) {
				//if this sets current value is less than the other sets current value
				int comparison = myCon.get(thisIndex).compareTo(
						otherSorted.myCon.get(otherIndex));
				if (comparison < 0) {
					thisIndex++;
					//if the other sets current value is less than this sets current value
				} else if (comparison > 0) {
					otherIndex++;
					//if both sets current values are equal
				} else {
					newSet.add(myCon.get(thisIndex));
					thisIndex++;
					otherIndex++;
				}
			}
		} else { //if otherSet is not a SortedSet
			for (E data: myCon) {
				//adds if both sets have the element
				if (otherSet.contains(data)) {
					newSet.myCon.add(data);
				}
			}
		}
		//completed intersection set
		return newSet;
	}
	
	
    /**
     * O(1)
     * Return an Iterator object for the elements of this set.
     * pre: none
     * @return an Iterator object for the elements of this set
     */
	public Iterator<E> iterator() {
		return myCon.iterator();
	}
	
	
    /**
     * O(1)
     * Return the number of elements of this set.
     * pre: none
     * @return the number of items in this set
     */
	public int size() {
		return myCon.size();
	}
	
	
    /**
     * O(N) if otherSet is a SortedSet.
     * O(N^2) if otherSet is not a SortedSet.
     * Create a new set that is the union of this set and otherSet.
     * <br>pre: otherSet != null
     * <br>post: returns a set that is the union of this set and otherSet.
     * Neither this set or otherSet are altered as a result of this operation.
     * <br> pre: otherSet != null
     * @param otherSet != null
     * @return a set that is the union of this set and otherSet
     */
    public ISet<E> union(ISet<E> otherSet) {
		if (otherSet == null) {
			throw new IllegalArgumentException("Violation of precondition: union."
					+ " Parameter must not be null.");
		}
		SortedSet<E> newSet = new SortedSet<>();
		//checks if otherSet is a SortedSet
		if (otherSet instanceof SortedSet<?>) {
			newSet.myCon = addAllHelper(((SortedSet<E>) otherSet).myCon);
		} else { //if otherSet is not a SortedSet
			for (E data: myCon) {
	    		//adds values from this set
	    		newSet.myCon.add(data);
	    	}
	    	for (E data: otherSet) {
	    		//only adds values if not already present
	    		newSet.add(data);
	    	}
		}
		return newSet;
    }
}