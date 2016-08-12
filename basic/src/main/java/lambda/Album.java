package lambda;

import java.util.List;

/**
 * @author flyman
 */
public class Album {

    private String name;

    private List<Track> tracks;

    private List<Artist> artists;

    public Album(String name, List<Track> tracks, List<Artist> artists) {
        this.name = name;
        this.tracks = tracks;
        this.artists = artists;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Track> getTracks() {
        return tracks;
    }

    public void setTracks(List<Track> tracks) {
        this.tracks = tracks;
    }

    public List<Artist> getArtists() {
        return artists;
    }

    public void setArtists(List<Artist> artists) {
        this.artists = artists;
    }


}
