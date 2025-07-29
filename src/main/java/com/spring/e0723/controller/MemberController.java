package com.spring.e0723.controller;

import com.spring.e0723.dto.MemberForm;
import com.spring.e0723.entity.Article;
import com.spring.e0723.entity.Member;
import com.spring.e0723.reopsitory.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.Optional;

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

    @PostMapping("/signupCreate")
    public String signupCreate(MemberForm memberForm) {
        log.info("이메일 정보 출력 : " + memberForm);

        //1, 사용자 정보가 저장된 DTO를 ENTITY로 변경.
        Member entity = memberForm.toEntity();
        log.info("entity:" + entity);

        //2. DataBase에 추가하기.
        Member save = memberRepository.save(entity);
        log.info("save : " + save);

        return "redirect:/members";
    }

    @GetMapping("/members")
    public String membersList(Model model) {
        log.info("membersList() 메소드 입니다. " );

        /** 디비에서 전체 회원정보 가지고 오기 */
        ArrayList<Member> all = memberRepository.findAll();

        /** 디비에서 가지고오 정보를 memberindex.mustache 파일로 전달해주기*/
        model.addAttribute("members", all);


        /** memberindex.mustache 파일로 이동하기 */
        return "memberindex";
    }

    @GetMapping("/member/{id}")
    public String showMember(@PathVariable Long id, Model model) {
        log.info("showMember() / id : " + id);
        /**1. 사용자가 입력한 id값으로 DB 조회 */
        Member memberInfo = memberRepository.findById(id).orElse(null);
        log.info("memberInfo : " + memberInfo);

        /** 조회된 정보를 show.mustache 파일에 전달 */
        model.addAttribute("memberInfo", memberInfo);

        /** showlmustache 파일로 이동 */
        return "membershow";
    }


}