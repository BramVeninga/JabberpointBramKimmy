package Facade;

import Command.KeyController;
import Command.MenuController;
import Observer.ControlPresentation;
import Observer.SlideViewerComponent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class SlideViewerFrame extends JFrame
{
    private static final long serialVersionUID = 3227L;
    private static final String JABBERPOINT_TITLE = "JabberPoint 1.6 - OU";
    private static final int WIDTH = 1200;
    private static final int HEIGHT = 800;

    private static SlideViewerFrame slideViewerFrame;

    private SlideViewerFrame(String title)
    {
        super(title);

        SlideViewerComponent slideViewerComponent = new SlideViewerComponent(this);
        ControlPresentation.getInstance().getPresentation().addPresentationListener(slideViewerComponent);

        this.setupWindow(slideViewerComponent);
    }

    public static SlideViewerFrame getInstance()
    {
        if (SlideViewerFrame.slideViewerFrame == null)
        {
            SlideViewerFrame.slideViewerFrame = new SlideViewerFrame(JABBERPOINT_TITLE);
        }

        return SlideViewerFrame.slideViewerFrame;
    }

    public void setupWindow(SlideViewerComponent slideViewerComponent)
    {
        this.setTitle(JABBERPOINT_TITLE);

        this.addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent e)
            {
                System.exit(0);
            }
        });

        this.getContentPane().add(slideViewerComponent);

        this.addKeyListener(KeyController.getInstance());
        this.setMenuBar(MenuController.getInstance(this));

        this.setSize(new Dimension(WIDTH, HEIGHT));
        this.setVisible(true);
    }
}
