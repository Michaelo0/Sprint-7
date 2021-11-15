package com.example.springbootmvc.controller

import com.example.springbootmvc.DataBase
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api")
class JSONController @Autowired constructor(val dataBase: DataBase) {

    @GetMapping("/list")
    fun getList() = dataBase.addressBook

    @PostMapping("/add")
    fun addToList(@RequestBody name : String) {
        dataBase.addressBook[dataBase.addressBook.size] = name
    }

    @GetMapping("/{id}/view")
    fun getListById(@PathVariable("id")id: Int) = dataBase.addressBook[id]

    @PostMapping("/{id}/delete")
    fun deleteFromListById(@PathVariable("id")id: Int) {
        dataBase.addressBook.remove(id)
    }

    @PostMapping("/{id}/edit")
    fun editNoteById(@PathVariable("id")id: Int, @RequestBody name : String) {
        dataBase.addressBook[id] = name
    }
}