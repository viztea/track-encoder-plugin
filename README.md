# Track Encoder

A [lavalink](https://github.com/lavalink-devs/lavalink) plugin for
encoding [lavaplayer](https://github.com/lavalink-devs/lavaplayer) tracks that are usually returned by
`/v4/loadtracks` (or `/v4/decodetrack`).

## API

This plugin offers two endpoints: `/v4/encodetrack` and `/v4/encodetracks`, the latter is available for encoding
multiple track objects at once.

### Track Model

The basic track structure should look something like

```json
{
    "info": {
        "identifier": "3w3y8KPTfNeOKPiqUTakBh",
        "author": "Bruno Mars",
        "length": 233478,
        "isStream": false,
        "title": "Locked out of Heaven",
        "uri": "https://open.spotify.com/track/3w3y8KPTfNeOKPiqUTakBh",
        "sourceName": "spotify",
        "position": 0,
        "artworkUrl": "https://i.scdn.co/image/ab67616d0000b273926f43e7cce571e62720fd46",
        "isrc": "USAT21203287"
    }
}
```

> [!NOTE]  
> The `userData` field is optional.

In case you need to encode source-specific information, you may either provide a plugin preset or a list of fields to
encode.

> [!NOTE]
> Currently, the only available presets are `lavasrc`.
> However, you may provide custom presets in your `application.yml` file.

<details>
<summary>Custom plugin presets</summary>

```yaml
plugins:
  track-encoder:
    presets:
      - name: http
        fields:
          - name: probeInfo
            type: TEXT
```

```json
{
    "plugin": "http",
    "pluginInfo": {
        "probeInfo": "mp3"
    }
}
```

</details>

```json5
{
    "plugin": "lavasrc",
    "pluginInfo": {
        "albumName": "...",
        "albumUrl": "...",
        "artistUrl": "...",
        "artistArtworkUrl": "...",
        "previewUrl": "...",
        "isPreview": false
    },
    // or
    "sourceDetails": [
        {
            "type": "TEXT",
            "value": "..."
        }
    ]
}
```

> [!NOTE]
> Supported field types: `NULLABLE_TEXT`, `BOOL`, `LONG`, `FLOAT`, `DOUBLE`, or `TEXT`

### Usage

Go to the releases page and download the plugin jar.

#### `POST /v4/encodetrack`

Request

```json5
{
    // track object
}
```

Response:

```json
{
    "track": "encoded track"
}
```

#### `POST /v4/encodetracks`

Request

```json5
[
    {
        // track object
    }
]
```

```json
{
    "tracks": [
        "<encoded track>"
    ]
}
```
