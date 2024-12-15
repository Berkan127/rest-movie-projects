package model;

import jakarta.json.bind.annotation.JsonbPropertyOrder;
import jakarta.persistence.*;

/**
 * Denna klass representerar en film som sparas i databasen.
 * Den används också för att skicka och ta emot filmdata via API:et.
 * Fälten i denna klass mappas till motsvarande kolumner i databastabellen.
 * @author Berkan
 * @version 1.0
 */

@JsonbPropertyOrder({"title", "genre", "releaseYear", "description", "director"})
@Entity // Markerar att detta är en entitet som mappas till en databas.
@Table(name = "movies") // Kopplar entiteten till tabellen "movies".
public class Movie {

    /**
     * Huvudnyckeln (ID) för filmen. Detta används för att identifiera varje film i databasen.
     * ID:t genereras automatiskt.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false) // Fältet är inte null och kopplas till kolumnen "id".
    private Long id;

    /**
     * Titel för filmen.
     */
    private String title;

    /**
     * Filmens genre, t.ex. "Drama" eller "Action".
     */
    private String genre;

    /**
     * Året då filmen släpptes.
     */
    private int releaseYear;

    /**
     * En kort beskrivning av filmen.
     */
    private String description;

    /**
     * Regissören som skapade filmen.
     */
    private String director;

    /**
     * Standardkonstruktor. Krävs av JPA för att kunna skapa objekt av denna klass.
     */
    public Movie() {
        // Tom konstruktor.
    }

    /**
     * Konstruktor för att skapa ett Movie-objekt med angivna värden.
     * @param title titel för filmen.
     * @param genre genre för filmen.
     * @param releaseYear året då filmen släpptes.
     * @param description en kort beskrivning av filmen.
     * @param director regissören för filmen.
     */
    public Movie(String title, String genre, int releaseYear, String description, String director) {
        this.title = title;
        this.genre = genre;
        this.releaseYear = releaseYear;
        this.description = description;
        this.director = director;
    }

    /**
     * Hämtar ID:t för filmen.
     * @return filmens ID.
     */
    public Long getId() {
        return id;
    }

    /**
     * Ändrar filmens ID.
     * @param id det nya ID:t för filmen.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Hämtar titeln för filmen.
     * @return filmens titel.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Ändrar titeln för filmen.
     * @param title den nya titeln för filmen.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Hämtar genren för filmen.
     * @return filmens genre.
     */
    public String getGenre() {
        return genre;
    }

    /**
     * Ändrar genren för filmen.
     * @param genre den nya genren för filmen.
     */
    public void setGenre(String genre) {
        this.genre = genre;
    }

    /**
     * Hämtar året då filmen släpptes.
     * @return året då filmen släpptes.
     */
    public int getReleaseYear() {
        return releaseYear;
    }

    /**
     * Ändrar året då filmen släpptes.
     * @param releaseYear det nya året då filmen släpptes.
     */
    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    /**
     * Hämtar en kort beskrivning av filmen.
     * @return en kort beskrivning av filmen.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Ändrar beskrivningen av filmen.
     * @param description den nya beskrivningen av filmen.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Hämtar namnet på regissören för filmen.
     * @return regissörens namn.
     */
    public String getDirector() {
        return director;
    }

    /**
     * Ändrar namnet på regissören för filmen.
     * @param director det nya namnet på regissören.
     */
    public void setDirector(String director) {
        this.director = director;
    }
}