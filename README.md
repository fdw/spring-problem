We build one main `ApplicationContext` and dynamically add two child contexts, each with one `AnimalController`. In older versions of Spring Boot (f.e. 2.2.6), both controllers could be accessed via `/lizard/feed` and `/cat/feed`, respectively. With 2.3.4, however, we only get 404 for both paths.

# To reproduce:
- `./gradlew :parent:bootRun`
- `curl --request GET --url http://localhost:8080/lizard/feed` and `curl --request GET --url http://localhost:8080/cat/feed` should both result in a `200`. With the latest Spring Boot version, both result in `404`.
