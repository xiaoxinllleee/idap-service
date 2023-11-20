package org.cmms.modules.word.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo")
public class TestMethdoController {
    @RequestMapping("/method")
    public String methdo1(){
        return "hello word";
    }
}
