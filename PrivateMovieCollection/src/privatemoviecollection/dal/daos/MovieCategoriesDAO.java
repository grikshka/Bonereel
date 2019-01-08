/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package privatemoviecollection.dal.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import privatemoviecollection.be.Category;
import privatemoviecollection.be.Movie;
import privatemoviecollection.dal.DbConnectionProvider;

/**
 *
 * @author Acer
 */
public class MovieCategoriesDAO {
    
    private DbConnectionProvider connector;
    
    /**
     * Creates connector with database.
     */
    public MovieCategoriesDAO()
    {
        connector = new DbConnectionProvider();
    }
    
    public void addCategoriesToMovie(Movie movie, List<Category> categories) throws SQLException
    {
        String sqlStatement = "INSERT INTO MovieCategories(movieId, categoryId) VALUES(?,?)";
        try(Connection con = connector.getConnection();
                PreparedStatement statement = con.prepareStatement(sqlStatement, Statement.RETURN_GENERATED_KEYS))
        {
            for(Category category: categories)
            {
                statement.setInt(1, movie.getId());
                statement.setInt(2, category.getId());
                statement.addBatch();
            }
            statement.execute();
        }
    }
    
}
