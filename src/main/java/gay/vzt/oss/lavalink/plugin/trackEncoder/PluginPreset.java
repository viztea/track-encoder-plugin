package gay.vzt.oss.lavalink.plugin.trackEncoder;

/// A plugin preset.
///
/// The fields array must be in the same order as they are encoded in the plugin's source code.
public record PluginPreset(String name, PluginPreset.Field[] fields) {
    public static final PluginPreset LAVASRC = new PluginPreset(
            "lavasrc",
            new PluginPreset.Field[]{
                    new PluginPreset.Field("albumName", FieldType.NULLABLE_TEXT),
                    new PluginPreset.Field("albumUrl", FieldType.NULLABLE_TEXT),
                    new PluginPreset.Field("artistUrl", FieldType.NULLABLE_TEXT),
                    new PluginPreset.Field("artistArtworkUrl", FieldType.NULLABLE_TEXT),
                    new PluginPreset.Field("previewUrl", FieldType.NULLABLE_TEXT),
                    new PluginPreset.Field("isPreview", FieldType.BOOLEAN),
            }
    );

    public record Field(String name, FieldType type) {
    }
}
