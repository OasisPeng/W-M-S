package com.example.wms.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.wms.common.QueryPageParam;
import com.example.wms.common.Result;
import com.example.wms.entity.Storage;
import com.example.wms.service.IStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wms
 * @since 2023-08-17
 */
@RestController
@RequestMapping("/storage")
public class StorageController {
    @Autowired
    private IStorageService storageService;
    //新增
    @PostMapping("/save")
    public Result save(@RequestBody Storage storage){
        return storageService.save(storage)?Result.suc(1L,storage):Result.fail();
    }
    //更新
    @PostMapping("/update")
    public Result update(@RequestBody Storage storage){
        return storageService.updateById(storage)?Result.suc(1L,storage):Result.fail();
    }
    //删除
    @GetMapping("/del")
    public Result del(@RequestParam String id){
        return storageService.removeById(id)?Result.suc(0L,id):Result.fail();
    }
    //分页查询
    @PostMapping("/listPage")
    //public List<storage> listPage(@RequestBody HashMap map){
    //System.out.println("num=="+(String) map.get("pageSize"));
    public Result listPage(@RequestBody QueryPageParam query) {
//        System.out.println(query);
//        System.out.println("num==="+query.getPageNum());
//        System.out.println("size==="+query.getPageSize());
//        HashMap param =query.getParam();
//        System.out.println("name==="+(String) param.get("name"));
//        System.out.println("no==="+(String) param.get("no"));
        String name = (String) query.getParam().get("name");

        Page<Storage> page = new Page<>();
        page.setCurrent(query.getPageNum());
        page.setSize(query.getPageSize());

        LambdaQueryWrapper<Storage> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        if (StringUtils.isNotBlank(name) && !name.equals("null"))
            lambdaQueryWrapper.like(Storage::getName, query.getParam().get("name"));//设置查询条件

        IPage result=storageService.page(page,lambdaQueryWrapper);
        System.out.println(result.getTotal());

        return Result.suc(result.getTotal(),result.getRecords());
    }
    //
    @GetMapping("/list")
    public Result list(){
        List list=storageService.list();
        return Result.suc(0L,list);
    }
}
