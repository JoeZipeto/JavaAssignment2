//********************************************************************************************************
//*	Project: COMP2080_ASSIGN2_Coder_Folks
//*	Assignment: assignment#2
//*	Authors: Bahman Yaghoubi Vije, Joseph Zipeto
//*	Student numbers: 100968843, 100963441
//*	Date: April 10, 2016
//*	Description: This file contains a class to find and return 
//* 			 all matching elements to a certain prefix
//********************************************************************************************************

package autoComplete;

import java.util.Arrays;

import binarySearch.BinarySearchDeluxe;
import term.Term;

public class Autocomplete {

	// An array of type Term as the only private data member of the class
	private Term[] terms;
	// Initialize the constructor of the class using the given array of terms
	public Autocomplete(Term[] terms)
	{
		// If the given array is null or empty throw java.lang.NullPointer exception
		if (terms == null || terms.length==0) 
		{
	        throw new java.lang.NullPointerException(); 
	    }
		
		
		// set the array of current object to the given array
		this.terms = terms;
		
	}
	
	//Return all terms that start with the given prefix, in descending order of weight
	@SuppressWarnings("unused")
	public Term[] allMatches(String prefix)
	{
		
		// if the string to be matched is null, throw java.lang.NullPointer exception
		if (prefix==null)
			throw new java.lang.NullPointerException();
		// create a Term object taking given prefix as its query
		Term termToMatch = new Term(prefix, 0);
		// find first index of given prefix in the array of Term type
		int first = BinarySearchDeluxe.firstIndexOf(terms, termToMatch, Term.byPrefixOrder(prefix.length()));
		// find last index of given prefix in the terms array 
		int last = BinarySearchDeluxe.lastIndexOf(terms, termToMatch, Term.byPrefixOrder(prefix.length()));
		// if no match is found, throw java.lang.NullPointer exception
		//if (first==-1 || last==-1)
			//throw new java.lang.NullPointerException();
		// create a new array with number of elements equal to the number of matched elements found 
		Term[] matches = new Term[last-first+1];
		// iterate through terms array and copy the matched elements into a new array
		if (first==0 && last==0)
			matches[0]=termToMatch;
		else
		for (int i=first; i<=last; i++)
			matches[i-first] = terms[i];
		// return the array including all matched elements*/
		return matches;
	}
	
	//Return the number of terms that start with the given prefix.
	public int numberOfMatches(String prefix)
	{
		// if the string to be matched is null, throw java.lang.NullPointer exception
		if (prefix==null)
			throw new java.lang.NullPointerException(); 
		// create a Term object taking given prefix as its query
		Term termToMatch = new Term(prefix, 0);
		// find first index of given prefix in the array of Term type
		int first = BinarySearchDeluxe.firstIndexOf(terms, termToMatch, Term.byPrefixOrder(prefix.length()));
		// find last index of given prefix in the array of Term type
		int last = BinarySearchDeluxe.lastIndexOf(terms, termToMatch, Term.byPrefixOrder(prefix.length()));
		// return the number of elements in between first and last elements
		return last-first+1;
	}
}
