package source.exceptions;

public class WallsIntersectException extends Throwable {
    public String toString()
    {
        return getClass() + ": The walls should'nt intersect...";
    }
}
