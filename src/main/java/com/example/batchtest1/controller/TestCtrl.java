package com.example.batchtest1.controller;

import com.example.batchtest1.mapper.TestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestCtrl {

    @Autowired
    TestMapper testMapper;

    @GetMapping(value = "/test")
    ResponseEntity getTestItem(){

        return ResponseEntity.ok().body(testMapper.testItem());
    }


}
