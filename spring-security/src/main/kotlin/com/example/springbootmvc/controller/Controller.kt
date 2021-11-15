package com.example.springbootmvc.controller

import com.example.springbootmvc.DataBase
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.ui.Model
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


@Controller
@RequestMapping("/")
class Controller @Autowired constructor(val dataBase: DataBase){

    @GetMapping("/app/list")
    fun getList(model: Model): String {
        model.addAttribute("addressBook", dataBase.addressBook)
        return "app-page"
    }

    @PostMapping("/app/add")
    fun addToList(@RequestParam("name")name: String, model: Model): String {
        dataBase.addressBook[dataBase.addressBook.size] = name
        model.addAttribute("names", dataBase.addressBook)
        return "redirect:/app/list"
    }

    @GetMapping("/app/{id}/view")
    fun getListById(@PathVariable("id")id: Int, model: Model): String {
        model.addAttribute("names", dataBase.addressBook[id])
        return "redirect:/app/list"
    }

    @PostMapping("/app/delete")
    fun deleteFromList(@RequestParam("id")id: String, request: HttpServletRequest, response: HttpServletResponse) {
        request.getRequestDispatcher("/app/$id/delete").forward(request, response)
    }

    @PostMapping("/app/{id}/delete")
    fun deleteFromListById(@PathVariable("id")id: Int, model: Model): String {
        dataBase.addressBook.remove(id)
        model.addAttribute("notes", dataBase.addressBook)
        return "redirect:/app/list"
    }

    @PostMapping("/app/edit")
    fun editNote(@RequestParam("id")id: String, request: HttpServletRequest, response: HttpServletResponse) {
        request.getRequestDispatcher("/app/$id/edit").forward(request, response)
    }

    @PostMapping("/app/{id}/edit")
    fun editNoteById(@PathVariable("id")id: Int, @RequestParam("name")name: String, model: Model): String {
        dataBase.addressBook[id] = name
        model.addAttribute("notes", dataBase.addressBook)
        return "redirect:/app/list"
    }
}