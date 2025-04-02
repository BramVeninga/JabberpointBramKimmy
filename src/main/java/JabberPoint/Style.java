package JabberPoint;

import java.awt.Color;
import java.awt.Font;



public class Style
{
    private static Style[] styles; // de styles

    private static final String FONTNAME = "Helvetica";

    private final int indent;
    private final Color color;
    private final Font font;
    private final int fontSize;
    private final int leading;

    public Style(int indent, Color color, int points, int leading)
    {
        this.indent = indent;
        this.color = color;
        font = new Font(FONTNAME, Font.BOLD, fontSize=points);
        this.leading = leading;
    }

    public int getLeading()
    {
        return this.leading;
    }

    public int getIndent()
    {
        return this.indent;
    }

    public Color getColor()
    {
        return this.color;
    }

    public static Style getStyle(int level)
    {
        if (level >= styles.length)
        {
            level = styles.length - 1;
        }
        return styles[level];
    }

    public Font getFont(float scale)
    {
        return font.deriveFont(fontSize * scale);
    }

    public String toString()
    {
        return "["+ indent + "," + color + "; " + fontSize + " on " + leading +"]";
    }

    public static void createStyles()
    {
        styles = new Style[5];
        // De styles zijn vast ingecodeerd.
        styles[0] = new Style(0, Color.red,   48, 20);	// style voor item-level 0
        styles[1] = new Style(20, Color.blue,  40, 10);	// style voor item-level 1
        styles[2] = new Style(50, Color.black, 36, 10);	// style voor item-level 2
        styles[3] = new Style(70, Color.black, 30, 10);	// style voor item-level 3
        styles[4] = new Style(90, Color.black, 24, 10);	// style voor item-level 4
    }
}
