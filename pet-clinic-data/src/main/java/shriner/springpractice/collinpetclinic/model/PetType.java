package shriner.springpractice.collinpetclinic.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "types")
public class PetType extends BaseEntity{

    @Builder
    public PetType(Long id, String name)
    {
        super(id);
        this.name = name;
    }

    @Column(name = "name")
    private String name;

    @Override
    public String toString(){
        return name;
    }
}
