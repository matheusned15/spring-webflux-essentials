package academy.ned.spring_webflux_essentials.util;

import academy.ned.spring_webflux_essentials.domain.Anime;

public class AnimeCreator {

    public static Anime createAnimeToBeSaved(){
        return Anime.builder()
                .name("Tensei Shitara slime datta ken")
                .build();
    }

    public static Anime createAnimeValidAnime(){
        return Anime.builder()
                .id(1)
                .name("Tensei Shitara slime datta ken")
                .build();
    }

    public static Anime createUpdateAnime(){
        return Anime.builder()
                .id(1)
                .name("Tensei Shitara slime datta ken 2")
                .build();
    }
}
