package dev.eden.music_site_web_application.interfaceService;

import dev.eden.music_site_web_application.entity.Album;

import java.util.List;

public interface AlbumService {
    List<Album> findAll();

    Album findAlbumById(int id);

    Album findAlbumByIdJoinFetch(int id);

    Album addAlbum(Album album);

    Album updateAlbum(Album album);

    void removeAlbum(int id);
}
