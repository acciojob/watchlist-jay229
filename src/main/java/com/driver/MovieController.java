package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController

public class MovieController {
    @Autowired
    MovieService mvservice;
    @PostMapping("/add-movie")
    public ResponseEntity addMovie(@RequestBody Movie movie){
       mvservice.addMovie(movie);
        return new ResponseEntity<>("New movie added successfully", HttpStatus.CREATED);
    }
    @PostMapping("/add-director")
    public ResponseEntity addDirector(@RequestBody Director director){
        mvservice.addDirector(director);
        return new ResponseEntity<>("New director added successfully", HttpStatus.CREATED);
    }
    @PostMapping("add-movie-director-pair")
    public ResponseEntity addMovieDirectorPair(@RequestParam("mvName") String movieName,@RequestParam("dirName") String dirName){
            String response=mvservice.addMovieDirectorPair(movieName,dirName);
            if (response.equals("SUCCESS")){
                return new ResponseEntity<>(response,HttpStatus.CREATED);
            }
            return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
    }
    @GetMapping("get-movie-by-name/{name}")
    public ResponseEntity getMovieByName(@PathVariable("name") String movieName){
        Movie movie=mvservice.getMovieByName(movieName);
        return new ResponseEntity<>(movie,HttpStatus.FOUND);

    }
    @GetMapping("get-director-by-name/{name}")
    public ResponseEntity getDirectorByName(@PathVariable("name") String dirName){
        Director dr=mvservice.getDirectorByName(dirName);
        if (dr!=null)
            return new ResponseEntity<>(dr,HttpStatus.FOUND);
        return new ResponseEntity<>("Director not found",HttpStatus.NOT_FOUND);
    }
    @GetMapping("get-movies-by-director-name/{director}")
    public ResponseEntity getMoviesByDirectorName(@PathVariable("director") String dirName){
        List<String>movies=new ArrayList<>();
        movies=mvservice.getMoviesByDirectorName(dirName);
        return new ResponseEntity<>(movies,HttpStatus.FOUND);
    }
    @GetMapping("get-all-movies")
    public ResponseEntity findAllMovies(){
        List<String>movies=new ArrayList<>();
        movies=mvservice.findAllMovies();
        return new ResponseEntity<>(movies,HttpStatus.FOUND);
    }
    @DeleteMapping("delete-director-by-name")
    public ResponseEntity deleteDirectorByName(@RequestParam("name") String dirname){
        String response=mvservice.deleteDirectorByName(dirname);
        if (response.equals("SUCCESS")){
            return new ResponseEntity<>(response,HttpStatus.OK);
        }
        return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
    }
    @DeleteMapping("delete-all-directors")
    public ResponseEntity deleteAllDirectors(){
        String response=mvservice.deleteAllDirectors();
        return new ResponseEntity<>(response,HttpStatus.OK);
    }


}
