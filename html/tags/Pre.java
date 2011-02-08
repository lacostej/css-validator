/* Copyright (c) 1997 by Groupe Bull.  All Rights Reserved */
/* $Id: Pre.java,v 1.2 2002-04-08 21:22:41 plehegar Exp $ */
/* Author: Jean-Michel.Leon@sophia.inria.fr */

package html.tags;

import html.tree.*;

public class Pre extends HtmlTree {

    public boolean isBlock() {
	return true;
    }

    public boolean isPreformatted() {
	return true;
    }
}
