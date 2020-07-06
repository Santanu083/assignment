Generate an http4s service on the blaze backend with Circe.
1) Install sbt
2) sbt new http4s/http4s.g8
3) cd quickstart
4) sbt run
5) curl http://localhost:8080/interview/$USER 
6) Learn more 

Note:

We have enabled sbt-partial-unification necessary for utilizing all features of Http4s and Cats. As well as sbt-revolver for easier project reloading.

http4s DSL provides a minimal foundation for declaring services and executing them on blaze or a servlet container. 

Client-Side HTTP Requests using ScalaDSL.
Add the http4s-dsl to your build and imports it.
Adds A minimum set of headers depending on the response.

create a client with http4,Describing a call,Making the call,constructing a URi,Post a form, decoding the JSON response to a case class and reusable way to decoding/encoding a request is to write a custom EntityDecoder and EntityEncoder.

We have commented out the sbt-tpolecat plugin in the generated project/plugins.sbt. We highly recommend it for projects, but it may not be a good baseline for new users.



