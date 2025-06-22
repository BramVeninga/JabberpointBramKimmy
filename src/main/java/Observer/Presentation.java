package Observer;

import Slide.Slide;
import java.util.ArrayList;

public class Presentation
{
    private String title;  // Titel van de presentatie
    private int currentSlideNumber = 0;  // Huidig weergegeven slide nummer
    private ArrayList<Slide> slideList;  // Lijst van alle slides in de presentatie
    private ArrayList<PresentationListener> presentationListeners;  // Lijst van luisteraars voor de presentatie

    // Constructor zonder argumenten, maakt een lege presentatie aan
    public Presentation()
    {
        this.title = "";  // Zet de titel op een lege string
        this.slideList = new ArrayList<Slide>();  // Maakt een lege lijst van slides
        this.currentSlideNumber = 0;  // Zet het huidige slide nummer op 0
        this.presentationListeners = new ArrayList<PresentationListener>();  // Maakt een lege lijst van luisteraars
        this.clear();  // Maak de presentatie leeg
    }

    // Constructor met PresentationListener als argument, voegt een listener toe
    public Presentation(PresentationListener presentationListener)
    {
        this.title = "";  // Zet de titel op een lege string
        this.slideList = new ArrayList<Slide>();  // Maakt een lege lijst van slides
        this.currentSlideNumber = 0;  // Zet het huidige slide nummer op 0
        this.presentationListeners = new ArrayList<PresentationListener>();  // Maakt een lege lijst van luisteraars
        this.addPresentationListener(presentationListener);  // Voeg de gegeven listener toe
        this.clear();  // Maak de presentatie leeg
    }

    // Retourneert de titel van de presentatie
    public String getTitle()
    {
        return this.title;
    }

    // Stel de titel van de presentatie in
    public void setTitle(String title)
    {
        this.title = title;
    }

    // Retourneert de lijst van slides
    public ArrayList<Slide> getSlideList()
    {
        return this.slideList;
    }

    // Stel het huidige slide nummer in en update de luisteraars
    public void setSlideNumber(int number)
    {
        this.currentSlideNumber = number;
        this.updatePresentationListeners();
    }

    // Retourneert de grootte van de slide lijst (aantal slides)
    public int getSize()
    {
        return this.slideList.size();
    }

    // Voeg een PresentationListener toe aan de lijst van luisteraars
    public void addPresentationListener(PresentationListener presentationListener)
    {
        this.presentationListeners.add(presentationListener);
    }

    // Verwijder een PresentationListener uit de lijst van luisteraars
    public void removePresentationListener(PresentationListener presentationListener)
    {
        this.presentationListeners.remove(presentationListener);
    }

    // Werk alle presentatie luisteraars bij met de huidige presentatie en slide
    public void updatePresentationListeners()
    {
        for (PresentationListener presentationListener : this.presentationListeners)
        {
            presentationListener.update(this, this.getCurrentSlide());
        }
    }

    // Wis alle slides en stel het huidige slide nummer in op -1 (geen slide)
    public void clear()
    {
        this.slideList = new ArrayList<Slide>();
        this.setSlideNumber(-1);
    }

    // Voeg een slide toe aan de presentatie
    public void append(Slide slide)
    {
        this.slideList.add(slide);
    }

    // Retourneer een slide op basis van de gegeven index
    public Slide getSlide(int indexOfSlide)
    {
        if (indexOfSlide <  0 || indexOfSlide >= getSize())
        {
            return null;
        }

        return (Slide) this.slideList.get(indexOfSlide);
    }

    // Retourneer de huidige slide die getoond wordt
    public Slide getCurrentSlide()
    {
       return this.getSlide(this.currentSlideNumber);
    }

    // Retourneer het huidige slide nummer
    public int getSlideNumber()
    {
        return this.currentSlideNumber;
    }

    // Stop de applicatie met de gegeven exit code
    public void exit(int exitCode)
    {
        System.exit(exitCode);  // Stop de applicatie met de opgegeven exit code
    }
}
