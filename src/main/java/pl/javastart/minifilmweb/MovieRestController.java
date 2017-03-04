package pl.javastart.minifilmweb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MovieRestController {

    @Autowired
    private MovieRepository movieRepository;

    @GetMapping("/api/movies")
    public List<Movie> getAllMovies() {
        System.out.println("Odpytanie o wszystkie filmy");
        return movieRepository.findAll();
    }

}
