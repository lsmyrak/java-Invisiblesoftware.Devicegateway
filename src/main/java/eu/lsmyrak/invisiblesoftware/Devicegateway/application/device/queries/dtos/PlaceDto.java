package eu.lsmyrak.invisiblesoftware.Devicegateway.application.device.queries.dtos;

public class PlaceDto extends BaseDto {
    private String location;
    private String address;
    private String city;
    private String state;
    
    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }

    
}
