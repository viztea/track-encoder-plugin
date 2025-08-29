# Track Encoder

A [lavalink](https://github.com/lavalink-devs/lavalink) plugin for
encoding [lavaplayer](https://github.com/lavalink-devs/lavaplayer) tracks that are usually returned by /loadtracks (or
/decodetrack).

This plugin can be used to create tracks in your bot's code that can be used

## API

This plugin offers two endpoints `/v4/encodetrack` and `/v4/encodetracks`, the latter can be used to encode multiple
tracks at once.

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

In case you need to encode source-specific information, you may either provide a plugin preset or a list of fields to encode.
Currently, the only available presets are `lavasrc`.

```json
{
    ...,
    "plugin": "lavasrc",
    "pluginInfo": {
        "albumName": "...",
        "albumUrl": "...",
        "artistUrl": "...",
        "artistArtworkUrl": "...",
        "previewUrl": "...",
        "isPreview": false
    },
    // or source details.
    "sourceDetails": [
        {
            "type": "NULLABLE_TEXT" | "BOOL" | "LONG" | "FLOAT" | "DOUBLE" | "TEXT",
            "value": ...
        }
    ]
}
```

### Usage

#### `POST /v4/encodetrack`

Request

```json
{
    // track object
    ...
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

```json
[
    {
        // track object
        ...
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
