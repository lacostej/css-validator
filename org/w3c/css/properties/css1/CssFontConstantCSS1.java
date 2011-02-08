//
// $Id: CssFontConstantCSS1.java,v 1.3 2005-09-14 15:14:31 ylafon Exp $
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
package org.w3c.css.properties.css1;

/**
 * @version $Revision: 1.3 $
 */
public interface CssFontConstantCSS1 {

  /**
   * Array of font-style values
   */
  static String[] FONTSTYLE = { "normal", "italic", "oblique", "inherit" };

  /**
   * Array of font-variant values
   */
  static String[] FONTVARIANT = { "normal", "small-caps", "inherit" };

  /**
   * Array of font-size values
   */
  static String[] FONTSIZE = {
    "xx-small", "x-small", "small", "medium", "large", "x-large", "xx-large",
    "larger", "smaller", "inherit"
  }; // relative-size

  /**
   * Array of font-weight values
   */
  static String[] FONTWEIGHT = { "normal", "bold", "bolder",
				 "lighter", "inherit" };

  /**
   * Array of font-stretch values
   */
  static String[] FONTSTRETCH = { "normal", "wider", "narrower",
				  "ultra-condensed", "extra-condensed",
				  "condensed", "semi-condensed",
				  "semi-expanded", "expanded", "extra-expanded",
				  "ultra-expanded", "inherit" };



}
