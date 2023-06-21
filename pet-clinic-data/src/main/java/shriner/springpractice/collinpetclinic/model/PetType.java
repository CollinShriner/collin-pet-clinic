package shriner.springpractice.collinpetclinic.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "types")
public class PetType extends BaseEntity{
    @Column(name = "name")
    private String name;

}
