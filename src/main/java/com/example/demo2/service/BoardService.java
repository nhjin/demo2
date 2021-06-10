package com.example.demo2.service;

import com.example.demo2.entity.Board;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

//쿼리문 대신 수행하는 거
@Service
public class BoardService {

    private static final String COLLECTION_NAME = "board";


    //post 정보올리기
    public String postBoard(Board board) {

        Firestore firestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> apiFuture = firestore.collection(COLLECTION_NAME).document(board.getUid()).set(board); //get uid 바꾸기 ? 뭐 모르겠다 아무튼

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


//    //post 정보올리기
//    public String saveMemo(User user){
//
//        Firestore firestore = FirestoreClient.getFirestore();
//        ApiFuture<WriteResult> apiFuture = firestore.collection(COLLECTION_NAME).document(user.getUid()).set(user); //get uid 바꾸기 ? 뭐 모르겠다 아무튼
//
//        String result = null;
//        try {
//            result = apiFuture.get().getUpdateTime().toString();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }
//
//        return result;
//    }


    //get 정보뿌리기
    public Board getBoardList(String uid)  {
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

        Board board = null;
        if (document.exists()) {
            board = document.toObject(Board.class);
            return board;
        } else {
            return null;
        }
    }

    //update 정보 수정

    public String updateBoard(Board board) {

        Firestore firestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionApifuture = firestore.collection(COLLECTION_NAME).document(board.getUid()).set(board);

        String result = null;

        try {
            return collectionApifuture.get().getUpdateTime().toString();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return result;
    }


    //update 정보 수정2

    //delete 삭제 1

    public String deleteBoard(String uid) {

        Firestore firestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionApifuture = firestore.collection(COLLECTION_NAME).document(uid).delete();

        String result = null;

        return "Documet with Board uid" + uid +"has been deleted";
    }

}









