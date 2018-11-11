# Kafka streams scala examples
## Run
```
# Spin up 1 ZK and 1 Kafka
docker-compose -f src/test/docker-compose.yml up -d
# Run the app
sbt run
```

## Documentation

### Streams
* https://kafka.apache.org/20/documentation/streams/developer-guide/dsl-api.html#scala-dsl
* https://kafka.apache.org/20/documentation/streams/developer-guide/write-streams.html
* https://cwiki.apache.org/confluence/display/KAFKA/KIP-270+-+A+Scala+Wrapper+Library+for+Kafka+Streams
* https://legacy.gitbook.com/book/jaceklaskowski/mastering-kafka-streams
### How to test
* https://kafka.apache.org/20/documentation/streams/developer-guide/testing.html

### Logging
* https://jaceklaskowski.gitbooks.io/mastering-kafka-streams/kafka-logging.html

## Special credits
Thanks to Alexis Seigneurin for creating the [Scala Kafka Streams API](https://github.com/aseigneurin/kafka-streams-scala) in 2017

