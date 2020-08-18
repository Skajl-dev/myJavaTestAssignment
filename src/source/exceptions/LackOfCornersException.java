package source.exceptions;

public class LackOfCornersException extends Throwable {
    public String toString()
    {
        return getClass() + ": There are less than 4 corners...";
    }
}
