package bj.edem.softcare.entities;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Aaron
 */
@Getter
@Setter
@Entity
public class Personne implements Serializable {

    private static final long serialVersionUID = 1L;

    public Personne() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String civilite, nom, prenoms, nomPrenoms, genre, situationMatrimoniale,
            contact, email, adresse;

    @Temporal(TemporalType.DATE)
    private Date dateNaissance;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreation;
    @Version
    private Timestamp version;

    @PrePersist
    public void initDateCreation() {
        dateCreation = new Date();
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Personne)) {
            return false;
        }
        Personne other = (Personne) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Id : " + this.id + " Nom : " + this.nomPrenoms;
    }
}
