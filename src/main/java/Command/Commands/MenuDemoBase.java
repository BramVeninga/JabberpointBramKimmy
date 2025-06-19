package Command.Commands;

import Command.Command;
import Command.MenuController;
import Facade.Accessor;
import Facade.DemoPresentation;
import Observer.ControlPresentation;
import Observer.Presentation;
import Observer.SlideViewerComponent;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class MenuDemoBase implements Command
{
    @Override
    public String getLabel()
    {
        return "Basic Demo";
    }

    @Override
    public char getShortcut()
    {
        return '1';
    }

    @Override
    public void execute()
    {
        ControlPresentation.getInstance().clear(); // Maakt presentatie leeg
        Frame frame = MenuController.getInstance().getFrame();
        Accessor demoPresentation = new DemoPresentation();

        try
        {
            // 1. Haal de presentatie op
            Presentation presentation = ControlPresentation.getInstance().getPresentation();

            // 2. Voeg opnieuw de viewer toe als listener
            SlideViewerComponent viewer = MenuController.getInstance().getViewerComponent();
            presentation.addPresentationListener(viewer);

            // 3. Laad demo
            demoPresentation.loadFile(presentation, null);

            System.out.println("Aantal slides geladen: " + presentation.getSize());
            if (presentation.getSize() > 0) {
                System.out.println("Eerste slide title: " + presentation.getSlide(0).getTitle());
            }

            // 4. Zet eerste slide zichtbaar
            presentation.setSlideNumber(0);
        }
        catch (IOException exception)
        {
            JOptionPane.showMessageDialog(frame, MenuController.getInstance().getIoException() + exception,
                    MenuController.getInstance().getLoadError(), JOptionPane.ERROR_MESSAGE);
        }

        frame.repaint(); // Herteken het scherm
    }
}
