package com.springbootkafka.wikimedia;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.springbootkafka.wikimedia.producers.WikimediaChangesProducers;

@SpringBootApplication
public class SpringbootProducerApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(SpringbootProducerApplication.class);
	}

	@Autowired
	private WikimediaChangesProducers wikimediaChangesProducers;
	
	@Override
	public void run(String... args) throws Exception {
		wikimediaChangesProducers.sendMessage();
	}
}
