package com.onlinemedia.workshopmongo.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.onlinemedia.workshopmongo.domain.Post;
import com.onlinemedia.workshopmongo.domain.User;
import com.onlinemedia.workshopmongo.dto.AuthorDTO;
import com.onlinemedia.workshopmongo.dto.CommentsDTO;
import com.onlinemedia.workshopmongo.repository.PostRepository;
import com.onlinemedia.workshopmongo.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner{
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PostRepository postRepository;

	
	@Override
	public void run(String... args) throws Exception {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		
		userRepository.deleteAll();
		postRepository.deleteAll();
		
		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");
		
		userRepository.saveAll(Arrays.asList(maria, alex, bob));
		
		Post post1 = new Post(null, sdf.parse("21/03/2018"),"Partiu viagem", "Vou viajar para SP", new AuthorDTO(maria));
		Post post2 = new Post(null, sdf.parse("23/03/2018"),"Bom dia", "Acordei feliz hoje", new AuthorDTO(maria));
		
		CommentsDTO c1 = new CommentsDTO("Boa viajem mano!", sdf.parse("21/03/2019"),new AuthorDTO(maria));
		CommentsDTO c2 = new CommentsDTO("Aproveite!", sdf.parse("21/03/2019"),new AuthorDTO(maria));
		CommentsDTO c3 = new CommentsDTO("Tenha um ótimo dia!", sdf.parse("21/03/2019"),new AuthorDTO(maria));
		
		post1.getComments().addAll(Arrays.asList(c1,c2));
		post2.getComments().addAll(Arrays.asList(c3));
		
		postRepository.saveAll(Arrays.asList(post1, post2));
		
		maria.getPosts().addAll(Arrays.asList(post1, post2));
		userRepository.save(maria);
	}
	

}
