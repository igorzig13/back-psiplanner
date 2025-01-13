package web2.dev.backpsiplanner.config.misc;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import web2.dev.backpsiplanner.model.Role;
import web2.dev.backpsiplanner.repository.RoleRepository;

@Component
public class DataInitializer implements CommandLineRunner {

    private final RoleRepository roleRepository;

    public DataInitializer(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        createRoleIfNotExists("ROLE_CLIENT");
        createRoleIfNotExists("ROLE_PROFESSIONAL");
        createRoleIfNotExists("ROLE_ENTERPRISE");
    }

    private void createRoleIfNotExists(String roleName) {
        if (roleRepository.findByName(roleName).isEmpty()) {
            Role role = new Role();
            role.setName(roleName);
            roleRepository.save(role);
        }
    }
}
