package com.spring.e0723.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class Controller_01 {
    @GetMapping("/abcd")
    public String method01(Model model) {
        model.addAttribute("username", "dalbong2");
        return "hi";
    }

    @GetMapping("/test01")
    public String method02(Model model) {
        model.addAttribute("username2", "안녕하세요.");
        return "test01";
    }
    @GetMapping("/exam01")
    public String method03(Model model) {
        model.addAttribute("textArr", "문장형");
        return "ee01";
    }

    @GetMapping("/exam02")
    public String method04(Model model,
                         @RequestParam String num01,
                         @RequestParam String var02 ) {
        int sum = Integer.parseInt(num01) + Integer.parseInt(var02);
        model.addAttribute("numValue", "" + sum );
        return "ex02";
    }

}

/**
 * DTO - Data Access Object
 *  사용자가 서버에 데이터를 전달할때 사용하는 클래스
 *  서버가 사용자한테 데이터를 전달할때 사용하는 클래스.
 *
 *  DAO -Data Access Object
 *  ENTITY -
 *   서버가 데이터베이스한테 데이터를 전달할때 사용하는 클래스
 *   데이터베이스가 서버한테 데이터를 전달할때 사용하는 클래스
 *   H2.DB, JPA
 *
 *
 *
 *   데이터베이스, 스카마, 테이블
 *
 *   클래스 란? - 먹이(?), 사용하면 안된다.
 *
 *   객체 란? - 먹이
 */