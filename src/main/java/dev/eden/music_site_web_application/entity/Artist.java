package dev.eden.music_site_web_application.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "artists")
public class Artist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "artist_id")
    private Integer id;
    @Column(name = "artist_name")
    @NotNull(message = "Name of artist is required")
    @Size(min = 1, message = "Name of artist is required")
    private String name;
    @NotNull(message = "Description of artist is required")
    @Size(min = 1, message = "Description of artist is required")
    @Column(name = "artist_description")
    private String description;
    @NotNull(message = "Photo of artist is required")
    @Size(min = 1, message = "Photo of artist is required")
    @Column(name = "artist_photo")
    private String photo;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE,
            mappedBy = "artist")
    private List<Album> albums;

    public Artist() {
    }

    public Artist(String name, String description, String photo) {
        this.name = name;
        this.description = description;
        this.photo = photo;
    }

    public void addAlbum(Album album) {
        if (albums == null) {
            albums = new ArrayList<>();
        }
        album.setArtist(this);
        albums.add(album);
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

    public List<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(List<Album> albums) {
        this.albums = albums;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

//    @Override
//    public String toString() {
//        return "Artist{" +
//                "id=" + id +
//                ", name='" + name + '\'' +
//                ", description='" + description + '\'' +
//                ", photo='" + photo + '\'' +
//                ", albums=" + albums +
//                '}';
//    }
}
