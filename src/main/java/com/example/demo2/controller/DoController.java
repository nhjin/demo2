package com.example.demo2.controller;

import com.example.demo2.entity.KorData;
import com.example.demo2.entity.Nutrition;
import com.example.demo2.entity.PersonNutrition;
import com.example.demo2.entity.User;
import com.example.demo2.data.KcalCal;
import com.example.demo2.service.BoardService;
import com.example.demo2.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.*;
import java.util.concurrent.ExecutionException;

import javax.ws.rs.Consumes;

@Controller
public class DoController {

    //return html

    @Autowired
    BoardService boardService;

    @Autowired
    UserService userService;


    private static final Logger logger = LoggerFactory.getLogger(DoController.class);

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String ajaxTest() {
        logger.info("Welcome home! Test!{}");

        return "index";
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test() {

        return "test";
    }

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String home() {
//        logger.info("Welcome home! Test!{}");

        return "home";
    }

    @RequestMapping(value = "/about", method = RequestMethod.GET)
    public String about() {
//        logger.info("Welcome home! Test!{}");

        return "about";
    }

    @RequestMapping(value = "/home/{category}", method = RequestMethod.GET)
    public String setCategory(@PathVariable String category) {
//        logger.info("Welcome home! Test!{}");
//        session.setAttribute("category", category);
        return "redirect:/recipes?category=" + category;
    }

    @RequestMapping(value = "/recipes", method = RequestMethod.GET)
    public String recipe() {
//        logger.info("Welcome home! Test!{}");

        return "shop";
    }

    @RequestMapping(value = "/recipes/{id}", method = RequestMethod.GET)
    public String test2(@PathVariable int id, HttpServletRequest request) {
//        logger.info("Welcome home! Test!{}");
        request.setAttribute("id", id);

        return "shopDetail";
    }

    @RequestMapping(value = "/search/{search}", method = RequestMethod.GET)
    public String search(@PathVariable String search, HttpServletRequest request) {
//        logger.info("Welcome home! Test!{}");
        request.setAttribute("search", search);

        return "search";
    }

    @RequestMapping(value = "/mypage", method = RequestMethod.GET)
    public String mypage() {

        return "mypage";
    }

    @RequestMapping(value = "/recommend", method = RequestMethod.GET)
    public String recommend() {

        return "recommend";
    }

    //회원가입 구글계정
    @GetMapping("/start")
    public String start() {

        return "start";
    }

    // * 게시판 * //

    @GetMapping("/boardview")
    public String board_view() {
        return "board_view";
    }


    // * 회원정보관련 회원가입, 멤버테이블에 정보추가 등* //

    //여기다가 타임리프에 user를 만듦 그릇같은거
    @GetMapping("/list")
    public String list(Model model) {

        model.addAttribute("user", new User());

        return "list";
    }

    @PostMapping("/uesrExtraInfo")
    public String userExtraInfo(HttpServletRequest request, @ModelAttribute("user") User user, Model model) {

//        User user = new User();
        String uid = request.getSession().getAttribute("uid").toString();

        String age = request.getParameter("age");
        String gender = request.getParameter("gender");

        user.setUid(uid);
        user.setAge(age);
        user.setGender(gender);
        //model.addAttribute("user",new User());
        model.addAttribute("user", user);
        System.out.println("userExtraInfo : " + uid + age + gender);

//        boardService.saveMemo(user,uid);
        userService.saveUser(user, uid);

        return "index";

    }


    /**
     * @param userMap
     * @param request
     * @return 로그인시 세션의 정보를 담는 url
     * @desc
     */
    @PostMapping("/sessionLogin")
    @Consumes("application/json")
    public @ResponseBody
    Map<String, Object> createSessionCookie(@RequestBody Map<String, Object> userMap, HttpServletRequest request) {

        Map<String, Object> result = new HashMap<>();

//        HttpSession session = request.getSession();

        request.getSession().setAttribute("uid", userMap.get("uid"));
        request.getSession().setAttribute("email", userMap.get("email"));

        System.out.println(userMap.get("uid"));
        System.out.println(userMap.get("email"));

        return result;
    }


    //이메일, 비번으로 회원가입하는 거 테스트 url localhost:8086/signup -> signUp.html이 켜진다 헷갈리지 말자 ㅋㅋㅋㅋ
    @GetMapping("/signup")
    public String signUp() {
        return "signUp";
    }

    @PostMapping("/best")
    public String signUpDone() {
        //리턴 리스일 경우엔 list.html이 뜨는거라 그릇이 없어서 그렇습니다 ㅇㅅㅇ
        return "redirect:list";
    }


    //데이터 테스트하는 중

    //데이터 관리?하는 뷰페이지 띄우기 //객체보관 ??????
    @GetMapping("/viewfood")
    public String viewFood(Model model, HttpSession session, HttpServletRequest request) {

        String CAR = request.getParameter("CAR");
        String CAR2 = request.getParameter("car");
        model.addAttribute("kordata", new KorData());
//        String CAR = (String) session.getAttribute("CAR");
//        request.setAttribute("CAR", CAR);
        session.setAttribute("CAR", CAR);

        System.out.println("꿩불고기 탄수화물: " + CAR);
        System.out.println("꿩불고기 탄수화물: " + CAR2);

        return "foodSearch";
    }

//    //음식 데이터를 담는 그릇 만들기 (타임리프)?? 없애라
//    @GetMapping("/getdatabox")
//    public  String setData(Model model){
//
//
//        return "redirect:index";
//    }


    //  uid를 불러서 회원정보를 받아오기 위한 html //user정보를 처리하는 구간 + 객체담기
    @GetMapping("/getuid")
    public String getuid(Model model) {

        model.addAttribute("kordata", new KorData());

//        model.addAttribute("user", new User());

        return "getuid";
    }

    //user 나이 성별 세션올리기

    @PostMapping("/puinfo")
    public String passuserinfo(HttpServletRequest request, HttpSession session, Model model) {

        model.addAttribute("nutrition", new PersonNutrition());

        String age = request.getParameter("age");
        String gender = request.getParameter("gender");

        session.setAttribute("age", age);
        session.setAttribute("gender", gender);

        System.out.println(age);
        System.out.println(gender);


        //  return "redirect:list"; //puinfo로 써도 될거같긴한뎅 일단 뭐
        return "getnutri";
    }

    //영양소 불러오기 아예필요가 없는...?>
    @PostMapping("/getnutri") //get이냐 아니냐
    public String getNutri() {

        return "redirect:list";
    }

    //데이터 내용 담아서 여기서 연산하기 =>이름바꾸기
    @PostMapping("/getdata")
    public String getData(HttpServletRequest request, KorData korData, Model model, HttpSession session) {
        //세션 두종류 정리하기

//        KcalCal kcalCal = new KcalCal();

        model.addAttribute("kordata", new KorData());

        String car = request.getParameter("car");
        String pro = request.getParameter("pro");
        String fat = request.getParameter("fat");

        session.setAttribute("car", car);
        session.setAttribute("pro", pro);
        session.setAttribute("fat", fat);
//        String car2 = (String) session.getAttribute("car");
//
//        String CAR = request.getParameter("CAR");
//        session.setAttribute("CAR", CAR);

        String age = (String) session.getAttribute("age");
        System.out.println("넌나올거냐고" + age);
        System.out.println("탄수화물:" + car);

//        System.out.println("탄수화물2:" + CAR); //d이거는 나오던데

//        return "list2";

        return "redirect:viewfood";
    }

    //완결판
    @PostMapping("/her")
    public String please(HttpServletRequest request, HttpSession session) {
//
//        String CAR = request.getParameter("CAR");
//        session.setAttribute("CAR", CAR);
//        System.out.println("꿩불고기 탄수화물: " + CAR);
//        String PRO = request.getParameter("PRO");
//        session.setAttribute("PRO", PRO);
//        String FAT = request.getParameter("FAT");
//        session.setAttribute("FAT", FAT);
//
////        String CAR = (String) session.getAttribute("CAR");
////        request.setAttribute("CAR", CAR);
////        String age = (String) session.getAttribute("age"); //잘 작동하고 있습니다.
//
//
//        float car = Float.parseFloat((String) session.getAttribute("car"));
//        float CAR2 = Float.parseFloat((String) session.getAttribute("CAR"));
//
//        float pro = Float.parseFloat((String) session.getAttribute("pro"));
//        float PRO2 = Float.parseFloat((String) session.getAttribute("PRO"));
//
//        float fat = Float.parseFloat((String) session.getAttribute("fat"));
//        float FAT2 = Float.parseFloat((String) session.getAttribute("FAT"));
//
//        System.out.println("====================");
//        System.out.println(car);
//        System.out.println(CAR2);
//        System.out.println(PRO2);
//        System.out.println();
//
//        KcalCal kcalCal = new KcalCal();
//
//        float carCal = kcalCal.lesscal(car, CAR2);
//        float proCal = kcalCal.lesscal(pro, PRO2);
//        float fatCal = kcalCal.lesscal(fat, FAT2);
//
//        session.setAttribute("carCal", carCal);
//        session.setAttribute("proCal", proCal);
//        session.setAttribute("fatCal", fatCal);
//
//        System.out.println("===================");
//        System.out.println(carCal);
//        System.out.println(proCal);
//        System.out.println(fatCal);

        //arraylist 써서 데이터값 담기

        return "list2";
    }
}


//************************************************메일서비스부분************************************************************************






