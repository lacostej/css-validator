//
// $Id: PagedProperty.java,v 1.2 2010-01-05 13:49:58 ylafon Exp $
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
/*
 */

package org.w3c.css.properties.paged;

import org.w3c.css.properties.css.CssProperty;

/**
 * @version $Revision: 1.2 $
 */
public abstract class PagedProperty extends CssProperty {

  /**
   * Returns true if the property is inherited
   */
  public boolean Inherited() {
    return PagedProperties.getInheritance(this);
  }

}
