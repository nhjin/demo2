package com.example.demo2.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutionException;

import com.example.demo2.entity.*;
import com.google.cloud.firestore.*;
import org.springframework.stereotype.Service;


import com.google.api.core.ApiFuture;
import com.google.firebase.cloud.FirestoreClient;

import javax.servlet.http.HttpServletRequest;

@Service
public class ProductService {

    private static final String COLLECTION_NAME = "products";

//    public String saveProduct(Product product) throws InterruptedException, ExecutionException {
//        Firestore dbFireFire = FirestoreClient.getFirestore();
//
//        ApiFuture<WriteResult> collectionApiFuture = dbFireFire.collection(COLLECTION_NAME).document(product.getName()).set(product);
//
//        return collectionApiFuture.get().getUpdateTime().toString();
//
//    }

    public String saveProduct(Product product) throws InterruptedException, ExecutionException {
        Firestore dbFireFire = FirestoreClient.getFirestore();

        ApiFuture<WriteResult> collectionApiFuture = dbFireFire.collection(COLLECTION_NAME).document(product.getName()).set(product);

        return collectionApiFuture.get().getUpdateTime().toString();

    }

    public String deleteUser(String uid) throws InterruptedException, ExecutionException {
        Firestore dbFireFire = FirestoreClient.getFirestore();

        ApiFuture<WriteResult> writeResult = dbFireFire.collection("users").document(uid).delete();

//        System.out.println("Update time : " + writeResult.get().getUpdateTime());

        return writeResult.get().getUpdateTime().toString();

    }

    public String saveUserInfo(User user) throws InterruptedException, ExecutionException {
        Firestore dbFireFire = FirestoreClient.getFirestore();

        ApiFuture<WriteResult> collectionApiFuture = dbFireFire.collection("users").document(user.getUid()).set(user);

        return collectionApiFuture.get().getUpdateTime().toString();

    }

    public String updateFavorite(String uid, String favorite) throws InterruptedException, ExecutionException {
        Firestore dbFireFire = FirestoreClient.getFirestore();

        ApiFuture<WriteResult> collectionApiFuture = dbFireFire.collection("users").document(uid).update("favorite", favorite);

        return collectionApiFuture.get().getUpdateTime().toString();

    }

//    public List<Flex> searchProducts(String searchTxt) throws ExecutionException, InterruptedException {
//
//        String value = "";
//
//        if((searchTxt.contains("flex") || searchTxt.contains("Flex"))){
//            value = "flexitarian";
//        }else if((searchTxt.contains("pesce") || searchTxt.contains("Pesce") || searchTxt.contains("fish") || searchTxt.contains("Fish"))){
//            value = "Pescetarian";
//        }else if((searchTxt.contains("vege") || searchTxt.contains("Vege"))){
//            value = "Vegetarian";
//        }else if((searchTxt.contains("vegan") || searchTxt.contains("Vegan"))){
//            value = "vegan";
//        }else {
//            value = "recipe";
//        }
//
//        Firestore dbFirestore = FirestoreClient.getFirestore();
//
//        Iterable<DocumentReference> documentReference = dbFirestore.collection(value).listDocuments();
//        Iterator<DocumentReference> iterator = documentReference.iterator();
//
//        List<Flex> productList = new ArrayList<>();
//        Flex product = null;
//
//        while (iterator.hasNext()) {
//            DocumentReference documentReference1 = iterator.next();
//            ApiFuture<DocumentSnapshot> future = documentReference1.get();
//            DocumentSnapshot document = future.get();
//
//            product = document.toObject(Flex.class);
//            productList.add(product);
//        }
//        return productList;
//    }

    public List<Flex> searchProducts(String searchTxt) throws ExecutionException, InterruptedException {

        String value = "";

        if((searchTxt.contains("flex") || searchTxt.contains("Flex"))){
            value = "flexitarian";
        }else if((searchTxt.contains("pesce") || searchTxt.contains("Pesce"))){
            value = "Pescetarian";
        }else if((searchTxt.contains("vege") || searchTxt.contains("Vege"))){
            value = "Vegetarian";
        }else if((searchTxt.contains("vegan") || searchTxt.contains("Vegan"))){
            value = "vegan";
        }else {
            value = "recipe";
        }

        Firestore dbFirestore = FirestoreClient.getFirestore();

        CollectionReference reference = dbFirestore.collection(value);

        Query query = reference.orderBy("id", Query.Direction.DESCENDING);
        ApiFuture<QuerySnapshot> querySnapshot = query.get();

        List<Flex> productList = new ArrayList<>();
        Flex product = null;

        for (DocumentSnapshot document : querySnapshot.get().getDocuments()) {
            product = document.toObject(Flex.class);
            productList.add(product);
        }
        return productList;
    }

    public FlexDetail getProductDetails(String id) throws ExecutionException, InterruptedException {

        Firestore dbFirestore = FirestoreClient.getFirestore();

        DocumentReference documentReference = dbFirestore.collection("recipe").document(id);

        ApiFuture<DocumentSnapshot> future = documentReference.get();

        DocumentSnapshot document = future.get();

        FlexDetail product = null;

        if (document.exists()) {
            product = document.toObject(FlexDetail.class);
            return product;
        } else {
            return null;
        }
    }

    public Flex getProductCheck(String category, String id) throws ExecutionException, InterruptedException {

        Firestore dbFirestore = FirestoreClient.getFirestore();

        DocumentReference documentReference = dbFirestore.collection(category).document(id);

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

        CollectionReference reference = dbFirestore.collection(category);

        System.out.println(id);

        Query query = reference.orderBy("id", Query.Direction.DESCENDING).startAfter(id).limit(6);
        ApiFuture<QuerySnapshot> querySnapshot = query.get();

        List<Flex> productList = new ArrayList<>();
        Flex product = null;

        for (DocumentSnapshot document : querySnapshot.get().getDocuments()) {
            product = document.toObject(Flex.class);
            productList.add(product);
        }
        return productList;
    }

    public List<Nutrition> getTest(float car, float pro, float fat) throws ExecutionException, InterruptedException {
        float Gcar = car + car * 10 / 100;
        float Lcar = car - car * 40 / 100;
        float Gpro = pro + pro * 10 / 100;
        float Lpro = pro - pro * 40 / 100;
        float Gfat = fat + fat * 10 / 100;
        float Lfat = fat - fat * 40 / 100;

        Firestore dbFirestore = FirestoreClient.getFirestore();

        CollectionReference reference = dbFirestore.collection("Nutrition");

        Query query = reference.whereGreaterThanOrEqualTo("CAR", Lcar).whereLessThanOrEqualTo("CAR", Gcar);

        ApiFuture<QuerySnapshot> querySnapshot = query.get();

        List<Nutrition> productList = new ArrayList<>();
        Nutrition product = null;

        for (DocumentSnapshot document : querySnapshot.get().getDocuments()) {
            float fPro = Float.valueOf(String.valueOf(document.get("PRO")));
            float fFat = Float.valueOf(String.valueOf(document.get("FAT")));

            if((fPro >= Lpro && fPro <= Gpro) && (fFat >= Lfat && fFat <= Gfat)){

                product = document.toObject(Nutrition.class);
                productList.add(product);
            }
        }
        return productList;
    }

}
