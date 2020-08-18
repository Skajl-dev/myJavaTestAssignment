package source.exceptions;

public class DiagonalWallsException extends Throwable {
    public String toString()
    {
        return getClass() + ": The wall can't be diagonal...";
    }
}

