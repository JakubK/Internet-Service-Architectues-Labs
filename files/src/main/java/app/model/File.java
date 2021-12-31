package app.model;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString(callSuper = true)
@EqualsAndHashCode()
@SuperBuilder

@Entity
@Table(name = "files")
public class File {
    @Id
    private int id;

    private String filePath;
}
