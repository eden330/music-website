package dev.eden.music_site_web_application.implementationDao;

import dev.eden.music_site_web_application.entity.Album;
import dev.eden.music_site_web_application.interfaceDao.AlbumDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AlbumDAOImpl implements AlbumDAO {
    private EntityManager entityManager;

    @Autowired
    public AlbumDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Album> findAll() {
        TypedQuery<Album> albums = entityManager.createQuery("FROM Album", Album.class);
        return albums.getResultList();
    }

    @Override
    public Album findByIdJoinFetch(int id) {
        TypedQuery<Album> query = entityManager.createQuery("SELECT a FROM Album a " +
                "LEFT JOIN FETCH a.songs " +
                "WHERE a.id = :data", Album.class);
        query.setParameter("data", id);
        return query.getSingleResult();
    }

    @Override
    public Album findById(int id) {
        var album = entityManager.find(Album.class, id);
        if (album != null) {
            return album;
        }
        throw new EntityNotFoundException("Album can't be found!");
    }

    @Override
    public Album addAlbum(Album album) {
        entityManager.persist(album);
        return album;
    }

    @Override
    public Album updateAlbum(Album album) {
        return entityManager.merge(album);
    }

    @Override
    public void removeAlbum(int id) {
        var album = entityManager.find(Album.class, id);
        if (album != null) {
            entityManager.remove(album);
        }
    }
}
