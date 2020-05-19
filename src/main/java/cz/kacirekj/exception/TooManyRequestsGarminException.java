package cz.kacirekj.exception;

import cz.kacirekj.domain.*;

public class TooManyRequestsGarminException extends Exception {
    public TooManyRequestsGarminException(){
        super("Http error code 429 - Too many requests. This is very common error in free Garmin API. " +
                "It will happen after approx 10 attempts of Login in short period of time." +
                "To avoid this kind of error to happen very soon, try to re-use instance of " + GarminSession.class.getSimpleName() + ". " +
                "Instance of this class can be also serialized into JSON.");
    }
}
