package academy.ned.spring_webflux_essentials.controller;

import academy.ned.spring_webflux_essentials.domain.Anime;
import academy.ned.spring_webflux_essentials.repository.AnimeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
@RestController
@RequestMapping("animes")
@Slf4j
public class AnimeController {
    private final AnimeRepository animeRepository;

    @GetMapping
    public Flux<Anime> listAll(){
        return animeRepository.findAll();
    }
}
