//
// $Id: SVGBasicStyle.java,v 1.4 2010-01-05 13:49:59 ylafon Exp $
// From Sijtsche de Jong
//
// COPYRIGHT (c) 1995-2002 World Wide Web Consortium, (MIT, INRIA, Keio University)
// Please first read the full copyright statement at
// http://www.w3.org/Consortium/Legal/copyright-software-19980720

package org.w3c.css.properties.svg;

import org.w3c.css.parser.CssPrinterStyle;

public class SVGBasicStyle extends SVGTinyStyle {

    AlignmentBaseline alignmentBaseline;
    ClipPath clipPath;
    ClipRule clipRule;
    ColorInterpolation colorInterpolation;
    ColorInterpolationFilters colorInterpolationFilters;
    ColorRendering colorRendering;
    EnableBackground enableBackground;
    WritingModeSVG writingModeSVG;
    FloodOpacity floodOpacity;
    Filter filter;
    FillOpacity fillOpacity;
    ImageRendering imageRendering;
    Mask mask;
    StopOpacity stopOpacity;
    Kerning kerning;
    PointerEvents pointerEvents;
    ShapeRendering shapeRendering;
    TextRendering textRendering;
    TextAnchor textAnchor;
    StrokeOpacity strokeOpacity;
    StopColor stopColor;
    SolidColor solidColor;
    FloodColor floodColor;
    ColorProfile colorProfile;
    DominantBaseLine dominantBaseLine;
    SolidOpacity solidOpacity;

    public AlignmentBaseline getAlignmentBaseline() {
	if (alignmentBaseline == null) {
	    alignmentBaseline =
		(AlignmentBaseline) style.CascadingOrder (
		    new AlignmentBaseline(), style, selector);
	}
	return alignmentBaseline;
    }

    public DominantBaseLine getDominantBaseLineSVG() {
	if (dominantBaseLine == null) {
	    dominantBaseLine =
		(DominantBaseLine) style.CascadingOrder (
		    new DominantBaseLine(), style, selector);
	}
	return dominantBaseLine;
    }

    public ClipPath getClipPath() {
	if (clipPath == null) {
	    clipPath =
		(ClipPath) style.CascadingOrder (
		    new ClipPath(), style, selector);
	}
	return clipPath;
    }

    public ClipRule getClipRule() {
	if (clipRule == null) {
	    clipRule =
		(ClipRule) style.CascadingOrder (
		    new ClipRule(), style, selector);
	}
	return clipRule;
    }

    public ColorInterpolation getColorInterpolation() {
	if (colorInterpolation == null) {
	    colorInterpolation =
		(ColorInterpolation) style.CascadingOrder (
		    new ColorInterpolation(), style, selector);
	}
	return colorInterpolation;
    }

    public ColorInterpolationFilters getColorInterpolationFilters() {
	if (colorInterpolationFilters == null) {
	    colorInterpolationFilters =
		(ColorInterpolationFilters) style.CascadingOrder (
		    new ColorInterpolationFilters(), style, selector);
	}
	return colorInterpolationFilters;
    }

    public ColorRendering getColorRendering() {
	if (colorRendering == null) {
	    colorRendering =
		(ColorRendering) style.CascadingOrder (
		    new ColorRendering(), style, selector);
	}
	return colorRendering;
    }

    public EnableBackground getEnableBackground() {
	if (enableBackground == null) {
	    enableBackground =
		(EnableBackground) style.CascadingOrder (
		    new EnableBackground(), style, selector);
	}
	return enableBackground;
    }

    public WritingModeSVG getWritingModeSVG() {
	if (writingModeSVG == null) {
	    writingModeSVG =
		(WritingModeSVG) style.CascadingOrder (
		    new WritingModeSVG(), style, selector);
	}
	return writingModeSVG;
    }

    public FloodOpacity getFloodOpacity() {
	if (floodOpacity == null) {
	    floodOpacity =
		(FloodOpacity) style.CascadingOrder (
		    new FloodOpacity(), style, selector);
	}
	return floodOpacity;
    }

    public Filter getFilter() {
	if (filter == null) {
	    filter =
		(Filter) style.CascadingOrder (
		    new Filter(), style, selector);
	}
	return filter;
    }

    public FillOpacity getFillOpacity() {
	if (fillOpacity == null) {
	    fillOpacity =
		(FillOpacity) style.CascadingOrder (
		    new FillOpacity(), style, selector);
	}
	return fillOpacity;
    }

    public ImageRendering getImageRendering() {
	if (imageRendering == null) {
	    imageRendering =
		(ImageRendering) style.CascadingOrder (
		    new ImageRendering(), style, selector);
	}
	return imageRendering;
    }

    public Mask getMask() {
	if (mask == null) {
	    mask =
		(Mask) style.CascadingOrder (
		    new Mask(), style, selector);
	}
	return mask;
    }

