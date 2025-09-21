package eu.lsmyrak.invisiblesoftware.Devicegateway.domain.model;

import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;

@Entity
public class DeviceGroup extends BaseAggregate {

    @ManyToMany
    @JoinTable(name = "device_group_assignment", joinColumns = @JoinColumn(name = "group_id"), inverseJoinColumns = @JoinColumn(name = "device_id"))
    private List<Device> devices = new ArrayList<>();

    public List<Device> getDevicess() {
        return devices;
    }

    public void setDevicess(List<Device> devicess) {
        this.devices = devicess;
    }

    public DeviceGroup() {

    }
}
