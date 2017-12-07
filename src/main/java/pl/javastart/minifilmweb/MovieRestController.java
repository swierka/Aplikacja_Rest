package pl.javastart.minifilmweb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movies")
public class MovieRestController {

    @Autowired
    private MovieRepository movieRepository;

    @GetMapping("")
    public List<Movie> getAllMovies() {
        System.out.println("Odpytanie o wszystkie filmy");
        return movieRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findMovieById (@PathVariable Long id){
        Movie movie = movieRepository.findOne(id);
        if(movie == null){return ResponseEntity.notFound().build();}
        else return ResponseEntity.ok(movie);
    }

    @PostMapping("")
    public ResponseEntity<?> addMovie(@RequestBody Movie movie){
        Movie newMovie = movieRepository.save(movie);
        return ResponseEntity.ok(movie);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Movie> deleteMovie(@PathVariable Long id){
        Movie movie = movieRepository.findOne(id);

        if (movie==null){
            System.out.println("Unable to delete. User with id " + id + " not found");
            return new ResponseEntity<Movie>(HttpStatus.NOT_FOUND);
        }
        movieRepository.delete(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateMovie (@PathVariable long id, @RequestBody Movie movie){
        System.out.println("Updating movie no "+id);

        Movie currentMovie = movieRepository.findOne(id);
        if(currentMovie==null){return ResponseEntity.notFound().build();
        }

        currentMovie.setTitle(movie.getTitle());
        currentMovie.setPremiereDate(movie.getPremiereDate());
        currentMovie.setActors(movie.getActors());

        movieRepository.save(currentMovie);

        return ResponseEntity.ok().build();
    }

}
