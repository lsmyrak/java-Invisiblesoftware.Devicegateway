package eu.lsmyrak.invisiblesoftware.Devicegateway.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/settings")
public class SettingController {

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

}
