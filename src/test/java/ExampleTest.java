import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

class ExampleTest
{

    private Example example;

    @BeforeEach
    void setUp()
    {
        example = new Example("testValue");
    }

    @Test
    void testConstructor()
    {
        assertNotNull(example);
        assertEquals("testValue", example.getField());
    }

    @Test
    void testGetField()
    {
        assertEquals("testValue", example.getField());
    }

    @Test
    void testExampleMethodWithNullField()
    {
        Example nullExample = new Example(null);

        // Redirect System.out to capture output
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        nullExample.exampleMethod();

        // Reset System.out
        System.setOut(System.out);

        assertEquals("", outContent.toString()); // Verwacht geen output
    }
}
