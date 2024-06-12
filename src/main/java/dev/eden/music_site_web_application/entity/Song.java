package dev.eden.music_site_web_application.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "songs")
public class Song {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "song_id")
    private Integer id;

    @NotNull(message = "Number of song is required")
    @Column(name = "song_number")
    @Min(value = 0, message = "Number must be non-negative")
    private int number;

    @NotNull(message = "Song title is required")
    @Size(min = 1, message = "Song title is required")
    @Column(name = "song_title")
    private String title;

    @NotNull(message = "Song duration is required")
    @Min(value = 0, message = "Duration must be non-negative")
    @Column(name = "song_duration")
    private double duration;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "album_id")
    private Album album;

    public Song() {
    }

    public Song(int number, String title, double duration) {
        this.number = number;
        this.title = title;
        this.duration = duration;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    @Override
    public String toString() {
        return "Song{" +
                "id=" + id +
                ", number=" + number +
                ", title='" + title + '\'' +
                ", duration=" + duration +
                '}';
    }
}
