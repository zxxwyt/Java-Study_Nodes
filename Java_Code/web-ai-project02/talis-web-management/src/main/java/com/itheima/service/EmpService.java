package com.itheima.service;


import com.itheima.pojo.Emp;
import com.itheima.pojo.EmpQueryParam;
import com.itheima.pojo.PageResult;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import java.time.LocalDate;


public interface EmpService {
    PageResult<Emp> page(EmpQueryParam empQueryParam);

    void save(Emp emp);
//    PageResult<Emp> page(Integer page, Integer pageSize,  String name, Integer gender,
//                          LocalDate begin, LocalDate end);
}
