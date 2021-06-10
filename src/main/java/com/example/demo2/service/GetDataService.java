package com.example.demo2.service;

import com.example.demo2.entity.KorData;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class GetDataService {

    private static final String COLLECTION_NAME = "KORFOOD";

    public String saveProduct(KorData korData) throws InterruptedException, ExecutionException {
        Firestore dbFireFire = FirestoreClient.getFirestore();

        ApiFuture<WriteResult> collectionApiFuture = dbFireFire.collection(COLLECTION_NAME).document(String.valueOf(korData.getNumber())).set(korData);

        return collectionApiFuture.get().getUpdateTime().toString();

    }

//    private final Firestore db;
//
//    public GetData(Firestore db) {
//        this.db = db;
//    }

//    Query createOrderByNameWithLimitToLastQuery() {
//        Firestore dbFirestore = FirestoreClient.getFirestore();
//
//        CollectionReference foodData = dbFirestore.collection("KORFOOD");
//        Query query = foodData.orderBy("NUM").limitToLast(1);
//        return query;
//    }

    //데이터 개수 제한에서 불러오기xx *****데이터 검색시 써야하는 함수*****

    public List<KorData> getFoodData(int num) throws ExecutionException, InterruptedException {

        Firestore dbFirestore = FirestoreClient.getFirestore();

        CollectionReference foodData = dbFirestore.collection("KFD");
        if (num == 0) {
            Query query = foodData.orderBy("Number").startAt(num).limit(1000);
            ApiFuture<QuerySnapshot> querySnapshot = query.get();

            List<KorData> korDataList = new ArrayList<>();
            KorData korData = null;

            for (DocumentSnapshot document : querySnapshot.get().getDocuments()) {
                //포함
                korData = document.toObject(KorData.class);
                korDataList.add(korData);
            }
            System.out.println(korData);

            return korDataList;
        } else {
            Query query = foodData.orderBy("Number").startAfter(num).limit(10);
            ApiFuture<QuerySnapshot> querySnapshot = query.get();

            List<KorData> korDataList = new ArrayList<>();
            KorData korData = null;

            for (DocumentSnapshot document : querySnapshot.get().getDocuments()) {
                //포함
                korData = document.toObject(KorData.class);
                korDataList.add(korData);
            }
            System.out.println(korData);

            return korDataList;
        }
    }
}

    //************요 이하로 필요없어짐ㅋㅋㅋㅋ 현수님이 만듦 *************

    //그냥 쓴거 곧 지워야징
//    public VeganNutrion getVegan(int id) {
//
//        Firestore firestore = FirestoreClient.getFirestore();
//        DocumentReference documentReference = firestore.collection("Nutrition").document(String.valueOf(id));
//
//        ApiFuture<DocumentSnapshot> future = documentReference.get();
//        DocumentSnapshot document = null;
//        try {
//            document = future.get();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }
//
//       VeganNutrion veganNutrion = null;
//        if (document.exists()) {
//            veganNutrion = document.toObject(VeganNutrion.class);
//            return veganNutrion;
//        } else {
//            return null;
//        }
//    }
//
////이름이 nn두개 ㅋㅋㅋㅋ
//    public List<VeganNutrion> getVegann(int id) throws Exception {
//
//        Firestore dbFirestore = FirestoreClient.getFirestore();
//
//        //ApiFuture<WriteResult> collectionApiFuture = dbFireFire.collection(COLLECTION_NAME).document(String.valueOf(korData.getNumber())).set(korData);
////
////        CollectionReference veganData = dbFirestore.collection("Nutrition");
//
//        ApiFuture<QuerySnapshot> future =dbFirestore.collection("Nutrition").get();
//
//        List<VeganNutrion> veganNutrionList = new ArrayList<>();
//        VeganNutrion veganNutri = null;
//
//        for (DocumentSnapshot document : future.get().getDocuments()) {
//            //포함
//            veganNutri = document.toObject(VeganNutrion.class);
//            veganNutrionList.add(veganNutri);
//        }
//        return veganNutrionList;
//    }
//
//
//

////리스트로 쓰는법
//    public List<VeganNutrion> getVeganData(int id) throws ExecutionException, InterruptedException {
//
//        Firestore dbFirestore = FirestoreClient.getFirestore();
//
//        CollectionReference veganData = dbFirestore.collection("Nutrition");
//
//
//
//        Query query = foodData.orderBy("id").startAt(num).limit(1000);
//        ApiFuture<QuerySnapshot> querySnapshot = query.get();
//
//        List<KorData> korDataList = new ArrayList<>();
//        KorData korData = null;
//
//        for (DocumentSnapshot document : querySnapshot.get().getDocuments()) {
//            //포함
//            korData = document.toObject(KorData.class);
//            korDataList.add(korData);
//        }
//        System.out.println(korData);
//
//        return VeganNutrion;
//    }

