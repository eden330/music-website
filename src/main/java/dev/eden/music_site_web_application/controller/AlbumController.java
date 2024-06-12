package dev.eden.music_site_web_application.controller;

import dev.eden.music_site_web_application.entity.Album;
import dev.eden.music_site_web_application.entity.Artist;
import dev.eden.music_site_web_application.interfaceService.AlbumService;
import dev.eden.music_site_web_application.interfaceService.ArtistService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Controller
@RequestMapping("/music")
public class AlbumController {

    private AlbumService albumService;
    private ArtistService artistService;
    private List<Album> albums;

    @Autowired
    public AlbumController(AlbumService albumService, ArtistService artistService) {
        this.albumService = albumService;
        this.artistService = artistService;
    }

    @GetMapping("/albums")
    public String getAlbumsPage(Model model, @RequestParam(name = "sort", required = false) String sort) {
        if (sort != null && sort.equals("asc")) {
            albums = albums.stream()
                    .sorted(Comparator.comparing(Album::getName))
                    .toList();
        } else if (sort != null && sort.equals("desc")) {
            albums = albums.stream()
                    .sorted(Comparator.comparing(Album::getName).reversed())
                    .toList();
        } else {
            albums = albumService.findAll();
        }
        model.addAttribute("albums", albums);
        return "pages-for-album/albums-page";
    }

    @GetMapping("/searchResultAlbum")
    public String searchAlbum(Model model, @RequestParam(name = "name") String name) {
        List<Album> tempAlbums = new ArrayList<>();
        for (Album album : albums) {
            if (album.getName().toLowerCase().startsWith(name.toLowerCase())) {
                tempAlbums.add(album);
            }
        }
        model.addAttribute("albums", tempAlbums);
        return "pages-for-album/albums-page";
    }

    @GetMapping("/album")
    public String getAlbumPage(@RequestParam("albumId") int id, Model model) {
        var album = albumService.findAlbumByIdJoinFetch(id);
        if (album == null) {
            throw new RuntimeException("Album not found");
        }
        model.addAttribute("album", album);
        return "pages-for-album/album-page";
    }

    @GetMapping("/showFormForAddAlbum")
    public String showFormForAddAlbum(Model model) {
        List<Artist> artists = artistService.findAll();
        List<Album> albums = albumService.findAll();
        Album album = new Album();
        model.addAttribute("album", album);
        model.addAttribute("albums", albums);
        model.addAttribute("artists", artists);
        return "admin-pages/album-form";
    }

    @PostMapping("/saveAlbum")
    public String addAlbum(@Valid @ModelAttribute Album album,
                           BindingResult bindingResult,
                           Model model) {
        if (bindingResult.hasErrors() || album.getArtist().getId() == null) {
            List<Artist> artists = artistService.findAll();
            List<Album> albums = albumService.findAll();
            model.addAttribute("artists", artists);
            model.addAttribute("albums", albums);
            return "admin-pages/album-form";
        }
        Artist artist = artistService.findArtistById(album.getArtist().getId());
        artist.addAlbum(album);
        if (album.getSongs() == null) {
            album.setSongs(new ArrayList<>());
        }
        album.getSongs().forEach(song -> song.setAlbum(album));
        albumService.addAlbum(album);
        return "redirect:/music/albums";
    }

    @GetMapping("/deleteAlbum")
    public String deleteAlbum(@RequestParam("albumId") int id) {
        albumService.removeAlbum(id);
        return "redirect:/music/showFormForAddAlbum";
    }
}
