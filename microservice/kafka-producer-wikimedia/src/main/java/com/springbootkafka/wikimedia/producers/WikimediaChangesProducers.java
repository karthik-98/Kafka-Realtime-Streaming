package com.springbootkafka.wikimedia.producers;

import java.net.URI;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.launchdarkly.eventsource.EventHandler;
import com.launchdarkly.eventsource.EventSource;
import com.sprinbootkafka.wikimedia.eventhandlers.WikimediaChangesHandler;

@Service
public class WikimediaChangesProducers {

	public static Logger logger = LoggerFactory.getLogger(WikimediaChangesProducers.class);
	
	private KafkaTemplate<String, String> kafkaTemplate;

	public WikimediaChangesProducers(KafkaTemplate<String, String> kafkaTemplate) {
		super();
		this.kafkaTemplate = kafkaTemplate;
	}
	
	public void sendMessage() throws InterruptedException {
		String topic ="wikimedia_topic";
		EventHandler eventhandler = new WikimediaChangesHandler(kafkaTemplate, topic);
		String url = "https://stream.wikimedia.org/v2/stream/recentchange";
		EventSource.Builder builder = new EventSource.Builder (eventhandler, URI.create(url));
		EventSource eventSource = builder.build();
		eventSource.start();
		
		TimeUnit.MINUTES.sleep(10);
	}
}
