package Command;

import Observer.ControlPresentation;
import Observer.Presentation;
import JabberPoint.JabberPoint;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class KeyControllerTest {

    private ControlPresentation controlPresentation;
    private Presentation presentation;
    private KeyController keyController;
    private Component dummyComponent;

    // Initialize the test objects before each test
    @BeforeEach
    public void setup() throws IOException {
        this.keyController = KeyController.getInstance();
        this.dummyComponent = new JFrame();
        JabberPoint.initialize(new String[0]);
        this.controlPresentation = ControlPresentation.getInstance();
        this.presentation = this.controlPresentation.getPresentation();
    }

    // Test if pressing "next slide" keys correctly increments the slide number
    @ParameterizedTest
    @ValueSource(ints = {KeyEvent.VK_PAGE_DOWN, KeyEvent.VK_DOWN, KeyEvent.VK_ENTER, '+'})
    void pressingNextSlideKeys_fromSlide0_expectsSlide1(int keyCode) {
        assertEquals(0, this.presentation.getSlideNumber(), "Initial slide number should be 0");

        this.keyController.keyPressed(new KeyEvent(this.dummyComponent, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, keyCode, 'a'));

        assertEquals(1, this.presentation.getSlideNumber(), "Slide number should increment to 1 after pressing next slide key");
    }

    // Test if pressing "previous slide" keys correctly decrements the slide number
    @ParameterizedTest
    @ValueSource(ints = {KeyEvent.VK_PAGE_UP, KeyEvent.VK_UP, '-'})
    void pressingPrevSlideKeys_fromSlide1_expectsSlide0(int keyCode) {
        this.presentation.setSlideNumber(1);
        assertEquals(1, this.presentation.getSlideNumber(), "Initial slide number should be 1");

        this.keyController.keyPressed(new KeyEvent(this.dummyComponent, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, keyCode, 'a'));

        assertEquals(0, this.presentation.getSlideNumber(), "Slide number should decrement to 0 after pressing previous slide key");
    }
}

