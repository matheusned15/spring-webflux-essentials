package academy.ned.spring_webflux_essentials.domain;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.data.relational.core.mapping.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@With
@Table("anime")
public class Anime {

    private Integer id;
    @NotNull
    @NotEmpty(message = "The name of this anime cannot be empty")
    private String name;

}
