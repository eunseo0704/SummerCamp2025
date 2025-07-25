package com.spring.e0723.controller;

import com.spring.e0723.dto.MemberForm;
import com.spring.e0723.entity.Article;
import com.spring.e0723.entity.Member;
import com.spring.e0723.reopsitory.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * 의존성 주입 ( DI )
 * 1. 변수선언 하는 방법
 *
 * 2. 메소드에서 선언하는 방법
 *
 * 3. 생성자에서 선언하는 방법
 *
 * 글쓰기 게시판을 사용하기 위해서.
 * 1.DTO 클래스를 만든다.
 * ENTITY 클래스를 만든다.
 * Controller 클래스를 만든다.
 * Repository 클래스를 만든다.
 *
 */

@Controller
@Slf4j
public class MemberController {
    private final MemberRepository memberRepository;
    MemberController(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }

    @GetMapping("/signup")
    public String input() {
        return "signup";
    }

    @PostMapping("/member")
    public String createMember(MemberForm form) {
        log.info("사용자 정보 출력 : " + form);

        //1, 사용자 정보가 저장된 DTO를 ENTITY로 변경.
        Member entity = form.toEntity();
        log.info("entity:" + entity);

        //2. DataBase에 추가하기.
        Member save = memberRepository.save(entity);
        log.info("save : " + save);


        return "";
    }


}