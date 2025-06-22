package Command;

import Command.Commands.*;
import Facade.SlideViewerFrame;
import Observer.SlideViewerComponent;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MenuController extends MenuBar
{
    private static final String IO_EXCEPTION = "io_exception";
    private static final String LOAD_ERROR = "load_error";
    private static final String SAVE_ERROR = "save_error";
    private static final String SAVE_FILE = "dump.xml";

    protected static final String FILE = "File";
    protected static final String VIEW = "View";
    protected static final String DEMO = "Demo";
    protected static final String HELP = "Help";

    private static final long serialVersionUID = 227L;
    private static MenuController menuController;

    private Frame frame;
    private SlideViewerComponent viewerComponent;


    private MenuController(Frame frame)
    {
        this.frame = frame;
        this.setupMenuItems();
    }

    public static MenuController getInstance(SlideViewerFrame frame)
    {
        if (MenuController.menuController == null)
        {
            MenuController.menuController = new MenuController(frame);
        }

        return MenuController.menuController;
    }

    public static MenuController getInstance()
    {
        if (MenuController.menuController == null)
        {
            MenuController.menuController = new MenuController(SlideViewerFrame.getInstance());
        }

        return MenuController.menuController;
    }

    public String getIoException()
    {
        return IO_EXCEPTION;
    }

    public String getLoadError()
    {
        return LOAD_ERROR;
    }

    public String getSaveError()
    {
        return SAVE_ERROR;
    }

    public String getSaveFile()
    {
        return SAVE_FILE;
    }

    public void setViewerComponent(SlideViewerComponent viewerComponent)
    {
        this.viewerComponent = viewerComponent;
    }

    public SlideViewerComponent getViewerComponent()
    {
        return this.viewerComponent;
    }

    private void setupMenuItems()
    {
        // Alle "File" menu-items
        ArrayList<Command> fileCommands = new ArrayList<Command>();
        fileCommands.add(new MenuOpen());
        fileCommands.add(new MenuNew());
        fileCommands.add(new MenuSave());
        fileCommands.add(new Exit());

        // Alle "View" menu-items
        ArrayList<Command> viewCommands = new ArrayList<Command>();
        viewCommands.add(new Previous());
        viewCommands.add(new Next());
        viewCommands.add(new GoToPage());

        // Alle "Demo" menu-items
        ArrayList<Command> demoCommands = new ArrayList<Command>();
        demoCommands.add(new MenuDemoBase());
        demoCommands.add(new MenuDemoImage());
        demoCommands.add(new MenuDemoSubject());

        // Alle "Help" menu-items
        ArrayList<Command> helpCommands = new ArrayList<Command>();
        helpCommands.add(new ShowAbout());

        // Menu's toevogen
        add(this.createMenu(FILE, fileCommands, true));
        add(this.createMenu(VIEW, viewCommands, false));
        add(this.createMenu(DEMO, demoCommands, false));
        setHelpMenu(this.createMenu(HELP, helpCommands, false));
    }

    private Menu createMenu(String menuTitle, ArrayList<Command> commands, boolean addSeparator)
    {
        Menu menu = new Menu(menuTitle);
        MenuItem menuItem;

        for (int i = 0; i < commands.size(); i++)
        {
            Command command = commands.get(i);

            if (i == commands.size() - 1 && addSeparator)
            {
                menu.addSeparator();
            }

            menu.add(menuItem = this.createMenuItem(command.getLabel(), command.getShortcut()));

            menuItem.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent actionEvent)
                {
                    executeCommand(command);
                }
            });
        }

        return menu;
    }

    public Frame getFrame()
    {
        return this.frame;
    }

    public void executeCommand(Command command)
    {
        command.execute();
    }

    public MenuItem createMenuItem(String name, char shortcut)
    {
        return new MenuItem(name, new MenuShortcut(shortcut));
    }
}
