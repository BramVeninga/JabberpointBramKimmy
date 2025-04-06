package Command.Commands;

import Command.Command;
import Command.AboutBox;

public class ShowAbout implements Command
{
    @Override
    public String getLabel()
    {
        return "About";
    }

    @Override
    public char getShortcut()
    {
        return 'H';
    }

    @Override
    public void execute()
    {
        AboutBox.show();
    }
}
