package com.example.demo2.service;

import com.example.demo2.entity.Nutrition;
import com.example.demo2.entity.PersonNutrition;
import com.example.demo2.entity.User;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service //이거 안쓰면 서비스에서 오류남 autowired시
public class UserService {

    private static final String COLLECTION_NAME = "users";

    //post 정보올리기2 오버로딩
    public String saveUser(User user, String uid){

        Firestore firestore = FirestoreClient.getFirestore();
//        ApiFuture<WriteResult> apiFuture = firestore.collection(COLLECTION_NAME).document(uid).update("bmeno2", board.getBmemo());
        ApiFuture<WriteResult> apiFuture = firestore.collection(COLLECTION_NAME).document(uid).set(user);

        String result = null;
        try {
            result = apiFuture.get().getUpdateTime().toString();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return result;
    }

    //user 정보호출해서 받아오기
    public User getUserInfo(String uid) {

        Firestore firestore = FirestoreClient.getFirestore();
        DocumentReference documentReference = firestore.collection(COLLECTION_NAME).document(uid);

        ApiFuture<DocumentSnapshot> future = documentReference.get();
        DocumentSnapshot document = null;
        try {
            document = future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        User user = null;
        if (document.exists()) {
            user = document.toObject(User.class);
            return user;
        } else {
            return null;
        }
    }

    //연령별 성별 영양소 가져오기

    public PersonNutrition getNutrition(String gender, int age) {

        Firestore firestore = FirestoreClient.getFirestore();
        DocumentReference documentReference = firestore.collection(gender).document(String.valueOf(age));

        ApiFuture<DocumentSnapshot> future = documentReference.get();
        DocumentSnapshot document = null;
        try {
            document = future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        PersonNutrition nutrition = null;
        if (document.exists()) {
            nutrition = document.toObject(PersonNutrition.class);
            return nutrition;
        } else {
            return null;
        }
    }


}
