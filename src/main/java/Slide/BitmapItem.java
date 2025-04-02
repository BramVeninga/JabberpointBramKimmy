package Slide;

import JabberPoint.Style;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;

public class BitmapItem extends SlideItem
{
    protected static final String NOT_FOUND_IMAGE_PATH = "images/NotFound.png";
    protected static final String FILE = "Bestand ";
    protected static final String NOT_FOUND = "niet gevonden";
    protected static final String NOT_FOUND_IMAGE_NAME = "not-found";

    private BufferedImage image;
    private String imageName;

    public BitmapItem(int level, String imageName)
    {
        super(level);
        this.imageName = imageName;

        try
        {
            this.image = ImageIO.read(new File(imageName));
        }
        catch (IOException exception)
        {
            System.out.println(FILE + this.imageName + NOT_FOUND);

            try
            {
                this.image = ImageIO.read(new File(NOT_FOUND_IMAGE_PATH));
                this.imageName = NOT_FOUND_IMAGE_NAME;
            }
            catch (IOException notFoundException)
            {
                System.err.println(FILE + this.imageName + NOT_FOUND);
            }
        }
    }

    public BitmapItem()
    {
        this(0, null);
    }

    public String getName()
    {
        return this.imageName;
    }

    @Override
    public Rectangle getBoundingBox(Graphics graphics, ImageObserver imageObserver, float scale, Style style)
    {
        return new Rectangle((int) (style.getIndent() * scale), 0, (int) (this.image.getWidth(imageObserver) * scale), ((int) (style.getLeading() * scale)) + (int) (this.image.getHeight(imageObserver) * scale));
    }

    @Override
    public void draw(int x, int y, float scale, Graphics graphics, Style style, ImageObserver imageObserver)
    {
        int width = x + (int) (style.getIndent() * scale);
        int height = y + (int) (style.getLeading() * scale);

        graphics.drawImage(this.image, width, height, (int) (this.image.getWidth(imageObserver) * scale), (int) (this.image.getHeight(imageObserver) * scale), imageObserver);
    }

    @Override
    public String toString()
    {
        return "BitmapItem[" + this.imageName + "]";
    }
}
