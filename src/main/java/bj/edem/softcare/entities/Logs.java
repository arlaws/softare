package bj.edem.softcare.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Aaron
 */
@Entity
@Getter
@Setter
public class Logs implements Serializable {

    private static final long serialVersionUID = 1L;

    public Logs() {
    }

    public Logs(String logAction, Date logDateHeure, Date logDate, String logRemoteIp, String logRemoteMac, Users user) {
        this.logAction = logAction;
        this.logDateHeure = logDateHeure;
        this.logDate = logDate;
        this.logRemoteIp = logRemoteIp;
        this.logRemoteMac = logRemoteMac;
        this.user = user;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String logAction;

    @Temporal(TemporalType.TIMESTAMP)
    private Date logDateHeure;

    @Temporal(TemporalType.DATE)
    private Date logDate;

    private String logRemoteIp;

    private String logRemoteMac;

    @ManyToOne
    private Users user;

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Logs)) {
            return false;
        }
        Logs other = (Logs) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.logRemoteIp + " " + this.logAction;
    }
}
