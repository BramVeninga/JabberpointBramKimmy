package Slide;

import JabberPoint.Style;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TextItemTest
{

    private TextItem textItem;
    private Style mockStyle;
    private Graphics mockGraphics;
    private ImageObserver mockObserver;
    private Graphics2D realGraphics;

    @BeforeEach
    void setUp()
    {
        textItem = new TextItem(1, "Test Text");
        mockStyle = mock(Style.class);
        mockGraphics = mock(Graphics.class);
        mockObserver = mock(ImageObserver.class);

        when(mockStyle.getFont(anyFloat())).thenReturn(new Font("Arial", Font.PLAIN, 12));
        when(mockStyle.getLeading()).thenReturn(5);
        when(mockStyle.getIndent()).thenReturn(10);
        when(mockStyle.getColor()).thenReturn(Color.BLACK);
    }

    @Test
    void testConstructorWithText()
    {
        assertEquals("Test Text", textItem.getText(), "De tekst moet correct worden ingesteld.");
    }

    @Test
    void testDefaultConstructor()
    {
        TextItem defaultItem = new TextItem();
        assertEquals("No Text Given", defaultItem.getText(), "De standaardtekst moet 'No Text Given' zijn.");
    }

    @Test
    void testGetText()
    {
        assertEquals("Test Text", textItem.getText());
    }

    @Test
    void testGetAttributedString()
    {
        assertDoesNotThrow(() -> textItem.getAttributedString(mockStyle, 1.0f), "Moet zonder fouten een AttributedString maken.");
    }

    @Test
    void testDrawWithEmptyText()
    {
        TextItem emptyItem = new TextItem(1, "");
        emptyItem.draw(0, 0, 1.0f, mockGraphics, mockStyle, mockObserver);
        verify(mockGraphics, never()).drawString(anyString(), anyInt(), anyInt());
    }

    @Test
    void testToString()
    {
        assertEquals("TextItem[1,Test Text]", textItem.toString(), "De toString moet correct geformatteerd zijn.");
    }

    @Test
    void testBoundingBoxSizeCalculation()
    {
        TextItem item = new TextItem(1, "Hello World");

        BufferedImage bufferedImage = new BufferedImage(500, 500, BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics = bufferedImage.createGraphics();

        Style testStyle = mock(Style.class);
        when(testStyle.getFont(anyFloat())).thenReturn(new Font("Arial", Font.PLAIN, 20));
        when(testStyle.getLeading()).thenReturn(5);
        when(testStyle.getIndent()).thenReturn(10);

        Rectangle boundingBox = item.getBoundingBox(graphics, mockObserver, 1.0f, testStyle);

        assertNotNull(boundingBox, "Bounding box mag niet null zijn.");
        assertTrue(boundingBox.width > 0, "De breedte moet groter dan 0 zijn.");
        assertTrue(boundingBox.height > 0, "De hoogte moet groter dan 0 zijn.");
        assertEquals(10, boundingBox.x, "De x-coördinaat moet overeenkomen met de indent.");
    }
}
