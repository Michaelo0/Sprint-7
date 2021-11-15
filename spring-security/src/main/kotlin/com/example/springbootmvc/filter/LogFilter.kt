package com.example.springbootmvc.filter

import org.springframework.core.annotation.Order
import java.time.Instant
import javax.servlet.DispatcherType
import javax.servlet.FilterChain
import javax.servlet.annotation.WebFilter
import javax.servlet.http.HttpFilter
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebFilter(urlPatterns = ["/*"], dispatcherTypes = [DispatcherType.REQUEST])
@Order(1)
class LogFilter : HttpFilter() {
    override fun doFilter(servletRequest: HttpServletRequest, servletResponse: HttpServletResponse, chain: FilterChain) {
        println("Request URL: ${servletRequest.requestURL} at ${Instant.now()}")
        chain.doFilter(servletRequest, servletResponse)
    }
}