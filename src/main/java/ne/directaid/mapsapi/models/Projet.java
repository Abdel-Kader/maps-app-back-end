package ne.directaid.mapsapi.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Blob;
import java.util.Date;

@Entity
@Data
public class Projet implements Serializable
{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String numeroProjet;

    @Column
    private String nomProjet;

    @Column
    private String typeProjet;

    @Column
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date dateExecution;

    @Column
    private String latProjet;

    @Column
    private String lngProjet;

    @Column
    @Lob()
    private byte[] photoProjet;

    @JoinColumn(name="code_utilisateur", nullable=false)
    @ManyToOne
    private Utilisateur utilisateur;
}
