package Facade;

import Observer.Presentation;

import java.io.IOException;

public abstract class Accessor
{
    public static final String DEMO_NAME = "Demonstratie presentatie";
    public static final String DEFAULT_EXTENSION = ".xml";

    public Accessor()
    {
    }

    abstract public void loadFile(Presentation presentation, String filename) throws IOException;

    abstract public void saveFile(Presentation presentation, String filename) throws IOException;

}
