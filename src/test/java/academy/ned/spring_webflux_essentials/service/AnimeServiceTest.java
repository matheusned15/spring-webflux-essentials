package academy.ned.spring_webflux_essentials.service;

import academy.ned.spring_webflux_essentials.domain.Anime;
import academy.ned.spring_webflux_essentials.repository.AnimeRepository;
import academy.ned.spring_webflux_essentials.util.AnimeCreator;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.server.ResponseStatusException;
import reactor.blockhound.BlockHound;
import reactor.blockhound.BlockingOperationError;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import reactor.test.StepVerifier;

import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
class AnimeServiceTest {

    @InjectMocks
    private AnimeService animeService;

    @Mock
    private AnimeRepository animeRepositoryMock;

    private final Anime anime = AnimeCreator.createAnimeValidAnime();

    @Test
    public void blockHoundWorks(){
        try {
            FutureTask<?> task = new FutureTask<>(() -> {
                Thread.sleep(0);
                return "";
            });
            Schedulers.parallel().schedule(task);

            task.get(10, TimeUnit.SECONDS);
            Assertions.fail("Should fail");
        } catch (Exception e){
            Assertions.assertTrue(e.getCause() instanceof BlockingOperationError);
        }
    }

    @BeforeAll
    public static void blockHoundSetup(){
        BlockHound.install();
    }

    @BeforeEach
    public void setUp(){
        BDDMockito.when(animeRepositoryMock.findAll())
                .thenReturn(Flux.just(anime));

        BDDMockito.when(animeRepositoryMock.findById(ArgumentMatchers.anyInt()))
                .thenReturn(Mono.just(anime));
    }

    @Test
    @DisplayName("findall returns a flux of anime")
    public void findAll_ReturnsFluxOfAnime_WhenSuccessful(){
        StepVerifier.create(animeService.findAll())
                .expectSubscription()
                .expectNext(anime)
                .verifyComplete();
    }

    @Test
    @DisplayName("findById returns Mono with anime when it exists")
    public void findById_ReturnsMonoAnime_WhenSuccessful(){
        StepVerifier.create(animeService.findById(1))
                .expectSubscription()
                .expectNext(anime)
                .verifyComplete();
    }

    @Test
    @DisplayName("findById returns Mono error when anime does not exists")
    public void findById_ReturnsMonoError_WhenEmptyMonoIsReturned(){
        BDDMockito.when(animeRepositoryMock.findById(ArgumentMatchers.anyInt()))
                .thenReturn(Mono.empty());

        StepVerifier.create(animeService.findById(1))
                .expectSubscription()
                .expectError(ResponseStatusException.class)
                .verify();
    }


}