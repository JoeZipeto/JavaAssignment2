//********************************************************************************************************
//*	Project: COMP2080_ASSIGN2_Coder_Folks
//*	Assignment: assignment#2
//*	Authors: Bahman Yaghoubi Vije, Joseph Zipeto
//*	Student numbers: 100968843, 100963441
//*	Date: April 10, 2016
//*	Description: This file contains a class from which Term objects  
//* 			 could be instantiated. This class implements the 
//*				 Comparable interface which provides methods for 
//*				 comparing the Term objects.
//********************************************************************************************************

package term;

import java.util.Comparator;

public class Term implements Comparable<Term>{

	final private String query;
	final private double weight;
	
	// Initialize a term with the given query string and weight.
	public Term(String query, double weight)
	{
		this.query = query;
		this.weight = weight;
	}
	
	// Compare the terms in descending order by weight.
	public static Comparator<Term> byReverseWeightOrder()
	{
		Comparator<Term> compared = new CompareReverse();
		return compared;
	}
	
	// Compare the terms in lexicographic order but using only 
	// the first r characters of each query.
	public static Comparator<Term> byPrefixOrder(int r)
	{
		Comparator<Term> compared = new ComparePrefix(r);
		return compared;
	}
			
	// Compare the terms in lexicographic order by query.
	public int compareTo(Term that)
	{
		return (int)Math.signum(this.query.compareTo(that.query));
	}
	
	// Return a string representation of the term 
	public String toString()
	{
		return getQuery();
	}

	public double getWeight() {
		return weight;
	}

	public String getQuery() {
		return query;
	}
}
