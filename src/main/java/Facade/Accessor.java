package Facade;

import Observer.Presentation;

import java.io.IOException;

public abstract class Accessor
{
    private static final String DEMO_NAME = "Demonstratie presentatie";
    private static final String DEFAULT_EXTENSION = ".xml";

    public Accessor()
    {
    }

    public String getDemoName()
    {
        return DEMO_NAME;
    }

    public String getDefaultExtension()
    {
        return DEFAULT_EXTENSION;
    }

    abstract public void loadFile(Presentation presentation, String filename) throws IOException;

    abstract public void saveFile(Presentation presentation, String filename) throws IOException;

}
