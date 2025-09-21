package eu.lsmyrak.invisiblesoftware.Devicegateway.api.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import eu.lsmyrak.invisiblesoftware.Devicegateway.application.setting.queries.GetRoleByIdQueryHandler;
import eu.lsmyrak.invisiblesoftware.Devicegateway.application.setting.queries.GetRolebyIdQuery;
import eu.lsmyrak.invisiblesoftware.Devicegateway.application.setting.queries.dtos.RoleDto;
import eu.lsmyrak.invisiblesoftware.Devicegateway.domain.model.CommandHistory;
import eu.lsmyrak.invisiblesoftware.Devicegateway.domain.repository.CommandHistoryRepository;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/api/settings")
public class SettingController {

    private final CommandHistoryRepository commandHistoryRepository;
    private final GetRoleByIdQueryHandler getRoleByIdQueryHandler;

    public SettingController(CommandHistoryRepository commandHistoryRepository,GetRoleByIdQueryHandler getRoleByIdQueryHandler) { 
        this.commandHistoryRepository = commandHistoryRepository;
        this.getRoleByIdQueryHandler = getRoleByIdQueryHandler;
    }

    @PostMapping("seed-data")
    public String seedData() {
        return "seed-data";
    }

    @GetMapping("role/{id}")
    public RoleDto getRoleById(@RequestBody GetRolebyIdQuery query) {

        return getRoleByIdQueryHandler.handle(query);

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
   
    @GetMapping("command-history")
    public List<CommandHistory> commandHistory() {
    return  commandHistoryRepository.findAll();
    }

}
