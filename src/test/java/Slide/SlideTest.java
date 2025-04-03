package Slide;

import JabberPoint.Style;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.Vector;

import static org.junit.jupiter.api.Assertions.*;

class SlideTest
{
    private Slide slide;

    @BeforeEach
    public void setup()
    {
        this.slide = new Slide();
    }

    @Test
    public void testSetTitleToHelloWorld_ExpectsTitleIsHelloWorld()
    {
        assertDoesNotThrow(() -> this.slide.setTitle("Hello World"));
        assertEquals("Hello World", this.slide.getTitle());
    }

    @Test
    public void testAppendSingleTextItem_ExpectsSizeToBe1()
    {
        TextItem textItem = new TextItem(0, "Sample Text");
        assertEquals(0, this.slide.getSize());
        assertEquals(new Vector<>(), this.slide.getSlideItems());
        assertDoesNotThrow(() -> this.slide.append(textItem));
        assertEquals(1, this.slide.getSize());
        assertEquals(textItem, this.slide.getSlideItem(0));
    }

    @Test
    public void testBitmapItemWithNonExistentImage_ExpectsImageNotFoundName()
    {
        BitmapItem bitmapItem = new BitmapItem(0, "NonExistentImage");
        assertEquals(BitmapItem.NOT_FOUND_IMAGE_NAME, bitmapItem.getName());
    }

    @Test
    public void testBitmapItemWithInvalidImagePath_ExpectsImagePathNotFound()
    {
        BitmapItem bitmapItem = new BitmapItem(0, BitmapItem.NOT_FOUND_IMAGE_PATH);
        assertEquals(BitmapItem.NOT_FOUND_IMAGE_PATH, bitmapItem.getName());
    }

    @Test
    public void testBitmapItemWithValidImage_ExpectCorrectImageData()
    {
        BitmapItem bitmapItem = new BitmapItem(2, "src/main/resources/hondDemo.jpg");
        assertNotNull(bitmapItem);
        assertEquals(2, bitmapItem.getLevel());
        assertEquals("src/main/resources/hondDemo.jpg", bitmapItem.getName());
    }

    @Test
    public void testBitmapItemWithInvalidImage_ExpectsNotFoundImage()
    {
        BitmapItem bitmapItem = new BitmapItem(2, "src/main/resources/images/nonExistentImage.jpg");
        assertNotNull(bitmapItem);
        assertEquals(2, bitmapItem.getLevel());
        assertEquals("not-found", bitmapItem.getName());
    }

    @Test
    public void testBitmapItemBoundingBoxForValidImage_ExpectsCorrectDimensions()
    {
        Style style = new Style(0, Color.blue, 14, 12);
        Style.createStyles();

        BitmapItem bitmapItem = new BitmapItem(3, "src/main/resources/hondDemo.jpg");
        Rectangle boundingBox = bitmapItem.getBoundingBox(null, null, 1.2f, style);

        assertNotEquals(null, boundingBox);
        assertEquals(0, boundingBox.getX());
        assertEquals(0, boundingBox.getY());
        // Adjusted values for sampleImage.jpg
        assertEquals(300.0, boundingBox.getWidth());
        assertEquals(200.0, boundingBox.getHeight());
    }

    @Test
    public void testTextItemWithNullText_ExpectsEmptyString()
    {
        TextItem textItem = new TextItem(0, null);
        assertEquals("", textItem.getText());
    }

    @Test
    public void testTextItemWithValidText_ExpectsCorrectText()
    {
        TextItem textItem = new TextItem(0, "Hello, this is a test!");
        assertEquals("Hello, this is a test!", textItem.getText());
    }
}