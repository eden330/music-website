package dev.eden.music_site_web_application.implementationService;

import dev.eden.music_site_web_application.interfaceDao.ArtistDAO;
import dev.eden.music_site_web_application.entity.Artist;
import dev.eden.music_site_web_application.interfaceService.ArtistService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArtistServiceImpl implements ArtistService {

    private ArtistDAO artistDAO;

    @Autowired
    public ArtistServiceImpl(ArtistDAO artistDAO) {
        this.artistDAO = artistDAO;
    }

    @Override
    public List<Artist> findAll() {
        return artistDAO.findAll();
    }

    @Override
    public List<Artist> findAllWithAlbums() {
        return artistDAO.findAllWithAlbums();
    }

    @Override
    public Artist findArtistById(int id) {
        return artistDAO.findById(id);
    }

    @Override
    public Artist findArtistByIdJoinFetch(int id) {
        return artistDAO.findByIdJoinFetch(id);
    }

    @Override
    @Transactional
    public Artist addArtist(Artist artist) {
        return artistDAO.addArtist(artist);
    }

    @Override
    @Transactional
    public Artist updateArtist(Artist artist) {
        return artistDAO.updateArtist(artist);
    }

    @Override
    @Transactional
    public void removeArtist(int id) {
        artistDAO.removeArtist(id);
    }
}
