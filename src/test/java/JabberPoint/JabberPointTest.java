package JabberPoint;

import Command.Commands.MenuDemoBase;
import Facade.SlideViewerFrame;
import Facade.XMLAccessor;
import Observer.ControlPresentation;
import Observer.Presentation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import javax.swing.*;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class JabberPointTest {

    @BeforeEach
    void setUp() {
        // Reset singleton instances (indien nodig)
        ControlPresentation.getInstance().setPresentation(new Presentation());
    }

    @Test
    void testMainMethod()
    {
        try (
                MockedStatic<SlideViewerFrame> frameMock = mockStatic(SlideViewerFrame.class);
                MockedStatic<JOptionPane> optionPaneMock = mockStatic(JOptionPane.class)
        )
        {
            SlideViewerFrame mockFrame = mock(SlideViewerFrame.class);
            frameMock.when(SlideViewerFrame::getInstance).thenReturn(mockFrame);

            assertDoesNotThrow(() -> JabberPoint.main(new String[]{}));

            // Controleer of de GUI wordt weergegeven
            verify(mockFrame, times(1)).setVisible(true);

            // Controleer of er geen foutmeldingen zijn weergegeven
            optionPaneMock.verify(() -> JOptionPane.showMessageDialog(any(), anyString(), anyString(), anyInt()), never());
        }
    }
}
