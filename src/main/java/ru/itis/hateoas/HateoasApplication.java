package ru.itis.hateoas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.itis.hateoas.forms.ClientForm;
import ru.itis.hateoas.forms.WorkForm;
import ru.itis.hateoas.mappers.ClientsMapper;
import ru.itis.hateoas.mappers.UsersMapper;
import ru.itis.hateoas.mappers.WorksMapper;
import ru.itis.hateoas.models.Client;
import ru.itis.hateoas.models.User;
import ru.itis.hateoas.models.Work;
import ru.itis.hateoas.models.embedded.Item;
import ru.itis.hateoas.models.embedded.Tool;
import ru.itis.hateoas.models.enums.Role;
import ru.itis.hateoas.repositories.ClientsRepository;
import ru.itis.hateoas.repositories.UsersRepository;
import ru.itis.hateoas.repositories.WorksRepository;
import ru.itis.hateoas.services.ClientsService;
import ru.itis.hateoas.services.UsersService;
import ru.itis.hateoas.services.WorksService;
import ru.itis.hateoas.services.impl.ClientsServiceImpl;
import ru.itis.hateoas.services.impl.UsersServiceImpl;
import ru.itis.hateoas.services.impl.WorksServiceImpl;

import java.util.List;

@SpringBootApplication
public class HateoasApplication {

    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(HateoasApplication.class, args);

        ClientsRepository clientsRepository = applicationContext.getBean(ClientsRepository.class);
        UsersRepository usersRepository = applicationContext.getBean(UsersRepository.class);
        WorksRepository worksRepository = applicationContext.getBean(WorksRepository.class);

        ClientsMapper clientsMapper = applicationContext.getBean(ClientsMapper.class);
        UsersMapper usersMapper = applicationContext.getBean(UsersMapper.class);
        WorksMapper worksMapper = applicationContext.getBean(WorksMapper.class);

        PasswordEncoder encoder = new BCryptPasswordEncoder();

        ClientsService clientsService = new ClientsServiceImpl(clientsRepository, clientsMapper);
        UsersService usersService = new UsersServiceImpl(usersRepository, encoder, usersMapper);
        WorksService worksService = new WorksServiceImpl(worksRepository, worksMapper, clientsService);

        Client client1 = clientsService.newClient(new ClientForm("Client", "One"));
        Client client2 = clientsService.newClient(new ClientForm("Client", "Two"));
        Client client3 = clientsService.newClient(new ClientForm("Client", "Three"));

        User user1 = usersService.save(User.builder()
                .username("User1")
                .password(encoder.encode("password"))
                .role(Role.WORKER)
                .build());
        User user2 = usersService.save(User.builder()
                .username("User2")
                .password(encoder.encode("password"))
                .role(Role.WORKER)
                .build());
        User user3 = usersService.save(User.builder()
                .username("User3")
                .password(encoder.encode("password"))
                .role(Role.MANAGER)
                .build());

        Work work1 = worksService.newWork(WorkForm.builder()
                .carMark("Toyota")
                .carNumber("A111AA")
                .client(client1.getId())
                .tools(List.of(Tool.builder()
                        .name("Пасатижи")
                        .build(),

                        Tool.builder()
                        .name("Отвертка")
                        .build(),

                        Tool.builder()
                        .name("Болты")
                        .build()))
                .items(List.of(Item.builder()
                        .name("Бампер")
                        .price(123.0)
                        .build(),

                        Item.builder()
                        .name("Крыло")
                        .price(56.6)
                        .build(),

                        Item.builder()
                        .name("Фара")
                        .price(123.21)
                        .build()))
                .workCost(120.0)
                .description("Car repairing")
                .build(), user1);

        Work work2 = worksService.newWork(WorkForm.builder()
                .carMark("BMW")
                .carNumber("O777OO")
                .client(client2.getId())
                .tools(List.of(Tool.builder()
                        .name("Плоскогубцы")
                        .build(),

                        Tool.builder()
                        .name("Отвертка")
                        .build(),

                        Tool.builder()
                        .name("Болты")
                        .build()))
                .items(List.of(Item.builder()
                        .name("Бампер")
                        .price(123.0)
                        .build(),

                        Item.builder()
                        .name("Крыло")
                        .price(56.6)
                        .build(),

                        Item.builder()
                        .name("Фара")
                        .price(123.21)
                        .build()))
                .workCost(120.0)
                .description("Car repairing")
                .build(), user2);
    }

}
