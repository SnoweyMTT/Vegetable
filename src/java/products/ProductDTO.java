/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package products;

import java.math.BigDecimal;
import java.sql.Date;

/**
 *
 * @author SnoweyMTT
 */
public class ProductDTO {
    private int productID;
    private String productName;
    private CategoryDTO category;
    private boolean status;
    private int quantity;
    private BigDecimal price;
    private Date createDate;
    private Date updateDate;
    private String productImage;

    public ProductDTO() {
    }

    public ProductDTO(String productName, CategoryDTO category, boolean status, int quantity, BigDecimal price, Date createDate, String productImage) {
        this.productName = productName;
        this.category = category;
        this.status = status;
        this.quantity = quantity;
        this.price = price;
        this.createDate = createDate;
        this.productImage = productImage;
    }

    public ProductDTO(int productId, String productName, CategoryDTO category, boolean status, int quantity, BigDecimal price, Date createDate, Date updateDate, String productImage) {
        this.productID = productId;
        this.productName = productName;
        this.category = category;
        this.status = status;
        this.quantity = quantity;
        this.price = price;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.productImage = productImage;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public CategoryDTO getCategory() {
        return category;
    }

    public void setCategory(CategoryDTO category) {
        this.category = category;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }
}
