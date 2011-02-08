/* Copyright (c) 1997 by Groupe Bull.  All Rights Reserved */
/* $Id: HtmlStreamListener.java,v 1.2 2002-04-08 21:22:41 plehegar Exp $ */
/* Author: Jean-Michel.Leon@sophia.inria.fr */

package html.tags;

public interface HtmlStreamListener {
    public void notifyActivity(int lines, long bytes);
}
