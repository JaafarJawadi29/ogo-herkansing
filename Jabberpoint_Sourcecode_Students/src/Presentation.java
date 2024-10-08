/**
 * <p>Presentations keeps track of the slides in a presentation.</p>
 * <p>Only one instance of this class is available.</p>
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.1 2002/12/17 Gert Florijn
 * @version 1.2 2003/11/19 Sylvia Stuurman
 * @version 1.3 2004/08/17 Sylvia Stuurman
 * @version 1.4 2007/07/16 Sylvia Stuurman
 * @version 1.5 2010/03/03 Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
 */

public class Presentation {
	private String showTitle; //The title of the presentation
	private SlideViewerComponent slideViewComponent; //The view component of the slides
	private final SlideController slideController; //The controller of the slides

	public Presentation(SlideController slideController) {
		this.slideViewComponent = null;
		this.slideController = slideController;
	}

	public Presentation(SlideViewerComponent slideViewerComponent, SlideController slideController) {
		this.slideViewComponent = slideViewerComponent;
		this.slideController = slideController;
		slideController.clear();
	}

	public SlideViewerComponent getSlideViewerComponent() {
		return this.slideViewComponent;
	}

	public void setShowView(SlideViewerComponent slideViewerComponent) {
		this.slideViewComponent = slideViewerComponent;
	}

	public SlideController getSlideController() {
		return this.slideController;
	}

	public String getTitle() {
		return this.showTitle;
	}

	public void setTitle(String nt) {
		this.showTitle = nt;
	}

	public void updateSlideView() {
		slideViewComponent.update(this, this.slideController.getCurrentSlide());
	}

	public void exit(int n) {
		System.exit(n);
	}
}
