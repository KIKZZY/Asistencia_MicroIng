package com.kmac.asistenciamicroing;

import android.view.View;

import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

public class MyFirebaseAPP extends android.app.Application{

    public void OnCreate(){
        super.onCreate();
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        
    }
}
