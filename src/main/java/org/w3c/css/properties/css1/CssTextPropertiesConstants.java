//
// $Id: CssTextPropertiesConstants.java,v 1.3 2010-01-05 13:49:45 ylafon Exp $
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
package org.w3c.css.properties.css1;

/**
 * @version $Revision: 1.3 $
 */
public interface CssTextPropertiesConstants {
  public static String[] TEXTDECORATION = {
    "underline", "overline", "line-through", "blink", "inherit" };

  public static String[] TEXTDECORATIONTV = {
	"none", "underline", "overline", "line-through", "inherit" };

  public static String[] TEXTDECORATIONMOB = {
    "underline", "inherit" };

  public static String[] VERTICALALIGN = {
    "auto", "use-script", "baseline", "sub", "super", "top", "text-top",
    "central", "middle", "bottom", "text-bottom", "inherit", "initial" };

  public static String[] VERTICALALIGNMOB = {
    "baseline", "sub", "super", "inherit" };

  public static String[] VERTICALALIGNTV = {
    "baseline", "sub", "super", "top", "middle", "bottom", "inherit" };

  public static String[] TEXTTRANSFORM = {
    "none", "capitalize", "uppercase", "lowercase", "inherit" };

  public static String[] TEXTALIGN = {
    "left", "right", "center", "justify", "inherit", "start", "end" };

  public static String[] TEXTALIGNTV = {
	"left", "right", "center", "justify", "inherit" };

}
