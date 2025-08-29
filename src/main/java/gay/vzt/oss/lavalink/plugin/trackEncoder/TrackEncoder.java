package gay.vzt.oss.lavalink.plugin.trackEncoder;

import com.sedmelluq.discord.lavaplayer.tools.DataFormatTools;
import com.sedmelluq.discord.lavaplayer.tools.io.MessageOutput;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.DataOutput;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Objects;

@Service
public class TrackEncoder {
    private static final int TRACK_INFO_VERSIONED = 1;

    private static final int TRACK_INFO_VERSION = 3;

    private final List<PluginPreset> presets;

    public TrackEncoder(TrackEncoderConfig config) {
        this.presets = new ArrayList<>(config.presets);

        this.presets.add(PluginPreset.LAVASRC);
    }

    public String encode(Track track) {
        var array = new ByteArrayOutputStream();
        var stream = new MessageOutput(array);
        var output = stream.startMessage();
        try {
            output.write(TRACK_INFO_VERSION);

            writeTrackInfo(track, output);
            writeTrackDetails(track, output);
            output.writeLong(track.info().position());

            stream.commitMessage(TRACK_INFO_VERSIONED);
            stream.finish();
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }

        return Base64.getEncoder().encodeToString(array.toByteArray());
    }

    private void writeTrackDetails(Track track, DataOutput output) throws IOException {
        output.writeUTF(track.info().sourceName());

        var plugin = track.plugin();
        if (plugin != null) {
            var preset = this.presets.stream()
                    .filter(it -> Objects.equals(it.name(), plugin))
                    .findFirst();

            if (preset.isEmpty()) {
                throw new IllegalArgumentException("unknown plugin " + plugin);
            }

            var info = track.pluginInfo();
            for (var field : preset.get().fields()) {
                var type = field.type();
                type.write(output, info.get(field.name()));
            }

            return;
        }

        var details = track.sourceInfo();
        if (details == null) {
            return;
        }

        for (var field : track.sourceInfo()) {
            field.type().write(output, field.value());
        }
    }

    private static void writeTrackInfo(Track track, DataOutput output) throws IOException {
        // track information.
        var trackInfo = track.info();
        output.writeUTF(trackInfo.title());
        output.writeUTF(trackInfo.author());
        output.writeLong(trackInfo.length());
        output.writeUTF(trackInfo.identifier());
        output.writeBoolean(trackInfo.isStream());
        DataFormatTools.writeNullableText(output, trackInfo.uri());
        DataFormatTools.writeNullableText(output, trackInfo.artworkUrl());
        DataFormatTools.writeNullableText(output, trackInfo.isrc());
    }
}
