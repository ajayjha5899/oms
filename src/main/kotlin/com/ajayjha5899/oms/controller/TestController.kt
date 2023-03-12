package com.ajayjha5899.oms.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1")
class TestController {

    @GetMapping("/test")
    fun displayText(): String {
        return "This works fine."
    }
}