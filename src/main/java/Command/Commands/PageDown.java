package Command.Commands;

import Command.Command;
import Observer.ControlPresentation;

public class PageDown implements Command
{
    @Override
    public String getLabel()
    {
        return "Previous slide";
    }

    @Override
    public char getShortcut()
    {
        return ',';
    }

    @Override
    public void execute()
    {
        ControlPresentation.getInstance().previousSlide();
    }
}
