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
import privatemoviecollection.be.User;
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
    
    public Movie createMovie(User user, String title, List<Category> categories, String path, int time, Integer rating) throws SQLException
    {
        String sqlStatement = "INSERT INTO Movies(userId, title, path, time, rating) values(?,?,?,?,?)";
        try(Connection con = connector.getConnection();
                PreparedStatement statement = con.prepareStatement(sqlStatement, Statement.RETURN_GENERATED_KEYS))
        {
            statement.setInt(1, user.getId());
            statement.setString(2, title);
            statement.setString(3, path);
            statement.setInt(4, time);
            if(rating == null)
            {
                statement.setNull(5, Types.INTEGER);
            }
            else
            {
                statement.setInt(5, rating);
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
    
    public List<Movie> getAllMovies(User user) throws SQLException
    {
        String sqlStatement = "SELECT * FROM Movies WHERE userId=?";
        List<Movie> allMovies = new ArrayList();
        try(Connection con = connector.getConnection();
                PreparedStatement statement = con.prepareStatement(sqlStatement))
        {
            statement.setInt(1, user.getId());
            ResultSet rs = statement.executeQuery();
            while(rs.next())
            {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                int primitiveRating = rs.getInt("rating");
                Integer rating = null;
                if(primitiveRating != 0)
                {
                    rating = primitiveRating;
                }
                String path = rs.getString("path");
                int time = rs.getInt("time");
                allMovies.add(new Movie(id, title, path, time, rating));
            }
            mcDao.addCategoriesToAllMovies(user, allMovies);
            return allMovies;
        }
    }
    
    public Movie updateMovie(Movie movie, String title, List<Category> categories, String path, int time, Integer rating) throws SQLException
    {
        String sqlStatement = "UPDATE Movies SET title=?, path=?, time=?, rating=? WHERE id=?";
        try(Connection con = connector.getConnection();
                PreparedStatement statement = con.prepareStatement(sqlStatement))
        {
            statement.setString(1, title);
            statement.setString(2, path);
            statement.setInt(3, time);
            statement.setInt(4, rating);
            statement.setInt(5, movie.getId());
            statement.execute();
            movie.setTitle(title);
            movie.setPath(path);
            movie.setTime(time);
            movie.setRating(rating);
            mcDao.deleteAllCategoriesFromMovie(movie);
            mcDao.addCategoriesToMovie(movie, categories);
            return movie;
        }
    }
    
    public void deleteMovie(Movie movieToDelete) throws SQLException
    {
        mcDao.deleteAllCategoriesFromMovie(movieToDelete);
        String sqlStatement = "DELETE FROM Movies WHERE id=?";
        try(Connection con = connector.getConnection();
                PreparedStatement statement = con.prepareStatement(sqlStatement))
        {
            statement.setInt(1, movieToDelete.getId());
            statement.execute();
        }
    }
    
}
