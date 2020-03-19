package com.example.demo.util;

import lombok.Data;

import java.util.List;

@Data
public class CommonPage<T> {
    private Integer pageNum;
    private Integer pageSize;
    private Integer totalPage;
    private Integer total;
    private List<T> list;

    public static <T> CommonPage<T> restPage(List<T> list,int pageNum,int pageSize,int total){
        CommonPage<T> result = new CommonPage<T>();
        result.setPageNum(pageNum);
        result.setPageSize(pageSize);
        result.setTotal(total);
        result.setTotalPage((total + pageSize - 1)/pageSize);
        result.setList(list);
        return result;
    }
}
