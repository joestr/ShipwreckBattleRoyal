package xyz.joestr.shipwreckbattleroyal.util;

public interface SuccessOrFailureListener {

    void onSuccess(String message, String jsonData);
    void onFailure(String message, Exception exception);
}
