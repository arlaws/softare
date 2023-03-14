package bj.edem.softcare.entities;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Users implements Serializable {

    public Users() {
    }

    public Users(String email, String contact, String nomPrenoms,
            String username, String password, boolean activated, boolean verified) {

        this.email = email;
        this.contact = contact;
        this.nomPrenoms = nomPrenoms;
        this.username = username;
        this.password = password;
        this.activated = activated;
        this.verified = verified;

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom, prenoms, email, contact, nomPrenoms;

    //Les identifiants de connexion
    private String username, password;

    private boolean activated, verified;

    @Transient
    private String passwordConfirm, typeAccount;

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
        if (!(object instanceof Users)) {
            return false;
        }
        Users other = (Users) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.id + " " + this.nom + " " + this.prenoms;
    }

}
