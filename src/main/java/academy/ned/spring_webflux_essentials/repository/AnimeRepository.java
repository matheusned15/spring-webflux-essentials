package academy.ned.spring_webflux_essentials.repository;

import academy.ned.spring_webflux_essentials.domain.Anime;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface AnimeRepository extends ReactiveCrudRepository<Anime, Integer> {

}
