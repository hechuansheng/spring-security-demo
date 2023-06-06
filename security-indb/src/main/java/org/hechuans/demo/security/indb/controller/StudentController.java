package org.hechuans.demo.security.indb.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : hechuansheng
 * @date : 2023/6/6 18:37
 * @description :
 * @since : version-1.0
 */
@RestController
@RequestMapping("/student")
public class StudentController {

    @GetMapping("list")
    public String list() {
        return "student list";
    }
}
