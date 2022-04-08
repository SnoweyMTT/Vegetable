/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package products;

/**
 *
 * @author SnoweyMTT
 */
public class ProductErrorDTO {
    private String productNameError;
    private String quantityError;
    private String priceError;
    private String productImageError;
    private String categoryNameError;

    public ProductErrorDTO() {
    }

    public ProductErrorDTO(String productNameError, String quantityError, String priceError, String productImageError, String categoryNameError) {
        this.productNameError = productNameError;
        this.quantityError = quantityError;
        this.priceError = priceError;
        this.productImageError = productImageError;
        this.categoryNameError = categoryNameError;
    }

    public String getProductNameError() {
        return productNameError;
    }

    public void setProductNameError(String productNameError) {
        this.productNameError = productNameError;
    }

    public String getQuantityError() {
        return quantityError;
    }

    public void setQuantityError(String quantityError) {
        this.quantityError = quantityError;
    }

    public String getPriceError() {
        return priceError;
    }

    public void setPriceError(String priceError) {
        this.priceError = priceError;
    }

    public String getProductImageError() {
        return productImageError;
    }

    public void setProductImageError(String productImageError) {
        this.productImageError = productImageError;
    }

    public String getCategoryNameError() {
        return categoryNameError;
    }

    public void setCategoryNameError(String categoryNameError) {
        this.categoryNameError = categoryNameError;
    }
    
    
}
