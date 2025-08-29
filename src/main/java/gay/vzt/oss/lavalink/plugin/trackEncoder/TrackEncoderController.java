package gay.vzt.oss.lavalink.plugin.trackEncoder;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class TrackEncoderController {
    private final TrackEncoder encoder;

    public TrackEncoderController(TrackEncoder encoder) {
        this.encoder = encoder;
    }

    @PostMapping("/v4/encodetrack")
    public Response<String> encodeTrack(@RequestBody Track track) {
        try {
            return new Response<>(encoder.encode(track));
        } catch (IllegalArgumentException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage(), ex);
        }
    }

    @PostMapping("/v4/encodetracks")
    public Response<List<String>> encodeTrack(@RequestBody List<Track> track) {
        try {
            return new Response<>(track.stream().map(encoder::encode).toList());
        } catch (IllegalArgumentException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage(), ex);
        }
    }

    public record Response<T>(T value) {
    }
}
