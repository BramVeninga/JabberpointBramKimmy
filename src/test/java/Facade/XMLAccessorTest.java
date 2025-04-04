package Facade;

import Observer.Presentation;
import Slide.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.w3c.dom.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Vector;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class XMLAccessorTest
{
    private XMLAccessor xmlAccessor;
    private Presentation mockPresentation;
    private Slide mockSlide;

    @TempDir
    Path tempDir;

    @BeforeEach
    void setUp()
    {
        xmlAccessor = new XMLAccessor();
        mockPresentation = mock(Presentation.class);
        mockSlide = mock(Slide.class);
    }

    @Test
    void testLoadFile() throws Exception
    {
        // Test met een geldige XML
        File testFile = tempDir.resolve("test.xml").toFile();
        Files.writeString(testFile.toPath(), """
                                             <?xml version="1.0"?>
                                             <presentation>
                                                 <showtitle>Test Presentation</showtitle>
                                                 <slide>
                                                     <title>First Slide</title>
                                                     <item kind="text" level="1">Hello World</item>
                                                     <item kind="image" level="2">image.jpg</item>
                                                 </slide>
                                             </presentation>
                                             """);

        xmlAccessor.loadFile(mockPresentation, testFile.getAbsolutePath());

        verify(mockPresentation).setTitle("Test Presentation");
        verify(mockPresentation, atLeastOnce()).append(any(Slide.class));
    }

    @Test
    void testSaveFile() throws IOException
    {
        // Mock de presentatie en slides
        when(mockPresentation.getTitle()).thenReturn("Saved Presentation");
        when(mockPresentation.getSize()).thenReturn(1);
        when(mockPresentation.getSlide(0)).thenReturn(mockSlide);
        when(mockSlide.getTitle()).thenReturn("Slide 1");

        Vector<SlideItem> slideItems = new Vector<>();
        slideItems.add(new TextItem(1, "Sample text"));
        when(mockSlide.getSlideItems()).thenReturn(slideItems);

        // Bestandspad genereren
        File testFile = tempDir.resolve("saved.xml").toFile();
        xmlAccessor.saveFile(mockPresentation, testFile.getAbsolutePath());

        // Controleer of het bestand correct is opgeslagen
        String content = Files.readString(testFile.toPath());
        assertTrue(content.contains("<showtitle>Saved Presentation</showtitle>"));
        assertTrue(content.contains("<title>Slide 1</title>"));
        assertTrue(content.contains("<item kind=\"text\" level=\"1\">Sample text</item>"));
    }

    @Test
    void testLoadSlideItem_TextItem()
    {
        Slide slide = new Slide();

        // Maak een mock XML-element voor een tekst-item
        Element item = mock(Element.class);
        NamedNodeMap attributes = mock(NamedNodeMap.class);
        Node kindNode = mock(Node.class);
        Node levelNode = mock(Node.class);

        when(item.getAttributes()).thenReturn(attributes);
        when(attributes.getNamedItem("kind")).thenReturn(kindNode);
        when(kindNode.getTextContent()).thenReturn("text");
        when(attributes.getNamedItem("level")).thenReturn(levelNode);
        when(levelNode.getTextContent()).thenReturn("1");
        when(item.getTextContent()).thenReturn("Sample Text");

        xmlAccessor.loadSlideItem(slide, item);

        assertEquals(1, slide.getSlideItems().size());
        assertTrue(slide.getSlideItems().get(0) instanceof TextItem);
    }

    @Test
    void testLoadSlideItem_BitmapItem()
    {
        Slide slide = new Slide();

        // Maak een mock XML-element voor een afbeelding-item
        Element item = mock(Element.class);
        NamedNodeMap attributes = mock(NamedNodeMap.class);
        Node kindNode = mock(Node.class);
        Node levelNode = mock(Node.class);

        when(item.getAttributes()).thenReturn(attributes);
        when(attributes.getNamedItem("kind")).thenReturn(kindNode);
        when(kindNode.getTextContent()).thenReturn("image");
        when(attributes.getNamedItem("level")).thenReturn(levelNode);
        when(levelNode.getTextContent()).thenReturn("2");
        when(item.getTextContent()).thenReturn("image.jpg");

        xmlAccessor.loadSlideItem(slide, item);

        assertEquals(1, slide.getSlideItems().size());
        assertTrue(slide.getSlideItems().get(0) instanceof BitmapItem);
    }
}
