package Observer;

public class ControlPresentation
{
    private static ControlPresentation controlPresentation; // Singleton instantie van ControlPresentation

    private Presentation presentation;  // Huidige presentatie die wordt beheerd

    // Private constructor zodat er slechts één instantie van ControlPresentation kan bestaan
    private ControlPresentation()
    {
    }

    // Retourneer de singleton instantie van ControlPresentation, maak deze aan als die nog niet bestaat
    public static ControlPresentation getInstance()
    {
        if (ControlPresentation.controlPresentation == null)  // Als de instantie nog niet bestaat
        {
            ControlPresentation.controlPresentation = new ControlPresentation();  // Maak een nieuwe instantie
        }

        return ControlPresentation.controlPresentation;  // Geef de bestaande instantie terug
    }

    // Retourneer de huidige presentatie
    public Presentation getPresentation()
    {
        return this.presentation;
    }

    // Stel de presentatie in
    public void setPresentation(Presentation presentation)
    {
        this.presentation = presentation;
    }

    // Ga naar de vorige slide, als de huidige slide niet de eerste is
    public void previousSlide()
    {
        if (this.presentation.getSlideNumber() > 0)  // Als de huidige slide niet de eerste is
        {
            // Verlaag het slide nummer om naar de vorige slide te gaan
            this.presentation.setSlideNumber(this.presentation.getSlideNumber() - 1);
        }
    }

    // Ga naar de volgende slide, als de huidige slide niet de laatste is
    public void nextSlide()
    {
        if (this.presentation.getSlideNumber() < (this.presentation.getSlideList().size() - 1))  // Als de huidige slide niet de laatste is
        {
            // Verhoog het slide nummer om naar de volgende slide te gaan
            this.presentation.setSlideNumber(this.presentation.getSlideNumber() + 1);
        }
    }

    // Stel een specifiek slide nummer in, mits het binnen de geldige range ligt
    public void setSlideNumber(int slideNumber)
    {
        if (slideNumber >= 0 && slideNumber < this.presentation.getSlideList().size())  // Als het slide nummer binnen de geldige range ligt
        {
            // Stel het slide nummer in
            this.presentation.setSlideNumber(slideNumber);
        }
    }

    // Wis de inhoud van de presentatie (bijvoorbeeld alle slides)
    public void clear()
    {
        this.presentation.clear();  // Roep de clear methode van de presentatie aan
    }
}
