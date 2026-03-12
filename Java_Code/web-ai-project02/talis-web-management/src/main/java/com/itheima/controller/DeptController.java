package com.itheima.controller;


import com.itheima.pojo.Dept;
import com.itheima.pojo.Result;
import com.itheima.service.DeptService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Delete;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping("/depts")
@RestController
public class DeptController {
//    private static final Logger log= LoggerFactory.getLogger(DeptController.class);

    @Autowired
    private DeptService deptService;

//    @RequestMapping(value = "/depts",method = RequestMethod.GET)
    @GetMapping
    public Result list (){
       log.info("查询全部的部门信息");
       List<Dept> deptList= deptService.findAll();
       return Result.success(deptList);
    }
//    @DeleteMapping("/depts")
//    public Result delete (HttpServletRequest request){
//        String idStr = request.getParameter("id");
//        int id = Integer.parseInt(idStr);
////        deptService.delete(id);
//        System.out.println("删除部门数据"+id);
//        return Result.success();
//    }


// 删除部门-方式二：@RequestParam
//注意事项：一旦声明了@RequestParam注解，就必须传递这个参数，否则会报错。如果参数是可选的，可以设置required=false，并提供一个默认值。
//@DeleteMapping("/depts")
//public Result delete (@RequestParam(value = "id",required = false) Integer deptId){
//
//    System.out.println("删除部门数据"+deptId);
//    return Result.success();
//}


//方式三：省略@RequestParam(前端传递的请求参数名与服务端方法形参名一致)【推荐】
    @DeleteMapping
    public Result delete ( Integer id){

        log.info("删除部门数据:{}",id);
        deptService.deleteById(id);
        return Result.success();
    }
    //新增部门
    @PostMapping
    public Result add(@RequestBody Dept dept){

//        System.out.println("新增部门数据"+dept);
        log.info("新增部门数据:{}",dept);
        deptService.add(dept);

        return Result.success();
    }

    //查询操作
    @GetMapping("/{id}")
    public Result getInfo(@PathVariable Integer id){
        System.out.println("查询部门数据"+id);
        Dept dept=deptService.getById(id);
        return Result.success(dept);
    }


    //修改部门
    @PutMapping
    public Result update(@RequestBody Dept dept){
//        System.out.println("修改部门数据"+dept);
        log.info("修改部门数据:{}",dept);
        deptService.update(dept);

        return Result.success();
    }
}
