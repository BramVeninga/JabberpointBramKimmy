package Command;

public interface Command
{
    public abstract String getLabel();

    public abstract char getShortcut();

    public abstract void execute();
}
