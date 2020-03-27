/**
 * FileName: TestController
 * Author:   shuyh
 * Date:     2020/3/26 15:58
 * Description:
 */
package com.syh.concurrent.controller;

import com.syh.concurrent.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validator;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Controller
@Validated //将Validated放在controller上，这个参数可以告诉 Spring 去校验方法参数。
public class TestController {
    @Autowired
    Validator validator;

    /**
     * 实体类验证
     * */
    @RequestMapping(value = "/addUser",method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity addUser(@RequestBody @Valid User user){

        return ResponseEntity.ok(user);
    }

    /**
     * 单独参数直接验证
     * */
    @RequestMapping(value = "/getEmail",method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity getEmail(@Email @RequestParam(value = "email") String a,@NotNull String username){

        return ResponseEntity.ok(a);
    }

    /**
     * 在方法中进行实体类验证
     * */
    @RequestMapping(value = "/validated",method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity Validated(@RequestBody User user){
        Set<ConstraintViolation<User>> violationSet= validator.validate(user);
        //如果set.length()不为空，则有参数不符合校验要求
        for (ConstraintViolation<User> users:violationSet
             ) {
            System.out.println(users.getMessage());
        }
        return ResponseEntity.ok(user);
    }



}
