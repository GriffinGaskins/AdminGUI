package GutenSearchGUI;

/**
 * Illegal Search Exception.
 * 
 * @author Scott Woodgate
 */
public class IllegalSearchException extends Exception
{
    private static final long serialVersionUID = 1L;

    /**
     * Illegal Search Exception constructor.
     */
    public IllegalSearchException()
    {
        super("Null Search");
    }
}
