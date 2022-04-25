package com.zsy.xuki.common;


import org.springframework.stereotype.Component;

/**
 * 分页基本信息
 * @author asus
 *
 */
@Component
public class Page {
    private int page = 1;//当前页码

    private int rows;//每页显示数量

    private int offset;//对应数据库中的偏移量

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }


}
