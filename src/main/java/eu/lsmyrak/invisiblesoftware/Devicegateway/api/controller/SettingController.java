package eu.lsmyrak.invisiblesoftware.Devicegateway.api.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import eu.lsmyrak.invisiblesoftware.Devicegateway.application.setting.command.AddRoleCommand;
import eu.lsmyrak.invisiblesoftware.Devicegateway.application.setting.command.AddRoleCommandHandler;
import eu.lsmyrak.invisiblesoftware.Devicegateway.application.setting.command.SeedDataCommand;
import eu.lsmyrak.invisiblesoftware.Devicegateway.application.setting.command.SeedDataCommandHandler;
import eu.lsmyrak.invisiblesoftware.Devicegateway.application.setting.queries.GetRoleByIdQueryHandler;
import eu.lsmyrak.invisiblesoftware.Devicegateway.application.setting.queries.GetRoleLookupQuery;
import eu.lsmyrak.invisiblesoftware.Devicegateway.application.setting.queries.GetRoleLookupQueryHandler;
import eu.lsmyrak.invisiblesoftware.Devicegateway.application.setting.queries.GetRolebyIdQuery;
import eu.lsmyrak.invisiblesoftware.Devicegateway.application.setting.queries.GetUserLookupQuery;
import eu.lsmyrak.invisiblesoftware.Devicegateway.application.setting.queries.GetUserLookupQueryHandler;
import eu.lsmyrak.invisiblesoftware.Devicegateway.application.setting.queries.dtos.RoleDto;
import eu.lsmyrak.invisiblesoftware.Devicegateway.domain.model.CommandHistory;
import eu.lsmyrak.invisiblesoftware.Devicegateway.domain.repository.CommandHistoryRepository;
import eu.lsmyrak.invisiblesoftware.Devicegateway.dto.common.LookupResponse;
import eu.lsmyrak.invisiblesoftware.Devicegateway.dto.common.NameRelatedDto;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/settings")
public class SettingController {

    private final CommandHistoryRepository commandHistoryRepository;
    private final GetRoleByIdQueryHandler getRoleByIdQueryHandler;
    private final SeedDataCommandHandler seedDataCommandHandler;
    private final AddRoleCommandHandler addRoleCommandHandler;

    private final GetRoleLookupQueryHandler getRoleLookupQueryHandler;
    private final GetUserLookupQueryHandler getUserLookupQueryHandler;

    public SettingController(CommandHistoryRepository commandHistoryRepository,
            GetRoleByIdQueryHandler getRoleByIdQueryHandler,
            SeedDataCommandHandler seedDataCommandHandler,
            AddRoleCommandHandler addRoleCommandHandler,
            GetRoleLookupQueryHandler getRoleLookupQueryHandler,
            GetUserLookupQueryHandler getUserLookupQueryHandler) {
        this.commandHistoryRepository = commandHistoryRepository;
        this.getRoleByIdQueryHandler = getRoleByIdQueryHandler;
        this.seedDataCommandHandler = seedDataCommandHandler;
        this.addRoleCommandHandler = addRoleCommandHandler;
        this.getRoleLookupQueryHandler = getRoleLookupQueryHandler;
        this.getUserLookupQueryHandler = getUserLookupQueryHandler;
    }

    @PostMapping("seed-data")
    public void seedData() {
        seedDataCommandHandler.handle(new SeedDataCommand());
    }

    @GetMapping("role/{id}")
    public RoleDto getRoleById(@RequestBody GetRolebyIdQuery query) {

        return getRoleByIdQueryHandler.handle(query);

    }

    @PostMapping("add-role")
    public void addRole(@RequestBody AddRoleCommand command) {
        addRoleCommandHandler.handle(command);
    }

    @PostMapping("user-role-management")
    public String userRoleManagement() {
        return "user-role-management";

    }

    @GetMapping("lookup-role")
    public LookupResponse<NameRelatedDto> lookupRole() {
        return getRoleLookupQueryHandler.handle(new GetRoleLookupQuery());
    }

    @GetMapping("lookup-user")
    public LookupResponse<NameRelatedDto> lookupUser() {
        return getUserLookupQueryHandler.handle(new GetUserLookupQuery());
    }

    @GetMapping("command-history")
    public List<CommandHistory> commandHistory() {
        return commandHistoryRepository.findAll();
    }

}
