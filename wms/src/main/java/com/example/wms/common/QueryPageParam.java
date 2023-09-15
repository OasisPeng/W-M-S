package com.example.wms.common;

import lombok.Data;

import java.util.HashMap;

@Data //自动生成getter setter
public class QueryPageParam {
    //分页查询入参封装（也可以不封装，用hashmap）
    //默认
    private static int PAGE_SIZE=20;
    private static int PAGE_NUM=1;
    //sql的两个查询条件去处理分页
    private int pageSize=PAGE_SIZE;
    private int pageNum=PAGE_NUM;
    private HashMap param=new HashMap();
}
