package cz.kacirekj.exception;

import org.apache.http.*;

public class HttpExceptionHandler {
    public static void handleReponseError(HttpResponse httpResponse) throws TooManyRequestsGarminException, HttpErrorGarminException {
        int code = httpResponse.getStatusLine().getStatusCode();
        if (code == 429) {
            throw new TooManyRequestsGarminException();
        } else if (code != 200) {
            throw new HttpErrorGarminException(code);
        }
    }
}
