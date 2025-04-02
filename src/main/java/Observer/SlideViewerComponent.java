package Observer;

import Facade.SlideViewerFrame;
import Slide.Slide;

import javax.swing.*;
import java.awt.*;

public class SlideViewerComponent extends JComponent implements PresentationListener
{
    private static final long serialVersionUID = 227L;
    private static final Color BACKGROUND_COLOR = Color.WHITE;  // Achtergrondkleur van de viewer
    private static final Color COLOR = Color.BLACK;            // Kleur van de tekst
    private static final String FONT_NAME = "Dialog";          // Naam van het lettertype
    private static final int FONT_STYLE = Font.BOLD;           // Stijl van het lettertype (vet)
    private static final int FONT_HEIGHT = 10;                 // Hoogte van het lettertype
    private static final int X_POSITION = 1100;                // X-positie voor de tekst
    private static final int Y_POSITION = 20;                  // Y-positie voor de tekst

    private Slide slide;                                      // Huidige slide
    private Font labelFont;                                    // Lettertype voor tekst
    private JFrame frame;                                      // Het frame waarin de viewer zich bevindt

    private int slideNumber;                                   // Huidige slide nummer
    private int presentationSize;                              // Totale aantal slides in de presentatie

    // Constructor die een frame ontvangt
    public SlideViewerComponent(JFrame frame)
    {
        this.setBackground(BACKGROUND_COLOR);                  // Zet de achtergrondkleur
        this.labelFont = new Font(FONT_NAME, FONT_STYLE, FONT_HEIGHT); // Stel het lettertype in
        this.frame = frame;                                    // Stel het frame in
    }

    // Standaard constructor, gebruikt het SlideViewerFrame als het frame
    public SlideViewerComponent()
    {
        this.setBackground(BACKGROUND_COLOR);                  // Zet de achtergrondkleur
        this.labelFont = new Font(FONT_NAME, FONT_STYLE, FONT_HEIGHT); // Stel het lettertype in
        this.frame = SlideViewerFrame.getInstance();           // Haal het SlideViewerFrame instance op
    }

    // Retourneert de grootte van de presentatie (aantal slides)
    public int getPresentationSize()
    {
        return this.presentationSize;
    }

    // Retourneert de huidige slide
    public Slide getSlide()
    {
        return this.slide;
    }

    // Retourneert de voorkeursgrootte voor de component (grootte van een slide)
    public Dimension getPreferredSize()
    {
        return new Dimension(Slide.WIDTH, Slide.HEIGHT); // Geef de breedte en hoogte van een slide
    }

    // Update de slide en presentatie-informatie wanneer de presentatie wijzigt
    @Override
    public void update(Presentation presentation, Slide data)
    {
        if (data == null) // Als er geen nieuwe slide is, reset alles
        {
            this.slide = null; // Zet de slide naar null
            this.repaint();    // Herteken de component
            return;
        }

        this.slide = slide;                                   // Wijs de nieuwe slide toe
        this.slideNumber = presentation.getSlideNumber();     // Verkrijg het nummer van de huidige slide
        this.presentationSize = presentation.getSize();       // Verkrijg de totale grootte van de presentatie

        this.repaint();    // Herteken de component
        this.frame.setTitle(presentation.getTitle());          // Zet de titel van het frame naar de titel van de presentatie
    }

    // Tekent de component (de slide) op het scherm
    public void paintComponent(Graphics graphics)
    {
        graphics.setColor(BACKGROUND_COLOR);                  // Stel de achtergrondkleur in
        graphics.fillRect(0, 0, getSize().width, getSize().height); // Vul de achtergrond van de component
        graphics.setFont(this.labelFont);                       // Zet het lettertype voor de tekst
        graphics.setColor(COLOR);                               // Zet de kleur van de tekst

        // Als de slide niet geldig is, doe dan niets
        if(this.slideNumber < 0 || this.slide == null)
        {
            return; // Stop met tekenen als er geen geldige slide is
        }

        // Teken de tekst die het huidige slide nummer en het totale aantal slides weergeeft
        graphics.drawString("Slide " + (1 + this.slideNumber) + " of " + this.presentationSize, X_POSITION, Y_POSITION);

        // Maak een rechthoek waarin de slide getekend kan worden
        Rectangle area = new Rectangle(0, Y_POSITION, getWidth(), (getHeight() - Y_POSITION));
        slide.draw(graphics, area, this); // Laat de slide tekenen binnen het opgegeven gebied
    }
}
