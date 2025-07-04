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
    protected static final String DEFAULT_API_TO_USE = "dom";

    protected static final String SHOWTITLE = "showtitle";
    protected static final String SLIDETITLE = "title";
    protected static final String SLIDE = "slide";
    protected static final String ITEM = "item";
    protected static final String LEVEL = "level";
    protected static final String KIND = "kind";
    protected static final String TEXT = "text";
    protected static final String IMAGE = "image";

    protected static final String PCE = "Parser Configuration Exception";
    protected static final String UNKNOWNTYPE = "Unknown Element type";
    protected static final String NFE = "Number Format Exception";

    private String getTitle(Element element, String tagName)
    {
        NodeList titles = element.getElementsByTagName(tagName);
        return titles.item(0).getTextContent();
    }

    @Override
    public void loadFile(Presentation presentation, String filename) throws IOException
    {
        int slideNumber;
        int itemNumber;
        int maxSlides = 0;
        int maxItems = 0;

        try
        {
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = builder.parse(new File(filename));
            Element documentElement = (Element) document.getDocumentElement();

            presentation.setTitle(this.getTitle(documentElement, SHOWTITLE));

            NodeList slides = documentElement.getElementsByTagName(SLIDE);
            maxSlides = slides.getLength();

            for (slideNumber = 0; slideNumber < maxSlides; slideNumber++)
            {
                Element xmlSlide = (Element) slides.item(slideNumber);
                Slide slide = new Slide();
                slide.setTitle(this.getTitle(xmlSlide, SLIDETITLE));
                presentation.append(slide);

                NodeList slideItems = xmlSlide.getElementsByTagName(ITEM);
                maxItems = slideItems.getLength();

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

    protected void loadSlideItem(Slide slide, Element item)
    {
        int level = 1;

        NamedNodeMap attributes = item.getAttributes();
        String leveltext = attributes.getNamedItem(LEVEL).getTextContent();

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

        String type = attributes.getNamedItem(KIND).getTextContent();
        if (TEXT.equals(type))
        {
            slide.append(new TextItem(level, item.getTextContent()));
        }
        else if (IMAGE.equals(type))
        {
            slide.append(new BitmapItem(level, item.getTextContent()));
        }
        else
        {
            System.err.println(UNKNOWNTYPE);
        }
    }

    @Override
    public void saveFile(Presentation presentation, String filename) throws IOException
    {
        PrintWriter out = new PrintWriter(new FileWriter(filename));

        out.println("<?xml version=\"1.0\"?>");
        out.println("<!DOCTYPE presentation SYSTEM \"jabberpoint.dtd\">");

        out.println("<presentation>");
        out.print("<showtitle>");
        out.print(presentation.getTitle());
        out.println("</showtitle>");

        for (int slideNumber = 0; slideNumber < presentation.getSize(); slideNumber++)
        {
            Slide slide = presentation.getSlide(slideNumber);

            out.println("<slide>");
            out.println("<title>" + slide.getTitle() + "</title>");

            Vector<SlideItem> slideItems = slide.getSlideItems();
            for (int itemNumber = 0; itemNumber < slideItems.size(); itemNumber++)
            {
                SlideItem slideItem = slideItems.elementAt(itemNumber);
                out.println(slideItem.toXML());
            }
            out.println("</slide>");
        }
        out.println("</presentation>");
        out.close();
    }
}
