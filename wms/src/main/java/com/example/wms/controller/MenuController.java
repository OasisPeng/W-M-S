package com.example.wms.controller;

import com.example.wms.common.Result;
import com.example.wms.entity.Menu;
import com.example.wms.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wms
 * @since 2023-08-12
 */
@RestController
@RequestMapping("/menu")
public class MenuController {
    @Autowired
    private IMenuService menuService;
    @GetMapping("/list")
    public Result list(@RequestParam String roleId){
        List<Menu> list=menuService.lambdaQuery().like(Menu::getMenuRight,roleId).list();
        return Result.suc(0L,list);
    }
}
