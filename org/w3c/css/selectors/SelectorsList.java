// $Id: SelectorsList.java,v 1.6 2009-02-25 20:44:50 ylafon Exp $
// Author: Jean-Guilhem Rouel
// (c) COPYRIGHT MIT, ERCIM and Keio, 2005.
// Please first read the full copyright statement in file COPYRIGHT.html
package org.w3c.css.selectors;

import java.util.ArrayList;

import org.w3c.css.selectors.attributes.AttributeAny;
import org.w3c.css.selectors.attributes.AttributeBegin;
import org.w3c.css.selectors.attributes.AttributeExact;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.util.Messages;

/**
 * SelectorsList<br />
 * A class to manage a list of selectors. The following selectors exists:
 * <ul>
 * <li>Universal: *</li>
 * <li>Type: E</li>
 * <li>Descendant: E F</li>
 * <li>Child: E > F</li>
 * <li>Adjacent: E + F</li>
 * <li>Attribute:
 *   <ul>
 *   <li>Any: E[foo]</li>
 *   <li>Begin: E[lang|=en]</li>
 *   <li>Exact: E[lang=en]</li>
 *   <li>One Of: E[lang~=en]</li>
 *   <li>Start: E[foo^=bar]</li>
 *   <li>Substring: E[foo*=bar]</li>
 *   <li>Suffix: E[foo$=bar]</li>
 *   </ul></li>
 * <li>ID: E#myid</li>
 * <li>Class: E.myclass</li>
 * <li>Pseudo-class: E:first-child, ...</li>
 * <li>Pseudo-element: E:first-line, ...</li>
 * <li>Pseudo-function:
 *   <ul>
 *   <li>contains</li>
 *   <li>lang</li>
 *   <li>not</li>
 *   <li>nth-child</li>
 *   <li>nth-last-child</li>
 *   <li>nth-of-type</li>
 *   <li>nth-last-of-type</li>
 *   <li>...</li>
 *   </ul></li>
 * </ul>
 *
 * Created: Sep 1, 2005 3:34:47 PM<br />
 */
public class SelectorsList {

    // the list of selectors
    private ArrayList<Selector> selectors;

    private ApplContext ac;

    private int specificity;

    private String stringrep = null;

    /**
     * Creates a new empty SelectorsList
     */
    public SelectorsList() {
	selectors = new ArrayList<Selector>();
    }

    /**
     * Creates a new SelectorsList given an context
     * @param ac the context in which the selectors appear
     */
    public SelectorsList(ApplContext ac) {
	this.ac = ac;
	selectors = new ArrayList<Selector>();
    }

    /**
     * Returns the selectors list
     * @return Returns the selectors list.
     */
    public ArrayList<Selector> getSelectors() {
        return selectors;
    }

    /**
     * Sets the selectors list
     * @param selectors The selectors list to set.
     */
    public void setSelectors(ArrayList<Selector> selectors) {
        this.selectors = selectors;
	stringrep = null;
    }

    /**
     * Return the nth selector in this SelectorsList
     * @param index the index of the selector to retreive
     * @return the nth selector
     */
    public Selector getSelector(int index) {
	return selectors.get(index);
    }

    /**
     * The number of selectors in this SelectorsList
     * @return the number of selectors in this SelectorsList
     */
    public int size() {
	return selectors.size();
    }

    /**
     * Adds a selector to this SelectorsList
     * @param selector the selector to add
     * @throws InvalidParamException when trying to add a selector after a pseudo-element
     */
    public void addSelector(Selector selector) throws InvalidParamException {
	if(selectors.size() > 0) {
	    Selector last = selectors.get(selectors.size() - 1);
	    if(last instanceof PseudoElementSelector) {
		throw new InvalidParamException("pseudo-element", selector, 
						ac.getMsg().getString(ac.getCssVersion()), ac);
	    }
	}
	selectors.add(selector);
	stringrep = null;
    }

    /**
     * Adds an attribute selector
     * @param attribute the attribute selector to add
     * @throws InvalidParamException when trying to add a selector after a pseudo-element
     */
    public void addAttribute(AttributeSelector attribute)
    throws InvalidParamException {
	addSelector(attribute);
    }

    /**
     * Adds an universal selector
     * @param universal the universal selector to add
     * @throws InvalidParamException when trying to add a selector after a pseudo-element
     */
    public void addUniversal(UniversalSelector universal)
    throws InvalidParamException {
	addSelector(universal);
    }

    /**
     * Adds a type selector
     * @param type the type selector to add
     * @throws InvalidParamException when trying to add a selector after a pseudo-element
     */
    public void addType(TypeSelector type) throws InvalidParamException {
	addSelector(type);
    }

    /**
     * Adds a descendant selector
     * @param descendant the descendant selector to add
     * @throws InvalidParamException when trying to add a selector after a pseudo-element
     */
    public void addDescendant(DescendantSelector descendant)
    throws InvalidParamException {
	addSelector(descendant);
    }

