package com._hackaton._back.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/createForm")
    public String createFormPage() {
        // createForm 페이지로 이동
        return "createForm"; // 뷰의 이름 (템플릿 파일의 이름)
    }
}
