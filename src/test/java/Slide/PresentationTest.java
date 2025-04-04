package Slide;

import JabberPoint.JabberPoint;

import Observer.ControlPresentation;
import Observer.Presentation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class PresentationTest
{
    @BeforeEach
    public void setup() throws IOException
    {
        JabberPoint.initialize(new String[0]);
    }

    @Test
    public void testGetInstance_ExpectsControlPresentationInstanceNotNull()
    {
        assertNotNull(ControlPresentation.getInstance());
    }

    @Test
    public void testGetPresentation_ExpectsValidPresentationInstance()
    {
        assertNotNull(ControlPresentation.getInstance().getPresentation());
    }

    @Test
    public void testSetNewPresentation_ExpectsPresentationTitleSetCorrectly()
    {
        Presentation newPresentation = new Presentation();
        newPresentation.setTitle("Sample Presentation");
        assertDoesNotThrow(() -> ControlPresentation.getInstance().setPresentation(newPresentation));
        assertEquals(newPresentation, ControlPresentation.getInstance().getPresentation());
        assertEquals(newPresentation.getTitle(), ControlPresentation.getInstance().getPresentation().getTitle());
    }

    @Test
    public void testPrevSlideFromSlide2_ExpectsSlideNumberDecrementedTo1()
    {
        assertDoesNotThrow(() -> ControlPresentation.getInstance().getPresentation().setSlideNumber(2));
        assertEquals(2, ControlPresentation.getInstance().getPresentation().getSlideNumber());
        assertDoesNotThrow(() -> ControlPresentation.getInstance().previousSlide());
        assertEquals(1, ControlPresentation.getInstance().getPresentation().getSlideNumber());
    }

    @Test
    public void testPrevSlideFromSlide0_ExpectsNoDecrement()
    {
        assertDoesNotThrow(() -> ControlPresentation.getInstance().getPresentation().setSlideNumber(0));
        assertEquals(0, ControlPresentation.getInstance().getPresentation().getSlideNumber());
        assertDoesNotThrow(() -> ControlPresentation.getInstance().previousSlide());
        assertEquals(0, ControlPresentation.getInstance().getPresentation().getSlideNumber());
    }

    @Test
    public void testNextSlideFromSlide1_ExpectsSlideNumberIncrementedTo2()
    {
        assertDoesNotThrow(() -> ControlPresentation.getInstance().getPresentation().setSlideNumber(1));
        assertEquals(1, ControlPresentation.getInstance().getPresentation().getSlideNumber());
        assertDoesNotThrow(() -> ControlPresentation.getInstance().nextSlide());
        assertEquals(2, ControlPresentation.getInstance().getPresentation().getSlideNumber());
    }

    @Test
    public void testNextSlideFromLastSlide_ExpectsNoIncrementBeyondLastSlide()
    {
        int capacity = ControlPresentation.getInstance().getPresentation().getSize() - 1;
        assertDoesNotThrow(() -> ControlPresentation.getInstance().getPresentation().setSlideNumber(capacity));
        assertEquals(capacity, ControlPresentation.getInstance().getPresentation().getSlideNumber());
        assertDoesNotThrow(() -> ControlPresentation.getInstance().nextSlide());
        assertEquals(capacity, ControlPresentation.getInstance().getPresentation().getSlideNumber());
    }

    @Test
    public void testSetSlideNumberFromNegative_ExpectsSlideNumberSetTo0()
    {
        assertDoesNotThrow(() -> ControlPresentation.getInstance().setSlideNumber(-1));
        assertEquals(0, ControlPresentation.getInstance().getPresentation().getSlideNumber());
    }

    @Test
    public void testSetSlideNumberTo2_ExpectsSlideNumberSetTo2()
    {
        assertDoesNotThrow(() -> ControlPresentation.getInstance().setSlideNumber(2));
        assertEquals(2, ControlPresentation.getInstance().getPresentation().getSlideNumber());
    }

    @Test
    public void testClearPresentation_ExpectsPresentationSizeResetToZero()
    {
        assertEquals(4, ControlPresentation.getInstance().getPresentation().getSize());
        assertDoesNotThrow(() -> ControlPresentation.getInstance().clear());
        assertEquals(0, ControlPresentation.getInstance().getPresentation().getSize());
    }
}

