package com.example.moviegamesapp.database;

import com.example.moviegamesapp.model.Result;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class RealtimeResultsDatabase {
    private DatabaseReference databaseReference;

    public RealtimeResultsDatabase() {
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        databaseReference = db.getReference(Result.class.getSimpleName());
    }
    public Task<Void> add(Result result) {
        return databaseReference.push().setValue(result);
    }

    public Query get() {
        return databaseReference.orderByKey();
    }
}
