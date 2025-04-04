package Command;

import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import javax.swing.*;
import java.awt.*;

import static org.mockito.Mockito.*;

class AboutBoxTest
{

    @Test
    void testShowDisplaysMessageDialog()
    {
        // Mock de MenuController om een Frame te retourneren
        MenuController mockMenuController = mock(MenuController.class);
        Frame mockFrame = mock(Frame.class);
        when(mockMenuController.getFrame()).thenReturn(mockFrame);

        // Gebruik Mockito om een statische methode van JOptionPane te mocken
        try (MockedStatic<MenuController> menuControllerMock = Mockito.mockStatic(MenuController.class);
             MockedStatic<JOptionPane> optionPaneMock = Mockito.mockStatic(JOptionPane.class))
        {

            menuControllerMock.when(MenuController::getInstance).thenReturn(mockMenuController);

            // Roep de show() methode aan
            AboutBox.show();

            // Controleer of JOptionPane.showMessageDialog correct is aangeroepen
            optionPaneMock.verify(() -> JOptionPane.showMessageDialog(
                    eq(mockFrame),
                    contains("JabberPoint is a primitive slide-show program"),
                    eq("About JabberPoint"),
                    eq(JOptionPane.INFORMATION_MESSAGE)
            ));
        }
    }
}