    /**
     * Adds a child selector
     * @param child the child selector to add
     * @throws InvalidParamException when trying to add a selector after a pseudo-element
     */
    public void addChild(ChildSelector child) throws InvalidParamException {
	addSelector(child);
    }

    /**
     * Adds a pseudo-class selector
     * @param pc the pseudo-class to add
     * @throws InvalidParamException when trying to add a selector after a pseudo-element
     */
    public void addPseudoClass(PseudoClassSelector pc)
    throws InvalidParamException {
	addSelector(pc);
    }

    /**
     * Adds a pseudo-element selector
     * No other selector can be added after a pseudo-element
     * @param pe the pseudo-element to add
     * @throws InvalidParamException when trying to add a selector after a pseudo-element
     */
    public void addPseudoElement(PseudoElementSelector pe)
    throws InvalidParamException {
	addSelector(pe);
    }

    /**
     * Adds a pseudo-function selector
     * @param pf the pseudo-function to add
     * @throws InvalidParamException when trying to add a selector after a pseudo-element
     */
    public void addPseudoFunction(PseudoFunctionSelector pf)
    throws InvalidParamException {
	addSelector(pf);
    }

    /**
     * Adds an adjacent sibling selector
     * @param adjacent the adjacent selector to add
     * @throws InvalidParamException when trying to add a selector after a pseudo-element
     */
    public void addAdjacentSibling(AdjacentSiblingSelector adjacent)
    throws InvalidParamException {
	addSelector(adjacent);
    }

    /**
     * Adds an adjacent sibling selector
     * @param adjacent the adjacent selector to add
     * @throws InvalidParamException when trying to add a selector after a pseudo-element
     */
    public void addGeneralSibling(GeneralSiblingSelector sibling)
    throws InvalidParamException {
	addSelector(sibling);
    }

    /**
     * Adds a class selector
     * @param cs the class selector to add
     * @throws InvalidParamException when trying to add a selector after a pseudo-element
     */
    public void addClass(ClassSelector cs) throws InvalidParamException {
	addSelector(cs);
    }

    /**
     * Adds an id selector
     * @param id the id selector to add
     * @throws InvalidParamException when trying to add a selector after a pseudo-element
     */
    public void addId(IdSelector id) throws InvalidParamException {
	addSelector(id);
    }

    /**
     * Returns a String representation of this SelectorsList
     * @return  the String representation of this SelectorsList
     */
    public String toString() {
	if (stringrep != null ) {
	    return stringrep;
	}
	StringBuilder res = new StringBuilder();
	int selsize = selectors.size();
	for(int i = 0; i < selsize; i++) {
	    res.append(selectors.get(i));
	}
	stringrep = res.toString();
	return stringrep;
    }
    
    public boolean isToStringCached() {
	return (stringrep != null);
    }

    public String toStringEscaped() {
	return Messages.escapeString(toString());
    }

    /**
     * Sets the specificity of this SelectorsList
     * @param specificity the specificity yo set
     */
    public void setSpecificity(int specificity) {
	this.specificity = specificity;
    }

    /**
     * Gets (and computes) the specificity of this selector.
     */
    public int getSpecificity() {
	int a = 0;
	int b = 0;
	int c = 0;

	for(int i = 0; i < size(); i++) {
	    Selector s = getSelector(i);
	    if(s instanceof IdSelector) {
		a++;
	    }
	    else if(s instanceof ClassSelector ||
		    s instanceof PseudoClassSelector) {
		b++;
	    }
	    else if(s instanceof TypeSelector ||
		    s instanceof AttributeSelector) {
		c++;
	    }
	    // some pseudo-functions might influence the specificity
	    else if(s instanceof PseudoFunctionSelector) {
		specificity += ((PseudoFunctionSelector)s).getSpecificity();
	    }
	}
	specificity += a * 100 + b * 10 + c;

	return specificity;
    }

    /**
     * Testing method
     * @param args unused
     */
    public static void main(String[] args) {
	SelectorsList s = new SelectorsList();
	try {
	    s.addType(new TypeSelector("E"));
	    s.addAttribute(new AttributeExact("foo", "warning"));
	    s.addChild(new ChildSelector());
	    s.addType(new TypeSelector("F"));
	    s.addAttribute(new AttributeBegin("lang", "en"));
	    s.addAttribute(new AttributeAny("bar"));
	    s.addAdjacentSibling(new AdjacentSiblingSelector());
	    s.addType(new TypeSelector("G"));
	    s.addId(new IdSelector("id"));
	    s.addAttribute(new AttributeAny("blop"));
	    s.addDescendant(new DescendantSelector());
	    s.addType(new TypeSelector("H"));

	    System.out.println(s);
	}
	catch(InvalidParamException e) {
	    System.err.println(e.getMessage());
	}
    }

}
