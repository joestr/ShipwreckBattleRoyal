package xyz.joestr.shipwreckbattleroyal.data.task;

import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import xyz.joestr.shipwreckbattleroyal.data.User;
import xyz.joestr.shipwreckbattleroyal.util.SuccessOrFailureListener;

public class SignInTask extends AsyncTask<User, Void, String>
        implements OnSuccessListener<QuerySnapshot>, OnFailureListener {
    private FirebaseFirestore db;
    private SuccessOrFailureListener listener;

    public SignInTask(SuccessOrFailureListener listener) {
        this.listener = listener;
        this.db = FirebaseFirestore.getInstance();
    }

    @Override
    public String doInBackground(User... parametersUsers) {
        try {
            db.collection("users")
                    .whereEqualTo("name", parametersUsers[0].getName())
                    .whereEqualTo("password", parametersUsers[0].getPassword())
                    .get()
                    .addOnSuccessListener(this)
                    .addOnFailureListener(this);
        } catch (Exception ex) {
            ex.printStackTrace();
            this.listener.onFailure("Sign in failed!", ex);
        }

        return "";
    }

    @Override
    public void onSuccess(QuerySnapshot querySnapshot) {
        if(querySnapshot.getDocuments().isEmpty()) {
            listener.onFailure("Sign in failed!", new Exception("No user with this name and password combination found!"));
            return;
        }

        listener.onSuccess("Signed in!", querySnapshot.getDocuments().get(0).getId());
    }

    @Override
    public void onFailure(@NonNull Exception ex) {
        listener.onFailure("Sign up failed!", ex);
    }
}

