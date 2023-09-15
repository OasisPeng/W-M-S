package com.example.wms.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.wms.common.QueryPageParam;
import com.example.wms.common.Result;
import com.example.wms.entity.Goods;
import com.example.wms.entity.Record;
import com.example.wms.service.IGoodsService;
import com.example.wms.service.IRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wms
 * @since 2023-08-20
 */
@RestController
@RequestMapping("/record")
public class RecordController {
    @Autowired
    private IRecordService recordService;
    @Autowired
    private IGoodsService goodsService;
    @PostMapping("/listPage")
    //public List<Goods> listPage(@RequestBody HashMap map){
    //System.out.println("num=="+(String) map.get("pageSize"));
    public Result listPage(@RequestBody QueryPageParam query) {
        String name = (String) query.getParam().get("name");
        String goodsType = (String) query.getParam().get("goodsType");
        String storage = (String) query.getParam().get("storage");
        String roleId = (String) query.getParam().get("roleId");
        String userId = (String) query.getParam().get("userId");

        Page<Record> page = new Page<>();
        page.setCurrent(query.getPageNum());
        page.setSize(query.getPageSize());

        QueryWrapper<Record> queryWrapper = new QueryWrapper<>();
        queryWrapper.apply(" a.goods=b.id and b.storage=c.id and b.goodsType=d.id");
        if("2".equals(roleId)){
            queryWrapper.apply("a.userId= "+userId);
        }
        if (StringUtils.isNotBlank(name) && !name.equals("null")){
            queryWrapper.like("b.name",name);
        }
        if (StringUtils.isNotBlank(goodsType) && !goodsType.equals("null")) {
            queryWrapper.eq("d.id",goodsType);
        }
        if (StringUtils.isNotBlank(storage) && !storage.equals("null")) {
            queryWrapper.eq("c.id",storage);
        }
        IPage<Record> result=recordService.pageC(page,queryWrapper);

        System.out.println(result.getTotal());

        return Result.suc(result.getTotal(),result.getRecords());
    }
    //新增
    @PostMapping("/save")
    public Result save(@RequestBody Record record){
        Goods goods=goodsService.getById(record.getGoods());
        int n=record.getCount();
        if(record.getAction()==2){
            record.setCount(-n);
            goods.setCount(goods.getCount()-n);
        }else {
            goods.setCount(goods.getCount() + n);
        }
        goodsService.updateById(goods);

        record.setCreatetime(LocalDateTime.now());
        return recordService.save(record)?Result.suc(1L,record):Result.fail();
    }
}
