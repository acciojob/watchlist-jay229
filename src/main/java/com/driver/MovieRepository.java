package com.driver;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

@Repository
public class MovieRepository {
    HashMap<String,Movie>moviedb;
    HashMap<String,Director>dirDb;
    HashMap<String, List<String>>pair;
    public MovieRepository(){
        this.moviedb=new HashMap<>();
        this.dirDb=new HashMap<>();
        this.pair=new HashMap<>();
    }
    public void addMovie( Movie movie){
        moviedb.put(movie.getName(),movie);

    }
    public void addDirector( Director director){
        dirDb.put(director.getName(),director);


    }
    public String addMovieDirectorPair(String movieName, String dirName){
        if(moviedb.containsKey(movieName)&& dirDb.containsKey(dirName)){
            List<String>movies=new ArrayList<>();
            if(pair.containsKey(dirName))
                movies=pair.get(dirName);
            movies.add(movieName);
            pair.put(dirName,movies);
            return "SUCCESS";


        }
        return "ERROR";
    }
    public Movie getMovieByName(String movieName){
        return moviedb.get(movieName);

    }
    public Director getDirectorByName(String dirName){
        return dirDb.get(dirName);
    }
    public List<String> getMoviesByDirectorName(String dirName){
        return pair.get(dirName);

    }
    public List<String> findAllMovies(){
        return new ArrayList<>(moviedb.keySet());
    }
    public String deleteDirectorByName(String dirname){
        List<String>movies=new ArrayList<>();
        if(pair.containsKey(dirname)){
            movies=pair.get(dirname);
            for (String movie:movies){
                if (moviedb.containsKey(movie))
                    moviedb.remove(movie);
            }
            pair.remove(dirname);
        }
        if (dirDb.containsKey(dirname))
            dirDb.remove(dirname);
        return "SUCCESS";
    }
    public String deleteAllDirectors(){
        HashSet<String>watchlist=new HashSet<>();
        for (String director:pair.keySet()){
            for (String movie:pair.get(director)){
                    watchlist.add(movie);
            }
        }
        for (String movie:watchlist){
            if (moviedb.containsKey(movie)){
                moviedb.remove(movie);
            }
        }
        return  "SUCCESS";
    }

}
