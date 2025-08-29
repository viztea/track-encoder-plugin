package gay.vzt.oss.lavalink.plugin.trackEncoder;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@ConfigurationProperties(prefix = "plugins.trackencoder")
@Component
public class TrackEncoderConfig {
    private List<PluginPreset> presets = List.of();

    public List<PluginPreset> getPresets() {
        return presets;
    }

    public void setPresets(List<PluginPreset> presets) {
        this.presets = presets;
    }
}
