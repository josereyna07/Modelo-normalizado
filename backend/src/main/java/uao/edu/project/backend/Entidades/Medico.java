package uao.edu.project.backend.Entidades;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "Medico")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Medico {
    @Id
    private String id;
    private String nombre;
    private String apellido;
    private String especialidad;
    private String telefono;
    private String email;
}