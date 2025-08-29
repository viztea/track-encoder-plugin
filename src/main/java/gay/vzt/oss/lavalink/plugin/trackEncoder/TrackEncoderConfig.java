package gay.vzt.oss.lavalink.plugin.trackEncoder;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@ConfigurationProperties(prefix = "plugins.track-encoder")
@Component
public class TrackEncoderConfig {
    public List<PluginPreset> presets = List.of();
}
