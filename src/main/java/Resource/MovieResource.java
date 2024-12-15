package Resource;

import MovieDatebase.MovieRepository;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import model.Movie;
import java.util.List;

// kommer hantera http

@Path("/movies") //Här säger vi att alla API-anrop för filmer ska börja med "/movies".
public class MovieResource {

    @Inject // säga åt programmet att automatiskt skapa och ge oss ett objekt som vi behöver använda som är MovieRepository
    private MovieRepository movieRepository; // Att jobba med filmer i databasen.


    @GET // Hanterar GET-förfrågningar för att hämta alla filmer.
    @Produces(MediaType.APPLICATION_JSON) // Skickar tillbaka data i JSON-format.
    public List<Movie> getMovies() { // Hämtar en lista med alla filmer från databasen.

        return movieRepository.findMovies(); // Hämtar och returnerar alla filmer från databasen.
    }

    @GET // Hanterar GET-förfrågningar för att hämta en specifik film.
    @Path("/{id}") // Tar emot filmens id från URL:en.
    @Produces(MediaType.APPLICATION_JSON) // Skickar tillbaka filmen i JSON-format.
    public Response getMovie(@PathParam("id") Long id) { // Hämtar en specifik film från databasen baserat på ID.
         Movie movie = movieRepository.findMovie(id); // Letar efter filmen med det id vi fått.

        if (movie == null) { // Om filmen inte finns.
            return Response.status(Response.Status.NOT_FOUND).build(); // Skickar tillbaka "404 Inte hittad".
        }
        return Response.ok(movie).build(); // Skickar tillbaka filmen med status "200 OK" om den finns.
    }



    @POST // Hanterar POST-förfrågningar för att lägga till en ny film.
    @Consumes(MediaType.APPLICATION_JSON) // Förväntar att filmen skickas i JSON-format.
    public Response addMovie(Movie movie) { // Metod för att lägga till en ny film i databasen.
        movieRepository.createMovie(movie); // Lagrar filmen i databasen.

        return Response.status(Response.Status.CREATED).entity("Film skapad").build(); // Skickar tillbaka status "201 Skapad" om filmen sparades.
    }



    @PUT // Hanterar PUT-förfrågningar för att ändra en film.
    @Path("/{id}") // Tar emot filmens id från URL:en.
    @Consumes(MediaType.APPLICATION_JSON) // Förväntar att ändringarna skickas som JSON-format.
    public Response updateMovie(@PathParam("id") Long id, Movie updatedMovie) { // Metod för att ändra en film i databasen.
        Movie existingMovie = movieRepository.findMovie(id); // Hämta filmen från databasen med det id som skickades in.

        if (existingMovie == null) { // Om filmen inte finns, skickar en "404 Inte hittad"-felkod.
            return Response.status(Response.Status.NOT_FOUND).entity("Filmen hittades inte").build(); // Skickar felmeddelande om filmen inte finns.
        }


        // Uppdaterar filmens detaljer med de nya värdena.
        existingMovie.setTitle(updatedMovie.getTitle());
        existingMovie.setGenre(updatedMovie.getGenre());
        existingMovie.setReleaseYear(updatedMovie.getReleaseYear());
        existingMovie.setDescription(updatedMovie.getDescription());
        existingMovie.setDirector(updatedMovie.getDirector());

        // Uppdaterar filmen i databasen.
        movieRepository.updateMovie(existingMovie);

        // Skickar tillbaka en "200 OK"-status som säger att filmen blev uppdaterad.
        return Response.ok("Filmen uppdaterad").build();
    }

    @DELETE // Hanterar DELETE-förfrågningar för att ta bort en film.
    @Path("/{id}") // Tar emot filmens id från URL:en för att identifiera filmen som ska tas bort.
    public Response deleteMovie(@PathParam("id") Long id) { // Metod som körs för att ta bort en film när vi får ett id.
        Movie existingMovie = movieRepository.findMovie(id); // Hämtar filmen från databasen med det id som skickades in.


        if (existingMovie == null) { // Kollar om filmen inte finns i databasen.
             return Response.status(Response.Status.NOT_FOUND).entity("Filmen hittades inte").build(); // Skickar tillbaka en "404 Inte hittad"-felkod om filmen saknas.
        }

        // Tar bort filmen från databasen.
        movieRepository.deleteMovie(id);

        // Skickar tillbaka en "200 OK"-status som bekräftar att filmen blev borttagen.
        return Response.ok("Filmen borttagen").build();
    }
}
