package Facade;

import Observer.Presentation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class AccessorTest
{
    private Presentation presentation;

    // Setup method to initialize a new Presentation instance before each test
    @BeforeEach
    public void setup()
    {
        this.presentation = new Presentation();
    }

    // Test if the demo presentation loads successfully and has the correct title and slide count
    @Test
    public void loadDemoPresentation_expectsCorrectTitleAndSlideCount()
    {
        assertDoesNotThrow(() -> new DemoPresentation().loadFile(this.presentation, ""));
        assertEquals("Demo Presentation", this.presentation.getTitle(), "Presentation title should match 'Demo Presentation'");
        assertEquals(4, this.presentation.getSize(), "Demo Presentation should contain 4 slides");
    }

    // Test if the image demo presentation loads successfully with expected title and slide count
    @Test
    public void loadImageDemoPresentation_expectsCorrectTitleAndSlideCount()
    {
        assertDoesNotThrow(() -> new ImageDemo().loadFile(this.presentation, ""));
        assertEquals("Image Demo Presentation", this.presentation.getTitle(), "Title should match 'Image Demo Presentation'");
        assertEquals(4, this.presentation.getSize(), "Image Demo should contain 4 slides");
    }

    // Test if XMLAccessor loads a valid XML file and sets the expected title
    @Test
    public void loadXmlFile_withValidFile_expectsTitleToBeSet() throws IOException
    {
        assertDoesNotThrow(() -> new XMLAccessor().loadFile(this.presentation, "test.xml"));
        assertEquals("Test", this.presentation.getTitle(), "Title should match 'Test' after loading 'test.xml'");
    }

    // Test if XMLAccessor fails to load a non-existent file and retains the default title
    @Test
    public void loadXmlFile_withInvalidFile_expectsDefaultTitle() throws IOException
    {
        new XMLAccessor().loadFile(this.presentation, "notAnActualFile.xml");
        assertEquals("", this.presentation.getTitle(), "Title should remain empty when an invalid file is loaded");
    }
}
