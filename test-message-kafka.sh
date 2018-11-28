#!/bin/bash
set -e
#### Description: Test if messages were well processed by Kafka Streams

# Init values
container_name='resources_kafka1_1'
topic='wordcount-out'
kafka_server='localhost:9092'

echo -e "\nReading from Kafka to see if messages were well received..."
read_topic(){
    docker exec $container_name /bin/sh -c \
     'kafka-console-consumer --bootstrap-server '$kafka_server' --topic '$topic' --from-beginning --timeout-ms 2000 | wc -l'
}

rows=$(read_topic)
if [ "$rows" != "0" ]; then
    echo -e '\nSuccess!'
else
    echo 'Initialization problem: ' $rows
fi
