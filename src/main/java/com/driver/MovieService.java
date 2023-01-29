package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

@Service
public class MovieService {
    @Autowired
    MovieRepository repo;
    public void addMovie( Movie movie){
         repo.addMovie(movie);
    }
    public void addDirector( Director director){
         repo.addDirector(director);
    }
    public String addMovieDirectorPair( String movieName, String dirName){
        return repo.addMovieDirectorPair(movieName,dirName);
    }
    public Movie getMovieByName(String movieName){
        return repo.getMovieByName(movieName);
    }
    public Director getDirectorByName( String dirName){
       return repo.getDirectorByName(dirName);
    }
    public List getMoviesByDirectorName(@PathVariable("director") String dirName){
        return repo.getMoviesByDirectorName(dirName);
    }
    public List<String> findAllMovies(){
        return repo.findAllMovies();
    }
    public String deleteDirectorByName(String dirname){
        return repo.deleteDirectorByName(dirname);
    }
    public String deleteAllDirectors(){
        return repo.deleteAllDirectors();
    }
}
