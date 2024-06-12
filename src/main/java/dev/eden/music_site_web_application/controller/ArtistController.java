package dev.eden.music_site_web_application.controller;

import dev.eden.music_site_web_application.entity.Artist;
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
import java.util.stream.Collectors;

@Controller
@RequestMapping("/music")
public class ArtistController {

    private ArtistService artistService;
    private List<Artist> artists;

    @Autowired
    public ArtistController(ArtistService artistService) {
        this.artistService = artistService;
    }

    @GetMapping("/home-page")
    public String getAllArtistsWithAlbums(Model model) {
        var artists = artistService.findAllWithAlbums();
        model.addAttribute("artists", artists);
        return "home-page";
    }


    @GetMapping("/artists")
    public String getArtistsPage(Model model, @RequestParam(name = "sort", required = false) String sort) {
        if (sort != null && sort.equals("asc")) {
            artists = artists.stream()
                    .sorted(Comparator.comparing(Artist::getName))
                    .collect(Collectors.toList());
        } else if (sort != null && sort.equals("desc")) {
            artists = artists.stream()
                    .sorted(Comparator.comparing(Artist::getName)
                            .reversed()).collect(Collectors.toList());
        } else {
            artists = artistService.findAll();
        }
        model.addAttribute("artists", artists);
        return "pages-for-artist/artists-page";
    }

    @GetMapping("/artist")
    public String getArtistPage(@RequestParam("artistId") int id, Model model) {
        var artist = artistService.findArtistByIdJoinFetch(id);
        if (artist == null) {
            throw new RuntimeException("Artist not found!");
        }
        model.addAttribute("artist", artist);
        return "pages-for-artist/artist-page";
    }

    @GetMapping("/searchResultArtists")
    public String searchArtist(Model model, @RequestParam(name = "name") String name) {
        List<Artist> tempArtists = new ArrayList<>();
        for (Artist artist : artists) {
            if (artist.getName().toLowerCase().startsWith(name.toLowerCase())) {
                tempArtists.add(artist);
            }
        }
        model.addAttribute("artists", tempArtists);
        return "pages-for-artist/artists-page";
    }

    @GetMapping("/showFormForAddArtist")
    public String showFormForAddArtist(Model model) {
        Artist artist = new Artist();
        List<Artist> artists = artistService.findAll();
        model.addAttribute("artists", artists);
        model.addAttribute("artist", artist);
        return "admin-pages/artist-form";
    }

    @PostMapping("/saveArtist")
    public String saveArtist(@Valid @ModelAttribute Artist artist,
                             BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            List<Artist> artists = artistService.findAll();
            model.addAttribute("artists", artists);
            return "pages-for-artist/artist-form";
        } else {
            artistService.addArtist(artist);
            return "redirect:/music/showFormForAddArtist";
        }
    }

    @GetMapping("/deleteArtist")
    public String deleteArtist(@RequestParam("artistId") int id) {
        artistService.removeArtist(id);
        return "redirect:/music/showFormForAddArtist";
    }

}
