package xyz.joestr.shipwreckbattleroyal.data.task;

import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import xyz.joestr.shipwreckbattleroyal.data.User;
import xyz.joestr.shipwreckbattleroyal.util.SuccessOrFailureListener;

public class SignUpTask extends AsyncTask<User, Void, String>
        implements OnSuccessListener<DocumentReference>, OnFailureListener {
    private FirebaseFirestore db;
    private SuccessOrFailureListener listener;

    public SignUpTask(SuccessOrFailureListener listener) {
        this.listener = listener;
        this.db = FirebaseFirestore.getInstance();
    }

    @Override
    public String doInBackground(User... parametersUsers) {
        try {

            final User user = parametersUsers[0];
            final SignUpTask signUpTask = this;

             db.collection("users")
                     .whereEqualTo("name", parametersUsers[0].getName())
                     .get()
                     .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                 @Override
                 public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                     if(!queryDocumentSnapshots.getDocuments().isEmpty()) {
                        signUpTask.onFailure(new Exception("This user already exists!"));
                        signUpTask.cancel(true);
                     } else {
                         db.collection("users")
                                 .add(user)
                                 .addOnSuccessListener(signUpTask)
                                 .addOnFailureListener(signUpTask);
                     }
                 }
             });

        } catch (Exception ex) {
            ex.printStackTrace();
            this.listener.onFailure("Sign up failed!", ex);
        }

        return "";
    }



    @Override
    public void onSuccess(DocumentReference documentReference) {
        listener.onSuccess("Signed up!", documentReference.getId());
    }

    @Override
    public void onFailure(@NonNull Exception ex) {
        listener.onFailure("Sign up failed!", ex);
    }
}

