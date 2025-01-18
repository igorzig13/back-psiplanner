package web2.dev.backpsiplanner.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import web2.dev.backpsiplanner.dto.ClinicRegisterDTO;
import web2.dev.backpsiplanner.dto.PersonRegisterDTO;
import web2.dev.backpsiplanner.dto.ProfessionalRegisterDTO;
import web2.dev.backpsiplanner.model.*;
import web2.dev.backpsiplanner.repository.*;

import java.util.HashSet;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final ClientRepository clientRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final PersonRepository personRepository;
    private final ProfessionalRepository professionalRepository;
    private final ClinicRepository clinicRepository;


    public UserService(UserRepository userRepository, ClientRepository clientRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository, PersonRepository personRepository, ProfessionalRepository professionalRepository, ClinicRepository clinicRepository) {
        this.userRepository = userRepository;
        this.clientRepository = clientRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
        this.personRepository = personRepository;
        this.professionalRepository = professionalRepository;
        this.clinicRepository = clinicRepository;
    }

    public void createClientUser(PersonRegisterDTO personRegisterDTO){
        User user = new User();
        Client client = new Client();

        user.setUsername(personRegisterDTO.getUsername());
        user.setPassword(passwordEncoder.encode(personRegisterDTO.getPassword()));
        user.setEmail(personRegisterDTO.getEmail());
        user.setRoles(new HashSet<>());
        user.getRoles().add(roleRepository.findByName("ROLE_CLIENT").orElseThrow(() -> new RuntimeException("ROLE 'CLIENT' NOT FOUND")));

        Person person = findPersonByDTO(personRegisterDTO);
        if (person.getId() == null || person.getId() == 0){
            personRepository.save(person);
        }

        user.setLegalOrNaturalPerson(person);
        client.setPerson(person);

        userRepository.save(user);
        clientRepository.save(client);
    }

    public void createProfessionalUser(ProfessionalRegisterDTO professionalRegisterDTO){
        User user = new User();
        Professional professional = new Professional();

        user.setUsername(professionalRegisterDTO.getUsername());
        user.setPassword(passwordEncoder.encode(professionalRegisterDTO.getPassword()));
        user.setEmail(professionalRegisterDTO.getEmail());
        user.setRoles(new HashSet<>());
        user.getRoles().add(roleRepository.findByName("ROLE_PROFESSIONAL").orElseThrow(() -> new RuntimeException("ROLE 'PROFESSIONAL' NOT FOUND")));

        Person person = findPersonByDTO(professionalRegisterDTO);
        if (person.getId() == null || person.getId() == 0){
            personRepository.save(person);
        }

        user.setLegalOrNaturalPerson(person);
        professional.setPerson(person);
        professional.setCrp(professionalRegisterDTO.getCrp());
        professional.setApproach(professionalRegisterDTO.getApproach());
        professional.setDescription(professionalRegisterDTO.getDescription());
        professional.setLocation(professionalRegisterDTO.getLocation());

        userRepository.save(user);
        professionalRepository.save(professional);

    }

    public void createClinicUser(ClinicRegisterDTO clinicRegisterDTO){
        User user = new User();
        Clinic clinic = new Clinic();

        user.setUsername(clinicRegisterDTO.getUsername());
        user.setPassword(passwordEncoder.encode(clinicRegisterDTO.getPassword()));
        user.setEmail(clinicRegisterDTO.getEmail());
        user.setRoles(new HashSet<>());
        user.getRoles().add(roleRepository.findByName("ROLE_CLINIC").orElseThrow(() -> new RuntimeException("ROLE 'CLINIC' NOT FOUND")));

        clinic.setName(clinicRegisterDTO.getClinicName());
        clinic.setCnpj(clinicRegisterDTO.getCnpj());
        clinic.setPhoneNumber(clinicRegisterDTO.getPhoneNumber());
        clinic.setDescription(clinicRegisterDTO.getDescription());
        clinic.setLocation(clinicRegisterDTO.getLocation());

        user.setLegalOrNaturalPerson(clinic);
        userRepository.save(user);
        clinicRepository.save(clinic);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow();
    }

    private Person findPersonByDTO(PersonRegisterDTO personRegisterDTO) {
        Optional<Person> personOptional = personRepository.findByCpf(personRegisterDTO.getCpf());
        if (personOptional.isPresent()) { // Person already registered in the system
            return personOptional.get();
        } else {
            Person person = new Person();
            person.setFirstName(personRegisterDTO.getFirstName());
            person.setLastName(personRegisterDTO.getLastName());
            person.setCpf(personRegisterDTO.getCpf());
            person.setPhoneNumber(personRegisterDTO.getPhoneNumber());
            person.setGender(personRegisterDTO.getGender());
            return person;
        }
    }
}
