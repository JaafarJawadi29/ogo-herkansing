import Utility.SlideSize;

import java.awt.*;
import java.awt.image.ImageObserver;
import java.util.Vector;

/**
 * <p>A slide. This class has drawing functionality.</p>
 *
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
 */

public class Slide {
	private String title; //The title is kept separately
	private Vector<SlideItem> items; //The SlideItems are kept in a vector

	public Slide() {
		items = new Vector<>();
	}

	//Add a SlideItem
	public void append(SlideItem anItem) {
		items.addElement(anItem);
	}

	//Return the title of a slide
	public String getTitle() {
		return title;
	}

	//Change the title of a slide
	public void setTitle(String newTitle) {
		title = newTitle;
	}

	//Returns the SlideItem
	public SlideItem getSlideItem(int number) {
		return items.elementAt(number);
	}

	//Return all the SlideItems in a vector
	public Vector<SlideItem> getSlideItems() {
		return items;
	}

	//Returns the size of a slide
	public int getSize() {
		return items.size();
	}

	//Draws the slide
	public void draw(Graphics g, Rectangle area, ImageObserver view) {
		float scale = getScale(area);
		int y = area.y;
		//The title is treated separately
		SlideItem slideItem = SlideItemFactory.createSlideItemWithLevelAndContent(0, getTitle());
		slideItem.draw(area.x, y, scale, g, view);
		y += slideItem.getBoundingBox(g, view, scale).height;
		for (int number = 0; number < getSize(); number++) {
			slideItem = getSlideItems().elementAt(number);
			slideItem.draw(area.x, y, scale, g, view);
			y += slideItem.getBoundingBox(g, view, scale).height;
		}
	}

	//Returns the scale to draw a slide
	private float getScale(Rectangle area) {
		return Math.min(((float) area.width) / ((float) SlideSize.WIDTH), ((float) area.height) / ((float) SlideSize.HEIGHT));
	}
}