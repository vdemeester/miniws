package com.github.vdemeester.miniws.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/default")
public class DefaultController {

    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultController.class);

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody String getDefaultMessage() {
        LOGGER.info("Default message");
        return "Nothing to see here";
    }
}
