import Utility.SlideSize;

import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.font.LineBreakMeasurer;
import java.awt.font.TextAttribute;
import java.awt.font.TextLayout;
import java.awt.geom.Rectangle2D;
import java.awt.image.ImageObserver;
import java.text.AttributedString;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * <p>A text item.</p>
 * <p>A text item has drawing capabilities.</p>
 *
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
 */

public class TextItem extends SlideItem {
	private String text;

	//A textitem of int level with text string
	public TextItem(int level, String string) {
		super(level);
		text = string;
	}

	//Returns the text
	public String getText() {
		return text == null ? "" : text;
	}

	//Returns the AttributedString for the Item
	public AttributedString getAttributedString(float scale) {
		AttributedString attrStr = new AttributedString(getText());
		attrStr.addAttribute(TextAttribute.FONT, style.getFont(scale), 0, text.length());
		return attrStr;
	}

	//Returns the bounding box of an Item
	public Rectangle getBoundingBox(Graphics g, ImageObserver observer, float scale) {
		List<TextLayout> layouts = getLayouts(g, scale);
		int xsize = 0, ysize = (int) (this.style.leading * scale);
		Iterator<TextLayout> iterator = layouts.iterator();
		while (iterator.hasNext()) {
			TextLayout layout = iterator.next();
			Rectangle2D bounds = layout.getBounds();
			if (bounds.getWidth() > xsize) {
				xsize = (int) bounds.getWidth();
			}
			if (bounds.getHeight() > 0) {
				ysize += bounds.getHeight();
			}
			ysize += layout.getLeading() + layout.getDescent();
		}
		return new Rectangle((int) (this.style.indent * scale), 0, xsize, ysize);
	}

	//Draws the item
	public void draw(int x, int y, float scale, Graphics g, ImageObserver o) {
		if (text == null || text.length() == 0) {
			return;
		}
		List<TextLayout> layouts = getLayouts(g, scale);
		Point pen = new Point(x + (int) (this.style.indent * scale),
				y + (int) (this.style.leading * scale));
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(this.style.color);
		Iterator<TextLayout> it = layouts.iterator();
		while (it.hasNext()) {
			TextLayout layout = it.next();
			pen.y += layout.getAscent();
			layout.draw(g2d, pen.x, pen.y);
			pen.y += layout.getDescent();
		}
	}

	private List<TextLayout> getLayouts(Graphics g, float scale) {
		List<TextLayout> layouts = new ArrayList<TextLayout>();
		AttributedString attrStr = getAttributedString(scale);
		Graphics2D g2d = (Graphics2D) g;
		FontRenderContext frc = g2d.getFontRenderContext();
		LineBreakMeasurer measurer = new LineBreakMeasurer(attrStr.getIterator(), frc);
		float wrappingWidth = (SlideSize.WIDTH - this.style.indent) * scale;
		while (measurer.getPosition() < getText().length()) {
			TextLayout layout = measurer.nextLayout(wrappingWidth);
			layouts.add(layout);
		}
		return layouts;
	}

	public String toString() {
		return "TextItem[" + getLevel() + "," + getText() + "]";
	}
}