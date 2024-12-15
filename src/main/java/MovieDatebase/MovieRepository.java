package MovieDatebase;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import model.Movie;

import java.util.List;

/**
 * En klass för att hantera filmer i databasen.
 * Tillgänglig i hela applikationen och använder transaktioner för att säkra databashanteringen.
 */
@ApplicationScoped // Gör att MovieRepository är tillgänglig i hela programmet.
@Transactional // Säkrar att alla ändringar i databasen antingen lyckas helt eller inte genomförs alls.
public class MovieRepository {

    /**
     * Ett verktyg för att prata med databasen.
     * @PersistenceContext injicerar EntityManager som används för att hantera databasen.
     */
    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Hämtar alla filmer från databasen.
     * @return En lista med alla filmer i databasen.
     */

    public List<Movie> findMovies() {
        return entityManager.createQuery("SELECT m FROM Movie m", Movie.class).getResultList();
    } // Är inte sql är Java Persistence Query Language.

    /**
     * Lägger till en ny film i databasen.
     * @param movie Filmen som ska sparas.
     */
    public void createMovie(Movie movie) {
        entityManager.persist(movie); // Lagrar filmen i databasen.
    }

    /**
     * Uppdaterar informationen för en film i databasen eller lägger till den om den inte redan finns.
     * @param movie Filmen med uppdaterad information.
     */
    public void updateMovie(Movie movie) {
        entityManager.merge(movie); // Uppdaterar eller lägger till filmen.
    }

    /**
     * Hämtar en specifik film från databasen med hjälp av dess ID.
     * @param id ID-numret för filmen som ska hämtas.
     * @return Filmen med det angivna ID-numret, eller null om den inte finns.
     */
    public Movie findMovie(Long id) {
        return entityManager.find(Movie.class, id); // Hämtar filmen från databasen.
    }

    /**
     * Tar bort en film från databasen med hjälp av dess ID.
     * @param id ID-numret för filmen som ska tas bort.
     */
    public void deleteMovie(Long id) {
        Movie movie = findMovie(id); // Hämtar filmen med det angivna ID-numret.
        if (movie != null) { // Kontrollerar om filmen finns i databasen.
            entityManager.remove(movie); // Tar bort filmen.
        }
    }
}
