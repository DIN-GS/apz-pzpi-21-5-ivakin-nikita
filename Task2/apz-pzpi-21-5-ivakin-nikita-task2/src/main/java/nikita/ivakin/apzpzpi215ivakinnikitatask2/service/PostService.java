package nikita.ivakin.apzpzpi215ivakinnikitatask2.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.dto.PostDTO;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.entity.Post;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.exceptions.PostNotFoundException;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public void createPost(PostDTO postDTO) {
        Post post = Post.builder()
                .location(postDTO.getLocation()).build();
        postRepository.save(post);
    }

    public Post findPostById(Integer id) {
       Optional<Post> tempPost = postRepository.findPostById(id);
       if (tempPost.isPresent()) {
           return tempPost.get();
       } else {
           throw new PostNotFoundException("There aren't any posts with id " + id);
       }
    }
}

