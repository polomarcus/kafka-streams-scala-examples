#!/bin/bash
set -e
#### Description: Init kafka with messages

# Init values
container_name='resources_kafka1_1'
destination='/root/'
topic='lyrics'
file_name='lyrics-jeanjass-caba.txt'
number_messages="108"
kafka_server='localhost:9092'

## Create stack
docker-compose -f src/test/resources/docker-compose.yml up -d
sleep 3

echo -e "\nCopying some messages inside Kafka container"
docker cp src/test/data/$file_name $container_name:$destination
echo -e "\nSending messages to kafka"
echo 'kafka-console-producer --broker-list localhost:9092 --topic '$topic
docker exec $container_name /bin/sh -c 'kafka-console-producer --broker-list '$kafka_server' --topic '$topic' < /'$destination$file_name

echo -e "\nReading from Kafka to see if messages were well received..."
read_topic(){
    docker exec $container_name /bin/sh -c \
     'kafka-console-consumer --bootstrap-server '$kafka_server' --topic '$topic' --from-beginning --timeout-ms 2000 | wc -l'
}

rows=$(read_topic)
if [ "$rows" == "$number_messages" ]; then
    echo -e '\nSuccess!'
else
    echo 'Initialization problem: ' $rows
fi