    public StopOpacity getStopOpacity() {
	if (stopOpacity == null) {
	    stopOpacity =
		(StopOpacity) style.CascadingOrder (
		    new StopOpacity(), style, selector);
	}
	return stopOpacity;
    }

    public Kerning getKerning() {
	if (kerning == null) {
	    kerning =
		(Kerning) style.CascadingOrder (
		    new Kerning(), style, selector);
	}
	return kerning;
    }

    public PointerEvents getPointerEvents() {
	if (pointerEvents == null) {
	    pointerEvents =
		(PointerEvents) style.CascadingOrder (
		    new PointerEvents(), style, selector);
	}
	return pointerEvents;
    }

    public ShapeRendering getShapeRendering() {
	if (shapeRendering == null) {
	    shapeRendering =
		(ShapeRendering) style.CascadingOrder (
		    new ShapeRendering(), style, selector);
	}
	return shapeRendering;
    }

    public TextRendering getTextRendering() {
	if (textRendering == null) {
	    textRendering =
		(TextRendering) style.CascadingOrder (
		    new TextRendering(), style, selector);
	}
	return textRendering;
    }

    public TextAnchor getTextAnchor() {
	if (textAnchor == null) {
	    textAnchor =
		(TextAnchor) style.CascadingOrder (
		    new TextAnchor(), style, selector);
	}
	return textAnchor;
    }

    public StrokeOpacity getStrokeOpacity() {
	if (strokeOpacity == null) {
	    strokeOpacity =
		(StrokeOpacity) style.CascadingOrder (
		    new StrokeOpacity(), style, selector);
	}
	return strokeOpacity;
    }

    public StopColor getStopColor() {
	if (stopColor == null) {
	    stopColor =
		(StopColor) style.CascadingOrder (
		    new StopColor(), style, selector);
	}
	return stopColor;
    }

    public SolidColor getSolidColor() {
	if (solidColor == null) {
	    solidColor =
		(SolidColor) style.CascadingOrder (
		    new SolidColor(), style, selector);
	}
	return solidColor;
    }

    public FloodColor getFloodColor() {
	if (floodColor == null) {
	    floodColor =
		(FloodColor) style.CascadingOrder (
		    new FloodColor(), style, selector);
	}
	return floodColor;
    }

    public ColorProfile getColorProfileSVG() {
	if (colorProfile == null) {
	    colorProfile =
		(ColorProfile) style.CascadingOrder (
		    new ColorProfile(), style, selector);
	}
	return colorProfile;
    }

    public SolidOpacity getSolidOpacity() {
	if (solidOpacity == null) {
	    solidOpacity =
		(SolidOpacity) style.CascadingOrder (
		    new SolidOpacity(), style, selector);
	}
	return solidOpacity;
    }
    
    /**
     * Print this style
     *
     * @param printer The printer interface
     */
    public void print(CssPrinterStyle printer) {
	super.print(printer);

	if (alignmentBaseline != null) {
	    alignmentBaseline.print(printer);
	}
	if (dominantBaseLine != null) {
	    dominantBaseLine.print(printer);
	}
	if (clipPath != null) {
	    clipPath.print(printer);
	}
	if (clipRule != null) {
	    clipRule.print(printer);
	}
	if (colorInterpolation != null) {
	    colorInterpolation.print(printer);
	}
	if (colorInterpolationFilters != null) {
	    colorInterpolationFilters.print(printer);
	}
	if (colorRendering != null) {
	    colorRendering.print(printer);
	}
	if (enableBackground != null) {
	    enableBackground.print(printer);
	}
	if (writingModeSVG != null) {
	    writingModeSVG.print(printer);
	}
	if (floodOpacity != null) {
	    floodOpacity.print(printer);
	}
	if (filter != null) {
	    filter.print(printer);
	}
	if (fillOpacity != null) {
	    fillOpacity.print(printer);
	}
	if (imageRendering != null) {
	    imageRendering.print(printer);
	}
	if (mask != null) {
	    mask.print(printer);
	}
	if (stopOpacity != null) {
	    stopOpacity.print(printer);
	}
	if (kerning != null) {
	    kerning.print(printer);
	}
	if (pointerEvents != null) {
	    pointerEvents.print(printer);
	}
	if (shapeRendering != null) {
	    shapeRendering.print(printer);
	}
	if (textRendering != null) {
	    textRendering.print(printer);
	}
	if (textAnchor != null) {
	    textAnchor.print(printer);
	}
	if (strokeOpacity != null) {
	    strokeOpacity.print(printer);
	}
	if (stopColor != null) {
	    stopColor.print(printer);
	}
	if (solidColor != null) {
	    solidColor.print(printer);
	}
	if (floodColor != null) {
	    floodColor.print(printer);
	}
	if (colorProfile != null) {
	    colorProfile.print(printer);
	}
	if (solidOpacity != null) {
	    solidOpacity.print(printer);
	}
    }
}
