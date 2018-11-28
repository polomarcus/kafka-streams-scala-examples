# Kafka streams scala examples [![Build Status](https://travis-ci.org/polomarcus/kafka-streams-scala-examples.svg?branch=master)](https://travis-ci.org/polomarcus/kafka-streams-scala-examples)
With docker-compose, launch a Kafka env to process a stream of data using Kafka Streams
## Run
### Kafka stack
```
# Spin up 1 ZK, 1 Kafka and Topics UI
./init-stack-kafka-with-data.sh
```

You can check messages with Topics UI:
*  http://localhost:8000/#/cluster/default/topic/n/lyrics/

### Sbt
```
# Run the app
sbt run
```

### Check output topic
Log inside the kafka container
```
# docker ps --> to get container_name
docker exec -ti resources_kafka1_1 bash
# all kafka scripts are available, example:
# kafka-console-producer --broker-list localhost:9092 --topic lyrics
```
Check output topic content
```
kafka-console-consumer --bootstrap-server localhost:9092 \
        --topic wordcount-out \
        --from-beginning \
        --formatter kafka.tools.DefaultMessageFormatter \
        --property print.key=true \
        --property key.deserializer=org.apache.kafka.common.serialization.StringDeserializer \
        --property value.deserializer=org.apache.kafka.common.serialization.LongDeserializer
```
Result
```dtd
mets	118
du	116
respect	110
sur	106
mon	112
nom	106
```

## CI - Test - Travis 
Tests used in Travis, see [.travis.yml](https://github.com/polomarcus/kafka-streams-scala-examples/blob/master/.travis.yml)

Run the streams app during 60 seconds
```
timeout 60 sbt run 
```
Then, test if the output topic contains messages
```
./test-message-kafka.sh
```
## Documentation

### Streams
* https://docs.confluent.io/current/streams/quickstart.html#
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



https://stackoverflow.com/questions/5161193/how-to-kill-a-child-process-after-a-given-timeout-in-bash
https://stackoverflow.com/questions/34230418/how-to-get-process-id-of-sbt