package com.daisuzz.samplespringsecurity

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.ModelAndView

@Controller
@RequestMapping("admin")
class AdminController {

    @GetMapping
    fun index(modelAndView: ModelAndView): ModelAndView {

        modelAndView.viewName = "admin/index"
        return modelAndView
    }
}
