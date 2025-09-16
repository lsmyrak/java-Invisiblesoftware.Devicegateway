package eu.lsmyrak.invisiblesoftware.Devicegateway.domain.model;

import jakarta.persistence.Entity;

@Entity
public class DeviceType extends BaseAggregate {
    private String category;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
