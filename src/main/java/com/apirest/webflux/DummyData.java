package com.apirest.webflux;

import java.util.UUID;

import org.springframework.boot.CommandLineRunner;

import com.apirest.webflux.document.Playlist;
import com.apirest.webflux.repository.PlaylistRepository;

import reactor.core.publisher.Flux;

public class DummyData implements CommandLineRunner {

	private final PlaylistRepository playlistRepository;
	
	DummyData(PlaylistRepository playlistRepository) {
		this.playlistRepository = playlistRepository;
	}
	
	@Override
	public void run(String... args) throws Exception {
		
		playlistRepository.deleteAll()
				.thenMany(
						Flux.just("API REST Spring Boot", "Deploy de uma aplicação java no IBM Cloud", "Java 11",
								"Gitbub", "Spring Secutiry", "Web Service RESTFull", "Bean no Spring Framework")
								.map(name -> new Playlist(UUID.randomUUID().toString(), name))
								.flatMap(playlistRepository::save))
				.subscribe(System.out::println);
		
	}
	
}
