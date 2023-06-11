package com.libraryExample.musicApplication

import com.libraryExample.musicApplication.dtos.LoginDTO
import com.libraryExample.musicApplication.dtos.RegisterDTO
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
@Controller
class WebController {

    @GetMapping("/")
    fun index(model: Model): String {
        model.addAttribute("registerDTO", RegisterDTO())
        model.addAttribute("loginDTO", LoginDTO())
        return "index"
    }
}
