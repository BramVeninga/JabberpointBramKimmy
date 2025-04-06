package JabberPoint;

import Command.Commands.MenuDemoBase;
import Facade.SlideViewerFrame;
import Facade.XMLAccessor;
import Observer.ControlPresentation;
import Observer.Presentation;

import javax.swing.*;
import java.io.IOException;
import java.awt.GraphicsEnvironment;


public class JabberPoint
{
    // Constants
    protected static final String IOERR = "IO Error: ";
    protected static final String JABERR = "Jabberpoint Error ";

    public static void initialize(String[] argv) throws IOException
    {
        // Creëer de verschillende stijlen
        Style.createStyles();

        // Creëer de presentatie
        Presentation presentation = new Presentation();

        // Haal de ControlPresentation op en zet de presentatie erin
        ControlPresentation controlPresentation = ControlPresentation.getInstance();
        controlPresentation.setPresentation(presentation);

        // Probeer de presentatie te laden
        if (argv.length == 0)
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

            if (!GraphicsEnvironment.isHeadless()) {
                SlideViewerFrame.getInstance().setVisible(true);
            }
        }
        catch (IOException exception)
        {
            if (!GraphicsEnvironment.isHeadless()) {
                JOptionPane.showMessageDialog(null, IOERR + exception, JABERR, JOptionPane.ERROR_MESSAGE);
            } else {
                System.err.println(IOERR + exception);
            }
        }
    }

}
