package academy.ned.spring_webflux_essentials.exception;

import io.micrometer.common.util.StringUtils;
import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.boot.autoconfigure.web.reactive.error.AbstractErrorWebExceptionHandler;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.reactive.error.ErrorAttributes;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.*;
import reactor.core.publisher.Mono;

import java.util.Map;

@Component
@Order(-2)
public class GlobalExceptionHandler extends AbstractErrorWebExceptionHandler {

    public GlobalExceptionHandler(ErrorAttributes errorAttributes, WebProperties webProperties, ApplicationContext applicationContext) {
        super(errorAttributes, webProperties.getResources(), applicationContext);
    }

    @Override
    protected RouterFunction<ServerResponse> getRoutingFunction(ErrorAttributes errorAttributes) {
        return RouterFunctions.route(RequestPredicates.all(), this::renderErrorResponse);
    }

    private Mono<ServerResponse> renderErrorResponse(ServerRequest request) {
        String query = request.uri().getQuery();
        ErrorAttributeOptions errorAttributeOptions = isTracedEnabled(query) ? ErrorAttributeOptions.of(ErrorAttributeOptions.Include.STACK_TRACE) : ErrorAttributeOptions.defaults();
        Map<String, Object> errorPropertiesMap = getErrorAttributes(request, errorAttributeOptions);
        ServerResponse.BodyBuilder responseBuilder = ServerResponse.status(HttpStatus.INTERNAL_SERVER_ERROR);
        return responseBuilder.contentType(MediaType.APPLICATION_JSON).bodyValue(errorPropertiesMap);
    }

    private boolean isTracedEnabled(String query){
        return !StringUtils.isEmpty(query) && query.contains("trace=true");
    }
}

