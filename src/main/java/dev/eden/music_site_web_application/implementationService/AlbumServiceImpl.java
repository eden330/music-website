package dev.eden.music_site_web_application.implementationService;

import dev.eden.music_site_web_application.entity.Album;
import dev.eden.music_site_web_application.interfaceDao.AlbumDAO;
import dev.eden.music_site_web_application.interfaceService.AlbumService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlbumServiceImpl implements AlbumService {

    private AlbumDAO albumDAO;

    @Autowired
    public AlbumServiceImpl(AlbumDAO albumDAO) {
        this.albumDAO = albumDAO;
    }

    @Override
    public List<Album> findAll() {
        return albumDAO.findAll();
    }

    @Override
    public Album findAlbumById(int id) {
        return albumDAO.findById(id);
    }

    @Override
    public Album findAlbumByIdJoinFetch(int id) {
        return albumDAO.findByIdJoinFetch(id);
    }

    @Override
    @Transactional
    public Album addAlbum(Album album) {
        return albumDAO.addAlbum(album);
    }

    @Override
    @Transactional
    public Album updateAlbum(Album album) {
        return albumDAO.updateAlbum(album);
    }

    @Override
    @Transactional
    public void removeAlbum(int id) {
        albumDAO.removeAlbum(id);
    }
}
