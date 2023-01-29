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
        moviedb=new HashMap<>();
        dirDb=new HashMap<>();
        pair=new HashMap<>();
    }
    public String addMovie( Movie movie){
        moviedb.put(movie.getName(),movie);
        return "SUCCESS";
    }
    public String addDirector( Director director){
        dirDb.put(director.getName(),director);
        return "SUCCESS";

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
        if (moviedb.containsKey(movieName))
            moviedb.get(movieName);
        return null;
    }
    public Director getDirectorByName(String dirName){
        if (dirDb.containsKey(dirName))
            dirDb.get(dirName);
        return null;
    }
    public List getMoviesByDirectorName(@PathVariable("director") String dirName){
        return pair.get(dirName);

    }
    public List<String> findAllMovies(){
        return new ArrayList<>(moviedb.keySet());
    }
    public String deleteDirectorByName(String dirname){
        if (pair.containsKey(dirname)){
            pair.remove(dirname);
            return "SUCCESS";
        }
        return "ERROR";

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
