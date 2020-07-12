# Video
A decentralized and Kotlin multi-platform video application.

Note that this project is still in early stages of development.

## About
The idea is to have a single application that can play videos from multiple providers. So instead of having an application for every video service provider, a single client application can work with multiple providers.

### How it works
The video service providers implement a standardized API and exposes the URI for this API. Then, a client application (ex: this repository) can use the URI to access the videos from the service provider.

This application is similar to a Web Browser but specifically for videos. Or can be viewed as an RSS Feed Reader but specifically for videos.

### API
The API is a GraphQL API which can be found in the [api/graphql/](api/graphql) directory. Reasons for choosing GraphQL include it's flexibility for clients to request the data that they need and the simplicity of exposing only a single URI.

### How it came about
Personally, I have multiple subscriptions to streaming video providers and thought about how convenient it would be to access all of their content from a single application. I thought this would make an interesting project, and so I began developing it.

### Benefits
With a standardized API, video service providers won't have to develop their own applications for displaying their content. This would be especially beneficial to smaller companies and start-ups, as it would save them time, resources, and capital.

A standardized video API is also beneficial for consumers since they would only need a single application to access all of their video subscription content.

With the API being open and standardized, multiple client applications can be developed providing choices to the consumer (similar to how there are multiple Web Browsers to choose from).

### Caveats
Obviously, for this application to work, a service provider that implements the API is required. Eventually, I will have a base implementation of a server application that implements the API, to simplify the process for service providers.

## Status of the project
This project is still in the early development stages with a focus on creating the Android client first.

### Roadmap
* Finish the first iteration of the Android application
* Support non-standard API videos (locally on device, LBRY, etc)
* Refresh and optimize the UI/UX
* Optimize the common code to simplify making applications for different platforms (iOS, Web, AndroidTV, etc)
* Create different platform applications as separate modules in this project
* Create the Server that implements the API (separate repository)
