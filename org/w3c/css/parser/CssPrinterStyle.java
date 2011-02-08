//
// $Id: CssPrinterStyle.java,v 1.5 2010-01-05 13:49:33 ylafon Exp $
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html

package org.w3c.css.parser;

import org.w3c.css.properties.css.CssProperty;

/**
 * This class is invoke by all propperties when a print is required.
 *
 * @version $Revision: 1.5 $
 * @see org.w3c.css.parser.CssStyle#print
 */
public interface CssPrinterStyle {

  /**
   * Print this property.
   *
   * @param property The property to print.
   * @see org.w3c.css.properties.css.CssProperty#toString
   * @see org.w3c.css.properties.css.CssProperty#getPropertyName
   */
  public void print(CssProperty property);
}
