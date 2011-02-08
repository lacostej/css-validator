//
// $Id: QuickSortAlgorithm.java,v 1.5 2007-11-26 05:07:17 ot Exp $
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
package org.w3c.css.util;

/**
 * The quicksort algorithm.
 *
 * @version $Revision: 1.5 $
 * @author  Philippe Le Hegaret
 */
public class QuickSortAlgorithm extends SortAlgorithm {
    int partition(Object[] objs,
		  int part_low_ind, int part_high_ind,
		  CompareFunction comp) {
	int lastsmall;
	int comp1;
	Object median_val;
	Object transit;

	// swap median value an first value of array
	comp1 = ( part_low_ind + part_high_ind ) / 2;

	transit = objs[part_low_ind];
	objs[part_low_ind] = objs[comp1];
	objs[comp1] = transit;

	median_val = objs[part_low_ind];

	lastsmall = part_low_ind;
	for (int i = part_low_ind + 1; i<=part_high_ind; i++) {
	    if (comp.compare(objs[i], median_val)) {
		lastsmall++;
		// swap lastsmall and i
		transit=objs[lastsmall];
		objs[lastsmall]=objs[i];
		objs[i]=transit;
	    }
	}
	// swap part_low_ind and lastsmall
	transit=objs[part_low_ind];
	objs[part_low_ind]=objs[lastsmall];
	objs[lastsmall]=transit;

	return lastsmall;
    }

    /**
     * The quicksort function.
     *
     * @param objs the array with all objects
     * @param start the start offset in the array
     * @param end the end offset in the array
     * @param comp The comparaison function between objects
     */
    public void sort(Object[] objs,
		     int start, int end,
		     CompareFunction comp) {
	if (start < end) {
	    int median = partition(objs, start, end, comp);
	    sort(objs, start, median, comp);
	    sort(objs, median+1, end, comp);
	}
    }


}
