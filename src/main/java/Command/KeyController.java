package Command;

import Command.Commands.Exit;
import Command.Commands.Previous;
import Command.Commands.Next;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyController extends KeyAdapter
{
    private static KeyController keyController;
    // Constants
    private final Command NEXT_SLIDE_COMMAND = new Previous();
    private final Command PREVIOUS_SLIDE_COMMAND = new Next();
    private final Command EXIT_COMMAND = new Exit();

    private KeyController()
    {
    }

    public static KeyController getInstance()
    {
        if (KeyController.keyController == null)
        {
            KeyController.keyController = new KeyController();
        }

        return KeyController.keyController;
    }

    public void keyPressed(KeyEvent keyEvent)
    {
        switch (keyEvent.getKeyCode())
        {
            case KeyEvent.VK_PAGE_DOWN:
            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_ENTER:
            case '+':
                this.executeCommand(PREVIOUS_SLIDE_COMMAND);
                break;
            case KeyEvent.VK_PAGE_UP:
            case KeyEvent.VK_UP:
            case '-':
                this.executeCommand(NEXT_SLIDE_COMMAND);
                break;
            case 'q':
            case 'Q':
                this.executeCommand(EXIT_COMMAND);
                break;
            default:
                break;
        }
    }

    public void executeCommand(Command command)
    {
        command.execute();
    }
}
