package eu.lsmyrak.invisiblesoftware.Devicegateway.domain.model;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;

@Entity
public class Role extends BaseAggregate {
    
    @ManyToMany(mappedBy = "roles")
    private Set<ApplicationUser> applicationUsers = new HashSet<>();

    public Role() {        
    }

    public Set<ApplicationUser> getApplicationUsers() {
        return this.applicationUsers;
    }

    public void setApplicationUsers(Set<ApplicationUser> applicationUsers) {
        this.applicationUsers = applicationUsers;
    }

}
