/* Copyright (c) 1996 by Groupe Bull.  All Rights Reserved */
/* $Id: HtmlTag.java,v 1.3 2005-09-14 15:14:17 ylafon Exp $ */
/* Author: Jean-Michel.Leon@sophia.inria.fr */

package html.tags;

import html.parser.*;
import html.tree.*;

public interface HtmlTag extends Tag, Tree {

   /**
    * Initializes the tag with the given elem,atts pair.
    *
    */
    void initialize(Element elem, Attributes atts, ParserFrame parserFrame);
}
