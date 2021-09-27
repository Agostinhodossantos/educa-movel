package educa.movel.com.utils;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class InitFirebase {
    private static FirebaseDatabase firebaseDatabase;
    private static DatabaseReference databaseReference;

    public static DatabaseReference initFirebase() {
        firebaseDatabase = FirebaseDatabase.getInstance();
        return databaseReference = firebaseDatabase.getReference();
    }
}
