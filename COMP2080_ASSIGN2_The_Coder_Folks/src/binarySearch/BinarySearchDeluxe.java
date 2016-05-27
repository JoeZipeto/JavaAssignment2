//********************************************************************************************************
//*	Project: COMP2080_ASSIGN2_Coder_Folks
//*	Assignment: assignment#2
//*	Authors: Bahman Yaghoubi Vije, Joseph Zipeto
//*	Student numbers: 100968843, 100963441
//*	Date: April 10, 2016
//*	Description: This file contains a class to perform binary search 
//* 			 in an array of generic elements
//********************************************************************************************************

package binarySearch;

import java.util.Comparator;

public class BinarySearchDeluxe {
	
	// Return the index of the first key in a[] that equals the search key, 
	// or -1 if no search key
	public static <Key> int firstIndexOf(Key[] a , Key key, Comparator<Key> comparator)
	{
		if (a == null || key == null || comparator == null) {
    		throw new java.lang.NullPointerException();
    	}
    	int first = 0, last = a.length - 1;
    	while (first < last-1) {
    		int mid = first + (last - first)/2;
    		if (comparator.compare(key, a[mid]) > 0) {
    			first = mid;
    		} else {
    			last = mid;
    		}
    	}
    	return (comparator.compare(key, a[first]) == 0) ? first :
    		(comparator.compare(key, a[last]) == 0) ? last : -1;
	}
	
	// Return the index of the last key in a[] that equals the search key, 
		// or -1 if no search key
	public static <Key> int lastIndexOf(Key[] a , Key key, Comparator<Key> comparator)
	{
		if (a == null || key == null || comparator == null) {
    		throw new java.lang.NullPointerException();
    	}
    	int first = 0, last = a.length - 1;
    	while (first < last-1) {
    		int mid = first + (last - first)/2;
    		if (comparator.compare(key, a[mid]) < 0) {
    			last = mid;
    		} else {
    			first = mid;
    		}
    	}
    	return (comparator.compare(key, a[last]) == 0) ? last :
    		(comparator.compare(key, a[first]) == 0) ? first : -1;
	}
}
