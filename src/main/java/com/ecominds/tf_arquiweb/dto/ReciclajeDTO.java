import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class ReciclajeDTO {
    private Integer id;
    private LocalDate fecha;
    private LocalTime hora;
    private Double peso;
    private Integer idUsuario;
    private String nombreUsuario;
    private Integer idMateriales;
    private String nombreMaterial;
    private Integer idAcopio;
    private String nombreAcopio;
}