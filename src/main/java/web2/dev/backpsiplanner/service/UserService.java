package web2.dev.backpsiplanner.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import web2.dev.backpsiplanner.dto.ClientRegisterDTO;
import web2.dev.backpsiplanner.model.Client;
import web2.dev.backpsiplanner.model.Person;
import web2.dev.backpsiplanner.model.User;
import web2.dev.backpsiplanner.repository.ClientRepository;
import web2.dev.backpsiplanner.repository.PersonRepository;
import web2.dev.backpsiplanner.repository.RoleRepository;
import web2.dev.backpsiplanner.repository.UserRepository;

import java.util.HashSet;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final ClientRepository clientRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final PersonRepository personRepository;


    public UserService(UserRepository userRepository, ClientRepository clientRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository, PersonRepository personRepository) {
        this.userRepository = userRepository;
        this.clientRepository = clientRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
        this.personRepository = personRepository;
    }

    public void createClientUser(ClientRegisterDTO clientRegisterDTO){
        User user = new User();
        Client client = new Client();
        Person person;

        user.setUsername(clientRegisterDTO.getUsername());
        user.setPassword(passwordEncoder.encode(clientRegisterDTO.getPassword()));
        user.setEmail(clientRegisterDTO.getEmail());
        user.setRoles(new HashSet<>());
        user.getRoles().add(roleRepository.findByName("ROLE_CLIENT").orElseThrow(() -> new RuntimeException("ROLE_CLIENT NOT FOUND")));

        Optional<Person> personOptional = personRepository.findByCpf(clientRegisterDTO.getCpf());
        if (personOptional.isPresent()) { // Person already registered in the system
            person = personOptional.get();
        } else {
            person = new Person();
            person.setFirstName(clientRegisterDTO.getFirstName());
            person.setLastName(clientRegisterDTO.getLastName());
            person.setCpf(clientRegisterDTO.getCpf());
            person.setPhoneNumber(clientRegisterDTO.getPhoneNumber());
            person.setGender(clientRegisterDTO.getGender());
            personRepository.save(person);
        }

        user.setLegalOrNaturalPerson(person);
        client.setPerson(person);

        userRepository.save(user);
        clientRepository.save(client);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username);
    }
}
