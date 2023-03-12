package com.paltvlad.market.api;

import java.util.List;

public class PageDto<E> {

    private List<E>items;
    private int page;
    private int totalPage;

    public PageDto(){

    }

    public List<E> getItems() {
        return items;
    }

    public void setItems(List<E> items) {
        this.items = items;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }
}
