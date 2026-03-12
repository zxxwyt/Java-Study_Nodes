package com.itheima.service.impl;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.mapper.EmpExprMapper;
import com.itheima.mapper.EmpMapper;
import com.itheima.pojo.Emp;
import com.itheima.pojo.EmpExpr;
import com.itheima.pojo.EmpQueryParam;
import com.itheima.pojo.PageResult;
import com.itheima.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Service
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpMapper empMapper;


    @Autowired
    private EmpExprMapper empExprMapper;

    @Override
    public PageResult<Emp> page(EmpQueryParam empQueryParam) {
//        1.设置分页参数
             PageHelper.startPage(empQueryParam.getPage(),empQueryParam.getPageSize());

            //2.执行查询
        List<Emp> empList = empMapper.list(empQueryParam);

        //3.解析查询结果,并且封装
        Page<Emp> p=(Page<Emp>)empList;
        return new PageResult<Emp>(p.getTotal(),p.getResult());
    }

    @Override
    public void save(Emp emp) {
        //1,保存员工的基本信息
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.insert(emp);
        //2.保存员工的经历信息

        List<EmpExpr> exprList = emp.getExprList();
        if(!CollectionUtils.isEmpty(exprList))
        {
            exprList.forEach(expr->{
                empExpr.setEmpId(emp.getId());
            });

        }
        empExprMapper.insertBatch(exprList);
    }
//    @Override
//    public PageResult<Emp> page(Integer page, Integer pageSize,String name, Integer gender,
//                                LocalDate begin, LocalDate end) {
//        //原始分页查询
//        //调用mapper接口，查询总记录数
////         Long total = empMapper.count();
////
////        //2.调用mapper接口，查询结果列表
////        Integer start = (page-1)*pageSize;
////        List<Emp> rows = empMapper.list(start,pageSize);
////
////        //3.封装PageResult对象并返回
////        return new PageResult<Emp>(total,rows);
//
//        //基于pageHelper分页查询
//
//            //1.设置分页参数
//             PageHelper.startPage(page,pageSize);
//
//            //2.执行查询
//        List<Emp> empList = empMapper.list(name, gender, begin, end);
//
//        //3.解析查询结果,并且封装
//        Page<Emp> p=(Page<Emp>)empList;
//        return new PageResult<Emp>(p.getTotal(),p.getResult());
//
//
//
//    }
}
