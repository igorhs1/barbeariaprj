package com.example.barbeariaprj2.config;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ConfiguracaoFirebase {

    private static DatabaseReference database;
    private static FirebaseAuth auth;

    //retorna instancia do FirebaseDatabase
    public static DatabaseReference getFirebaseDatabase(){

        if(database == null){
            database = FirebaseDatabase.getInstance().getReference();
        }
        return database;
    }

    //retorna instancia do FireBaseAuth
    public static FirebaseAuth getFirebaseAutenticacao(){

        if( auth == null){
            auth = FirebaseAuth.getInstance();
        }
        return auth;
    }


}
