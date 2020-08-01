# Video API
This Open Source Video API provides a consistent approach to accessing Video Content, and it's related data, across multiple Content Providers. This allows Users to use their preferred application to view their content from multiple sources.

The Video API is a simple [GraphQL](https://graphql.org/) API which is defined and documented in the SDL files in the [graphql](/api/graphql) directory. Together, these SDL files make up the GraphQL Schema of the API.

## Accessing the API
Because this is a GraphQL API, the entire API can be accessed through a single URI. A Video Content Provider would expose this URI to it's Users so that they can easily access their content.

Also, a Video Client Application may provide a means for a User to explicitly enter in their Video Content Provider's URI. Allowing simplicity for the User, a Video Client Application may attempt 
to access the API via the User entered URI, but if that fails, the Client may attempt to append `/videoql` as the last path segment if it isn't already there. The `videoql` name is a concatenation of 
`video` and `graphql`. Typically, GraphQL APIs are hosted at a `/graphql` endpoint, so to avoid the possibility of a naming conflict with other APIs at the URI, the `videoql` endpoint was chosen as the default.

An example of a Video API URI would be:
```
https://www.example.com/videoql
```

The Video API supports authentication but the base `Query` object and some other top level components should always be available regardless of whether the User is authenticated or not. 
Otherwise, a Video Client Application won't be able to determine if the provided URI implements the API. More information about this is available in the **Authentication** section.

## Authentication
The Video API supports optional authentication to access some or all of the content provided by the API.

### Access
The GraphQL Types and Fields in the API have an `@access` directive on them to indicate whether authentication is required to access that Type or Field.
The `@access` directive has a `type` field which is an enum value indicating whether the Type or Field is `OPEN` or dependent on the `LOGIN_STRATEGY`.
The `@access` directive is used in tandem with the `LoginType` enum from the `LoginInfo` object to determine whether the data can be provided to a User.

If an `@access` directive is provided on a type, that means that all fields belonging to that type have the same access level unless an `@access` directive is explicitly provided on the field.
In which case, the field's `@access` level overrides the parent type's `@access` level. However, a field's `@access` directive may only further restrict access, not relax it. For instance:
```graphql
type Query @access(type: OPEN) {
    viewer: Viewer! @access(type: LOGIN_STRATEGY)
}
```

### Personally Identifiable Information
The Video API is designed to not expose any personally identifiable information about a User. So, if a User's Video API Authentication Token becomes compromised, the damage would be relatively minor.
To increase the security of the Video API, it's recommended for the Video Content Provider to use a different Authentication Token for the Video API than ones used to access a User's Account or other APIs the Video Content Provider implements.

### Unauthorized
If an API query attempts to access information that it is not authorized to access, the Video Content Provider can either return `null` for that data along with a GraphQL Error in the Response, 
or it can return an HTTP Response Code of `401` - Unauthorized. The Video Client Application should be able to properly handle both cases.

### Performing Authentication
Since the API is designed to be federated, it uses the [OAUTH 2.0 Dynamic Client Registration Protocol](https://tools.ietf.org/html/rfc7591) for authentication. This allows a Video Client Application to 
dynamically register with a Video Content Provider so that it can perform authentication with a User.

Note that there is no initial authentication token for dynamically registering with a Video Content Provider, because that would require manually setting up that connection prior to registering, 
and since this is a federated API, there is no way to effectively do that. This approach can be considered Open Registration.

#### Process
The authentication process can be described by the following steps:
* The Video Client Application performs a GraphQL Query to the Video Content Provider API to access the `LoginInfo` object to obtain the `registrationUri`.
* The Video Client Application attempts to dynamically register by performing an HTTP POST request to the `registrationUri` with a JSON body of required and optional fields.
* The Video Content Provider responds with a JSON object containing the fields necessary to perform a standard Oauth 2.0 request.
* The Video Client Application performs the standard Oauth 2.0 flow using the values obtained in the previous step.

## Perspective
The API is designed to be in the perspective of the `Viewer`. The `Viewer` is the User who is accessing the content from the Video Content Provider.

For instance, when accessing the `feed` field on the `Query` object, the returned results are catered to the current User, or `Viewer`. If a different User accessed the `feed` field on the `Query` object, 
with the same parameters, they might receive different results, since they are in the perspective from a different `Viewer` and may have a unique experience.

## Open Android App from URI Intent
There are multiple ways to open the Android video client application to display a video:

* Through a video content URI, such as, a `file:` or `content:` schemed URI, with a MIME type of `video/*`.
* Through a specially formatted `video:` URI.
* Through a specially formatted `lbry:` URI that matches the [LBRY Protocol Specification](https://lbry.tech/spec).
* Through a particular URI that the application expects.

Note that not all Android video client applications will support each of the above approaches. However, it is recommended that at least the first two approaches are supported.

### Content URI
An Android video client application can be opened to display a particular video by an Android `Intent` with a content URI specifying the video resource. The MIME type of this resource should conform to the `video/*` MIME type, such as, `video/mp4`.

The content URI can have the `file` or `content` schemes for local resources. Or it can have the `http` or `https` schemes for remote resources.

Note that an Android video client application might not support all video file formats and may only accept the MIME types that it supports.

### Video URI
An Android video client application can be opened to display a particular video by an Android `Intent` with a Video URI specifying how to retrieve the video resource. This URI format conforms to a specific standard outlined below.

Note that this URI format is not an HTTP URI and does not correlate directly to a valid or owned HTTP URI. This difference is signified by the use of the `video` URI Scheme, as opposed to the `http(s)` URI Scheme.
This was deliberately chosen to be agnostic of a specific HTTP URI, so that it can be used in a de-centralized manner. So to be clear, `video://${HOST}` does not correlate to `https://${HOST}`, or any other URI Scheme for that matter.

* The Video URI must have a `video` scheme.
* The Video URI must have a `video` host.
* The Video URI must contain a `provider_uri` query parameter.
    * The `provider_uri` query parameter must be the URL encoded URI to the Video Content Provider API.
* The Video URI must contain a `video_id` query parameter.
    * The `video_id` query parameter must be the identifier for the video accessible from the Video Provider API.
* More query parameters may be provided, including non-standard query parameters.

The following is an example of a Video URI:
```
video://video?provider_uri=https%3A%2F%2Fwww.example.com%2Fvideoql&video_id=d147333b-ed38-4bcf-8a49-f5280ac3519a
```

In the above example we begin with the `video` scheme followed by the scheme separators `://` and the `video` host. 
Then the Video Provider URI query parameter is used, `provider_uri`, `https%3A%2F%2Fwww.example.com%2Fvideoql`, which correlates to `https://www.example.com/videoql`.
Finally, the `video_id` query parameter is provided. The `video_id` query parameter with the value of `d147333b-ed38-4bcf-8a49-f5280ac3519a`.

#### Additional recognized query parameters
The following are optional query parameters that may be provided to the Video URI:

* `auto_play` (optional) - a boolean value indicating whether this video should begin playing immediately. It is up to the Video Client to determine a default for this value.
* `start` (optional) - a long value indicating the starting point, in milliseconds, to begin playing the video. The default value is `0` which begins the video at it's natural starting point.
* `end` (optional) - a long value indicating the ending point, in milliseconds, to stop playing the video. The default value is the length of the video which ends the video at it's natural ending point.

### LBRY URI
An Android video client application can be opened to display a particular video by an Android `Intent` with a LBRY URI.

More information will be added about this approach when it is fully supported.

### Specific URI
An Android video client application can be opened to display a particular video by an Android `Intent` with a particular URI that the application expects.
For instance, an Android video client application may open URIs, that may or may not conform to the Video Provider API, that it recognizes, such as, it's own URL.
This approach may be particularly useful for applications that are for a particular Video Provider only.

Note that this approach might not work for all Android video client applications because it requires explicitly whitelisting each supported URI. The better approaches are to use the Content or Video URIs outlined previously.