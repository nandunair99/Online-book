package com.narola.bookmyseat.config;

import com.narola.bookmyseat.castcrew.dao.CastCrewDAOPostgreas;
import com.narola.bookmyseat.castcrew.dao.CastCrewDAOSQL;
import com.narola.bookmyseat.castcrew.dao.ICastCrewDAO;
import com.narola.bookmyseat.location.dao.ILocationDAO;
import com.narola.bookmyseat.location.dao.LocationDAOPostgreas;
import com.narola.bookmyseat.location.dao.LocationDAOSQL;
import com.narola.bookmyseat.movies.dao.IMovieDAO;
import com.narola.bookmyseat.movies.dao.MovieDAOPostgreas;
import com.narola.bookmyseat.movies.dao.MovieDAOSQL;
import com.narola.bookmyseat.utility.DBConnector;
import com.narola.bookmyseat.utility.DatabaseType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import javax.annotation.PostConstruct;

@Configuration
@PropertySource("classpath:db.properties")

public class DbConfig {

    @Autowired
    private Environment env;

    private static DatabaseType dbType;
    private ILocationDAO locationDAO;
    private IMovieDAO movieDAO;
    private ICastCrewDAO castCrewDAO;

    @PostConstruct
    public void init() throws Exception {

        dbType = DatabaseType.valueOf(env.getProperty("dbType"));
        if (dbType == DatabaseType.MYSQL) {
            movieDAO = new MovieDAOSQL();
            locationDAO = new LocationDAOSQL();
            castCrewDAO = new CastCrewDAOSQL();
        } else if (dbType == DatabaseType.POSTGRES) {
            movieDAO = new MovieDAOPostgreas();
            locationDAO = new LocationDAOPostgreas();
            castCrewDAO = new CastCrewDAOPostgreas();
        } else {
            throw new Exception("Type is not supported yet");
        }
        DBConnector.getInstance(env.getProperty("dbURL"), env.getProperty("dbUser"), env.getProperty("dbPassword"));
    }

    @Bean
    public ILocationDAO getlocationDAO() {
        return locationDAO;
    }

    @Bean
    public IMovieDAO getmovieDAO() {
        return movieDAO;
    }

    @Bean
    public ICastCrewDAO getcastcrewDAO() {
        return castCrewDAO;
    }
}
