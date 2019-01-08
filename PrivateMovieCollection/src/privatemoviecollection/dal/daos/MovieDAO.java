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
import java.util.List;
import privatemoviecollection.be.Category;
import privatemoviecollection.be.Movie;
import privatemoviecollection.dal.DbConnectionProvider;

/**
 *
 * @author Acer
 */
public class MovieDAO {
    
    private DbConnectionProvider connector;
    private MovieCategoriesDAO mcDao;
    
    /**
     * Creates connector with database.
     */
    public MovieDAO()
    {
        connector = new DbConnectionProvider();
        mcDao = new MovieCategoriesDAO();
    }
    
    public Movie createMovie(String title, List<Category> categories, String path, int time, Double rating) throws SQLException
    {
        String sqlStatement = "INSERT INTO Movies(title, path, time, rating) values(?,?,?,?)";
        try(Connection con = connector.getConnection();
                PreparedStatement statement = con.prepareStatement(sqlStatement, Statement.RETURN_GENERATED_KEYS))
        {
            statement.setString(1, title);
            statement.setString(2, path);
            statement.setInt(3, time);
            if(rating != null)
            {
                statement.setDouble(4, rating);
            }
            statement.execute();
            ResultSet rs = statement.getGeneratedKeys();
            rs.next();
            int id = rs.getInt(1);
            Movie movie = new Movie(id, title, categories, path, time, rating);
            mcDao.addCategoriesToMovie(movie, categories);
            return movie;
        }
    }
    
}
