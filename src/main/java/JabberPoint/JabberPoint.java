package JabberPoint;

import Command.Commands.MenuDemoBase;
import Command.MenuController;
import Facade.SlideViewerFrame;
import Facade.XMLAccessor;
import Observer.ControlPresentation;
import Observer.Presentation;
import Observer.SlideViewerComponent;
import Slide.Slide;

import javax.swing.*;
import java.io.IOException;
import java.awt.GraphicsEnvironment;


public class JabberPoint
{
    // Constants
    protected static final String IOERR = "IO Error: ";
    protected static final String JABERR = "Jabberpoint Error ";

    public static void initialize(String[] argv) throws IOException {
        // Creëer de stijlen
        Style.createStyles();

        // Maak de presentatie aan
        Presentation presentation = new Presentation();

        // Zet de presentatie in het control singleton
        ControlPresentation controlPresentation = ControlPresentation.getInstance();
        controlPresentation.setPresentation(presentation);

        if(argv.length == 0)
        {
            new MenuDemoBase().execute();
        }
        else
        {
            new XMLAccessor().loadFile(presentation, argv[0]);
        }

        presentation.setSlideNumber(0);
    }

    // Het hoofdprogramma
    public static void main(String argv[])
    {
        try
        {
            initialize(argv);
            SlideViewerFrame.getInstance().setVisible(true);
        }
        catch (IOException exception)
        {
            JOptionPane.showMessageDialog(null, MenuController.getInstance().getIoException() + exception, MenuController.getInstance().getLoadError(), JOptionPane.ERROR_MESSAGE);
        }
    }

}
