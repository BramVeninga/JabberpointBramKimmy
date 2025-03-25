public class Example
{
    private static final String CONSTANT = "EXAMPLE";
    private String field;

    public Example(String field)
    {
        this.field = field;
    }

    public void exampleMethod()
    {
        if (field != null)
        {
            System.out.println(CONSTANT);
        }
    }

    public String getField()
    {
        return field;
    }
}
