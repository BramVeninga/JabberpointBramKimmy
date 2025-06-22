package Command;

import Command.Commands.*;
import JabberPoint.JabberPoint;
import Observer.ControlPresentation;
import Observer.Presentation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class CommandTests
{

    private ControlPresentation controlPresentation;
    private Presentation presentation;
    private Next nextCommand;
    private Previous previousCommand;
    private MenuDemoBase menuDemoBaseCommand;
    private MenuDemoImage menuDemoImageCommand;
    private MenuDemoSubject menuDemoSubjectCommand;

    // Initialize test objects before each test
    @BeforeEach
    public void setup() throws IOException
    {
        JabberPoint.initialize(new String[0]);
        this.controlPresentation = ControlPresentation.getInstance();
        this.presentation = this.controlPresentation.getPresentation();
        this.nextCommand = new Next();
        this.previousCommand = new Previous();
        this.menuDemoBaseCommand = new MenuDemoBase();
        this.menuDemoImageCommand = new MenuDemoImage();
        this.menuDemoSubjectCommand = new MenuDemoSubject();
    }

    // Test if PageUp correctly increments the slide number
    @Test
    public void executePageUp_fromSlide0_expectsSlide1()
    {
        assertEquals(0, this.presentation.getSlideNumber(), "Initial slide number should be 0");
        this.nextCommand.execute();
        assertEquals(1, this.presentation.getSlideNumber(), "Slide number should increment to 1");
    }

    // Test if PageDown correctly decrements the slide number
    @Test
    public void executePageDown_afterPageUp_expectsSlide0()
    {
        assertEquals(0, this.presentation.getSlideNumber(), "Initial slide number should be 0");
        this.nextCommand.execute();
        assertEquals(1, this.presentation.getSlideNumber(), "Slide number should increment to 1");
        this.previousCommand.execute();
        assertEquals(0, this.presentation.getSlideNumber(), "Slide number should decrement back to 0");
    }

    // Test if executing MenuDemoBase loads the demo presentation correctly
    @Test
    public void executeMenuDemoBase_expectsDemoPresentationLoaded()
    {
        assertDoesNotThrow(() -> this.menuDemoBaseCommand.execute());
        assertEquals("Demo Presentation", this.presentation.getTitle(), "Title should match 'Demo Presentation'");
        assertEquals(4, this.presentation.getSize(), "Demo Presentation should contain 4 slides");
    }

    // Test if executing MenuDemoImage loads the image demo presentation correctly
    @Test
    public void executeMenuDemoImage_expectsImageDemoLoaded()
    {
        assertDoesNotThrow(() -> this.menuDemoImageCommand.execute());
        assertEquals("Image Demo Presentation", this.presentation.getTitle(), "Title should match 'Image Demo Presentation'");
        assertEquals(4, this.presentation.getSize(), "Image Demo should contain 4 slides");
    }

    // Test if executing MenuDemoPlenair loads the plenary meeting demo correctly
    @Test
    public void executeMenuDemoSubject_expectsPlenaryDemoLoaded()
    {
        assertDoesNotThrow(() -> this.menuDemoSubjectCommand.execute());
        assertEquals("Subject Demo", this.presentation.getTitle(), "Title should match 'Subject Demo'");
        assertEquals(4, this.presentation.getSize(), "Subject Demo should contain 4 slides");
    }
}
