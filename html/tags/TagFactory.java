/* Copyright (c) 1996 by Groupe Bull.  All Rights Reserved */
/* $Id: TagFactory.java,v 1.3 2005-09-14 15:14:17 ylafon Exp $ */
/* Author: Jean-Michel.Leon@sophia.inria.fr */

package html.tags;

import html.parser.Tag;

/**
 * A TagFactory is a ClassLoader whose job is to build instances of the Tag
 * interface.
 *
 */
public interface TagFactory {

   /**
    * creates an returns a valid Tag object for the given name.
    */
    Tag create(String name);
}
