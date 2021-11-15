package com.example.springbootmvc

import org.springframework.stereotype.Service
import java.util.concurrent.ConcurrentHashMap

@Service
class DataBase {
    val addressBook = ConcurrentHashMap<Int, String>()
}