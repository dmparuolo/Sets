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

/**
 * Students are to complete this class. 
 * Students should implement as many methods
 * as they can using the Iterator from the iterator 
 * method and the other methods. 
 *
 */
public abstract class AbstractSet<E> implements ISet<E> {

	/**
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
		boolean changed = false;
		for (E data: otherSet) {
			//adds new data to set
			if (this.add(data)) {
				changed = true;
			}
		}
		return changed;
	}


	/**
	 * Make this set empty.
	 * <br>pre: none
	 * <br>post: size() = 0
	 */
	public void clear() {
		Iterator<E> it = this.iterator();
		//removes every element
		while (it.hasNext()) {
			it.next();
			it.remove();
		}
	}


	/**
	 * Determine if item is in this set. 
	 * <br>pre: item != null
	 * @param item element whose presence is being tested. 
	 * Item may not equal null.
	 * @return true if this set contains the specified item, false otherwise.
//     */
	public boolean contains(E item) {
		if (item == null) {
			throw new IllegalArgumentException("Violation of precondition: contains."
					+ " Parameter must not be null.");
		}
		for (E data: this) {
			//finds if both elements are equal
			if (data.equals(item)) {
				return true;
			}
		}
		return false;
	}


	/**
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
		for (E data: otherSet) {
			//finds if every element has a matching value in other set
			if (!this.contains(data)) {
				return false;
			}
		}
		return true;
	}


	/**
	 * Determine if this set is equal to other.
	 * Two sets are equal if they have exactly the same elements.
	 * The order of the elements does not matter.
	 * <br>pre: none
	 * @param other the object to compare to this set 
	 * @return true if other is a Set and has the same elements as this set
	 */
	public boolean equals(Object other) {
		//ensures other is an ISet object
		if (other instanceof ISet<?>) {
			ISet<?> otherSet = (ISet<?>) other;
			//ensures both sets have same size
			if (this.size() == otherSet.size()) {
				Iterator<?> otherIterator = ((ISet<?>) other).iterator();
				boolean found = false;
				//ensures both sets have same elements
				for (E data: this) {
					while(otherIterator.hasNext() && !found) {
						if (otherIterator.next().equals(data)) {
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
	 * Remove the specified item from this set if it is present.
	 * pre: item != null
	 * @param item the item to remove from the set. item may not equal null.
	 * @return true if this set changed as a result of this operation, 
	 * false otherwise
	 */
	public boolean remove(E item) {
		if (item == null) {
			throw new IllegalArgumentException("Violation of precondition: remove."
					+ " Parameter must not be null.");
		}
		Iterator<E> it = this.iterator();
		while (it.hasNext()) {
			//finds if this is the target item and removes it
			if (it.next().equals(item)) {
				it.remove();
				return true;
			}
		}
		return false;
	}


	/**
	 * Return the number of elements of this set.
	 * pre: none
	 * @return the number of items in this set
	 */
	public int size() {
		int newSize = 0;
		Iterator<E> it = this.iterator();
		//finds number of elements in this set
		while (it.hasNext()) {
			it.next();
			newSize++;
		}
		return newSize;
	}


	/**
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
		//gets the unique values of this set
		ISet<E> newSet = this.difference(otherSet);
		//gets the unique values of other set
		newSet.addAll(otherSet.difference(this));
		//gets the values present in both sets
		newSet.addAll(this.intersection(otherSet));
		return newSet;
	}

	/**
	 * Return a String version of this set. 
	 * Format is (e1, e2, ... en)
	 * @return A String version of this set.
	 */
	public String toString() {
		StringBuilder result = new StringBuilder();
		String seperator = ", ";
		result.append("(");

		Iterator<E> it = this.iterator();
		while (it.hasNext()) {
			result.append(it.next());
			result.append(seperator);
		}
		// get rid of extra separator
		if (this.size() > 0) {
			result.setLength(result.length() - seperator.length());
		}

		result.append(")");
		return result.toString();
	}
}