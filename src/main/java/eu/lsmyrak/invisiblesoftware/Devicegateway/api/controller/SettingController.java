package eu.lsmyrak.invisiblesoftware.Devicegateway.api.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import eu.lsmyrak.invisiblesoftware.Devicegateway.domain.model.CommandHistory;
import eu.lsmyrak.invisiblesoftware.Devicegateway.domain.repository.CommandHistoryRepository;

@RestController
@RequestMapping("/api/settings")
public class SettingController {

    private final CommandHistoryRepository commandHistoryRepository;    

    public SettingController(CommandHistoryRepository commandHistoryRepository) {
        this.commandHistoryRepository = commandHistoryRepository;
    }

    @PostMapping("seed-data")
    public String seedData() {
        return "seed-data";
    }

    @GetMapping("role/{id}")
    public String getRoleById() {
        return "role-by-id";

    }

    @PostMapping("add-role")
    public String addRole() {
        return "add-role";
    
    }

    @PostMapping("user-role-management")
    public String userRoleManagement() {
        return "user-role-management";

    }
    @GetMapping("lookup-role")
    public String lookupRole() {
        return "lookup-role";

    }
    @GetMapping("lookup-user")
    public String lookupUser() {
        return "lookup-user";

    }
   
    @GetMapping("commandHistory")
    public List<CommandHistory> commandHistory() {
    return  commandHistoryRepository.findAll();
    }

}
