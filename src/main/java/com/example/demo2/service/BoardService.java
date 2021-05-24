package com.example.demo2.service;

import com.example.demo2.entity.Board;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
public class BoardService implements FirebaseService {

    private static final String COLLECTION_NAME = "board";


    @Override
    public String insertId(Board id) throws Exception {
        Firestore firestore = FirestoreClient.getFirestore();

        ApiFuture<WriteResult> apiFuture = firestore.collection(COLLECTION_NAME).document(id.getId()).set(id);

        return apiFuture.get().getUpdateTime().toString();
    }
}
