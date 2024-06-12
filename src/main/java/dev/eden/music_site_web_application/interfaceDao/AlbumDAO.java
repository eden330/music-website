package dev.eden.music_site_web_application.interfaceDao;

import dev.eden.music_site_web_application.entity.Album;

import java.util.List;

public interface AlbumDAO {
    List<Album> findAll();

    Album findByIdJoinFetch(int id);

    Album findById(int id);

    Album addAlbum(Album album);

    Album updateAlbum(Album album);

    void removeAlbum(int id);
}
