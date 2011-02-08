//
// $Id: ACssProperty.java,v 1.3 2010-01-05 13:49:36 ylafon Exp $
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html

package org.w3c.css.properties.aural;

import org.w3c.css.properties.css.CssProperty;

/**
 * @version $Revision: 1.3 $
 */
public abstract class ACssProperty extends CssProperty {

  /**
   * Returns true if the property is inherited
   */
  public boolean Inherited() {
    return ACssProperties.getInheritance(this);
  }

}
