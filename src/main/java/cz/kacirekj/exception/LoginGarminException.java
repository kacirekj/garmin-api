package cz.kacirekj.exception;

public class LoginGarminException extends Exception {
    public LoginGarminException(String whereItHappnen){
        super("Login exception during " + whereItHappnen + ". This exception mean that reverse engineered " +
                "Garmin API contains some strange unknown corner-case or that Garmin has changed his API. " +
                "Please contact developers of this library and provide additional informations.");
    }
}
