package eu.lsmyrak.invisiblesoftware.Devicegateway.api.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import eu.lsmyrak.invisiblesoftware.Devicegateway.CQRS.CommandBus;
import eu.lsmyrak.invisiblesoftware.Devicegateway.CQRS.QueryBus;
import eu.lsmyrak.invisiblesoftware.Devicegateway.application.setting.command.AddRoleCommand;
import eu.lsmyrak.invisiblesoftware.Devicegateway.application.setting.command.EditUserCommand;
import eu.lsmyrak.invisiblesoftware.Devicegateway.application.setting.command.SeedDataCommand;
import eu.lsmyrak.invisiblesoftware.Devicegateway.application.setting.queries.GetRoleLookupQuery;
import eu.lsmyrak.invisiblesoftware.Devicegateway.application.setting.queries.GetRolebyIdQuery;
import eu.lsmyrak.invisiblesoftware.Devicegateway.application.setting.queries.GetUserLookupQuery;
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
    private final CommandBus commandBus;
    private final QueryBus queryBus;

    public SettingController(CommandHistoryRepository commandHistoryRepository, CommandBus commandBus,
            QueryBus queryBus) {
        this.commandHistoryRepository = commandHistoryRepository;
        this.commandBus = commandBus;
        this.queryBus = queryBus;
    }

    @PostMapping("seed-data")
    public void seedData() {
        commandBus.execute(new SeedDataCommand());
    }

    @GetMapping("role/{id}")
    public RoleDto getRoleById(@RequestBody GetRolebyIdQuery query) {

        return queryBus.execute(query);
    }

    @PostMapping("add-role")
    public void addRole(@RequestBody AddRoleCommand command) {
        commandBus.execute(command);
    }

    @PostMapping("user-role-management")
    public Void userRoleManagement(@RequestBody EditUserCommand command) {
       return commandBus.execute(command);       
    }

    @GetMapping("lookup-role")
    public LookupResponse<NameRelatedDto> lookupRole() {
        return queryBus.execute(new GetRoleLookupQuery());
    }

    @GetMapping("lookup-user")
    public LookupResponse<NameRelatedDto> lookupUser() {
        return queryBus.execute(new GetUserLookupQuery());
    }

    @GetMapping("command-history")
    public List<CommandHistory> commandHistory() {
        return commandHistoryRepository.findAll();
    }

}
