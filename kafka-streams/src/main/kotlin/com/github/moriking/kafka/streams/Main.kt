package com.github.moriking.kafka.streams

import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.common.serialization.Serdes
import org.apache.kafka.streams.KafkaStreams
import org.apache.kafka.streams.StreamsBuilder
import org.apache.kafka.streams.StreamsConfig
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.util.*

fun main() {
    val config = Properties()
    config[StreamsConfig.APPLICATION_ID_CONFIG] = "alarm-count-applications"
    config[StreamsConfig.BOOTSTRAP_SERVERS_CONFIG] = "localhost:9092"
    config[ConsumerConfig.AUTO_OFFSET_RESET_CONFIG] = "earliest"
    config[StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG] = Serdes.String().javaClass
    config[StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG] = Serdes.String().javaClass
    val stream = Streams()
    val streams = KafkaStreams(stream.createTopology(), config)
    streams.start()

    // shutdown hook to correctly close the streams application
    Runtime.getRuntime().addShutdownHook(Thread { streams.close() })
}

