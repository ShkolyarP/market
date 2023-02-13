package com.paltvlad.market.api;


import java.math.BigDecimal;
import java.util.Objects;



public class ProductDto {


    private Long id;

    private String title;

    private BigDecimal price;

    private String categoryTitle;

    public ProductDto(Long id, String title, BigDecimal price, String categoryTitle) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.categoryTitle = categoryTitle;
    }

    public ProductDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getCategoryTitle() {
        return categoryTitle;
    }

    public void setCategoryTitle(String categoryTitle) {
        this.categoryTitle = categoryTitle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductDto that = (ProductDto) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
