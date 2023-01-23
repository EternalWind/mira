package io.eternalwind.mira.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.eternalwind.mira.model.Entity;

@RestController
public class IndexController {
    @GetMapping("/index")
    public String index() {
        final Entity entity = new Entity("mira");
        return "hello " + entity.toString();
    }
}
