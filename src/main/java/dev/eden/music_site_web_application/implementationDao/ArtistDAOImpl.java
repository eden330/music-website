package dev.eden.music_site_web_application.implementationDao;

import dev.eden.music_site_web_application.entity.Artist;
import dev.eden.music_site_web_application.interfaceDao.ArtistDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ArtistDAOImpl implements ArtistDAO {

    private EntityManager entityManager;

    @Autowired
    public ArtistDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Artist> findAll() {
        TypedQuery<Artist> artists = entityManager.createQuery("FROM Artist", Artist.class);
        return artists.getResultList();
    }

    @Override
    public List<Artist> findAllWithAlbums() {
        TypedQuery<Artist> query = entityManager.createQuery(
                "SELECT DISTINCT a FROM Artist a " +
                        "LEFT JOIN FETCH a.albums", Artist.class);
        return query.getResultList();
    }

    @Override
    public Artist findByIdJoinFetch(int id) {
        TypedQuery<Artist> query = entityManager.createQuery("SELECT a FROM Artist a " +
                "LEFT JOIN FETCH a.albums " +
                "WHERE a.id = :data", Artist.class);
        query.setParameter("data", id);
        return query.getSingleResult();
    }

    @Override
    public Artist findById(int id) {
        var artist = entityManager.find(Artist.class, id);
        if (artist != null) {
            return artist;
        }
        throw new EntityNotFoundException("Artist can't be found!");
    }

    @Override
    public Artist addArtist(Artist artist) {
        entityManager.persist(artist);
        return artist;
    }

    @Override
    public Artist updateArtist(Artist artist) {
        return entityManager.merge(artist);
    }

    @Override
    public void removeArtist(int id) {
        var artist = entityManager.find(Artist.class, id);
        if (artist != null) {
            entityManager.remove(artist);
        }
    }
}
