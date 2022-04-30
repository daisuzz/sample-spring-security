package com.daisuzz.samplespringsecurity.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.ModelAndView

@Controller
@RequestMapping("login")
class LoginController {
    @GetMapping
    fun login(modelAndView: ModelAndView): ModelAndView {
        modelAndView.viewName = "login/login"
        return modelAndView
    }
}
