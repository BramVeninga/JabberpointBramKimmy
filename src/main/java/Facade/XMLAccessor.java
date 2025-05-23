package Facade;

import Observer.Presentation;
import Slide.*;
import java.util.Vector;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.FileWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;

public class XMLAccessor extends Accessor
{
    // Standaard te gebruiken API voor XML verwerking
    protected static final String DEFAULT_API_TO_USE = "dom";

    // Constanten voor de XML tags die in het document worden gebruikt
    protected static final String SHOWTITLE = "showtitle";
    protected static final String SLIDETITLE = "title";
    protected static final String SLIDE = "slide";
    protected static final String ITEM = "item";
    protected static final String LEVEL = "level";
    protected static final String KIND = "kind";
    protected static final String TEXT = "text";
    protected static final String IMAGE = "image";

    // Error berichten
    protected static final String PCE = "Parser Configuration Exception";
    protected static final String UNKNOWNTYPE = "Unknown Element type";
    protected static final String NFE = "Number Format Exception";

    // Haalt de titel van een XML-element op
    private String getTitle(Element element, String tagName)
    {
        NodeList titles = element.getElementsByTagName(tagName);
        return titles.item(0).getTextContent();
    }

    // Laadt een presentatie vanaf het opgegeven bestand
    @Override
    public void loadFile(Presentation presentation, String filename) throws IOException
    {
        // Variabelen voor het aantal slides en items in de presentatie
        int slideNumber;
        int itemNumber;
        int maxSlides = 0;
        int maxItems = 0;

        try
        {
            // Maakt een DocumentBuilder aan
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();

            // Laadt het XML-bestand in een Document object
            Document document = builder.parse(new File(filename));

            // Haalt het root-element van het document op
            Element documentElement = (Element) document.getDocumentElement();

            // Haalt de titel van de presentatie op
            presentation.setTitle(this.getTitle(documentElement, SHOWTITLE));

            // Haalt alle slides uit het document
            NodeList slides = documentElement.getElementsByTagName(SLIDE);
            maxSlides = slides.getLength();

            // Laad elke slide in de presentatie
            for (slideNumber = 0; slideNumber < maxSlides; slideNumber++)
            {
                // Haalt de huidige slide op
                Element xmlSlide = (Element) slides.item(slideNumber);
                Slide slide = new Slide();
                slide.setTitle(this.getTitle(xmlSlide, SLIDETITLE));
                presentation.append(slide);

                // Haalt de items van de huidige slide op
                NodeList slideItems = xmlSlide.getElementsByTagName(ITEM);
                maxItems = slideItems.getLength();

                // Laad elk item in de slide
                for (itemNumber = 0; itemNumber < maxItems; itemNumber++)
                {
                    Element item = (Element) slideItems.item(itemNumber);
                    this.loadSlideItem(slide, item);
                }
            }
        }
        catch (IOException ioException)
        {
            System.err.println(ioException.toString());
        }
        catch (SAXException saxException)
        {
            System.err.println(saxException.getMessage());
        }
        catch (ParserConfigurationException parcerConfigurationException)
        {
            System.err.println(PCE);
        }
    }

    // Laadt een item van een slide
    protected void loadSlideItem(Slide slide, Element item)
    {
        // Standaard niveau is 1
        int level = 1;

        // Haalt de attributen van het item op
        NamedNodeMap attributes = item.getAttributes();
        String leveltext = attributes.getNamedItem(LEVEL).getTextContent();

        // Als er een level is, wordt deze geparsed
        if (leveltext != null)
        {
            try
            {
                level = Integer.parseInt(leveltext);
            }
            catch (NumberFormatException numberFormatException)
            {
                System.err.println(NFE);
            }
        }

        // Haalt het type op
        String type = attributes.getNamedItem(KIND).getTextContent();
        if (TEXT.equals(type))
        {
            slide.append(new TextItem(level, item.getTextContent()));
        }
        else
        {
            // Als het type een afbeelding is, wordt deze toegevoegd
            if (IMAGE.equals(type))
            {
                slide.append(new BitmapItem(level, item.getTextContent()));
            }
            else
            {
                System.err.println(UNKNOWNTYPE);
            }
        }
    }

    // Slaat een presentatie op naar het gegeven bestand
    @Override
    public void saveFile(Presentation presentation, String filename) throws IOException
    {
        // Maakt een printwriter
        PrintWriter out = new PrintWriter(new FileWriter(filename));

        // Schrijft de XML header
        out.println("<?xml version=\"1.0\"?>");
        out.println("<!DOCTYPE presentation SYSTEM \"jabberpoint.dtd\">");

        // Schrijft de presentatie
        out.println("<presentation>");
        out.print("<showtitle>");
        out.print(presentation.getTitle());
        out.println("</showtitle>");

        // Voor elke slide in de presentatie wordt de slide opgeslagen
        for (int slideNumber = 0; slideNumber < presentation.getSize(); slideNumber++)
        {
            // Haalt de slide op
            Slide slide = presentation.getSlide(slideNumber);

            // Schrijft de slide
            out.println("<slide>");
            out.println("<title>" + slide.getTitle() + "</title>");

            // Voor elk item in de slide wordt het item opgeslagen
            Vector<SlideItem> slideItems = slide.getSlideItems();
            for (int itemNumber = 0; itemNumber < slideItems.size(); itemNumber++)
            {
                // Haalt het item op
                SlideItem slideItem = (SlideItem) slideItems.elementAt(itemNumber);
                out.print("<item kind=");

                // Als het item een tekst item is, wordt deze opgeslagen
                if (slideItem instanceof TextItem)
                {
                    out.print("\"text\" level=\"" + slideItem.getLevel() + "\">");
                    out.print(((TextItem) slideItem).getText());
                }
                else
                {
                    // Als het item een afbeelding is, wordt deze opgeslagen
                    if (slideItem instanceof BitmapItem)
                    {
                        out.print("\"image\" level=\"" + slideItem.getLevel() + "\">");
                        out.print(((BitmapItem) slideItem).getName());
                    }
                    else
                    {
                        System.out.println("Ignoring " + slideItem);
                    }
                }
                out.println("</item>");
            }
            out.println("</slide>");
        }
        out.println("</presentation>");
        out.close();
    }

}
