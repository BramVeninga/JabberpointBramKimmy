package Facade;

import Observer.Presentation;
import Slide.Slide;
import Slide.BitmapItem;

public class ImageDemo extends Accessor
{
    @Override
    public void loadFile(Presentation presentation, String unusedFilename)
    {
        presentation.setTitle("Image Demo Presentation");

        Slide slide = new Slide();
        slide.setTitle("Introduction");
        slide.append(1, "Welkom bij de afbeeldingen presentatie");
        slide.append(2, "De presentatie laat de afbeeldingen zien");
        slide.append(new BitmapItem(1, "src/main/resources/hondDemo.jpg"));
        slide.append(2, "Laten we beginnen!");
        presentation.append(slide);

        slide = new Slide();
        slide.setTitle("Using Images");
        slide.append(1, "Hier hebben wij een hele mooi afbeelding van een hond");
        slide.append(new BitmapItem(1, "src/main/resources/hond2Demo.jpg"));
        slide.append(2, "Schattig he");
        presentation.append(slide);

        slide = new Slide();
        slide.setTitle("More Images");
        slide.append(1, "Is het duidelijk dat wij van honden houden?");
        slide.append(new BitmapItem(1, "src/main/resources/hon3Demo.jpg"));
        slide.append(2, "Ook weer zo schattig");
        presentation.append(slide);

        slide = new Slide();
        slide.setTitle("Conclusion");
        slide.append(1, "Bedankt voor het kijken naar deze demo presentatie");
        slide.append(2, "Hopenlijk hou je nu ook van honden");
        presentation.append(slide);
    }

    @Override
    public void saveFile(Presentation presentation, String unusedFilename)
    {
        throw new IllegalStateException("Save operation is not supported in Image Demo Presentation");
    }
}
