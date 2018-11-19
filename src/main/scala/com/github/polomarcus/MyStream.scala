package com.github.polomarcus

import java.util.Properties
import java.util.concurrent.TimeUnit

import com.github.polomarcus.conf.ConfService
import com.typesafe.scalalogging.Logger
import org.apache.kafka.streams.scala.ImplicitConversions._
import org.apache.kafka.streams.scala._
import org.apache.kafka.streams.scala.kstream._
import org.apache.kafka.streams.{KafkaStreams, StreamsConfig}

/**
  * From https://kafka.apache.org/20/documentation/streams/developer-guide/dsl-api.html#scala-dsl
  */
object WordCountApplication extends App {
  val logger = Logger(WordCountApplication.getClass)

  import Serdes._

  val props: Properties = {
    val p = new Properties()
    p.put(StreamsConfig.APPLICATION_ID_CONFIG, "wordcount-application")
    p.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, ConfService.BOOTSTRAP_SERVERS_CONFIG)
    p
  }

  val builder: StreamsBuilder = new StreamsBuilder
  val textLines: KStream[String, String] = builder.stream[String, String](ConfService.TOPIC_IN)
  val wordCounts: KTable[String, Long] = textLines
    .flatMapValues(textLine => textLine.toLowerCase.split("\\W+"))
    .groupBy((_, word) => word)
    .count()

  wordCounts.toStream.to(ConfService.TOPIC_OUT)

  val streams: KafkaStreams = new KafkaStreams(builder.build(), props)

  logger.info("Stream started")
  streams.start()

  sys.ShutdownHookThread {
    logger.info("Stream closed")
    streams.close(10, TimeUnit.SECONDS)
  }
}