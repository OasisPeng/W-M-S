package com.example.wms.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.wms.common.QueryPageParam;
import com.example.wms.common.Result;
import com.example.wms.entity.Menu;
import com.example.wms.entity.User;
import com.example.wms.service.IMenuService;
import com.example.wms.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wms
 * @since 2023-07-27
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private IUserService userService;
    @Autowired
    private IMenuService menuService;
    @GetMapping("/list")
    public List<User> list(){
        return userService.list();
    }
    @GetMapping("/findByNo")
    public Result findByNo(@RequestParam String no){
        List<User> list=userService.lambdaQuery().eq(User::getNo,no).list();
        return list.size()>0?Result.suc(0L,list):Result.fail();
    }
    //新增
    @PostMapping("/save")
    public Result save(@RequestBody User user){
        return userService.save(user)?Result.suc(1L,user):Result.fail();
    }
    //更新
    @PostMapping("/update")
    public Result update(@RequestBody User user){
        return userService.updateById(user)?Result.suc(1L,user):Result.fail();
    }

    //修改
    @PostMapping("/mod")
    public boolean mod(@RequestBody User user){
        return userService.updateById(user);
    }
    //新增或修改
    @PostMapping("/saveOrMod")
    public boolean saveOrMod(@RequestBody User user){
        return userService.saveOrUpdate(user);
    }
    //删除
    @GetMapping("/delete")
    public boolean delete(Integer id){
        return userService.removeById(id);
    }
    @GetMapping("/del")
    public Result del(@RequestParam String id){
        return userService.removeById(id)?Result.suc(0L,id):Result.fail();
    }

    //查询（模糊、匹配）
    @PostMapping("/listP")
    public Result listP(@RequestBody User user){
        LambdaQueryWrapper<User> lambdaQueryWrapper=new LambdaQueryWrapper<>();
        if(StringUtils.isNotBlank(user.getName())) {
            lambdaQueryWrapper.like(User::getName, user.getName());//精准匹配用eq,或者其他查询方式
        }

        return Result.suc(0L,userService.list(lambdaQueryWrapper));
    }
    //分页查询
    @PostMapping("/listPage")
    //public List<User> listPage(@RequestBody HashMap map){
    //System.out.println("num=="+(String) map.get("pageSize"));
    public Result listPage(@RequestBody QueryPageParam query) {
//        System.out.println(query);
//        System.out.println("num==="+query.getPageNum());
//        System.out.println("size==="+query.getPageSize());
//        HashMap param =query.getParam();
//        System.out.println("name==="+(String) param.get("name"));
//        System.out.println("no==="+(String) param.get("no"));
        String name = (String) query.getParam().get("name");
        String sex = (String) query.getParam().get("sex");
        String roleId =(String) query.getParam().get("roleId");

        Page<User> page = new Page<>();
        page.setCurrent(query.getPageNum());
        page.setSize(query.getPageSize());

        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        if (StringUtils.isNotBlank(name) && !name.equals("null"))
            lambdaQueryWrapper.like(User::getName, query.getParam().get("name"));//设置查询条件
        if(StringUtils.isNotBlank(sex)){
            lambdaQueryWrapper.eq(User::getSex,sex);
        }
        if(StringUtils.isNotBlank(roleId)){
            lambdaQueryWrapper.eq(User::getRoleId,roleId);
        }
        IPage result=userService.page(page,lambdaQueryWrapper);
        System.out.println(result.getTotal());

        return Result.suc(result.getTotal(),result.getRecords());
    }
    //登录
    @PostMapping("/login")
    public Result login(@RequestBody User user){
        List list=userService.lambdaQuery()
                .eq(User::getNo,user.getNo())
                .eq(User::getPassword,user.getPassword()).list();
        if(list.size()>0){
            User user1=(User) list.get(0);
            List menuList=menuService.lambdaQuery().like(Menu::getMenuRight,user1.getRoleId()).list();//根据roleId获取菜单信息
            HashMap res=new HashMap();
            res.put("user",user1);
            res.put("menu",menuList);//同时得到用户和菜单信息
            return Result.suc(0L,res);
        }
        return Result.fail();
    }
}
