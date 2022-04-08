/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package products;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.DBUtils;

/**
 *
 * @author SnoweyMTT
 */
public class ProductDAO {

    public ArrayList<ProductDTO> getListProducts(String search) throws SQLException, ClassNotFoundException {
        ArrayList<ProductDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = " SELECT Products.productID, Products.productName, Products.categoryID, Products.quantity, Products.price, Products.createDate, Products.updateDate, Products.productImage, Categories.categoryName, Categories.description " 
                        + " FROM Products " 
                        + " FULL JOIN Categories ON Products.categoryID = Categories.categoryID " 
                        + " WHERE productName LIKE ? AND status = 1";
                stm = conn.prepareStatement(sql);
                stm.setString(1, "%" + search + "%");
                rs = stm.executeQuery();
                while (rs.next()) {
                    int productID = rs.getInt("productID");
                    String productName = rs.getString("productName");
                    int quantity = rs.getInt("quantity");
                    BigDecimal price = rs.getBigDecimal("price");
                    Date createDate = rs.getDate("createDate");
                    Date updateDate = rs.getDate("updateDate");
                    String productImage = rs.getString("productImage");
                    
                    int categoryID = rs.getInt("categoryID");
                    String categoryName = rs.getString("categoryName");
                    String description = rs.getString("description");
                    CategoryDTO category = new CategoryDTO(categoryID, categoryName, description);
                    
                    list.add(new ProductDTO(productID, productName, category, true, quantity, price, createDate, updateDate, productImage));
                }
            }
        } catch (SQLException e) {
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }

        }
        return list;
    }

    public ArrayList<CategoryDTO> getCategories() throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        ArrayList<CategoryDTO> categories = new ArrayList<>();
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "SELECT categoryID, categoryName, description "
                        + " FROM Categories";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();

                while (rs.next()) {
                    CategoryDTO c = new CategoryDTO();
                    c.setCategoryID(rs.getInt("categoryID"));
                    c.setCategoryName(rs.getString("categoryName"));
                    c.setDescription(rs.getString("description"));
                    categories.add(c);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }

        return categories;
    }

    public ArrayList<ProductDTO> getProductsByCategories(String categoryName) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        ArrayList<ProductDTO> plist = new ArrayList<>();
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = " SELECT Products.productID, Products.productName, Products.status, Products.quantity, Products.price, Products.createDate, Products.updateDate, Products.productImage, Categories.categoryID, Categories.description "
                        + " FROM Products INNER JOIN Categories ON Products.categoryID = Categories.categoryID "
                        + " WHERE categoryName=? AND status=1";
                stm = con.prepareStatement(sql);
                stm.setString(1, categoryName);
                rs = stm.executeQuery();

                while (rs.next()) {
                    int productID = rs.getInt("productID");
                    String productName = rs.getString("productName");
                    boolean status = rs.getBoolean("status");
                    int quantity = rs.getInt("quantity");
                    BigDecimal price = rs.getBigDecimal("price");
                    Date createDate = rs.getDate("createDate");
                    Date updateDate = rs.getDate("updateDate");
                    String productImage = rs.getString("productImage");

                    int categoryID = rs.getInt("categoryID");
                    String description = rs.getString("description");
                    CategoryDTO category = new CategoryDTO(categoryID, categoryName, description);

                    if (status) {
                        plist.add(new ProductDTO(productID, productName, category, status, quantity, price, createDate, updateDate, productImage));
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }

        return plist;
    }
    
    public ProductDTO getProductsByID(String productID) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        ProductDTO product = new ProductDTO();
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = " SELECT Products.productName, Products.status, Products.quantity, Products.price, Products.createDate, Products.updateDate, Products.productImage, Categories.categoryID, Categories.categoryName, Categories.description "
                        + " FROM Products INNER JOIN Categories ON Products.categoryID = Categories.categoryID "
                        + " WHERE productID=? AND status=1";
                stm = con.prepareStatement(sql);
                stm.setString(1, productID);
                rs = stm.executeQuery();

                if (rs.next()) {
                    String productName = rs.getString("productName");
                    boolean status = rs.getBoolean("status");
                    int quantity = rs.getInt("quantity");
                    BigDecimal price = rs.getBigDecimal("price");
                    Date createDate = rs.getDate("createDate");
                    Date updateDate = rs.getDate("updateDate");
                    String productImage = rs.getString("productImage");
                    
                    int categoryId = rs.getInt("categoryID");
                    String categoryName = rs.getString("categoryName");
                    String description = rs.getString("description");
                    CategoryDTO category = new CategoryDTO(categoryId, categoryName, description);
                    
                    product = new ProductDTO(Integer.parseInt(productID), productName, category, status, quantity, price, createDate, updateDate, productImage);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        
        return product;
    }

    public boolean updateProductQuantity(ProductDTO p) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "UPDATE Products SET quantity=? "
                        + " WHERE productID=? ";
                stm = con.prepareStatement(sql);
                stm.setInt(1, p.getQuantity());
                stm.setInt(2, p.getProductID());
                stm.executeUpdate();
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }

        return false;
    }
    
    public boolean updateProduct(ProductDTO p) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = " UPDATE Products "
                        + " SET productName = ?, categoryID = ?, quantity =?, status = ?, price =?, updateDate =?, productImage =? "
                        + " WHERE productID=? ";
                stm = con.prepareStatement(sql);
                stm.setString(1, p.getProductName());
                stm.setInt(2, p.getCategory().getCategoryID());
                stm.setInt(3, p.getQuantity());
                stm.setBoolean(4, p.isStatus());
                stm.setBigDecimal(5, p.getPrice());
                stm.setDate(6, p.getUpdateDate());
                stm.setString(7, p.getProductImage());
                stm.setInt(8, p.getProductID());
                stm.executeUpdate();
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }

        return false;
    }
    
    public boolean deleteProduct(String productID) throws SQLException, ClassNotFoundException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = " UPDATE Products "
                        + " SET status = 0 "
                        + " WHERE productID = ?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, productID);
                check = stm.executeUpdate() > 0 ? true : false;
            }
        } catch (SQLException e) {
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }
    
    public boolean insertProduct(ProductDTO p) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = " INSERT Products "
                        + " (productName, categoryID, quantity, status, price, createDate, productImage) "
                        + " VALUES (?,?,?,?,?,?,?) ";
                stm = con.prepareStatement(sql);
                stm.setString(1, p.getProductName());
                stm.setInt(2, p.getCategory().getCategoryID());
                stm.setInt(3, p.getQuantity());
                stm.setBoolean(4, p.isStatus());
                stm.setBigDecimal(5, p.getPrice());
                stm.setDate(6, p.getCreateDate());
                stm.setString(7, p.getProductImage());
                stm.executeUpdate();
                return true;
            }
        } catch (SQLException ex) {
            return false;
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }

        return false;
    }
}
