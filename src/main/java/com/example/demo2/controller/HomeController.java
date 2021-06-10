package com.example.demo2.controller;

import com.example.demo2.entity.*;
import com.example.demo2.service.GetDataService;
import com.example.demo2.service.BoardService;
import com.example.demo2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;


@RestController
@RequestMapping("/next")
public class HomeController {

    @Autowired
    BoardService boardService;

    @Autowired
    GetDataService getDataService;

    @Autowired
    UserService userService;


    //글올리기
    @PostMapping("/the")
    public String saveBoard(@RequestBody Board board) throws Exception, InterruptedException {
        return boardService.postBoard(board);
    }

    //list? 근데 리스트나 정보 상세보기나 들어가는 정보가 얼추 비슷한데 이건 어떻게 해야할 지 한 번 생각을 해봐야겠군요
    /*
     * public Board getBoardList */


    //상세보기 + 글번호로 수정해서 url에는 글번호가 뜨도록 하기 ★
//    @GetMapping("/the/{uid}")
//    public Board getBoardDetail(@PathVariable("uid") String uid) throws Exception,  InterruptedException{
//        return boardService.getBoardList(uid);
//    }


    //글수정
    @PutMapping("/the")
    public String update(@RequestBody Board board) throws ExecutionException, InterruptedException {
        return boardService.updateBoard(board);
    }

    //글삭제
    @DeleteMapping("/the/{uid}")
    public String delete(@PathVariable String uid) throws ExecutionException, InterruptedException {

        return boardService.deleteBoard(uid);
    }

//** 데이터 테스트 레스트 컨트롤러 **//

    @GetMapping("/the/{NUM}") //이거를 컨테인으로 바꿔야하나? 글자를 포함하는 함수로 바꾸고 id값이 아니라 name값으로 검색이 되도록 해야하는건가?
    public List<KorData> getFoodData(@PathVariable int NUM) throws InterruptedException, ExecutionException {

        return getDataService.getFoodData(NUM);

    }

    //회원정보 담기
    @GetMapping("/get/{uid}")
    public User getUserInfo(@PathVariable("uid") String uid) throws ExecutionException, InterruptedException {

        System.out.println("실험1");
        System.out.println("테스트" + uid);
        // session.getAttribute()
        return userService.getUserInfo(uid);

        //, HttpSession session
    }

    //영양소

    @GetMapping("/get/{gender}/{age}")
    public Nutrition getNutrition(@PathVariable String gender, @PathVariable int age) throws ExecutionException, InterruptedException {

        int Age = 0;

        //int[] ages = {12, 14, 15, 18, 19, 29, 30, 49, 50, 64, 65, 74, 75};

        if (gender.equals("Male")) {
            if (age >= 12 && age <= 14) {
                Age = 1214;
            } else if (age >= 15 && age <= 18) {
                Age = 1518;
            } else if (age >= 19 && age <= 29) {
                Age = 1929;
            } else if (age >= 30 && age <= 49) {
                Age = 3049;
            } else if (age >= 50 && age <= 64) {
                Age = 5064;
            } else if (age >= 65 && age <= 74) {
                Age = 6574;
            } else {
                Age = 75;
            }
        } else if (gender.equals("Female")) {
            if (age >= 12 && age <= 14) {
                Age = 1214;
            } else if (age >= 15 && age <= 18) {
                Age = 1518;
            } else if (age >= 19 && age <= 29) {
                Age = 1929;
            } else if (age >= 30 && age <= 49) {
                Age = 3049;
            } else if (age >= 50 && age <= 64) {
                Age = 5064;
            } else if (age >= 65 && age <= 74) {
                Age = 6574;
            } else {
                Age = 75;
            }
        }
        return userService.getNutrition(gender, Age);
    }
}
