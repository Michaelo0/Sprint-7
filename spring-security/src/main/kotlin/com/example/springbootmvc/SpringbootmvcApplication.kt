package com.example.springbootmvc

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.boot.web.servlet.ServletComponentScan

@SpringBootApplication
@ServletComponentScan
class SpringbootmvcApplication

fun main(args: Array<String>) {
    runApplication<SpringbootmvcApplication>(*args)
}
