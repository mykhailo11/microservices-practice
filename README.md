# Microservices

My step into microservices architecture.

## Docker

Learned how to operate with docker, containerize applications. Also did some practice in virtualization using lima on macos.
I intentionally didn't use Docker Desktop in order to understand how everything works under the hood and to not to install
a lot of unnecessary staff on my laptop. The greatest thing I have managed to do is build optimization by using cached maven dependencies.

## Microservices

Developing microservices itself was straightfoward for me, had some experience with Spring Boot, so nothing outstanding here

## RPC

At first it was a bit hard to understand the concept, but eventually I adopted this feature to establish communication between different
parts of my application. gRPC implementation is contract based, language agnostic, I like that a lot. Also I tried to avoid using rpc starters
because they are not official. Spring incroduced it's own started since version 4, but not sure if it is mature. That's why I decided to instantiate
Netty Shaded from scratch, using as few dependencies as I can, even though I lacks some security configurations.

## Kafka

The hardest part is cluster architecture, producing and consuming events seems pretty simple
