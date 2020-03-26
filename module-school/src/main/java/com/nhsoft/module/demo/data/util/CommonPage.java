package com.nhsoft.module.demo.data.util;

import lombok.Data;

import java.util.List;

@Data
public class CommonPage<T> {
    private Integer offset;
    private Integer limit;
    private Integer totalPage;
    private Integer total;
    private List<T> list;

    public static <T> CommonPage<T> restPage(List<T> list, int offset, int limit, int total){
        CommonPage<T> result = new CommonPage<T>();
        result.setOffset(offset);
        result.setLimit(limit);
        result.setTotal(total);
        result.setTotalPage((total + limit - 1)/limit);
        result.setList(list);
        return result;
    }
}
