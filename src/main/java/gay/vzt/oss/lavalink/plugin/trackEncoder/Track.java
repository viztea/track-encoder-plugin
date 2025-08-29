package gay.vzt.oss.lavalink.plugin.trackEncoder;

import java.util.Map;

public record Track(
        Info info,
        String plugin,
        Map<String, Object> pluginInfo,
        Detail[] sourceInfo,
        Map<String, Object> userData
) {
    public record Info(
            String title,
            String author,
            long length,
            String identifier,
            boolean isStream,
            long position,
            String uri,
            String artworkUrl,
            String sourceName,
            String isrc
    ) {
    }

    public record Detail(FieldType type, Object value) {
    }
}
