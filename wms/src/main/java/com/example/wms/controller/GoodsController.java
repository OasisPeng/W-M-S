package com.example.wms.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.wms.common.QueryPageParam;
import com.example.wms.common.Result;
import com.example.wms.entity.Goods;
import com.example.wms.service.IGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wms
 * @since 2023-08-17
 */
@RestController
@RequestMapping("/goods")
public class GoodsController {
    @Autowired
    private IGoodsService goodsService;
    //新增
    @PostMapping("/save")
    public Result save(@RequestBody Goods goods){
        return goodsService.save(goods)?Result.suc(1L,goods):Result.fail();
    }
    //更新
    @PostMapping("/update")
    public Result update(@RequestBody Goods goods){
        return goodsService.updateById(goods)?Result.suc(1L,goods):Result.fail();
    }
    //删除
    @GetMapping("/del")
    public Result del(@RequestParam String id){
        return goodsService.removeById(id)?Result.suc(0L,id):Result.fail();
    }
    //分页查询
    @PostMapping("/listPage")
    //public List<Goods> listPage(@RequestBody HashMap map){
    //System.out.println("num=="+(String) map.get("pageSize"));
    public Result listPage(@RequestBody QueryPageParam query) {
//        System.out.println(query);
//        System.out.println("num==="+query.getPageNum());
//        System.out.println("size==="+query.getPageSize());
//        HashMap param =query.getParam();
//        System.out.println("name==="+(String) param.get("name"));
//        System.out.println("no==="+(String) param.get("no"));
        String name = (String) query.getParam().get("name");
        String goodsType = (String) query.getParam().get("goodsType");
        String storage = (String) query.getParam().get("storage");

        Page<Goods> page = new Page<>();
        page.setCurrent(query.getPageNum());
        page.setSize(query.getPageSize());

        LambdaQueryWrapper<Goods> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        if (StringUtils.isNotBlank(name) && !name.equals("null"))
            lambdaQueryWrapper.like(Goods::getName, name);//设置查询条件
        if (StringUtils.isNotBlank(goodsType) && !goodsType.equals("null"))
            lambdaQueryWrapper.eq(Goods::getGoodsType, goodsType);//设置查询条件
        if (StringUtils.isNotBlank(storage) && !storage.equals("null"))
            lambdaQueryWrapper.eq(Goods::getStorage, storage);//设置查询条件

        IPage result=goodsService.page(page,lambdaQueryWrapper);
        System.out.println(result.getTotal());

        return Result.suc(result.getTotal(),result.getRecords());
    }

}
