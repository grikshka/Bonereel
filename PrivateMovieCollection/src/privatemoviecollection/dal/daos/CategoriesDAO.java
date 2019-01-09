/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package privatemoviecollection.dal.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import privatemoviecollection.be.Category;
import privatemoviecollection.dal.DbConnectionProvider;

/**
 *
 * @author Acer
 */
public class CategoriesDAO {
    
    private DbConnectionProvider connector;
    
    /**
     * Creates connector with database.
     */
    public CategoriesDAO()
    {
        connector = new DbConnectionProvider();
    }
    
    public Category createCategory(String name) throws SQLException
    {
        String sqlStatement = "INSERT INTO Categories(name) VALUES(?)";
        try(Connection con = connector.getConnection();
                PreparedStatement statement = con.prepareStatement(sqlStatement, Statement.RETURN_GENERATED_KEYS))
        {
            statement.setString(1, name);
            statement.execute();
            ResultSet rs = statement.getGeneratedKeys();
            rs.next();
            int id = rs.getInt(1);
            return new Category(id, name);
            
        }
    }
    
}
