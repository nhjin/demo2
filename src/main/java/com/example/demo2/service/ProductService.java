package com.example.demo2.service;

import java.util.concurrent.ExecutionException;

import com.example.demo2.entity.Product;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import org.springframework.stereotype.Service;


import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;

@Service
public class ProductService {

    private static final String COLLECTION_NAME ="products";

    public String saveProduct(Product product) throws InterruptedException, ExecutionException {
        Firestore dbFireFire = FirestoreClient.getFirestore();

        ApiFuture<WriteResult> collectionApiFuture = dbFireFire.collection(COLLECTION_NAME).document(product.getName()).set(product);

        return collectionApiFuture.get().getUpdateTime().toString();

    }

    public Product getProductDetails(String name) throws ExecutionException, InterruptedException {

        Firestore dbFirestore = FirestoreClient.getFirestore();

        DocumentReference documentReference = dbFirestore.collection(COLLECTION_NAME).document(name);

        ApiFuture<DocumentSnapshot> future = documentReference.get();

        DocumentSnapshot document = future.get();

        Product product = null;
        if(document.exists()){
            product = document.toObject(Product.class);
        return product;
        }else {
            return null;
        }
    }

}
