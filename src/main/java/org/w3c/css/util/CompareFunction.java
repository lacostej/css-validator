//
// $Id: CompareFunction.java,v 1.5 2010-01-05 13:49:59 ylafon Exp $
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
package org.w3c.css.util;

/**
 * The comparaison function for the SortAlgorithm class
 *
 * @version $Revision: 1.5 $
 * @author  Philippe Le Hegaret
 */
public abstract class CompareFunction {
    public abstract boolean compare(Object obj1, Object obj2);
}
