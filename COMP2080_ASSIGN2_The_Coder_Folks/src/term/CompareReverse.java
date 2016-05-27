//********************************************************************************************************
//*	Project: COMP2080_ASSIGN2_Coder_Folks
//*	Assignment: assignment#2
//*	Authors: Bahman Yaghoubi Vije, Joseph Zipeto
//*	Student numbers: 100968843, 100963441
//*	Date: April 10, 2016
//*	Description: This file contains a class to Compare the terms 
//* 			 in descending order by weight.
//********************************************************************************************************

package term;

import java.util.Comparator;

public class CompareReverse implements Comparator<Term>{

	@Override
	public int compare(Term o1, Term o2) {
		// returns a negative, 0, or positive integer based on the compare result
		return (int)(o2.getWeight()-o1.getWeight());
	}

}
