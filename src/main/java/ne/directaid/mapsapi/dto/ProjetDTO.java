package ne.directaid.mapsapi.dto;

import lombok.Data;

import java.sql.Blob;
import java.util.Date;
import java.util.HashMap;

@Data
public class ProjetDTO
{
    public HashMap<String, Double> coordinate = new HashMap<>();
    public String numero;
    public String nom;
    public String type;
    public Date dateExec;
    public byte[] image;
}
