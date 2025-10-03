package eu.lsmyrak.invisiblesoftware.Devicegateway.application.setting.command;

import java.util.List;
import java.util.UUID;

import eu.lsmyrak.invisiblesoftware.Devicegateway.CQRS.Command;

public class EditUserCommand implements Command<Void>{
    private UUID userId;
    private List<UUID> roleids;
    public UUID getUserId() {
        return userId;
    }
    public void setUserId(UUID userId) {
        this.userId = userId;
    }
    public List<UUID> getRoleids() {
        return roleids;
    }
    public void setRoleids(List<UUID> roleids) {
        this.roleids = roleids;
    }    
    
    
}
