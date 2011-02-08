//
// $Id: SortAlgorithm.java,v 1.5 2007-11-26 05:07:17 ot Exp $
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
package org.w3c.css.util;

/**
 * This class is only to sort an array with an abstract algorithm.
 *
 * @version $Revision: 1.5 $
 * @author  Philippe Le Hegaret
 */
public abstract class SortAlgorithm {

    /**
     * The sort function.
     *
     * @param objs the array with all objects
     * @param start the start offset in the array
     * @param end the end offset in the array
     * @param comp The comparaison function between objects
     */
    public abstract void sort(Object[] objs,
			      int start, int end,
			      CompareFunction comp);
}
