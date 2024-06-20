package academy.ned.spring_webflux_essentials.service;

import academy.ned.spring_webflux_essentials.repository.AppUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
public class AppUserDetailsService implements ReactiveUserDetailsService {

    private final AppUserRepository appUserRepository;

    @Override
    public Mono<UserDetails> findByUsername(String username) {
        return appUserRepository.findByUsername(username)
                .cast(UserDetails.class);
    }
}
