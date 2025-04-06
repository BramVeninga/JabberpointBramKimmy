package Observer;

import Slide.Slide;

public interface PresentationListener
{
    public abstract void update(Presentation presentation, Slide data);
}
