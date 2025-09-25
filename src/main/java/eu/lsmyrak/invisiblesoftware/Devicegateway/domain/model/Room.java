package eu.lsmyrak.invisiblesoftware.Devicegateway.domain.model;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Room extends BaseAggregate {
    @ManyToOne
    @JoinColumn(name = "place_id")
    private Place place;

    @OneToMany(mappedBy = "room")
    private Set<Device> devices = new HashSet<>();

    @OneToMany
    private Set<ApplicationUser> applicationUsers = new HashSet<>();

    public Room() {
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }

    public Set<Device> getDevices() {
        return devices;
    }

    public void setDevices(Set<Device> devices) {
        this.devices = devices;
    }

    public Set<ApplicationUser> getApplicationUsers() {
        return applicationUsers;
    }

    public void setApplicationUsers(Set<ApplicationUser> applicationUsers) {
        this.applicationUsers = applicationUsers;
    }

}