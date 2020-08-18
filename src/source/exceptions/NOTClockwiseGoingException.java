package source.exceptions;

public class NOTClockwiseGoingException extends Throwable {
    public String toString()
    {
        return getClass() + ": The points should be entered clockwise...";
    }
}
