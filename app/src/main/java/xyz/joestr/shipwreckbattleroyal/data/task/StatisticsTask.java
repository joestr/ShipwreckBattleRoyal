package xyz.joestr.shipwreckbattleroyal.data.task;

import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import xyz.joestr.shipwreckbattleroyal.data.User;
import xyz.joestr.shipwreckbattleroyal.util.SuccessOrFailureListener;

public class StatisticsTask extends AsyncTask<User, Void, String>
        implements OnSuccessListener<QuerySnapshot>, OnFailureListener {
    private FirebaseFirestore db;
    private SuccessOrFailureListener listener;

    public StatisticsTask(SuccessOrFailureListener listener) {
        this.listener = listener;
        this.db = FirebaseFirestore.getInstance();
    }

    @Override
    public String doInBackground(User... parametersUsers) {
        try {
            db.collection("statistics")
                    .whereEqualTo("name", parametersUsers[0].getName())
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
        listener.onSuccess("Loaded statistics!", querySnapshot.getDocuments().get(0).getString("statistics"));
    }

    @Override
    public void onFailure(@NonNull Exception ex) {
        listener.onFailure("Statistics failed to load!", ex);
    }
}

