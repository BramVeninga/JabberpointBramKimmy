package Slide;

import JabberPoint.Style;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.awt.*;
import java.awt.image.ImageObserver;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BitmapItemTest
{

    private BitmapItem bitmapItem;
    private Graphics graphics;
    private ImageObserver imageObserver;
    private Style style;

    @BeforeEach
    void setUp()
    {
        bitmapItem = new BitmapItem(1, "test-image.jpg");
        graphics = mock(Graphics.class);
        imageObserver = mock(ImageObserver.class);
        style = mock(Style.class);

        when(style.getIndent()).thenReturn(10);
        when(style.getLeading()).thenReturn(5);
    }

    @Test
    void testToString()
    {
        assertEquals("BitmapItem[test-image.jpg]", bitmapItem.toString(), "toString output mismatch");
    }

}
