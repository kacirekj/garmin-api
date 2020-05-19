package cz.kacirekj.exception;

public class HttpErrorGarminException extends Exception {
    public HttpErrorGarminException(int errorCode){
        super("Garmin returned http error code " + errorCode);
    }
}
