package academy.ned.spring_webflux_essentials;

import academy.ned.spring_webflux_essentials.exception.GlobalExceptionHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.boot.web.reactive.error.ErrorAttributes;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.http.codec.ServerCodecConfigurer;
import reactor.blockhound.BlockHound;

@SpringBootApplication
public class SpringWebfluxEssentialsApplication {

	static {
		BlockHound.install(builder -> builder.allowBlockingCallsInside("java.util.UUID", "randomUUID"));
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringWebfluxEssentialsApplication.class, args);
	}

	@Bean
	public WebProperties webProperties() {
		return new WebProperties();
	}

	@Bean
	public ServerCodecConfigurer serverCodecConfigurer() {
		return ServerCodecConfigurer.create();
	}

	@Bean
	public GlobalExceptionHandler globalExceptionHandler(ErrorAttributes errorAttributes, WebProperties webProperties, ApplicationContext applicationContext, ServerCodecConfigurer serverCodecConfigurer) {
		return new GlobalExceptionHandler(errorAttributes, webProperties, applicationContext, serverCodecConfigurer);
	}

}
