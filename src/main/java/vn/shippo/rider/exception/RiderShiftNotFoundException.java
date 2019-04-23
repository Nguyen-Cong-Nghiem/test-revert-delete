package vn.shippo.rider.exception;

public class RiderShiftNotFoundException extends RuntimeException {

    public RiderShiftNotFoundException() {
        super();
    }

    public RiderShiftNotFoundException(String message) {
        super(message);
    }

    public RiderShiftNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public RiderShiftNotFoundException(Throwable cause) {
        super(cause);
    }

    protected RiderShiftNotFoundException(String message, Throwable cause,
                                          boolean enableSuppression,
                                          boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
