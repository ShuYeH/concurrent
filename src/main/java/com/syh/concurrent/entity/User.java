/**
 * FileName: User
 * Author:   shuyh
 * Date:     2020/3/26 14:50
 * Description:
 */
package com.syh.concurrent.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.*;
import java.util.Date;

@Data //提供getter，setter等一系列方法，如果编辑器的idea，需要安装lombok插件支持，不然无法获取getter，setter
@AllArgsConstructor //提供所有参数的构造方法
@NoArgsConstructor//提供没有参数的构造方法
public class User {

    private int id;

    @NotBlank(message = "用户名不能为空")  //NotBlank:非null，且长度必须大于0
    private String username;

    @Size(min = 8) //Size:被注释的元素的大小必须在指定的范围内
    @NotBlank(message = "密码不能为空") //只作用于string
    private String password;

    @Email //符合邮箱的规则
    @NotBlank(message = "邮箱不能为空")
    private String email;

    @Pattern(regexp ="((^man$|^women$))" ,message = "必须是男女其中一个值")
    @NotBlank(message = "性别不能为空")
    private String sex;

    //@Past //过去的日期
    //private Date lastLoginDate;

    @NotNull //int不能使用notEmpty
    private int errorLoginTime;

    /*
    JSR提供的校验注解:

    @Null   被注释的元素必须为 null
    @NotNull    被注释的元素必须不为 null
    @AssertTrue     被注释的元素必须为 true
    @AssertFalse    被注释的元素必须为 false
    @Min(value)    被注释的元素必须是一个数字，其值必须大于等于指定的最小值
    @Max(value)    被注释的元素必须是一个数字，其值必须小于等于指定的最大值
    @DecimalMin(value) 被注释的元素必须是一个数字，其值必须大于等于指定的最小值
    @DecimalMax(value)  被注释的元素必须是一个数字，其值必须小于等于指定的最大值
    @Size(max=, min=)  被注释的元素的大小必须在指定的范围内
    @Digits (integer, fraction)    被注释的元素必须是一个数字，其值必须在可接受的范围内
    @Past  被注释的元素必须是一个过去的日期
    @Future     被注释的元素必须是一个将来的日期
    @Pattern(regex=,flag=) 被注释的元素必须符合指定的正则表达式

    正则表达式的说明：
    - ^string : 匹配以 string 开头的字符串
    - string$ ：匹配以 string 结尾的字符串
    - ^string$ ：精确匹配 string 字符串
    - ((^Man$|^Woman$|^UGM$)) : 值只能在 Man,Woman,UGM 这三个值中选择

    Hibernate Validator提供的校验注解：

    @NotBlank(message =)  验证字符串非null，且长度必须大于0
    @Email  被注释的元素必须是电子邮箱地址
    @Length(min=,max=) 被注释的字符串的大小必须在指定的范围内
    @NotEmpty  被注释的字符串的必须非空
    @Range(min=,max=,message=)  被注释的元素必须在合适的范围内
    */

}
