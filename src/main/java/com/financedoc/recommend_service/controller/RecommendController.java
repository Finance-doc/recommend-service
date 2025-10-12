//package com.financedoc.recommend_service.controller;
//
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestHeader;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/recommend")
//public class RecommendController {
//
//    @GetMapping("/test")
//    public String test(
//            @RequestHeader(value = "X-User-Email", required = false) String email
//    ){
//        if (email == null) {
//            email = "null token"; // 기본값
//        }
//
//        return "Recommend Service Test OK & X-User-Email = "+email;
//    }
//
//}
