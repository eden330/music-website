package dev.eden.music_site_web_application.interfaceService;

import dev.eden.music_site_web_application.entity.Artist;

import java.util.List;

public interface ArtistService {

    List<Artist> findAll();

    List<Artist> findAllWithAlbums();

    Artist findArtistById(int id);

    Artist findArtistByIdJoinFetch(int id);

    Artist addArtist(Artist artist);

    Artist updateArtist(Artist artist);

    void removeArtist(int id);
}
