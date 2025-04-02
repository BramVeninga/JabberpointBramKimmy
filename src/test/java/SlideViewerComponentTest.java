import Observer.ControlPresentation;
import Observer.Presentation;
import Observer.SlideViewerComponent;
import Slide.Slide;
import JabberPoint.JabberPoint;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class SlideViewerComponentTest {
    private SlideViewerComponent slideViewerComponent;

    // Setup method to initialize required components before each test
    @BeforeEach
    public void setup() throws IOException {
        // Initialize JabberPoint to set up the presentation environment
        JabberPoint.initialize(new String[0]);
        // Create a new SlideViewerComponent instance
        this.slideViewerComponent = new SlideViewerComponent();
    }

    // Test to ensure that the update method correctly updates the presentation size
    @Test
    public void update_withControlPresentation_expectsPresentationToBeControlPresentation() {
        // Get the current presentation from ControlPresentation
        Presentation controlPresentation = ControlPresentation.getInstance().getPresentation();

        // Create a new Slide instance (which will be passed as data)
        Slide newSlide = new Slide();

        // Verify that the presentation size is initially different
        assertNotEquals(controlPresentation.getSize(), this.slideViewerComponent.getPresentationSize());

        // Call the update method on the SlideViewerComponent
        assertDoesNotThrow(() -> this.slideViewerComponent.update(controlPresentation, newSlide));

        // Verify that after the update, the presentation size is the same
        assertEquals(controlPresentation.getSize(), this.slideViewerComponent.getPresentationSize());
    }

    // Test to ensure that when no slide is provided, the slide is null
    @Test
    public void update_withoutSlide_expectsSlideToBeNull() {
        // Call the update method with an empty presentation and null slide
        assertDoesNotThrow(() -> this.slideViewerComponent.update(new Presentation(), null));

        // Verify that the slide is indeed null
        assertNull(this.slideViewerComponent.getSlide());
    }
}

