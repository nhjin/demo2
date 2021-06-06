package com.example.demo2.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutionException;

import com.example.demo2.entity.Flex;
import com.example.demo2.entity.Product;
import com.google.cloud.firestore.*;
import org.springframework.stereotype.Service;


import com.google.api.core.ApiFuture;
import com.google.firebase.cloud.FirestoreClient;

@Service
public class ProductService {

    private static final String COLLECTION_NAME = "products";

    public String saveProduct(Product product) throws InterruptedException, ExecutionException {
        Firestore dbFireFire = FirestoreClient.getFirestore();

        ApiFuture<WriteResult> collectionApiFuture = dbFireFire.collection(COLLECTION_NAME).document(product.getName()).set(product);

        return collectionApiFuture.get().getUpdateTime().toString();

    }

    public List<Flex> getProducts(String value) throws ExecutionException, InterruptedException {

        Firestore dbFirestore = FirestoreClient.getFirestore();

        Iterable<DocumentReference> documentReference = dbFirestore.collection(value).listDocuments();
        Iterator<DocumentReference> iterator = documentReference.iterator();

        List<Flex> productList = new ArrayList<>();
        Flex product = null;

        while (iterator.hasNext()) {
            DocumentReference documentReference1 = iterator.next();
            ApiFuture<DocumentSnapshot> future = documentReference1.get();
            DocumentSnapshot document = future.get();

            product = document.toObject(Flex.class);
            productList.add(product);
        }
        return productList;
    }

    public Flex getProductDetails(String value, String name) throws ExecutionException, InterruptedException {

        Firestore dbFirestore = FirestoreClient.getFirestore();

        DocumentReference documentReference = dbFirestore.collection(value).document(name);

        ApiFuture<DocumentSnapshot> future = documentReference.get();

        DocumentSnapshot document = future.get();

        Flex product = null;
        if (document.exists()) {
            product = document.toObject(Flex.class);
            return product;
        } else {
            return null;
        }
    }

    public List<Flex> getProductTest(String category, int id) throws ExecutionException, InterruptedException {

        Firestore dbFirestore = FirestoreClient.getFirestore();

        CollectionReference flexitarian = dbFirestore.collection(category);

        System.out.println(id);

        Query query = flexitarian.orderBy("id", Query.Direction.DESCENDING).startAfter(id).limit(6);
        ApiFuture<QuerySnapshot> querySnapshot = query.get();

        List<Flex> productList = new ArrayList<>();
        Flex product = null;

        for (DocumentSnapshot document : querySnapshot.get().getDocuments()) {
//                System.out.println(document.getId());
            product = document.toObject(Flex.class);
            productList.add(product);
        }
        return productList;
    }

}
