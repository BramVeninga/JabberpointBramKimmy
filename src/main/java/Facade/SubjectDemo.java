package Facade;

import Observer.Presentation;
import Slide.Slide;
import Slide.BitmapItem;

public class SubjectDemo extends Accessor
{
    @Override
    public void loadFile(Presentation presentation, String unusedFilename)
    {
        presentation.setTitle("Subject Demo");

        Slide slide = new Slide();
        slide.setTitle("Welkom");
        slide.append(1, "Vak demo presentatie");
        slide.append(2, "Deze demo kan worden gegeven voor verschillende vakken");
        slide.append(2, "Laten we beginnen!");
        presentation.append(slide);

        slide = new Slide();
        slide.setTitle("De inleiding");
        slide.append(1, "Dit is de inleiding");
        slide.append(2, "1. Opening");
        slide.append(2, "2. Inhoud");
        slide.append(2, "3. Beoordeling");
        slide.append(2, "4. Vragen");
        presentation.append(slide);

        slide = new Slide();
        slide.setTitle("Opening");
        slide.append(1, "We beginnen met de opening.");
        slide.append(2, "Hier kan de opening beginnen van het vak.");
        presentation.append(slide);

        slide = new Slide();
        slide.setTitle("Vragen");
        slide.append(1, "Ho we zijn al weer aangekomen bij de vragen ");
        slide.append(2, "Bedankt voor jullie tijd");
        presentation.append(slide);
    }

    @Override
    public void saveFile(Presentation presentation, String unusedFilename)
    {
        throw new IllegalStateException("Save operation is not supported in Plenary Meeting Demo");
    }
}