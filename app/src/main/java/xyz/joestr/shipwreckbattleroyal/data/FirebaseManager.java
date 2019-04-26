package xyz.joestr.shipwreckbattleroyal.data;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

public class FirebaseManager {

    private static FirebaseManager instance = null;
    public static FirebaseFirestore db = null;

    private FirebaseManager() {

        this.db = FirebaseFirestore.getInstance();
    }

    public static FirebaseManager getInstance() {

        if(instance == null) {
            instance = new FirebaseManager();
        }

        return instance;
    }

    public boolean signInUserAsync(User user, Function<Boolean, String> callback) {

        return true;
    }

    public boolean signUpUser(User user, Function<Boolean, String> callback) {

        return true;
    }
}
