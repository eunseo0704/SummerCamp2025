package com.spring.e0723.controller;

import com.spring.e0723.dto.ArticleForm;
import com.spring.e0723.entity.Article;
import com.spring.e0723.reopsitory.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
 * Repository 클래스를 ㅁ나든다.
 *
 */

@Controller
@Slf4j
public class Controller_input {
    //1. 변수 선언 DI
//    @Autowired
//    private ArticleRepository articleRepository;

    //3. 생성자 DI
    private final ArticleRepository articleRepository;
    Controller_input(ArticleRepository articleRepository){this.articleRepository = articleRepository;

    }

    @GetMapping("/input")
    public String input() {
        return "input";
    }

    @PostMapping("/create")
    public String createArticle(ArticleForm form) {
        log.info("사용자 정보 출력 : " + form);

        //1, 사용자 정보가 저장된 DTO를 ENTITY로 변경.
        Article entity = form.toEntity();
        log.info("entity:" + entity);

        //2. DataBase에 추가하기.
        Article save = articleRepository.save(entity);
        log.info("save : " + save);


        //return "redirect:/articles";
        return "redirect:/show/"+save.getId();
    }

    /** localhost:8080/show/3
     insert into article values(1,'111','가라가가');
     insert into article values(2,'222','나나나나');
     insert into article values(3,'333','다다다');
     * */


    @GetMapping("/show/{id}")
    public String show(@PathVariable Long id, Model model) {
        log.info("show() /id:" + id);
        Article article = articleRepository.findById(id).orElse(null);
        log.info("조회된 정보는: "+article);
        model.addAttribute("article", article);
        return "show";
    }

    @GetMapping("/articles")
    public String index(Model model) {
        /** 1. 디비에서 모든 데이터 가지고 오기*/
        ArrayList<Article> articleList = articleRepository.findAll();

        /** 2. 가지고온 데이터를 뷰화면에 전달 --> Model */
        model.addAttribute("articleList", articleList);

        return "index";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model

    ){
        log.info("/edit() / id : "+id);

        Article byId = articleRepository.findById(id).orElse(null);
        log.info("검샏긴 데이터: " + byId);

        model.addAttribute("article", byId);
        return "edit";
    }

    @PostMapping("/update")
    public String update( ArticleForm form) {
        log.info("update() / form : "+form);

        /** DTO 클래스를 Entitiy 클래스로 변경*/
        Article entity = form.toEntity();
        log.info("entity:" + entity);

        /** 사용자가 입력한 데이터를 디비에 업데이트*/
        Article save = articleRepository.save(entity);

        /** 상세페이지로 다시 이동*/
        return "redirect:/show/"+save.getId();


    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        log.info("delete() / 삭제합니다.");

        /** 지우는 방법 1. 아이디로 지우기*/
        articleRepository.deleteById(id);

        return "redirect:/articles";
    }


}