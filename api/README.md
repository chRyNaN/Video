# Video API

## Open Android App from Intent
There are multiple ways to open the Android video client application to display a video:

* Through a video content URI, such as, a `file:` or `content:` schemed URI, with a MIME type of `video/*`.
* Through a specially formatted `video:` URI.
* Through a particular URI that the application expects.

Note that not all Android video client applications will support each of the above approaches. However, it is recommended that at least the first two approaches are supported.

### Content URI
An Android video client application can be opened to display a particular video by an Android `Intent` with a content URI specifying the video resource. The MIME type of this resource should conform to the `video/*` MIME type, such as, `video/mp4`.

The content URI can have the `file` or `content` schemes for local resources. Or it can have the `http` or `https` schemes for remote resources.

Note that an Android video client application might not support all video file formats and may only accept the MIME types that it supports.

### Video URI
An Android video client application can be opened to display a particular video by an Android `Intent` with a Video URI specifying how to retrieve the video resource. This URI format conforms to a specific standard outlined below.

* The Video URI must have a `video` scheme.
* The Video Provider URI follows the `video` scheme.
    * The Video Provider URI excludes it's scheme component.
    * Typically the last path parameter of a Video Provider URI is `videoql`. If that is the case for this URI then it must be included.
    * The Video Provider URI must be a valid URI that is accessible via the `https` scheme.
* The Video URI must contain either a `videoId` or `videoUri` query parameter.
    * Both query parameters may be present.
    * The `videoId` query parameter must be the identifier for the video accessible from the Video Provider API.
    * The `videoUri` query parameter must be the URL encoded URI to the video resource. This typically matches the URI from the Video Provider API for the video.
    * More query parameters may be provided, including non-standard query parameters.

The following is an example of a Video URI:
```
video://www.example.com/videoql?videoId=d147333b-ed38-4bcf-8a49-f5280ac3519a&videoUri=https%3A%2F%2Fcom.example.com%2Fvideo%2Fd147333b-ed38-4bcf-8a49-f5280ac3519a
```

In the above example we begin with the `video` scheme followed by the scheme separators `://`. 
Then the Video Provider URI is used, `www.example.com/videoql`, which correlates to `https://www.example.com/videoql`.
Finally, the query parameters are provided. The `videoId` query parameter with the value of `d147333b-ed38-4bcf-8a49-f5280ac3519a` and the `videoUri` query parameter with the value of `https%3A%2F%2Fcom.example.com%2Fvideo%2Fd147333b-ed38-4bcf-8a49-f5280ac3519a`.
In practice, only one query parameter, `videoId` or `videoUri`, is necessary, but both may provided. If the `videoId` query parameter is provided, then the client application can retrieve information about the video that is being played from the Video Provider.

#### Additional recognized query parameters
The following are optional query parameters that may be provided to the Video URI:

* `autoplay` - a boolean value indicating whether this video should begin playing immediately. It is up to the Video Client to determine a default for this value.
* `start` - a long value indicating the starting point, in milliseconds, to begin playing the video. The default value is `0` which begins the video at it's natural starting point.
* `end` - a long value indicating the ending point, in milliseconds, to stop playing the video. The default value is the length of the video which ends the video at it's natural ending point.

### Specific URI
An Android video client application can be opened to display a particular video by an Android `Intent` with a particular URI that the application expects.
For instance, an Android video client application may open URIs, that may or may not conform to the Video Provider API, that it recognizes, such as, it's own URL.
This approach may be particularly useful for applications that are for a particular Video Provider only.

Note that this approach might not work for all Android video client applications because it requires explicitly whitelisting each supported URI. The better approaches are to use the Content or Video URIs outlined previously.