package dev.eden.music_site_web_application.interfaceDao;

import dev.eden.music_site_web_application.entity.Artist;

import java.util.List;

public interface ArtistDAO {

    List<Artist> findAll();

    List<Artist> findAllWithAlbums();

    Artist findByIdJoinFetch(int id);

    Artist findById(int id);

    Artist addArtist(Artist artist);

    Artist updateArtist(Artist artist);

    void removeArtist(int id);

}
