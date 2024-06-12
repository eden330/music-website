package dev.eden.music_site_web_application.entity;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "albums")
public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "album_id")
    private Integer id;
    @NotNull(message = "Name of album is required")
    @Size(min = 1, message = "Name of artist is required")
    @Column(name = "album_name")
    private String name;
    @NotNull(message = "Description of album is required")
    @Size(min = 1, message = "Name of artist is required")
    @Column(name = "album_description")
    private String description;
    @NotNull(message = "Date of album is required")
    @Column(name = "album_date")
    private LocalDate release_date;
    @NotNull(message = "Photo of album is required")
    @Size(min = 1, message = "Name of artist is required")
    @Column(name = "album_photo")
    private String photo;
    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.REMOVE, CascadeType.PERSIST},
            mappedBy = "album")
    private List<Song> songs;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "artist_id")
    private Artist artist;

    public Album() {
    }

    public Album(String name, String description, LocalDate release_date, String photo) {
        this.name = name;
        this.description = description;
        this.release_date = release_date;
        this.photo = photo;
    }

    public void addSong(Song song) {
        if (songs == null) {
            songs = new ArrayList<>();
        }
        song.setAlbum(this);
        songs.add(song);
    }

    public void addSongs(List<Song> newSongs) {
        if (songs == null) {
            songs = new ArrayList<>();
        }
        for (Song song : newSongs) {
            song.setAlbum(this);
            songs.add(song);
        }
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getRelease_date() {
        return release_date;
    }

    public void setRelease_date(LocalDate release_date) {
        this.release_date = release_date;
    }

    public List<Song> getSongs() {
        return songs;
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public Artist getArtist() {
        return artist;
    }

    //    @Override
//    public String toString() {
//        return "Album{" +
//                "id=" + id +
//                ", name='" + name + '\'' +
//                ", description='" + description + '\'' +
//                ", release_date=" + release_date +
//                ", photo='" + photo + '\'' +
//                ", songs=" + songs +
//                '}';
//    }
}
