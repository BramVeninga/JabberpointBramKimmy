package Observer;

import Facade.SlideViewerFrame;
import Slide.Slide;

import javax.swing.*;
import java.awt.*;

public class SlideViewerComponent extends JComponent implements PresentationListener {
    private static final long serialVersionUID = 227L;
    private static final Color BACKGROUND_COLOR = Color.WHITE;
    private static final Color COLOR = Color.BLACK;
    private static final String FONT_NAME = "Dialog";
    private static final int FONT_STYLE = Font.BOLD;
    private static final int FONT_HEIGHT = 20;
    private static final int X_POSITION = 1100;
    private static final int Y_POSITION = 20;

    private Slide slide;
    private Font labelFont;
    private JFrame frame;
    private int slideNumber;
    private int presentationSize;

    public SlideViewerComponent(JFrame frame) {
        setBackground(BACKGROUND_COLOR);
        labelFont = new Font(FONT_NAME, FONT_STYLE, FONT_HEIGHT);
        this.frame = frame;
    }

    public SlideViewerComponent() {
        this(SlideViewerFrame.getInstance());
    }

    public int getPresentationSize()
    {
        return this.presentationSize;
    }

    public Dimension getPreferredSize() {
        return new Dimension(Slide.WIDTH, Slide.HEIGHT);
    }

    public Slide getSlide()
    {
        return slide;
    }

    @Override
    public void update(Presentation presentation, Slide slide)
    {
        if(slide == null)
        {
            this.slide = null;
            this.slideNumber = presentation.getSlideNumber();
            this.presentationSize = presentation.getSize();
        }

        this.slide = slide;
        this.slideNumber = presentation.getSlideNumber();
        this.presentationSize = presentation.getSize();

        this.repaint();
        this.frame.setTitle(presentation.getTitle());
    }

    public void paintComponent(Graphics graphics) {
        graphics.setColor(BACKGROUND_COLOR);

        graphics.fillRect(0,0, getSize().width, getSize().height);
        graphics.setFont(this.labelFont);
        graphics.setColor(COLOR);

        if (this.slideNumber < 0 || this.slide == null)
        {
            return;
        }

        graphics.drawString("Slide " + (1 + this.slideNumber) + " of " + this.presentationSize, X_POSITION, Y_POSITION);

        Rectangle area = new Rectangle(0, Y_POSITION , getWidth(),  (getHeight() - Y_POSITION));
        slide.draw(graphics, area, this);
    }
}
