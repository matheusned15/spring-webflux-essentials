package academy.ned.spring_webflux_essentials.controller;

import academy.ned.spring_webflux_essentials.domain.Anime;
import academy.ned.spring_webflux_essentials.repository.AnimeRepository;
import academy.ned.spring_webflux_essentials.service.AnimeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@RestController
@RequestMapping("animes")
@Slf4j
public class AnimeController {
    private final AnimeService animeService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Flux<Anime> listAll(){
        return animeService.findAll();
    }

    @GetMapping(path = "{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Anime> findById(@PathVariable int id){
        return animeService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Anime> save(@Valid @RequestBody Anime anime){
        return animeService.save(anime);
    }

    @PutMapping(path = "{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> update(@PathVariable int id, @Valid @RequestBody Anime anime){
        return animeService.update(anime);
    }

    @DeleteMapping(path = "{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> delete(@PathVariable int id){
        return animeService.delete(id);
    }
}
