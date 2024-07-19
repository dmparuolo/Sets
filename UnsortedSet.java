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
 * A simple implementation of an ISet. 
 * Elements are not in any particular order.
 * Students are to implement methods that 
 * were not implemented in AbstractSet and override
 * methods that can be done more efficiently. 
 * An ArrayList must be used as the internal storage container.
 *
 */
public class UnsortedSet<E> extends AbstractSet<E> {

    private ArrayList<E> myCon;

    /**
     * O(1)
     * Create an empty unsorted set
     */
    public UnsortedSet() {
    	myCon = new ArrayList<>();
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
		//ensures item isn't already present
		if (!myCon.contains(item)) {
			return myCon.add(item);
		}
		return false;
	}
	

    /**
     * O(N^2)
     * Create a new set that is the difference of this set and otherSet. 
     * Return an ISet of elements that are in this Set but not in otherSet. 
     * Also called the relative complement. 
     * <br>Example: If ISet A contains [X, Y, Z] and ISet B contains [W, Z] 
     * then A.difference(B) would return an ISet with elements [X, Y] while
     * B.difference(A) would return an ISet with elements [W]. 
     * <br>pre: otherSet != null
     * <br>post: returns a set that is the difference of this set and otherSet.
     * Neither this set or otherSet are altered as a result of this operation.
     * <br> pre: otherSet != null
     * @param otherSet != null
     * @return a set that is the difference of this set and otherSet
     */
	public ISet<E> difference(ISet<E> otherSet) {
		if (otherSet == null) {
			throw new IllegalArgumentException("Violation of precondition: difference."
					+ " Parameter must not be null.");
		}
		UnsortedSet<E> newSet = new UnsortedSet<>();
		for (E data: myCon) {
			//only adds if other set doesn't have item
			if (!otherSet.contains(data)) {
				newSet.myCon.add(data);
			}
		}
		return newSet;
	}

	
    /**
     * O(N^2)
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
		UnsortedSet<E> newSet = new UnsortedSet<>();
		for (E data: myCon) {
			//adds if both sets have the element
			if (otherSet.contains(data)) {
				newSet.myCon.add(data);
			}
		}
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
     * O(N^2)
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
    	UnsortedSet<E> newSet = new UnsortedSet<>();
    	for (E data: myCon) {
    		//adds values from this set
    		newSet.myCon.add(data);
    	}
    	for (E data: otherSet) {
    		//only adds values if not already present
    		newSet.add(data);
    	}
    	return newSet;
    }
}