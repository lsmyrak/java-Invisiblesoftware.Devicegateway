package eu.lsmyrak.invisiblesoftware.Devicegateway.domain.model;

import java.util.ArrayList;
import java.util.List;


import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;

@Entity
public class Role extends BaseAggregate {
    
    @ManyToMany(mappedBy = "roles")
    private List<ApplicationUser> applicationUsers = new ArrayList<>();    

    public Role() {        
    }

    public List<ApplicationUser> getApplicationUsers() {
        return this.applicationUsers;
    }

    public void setApplicationUsers(List<ApplicationUser> applicationUsers) {
        this.applicationUsers = applicationUsers;
    }

}
