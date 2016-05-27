//********************************************************************************************************
//*	Project: COMP2080_ASSIGN2_Coder_Folks
//*	Assignment: assignment#2
//*	Authors: Bahman Yaghoubi Vije, Joseph Zipeto
//*	Student numbers: 100968843, 100963441
//*	Date: April 10, 2016
//*	Description: This file contains a class to compare the terms  
//* 			 in lexicographic order but using only 
//*				 the first r characters of each query. 
//********************************************************************************************************

package term;

import java.util.Comparator;

public class ComparePrefix implements Comparator<Term>{

	int r;
	public ComparePrefix(int r)
	{
		this.r = r;
	}
	@Override
	public int compare(Term o1, Term o2) {
		// if the term queries have more than r characters, get the substring 
		// including first r characters of them
		String so1 = o1.getQuery().length()>r?o1.getQuery().substring(0, r):o1.getQuery();
		String so2 = o2.getQuery().length()>r?o2.getQuery().substring(0, r):o2.getQuery();
		// returns a negative, 0, or positive integer based on the compare result
		return (int)so1.compareTo(so2);
	}

}
