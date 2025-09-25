package eu.lsmyrak.invisiblesoftware.Devicegateway.domain.model;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;

@Entity
public class DeviceGroup extends BaseAggregate {

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "device_group_assignment", 
    joinColumns = @JoinColumn(name = "group_id"),
    inverseJoinColumns = @JoinColumn(name = "device_id"))
    private Set<Device> devices = new HashSet<>();

    public Set<Device> getDevicess() {
        return devices;
    }

    public void setDevicess(Set<Device> devicess) {
        this.devices = devicess;
    }

    public DeviceGroup() {

    }
}
