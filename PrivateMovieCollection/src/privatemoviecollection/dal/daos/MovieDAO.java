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
import java.sql.Types;
import java.util.ArrayList;
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
            if(rating == null)
            {
                statement.setNull(4, Types.DOUBLE);
            }
            else
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
    
    public List<Movie> getAllMovies() throws SQLException
    {
        String sqlStatement = "SELECT * FROM Movies";
        List<Movie> allMovies = new ArrayList();
        try(Connection con = connector.getConnection();
                PreparedStatement statement = con.prepareStatement(sqlStatement))
        {
            ResultSet rs = statement.executeQuery();
            while(rs.next())
            {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                double primitiveRating = rs.getDouble("rating");
                Double rating = null;
                if(primitiveRating != 0)
                {
                    rating = primitiveRating;
                }
                String path = rs.getString("path");
                int time = rs.getInt("time");
                allMovies.add(new Movie(id, title, path, time, rating));
            }
            mcDao.addCategoriesToAllMovies(allMovies);
            return allMovies;
        }
    }
    
}
