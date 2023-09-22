package kz.job4j.cars.repository;

import kz.job4j.cars.models.User;
import kz.job4j.cars.repository.impl.HibernateUserRepository;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class UserUsage {

    public static void main(String[] args) {

        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        try (SessionFactory sf = new MetadataSources(registry)
                .buildMetadata().buildSessionFactory()) {
            var user = new User();
            user.setLogin("admin");
            user.setPassword("admin");
            var userRepository = new HibernateUserRepository(sf);
            userRepository.create(user);
            userRepository.findAllOrderById()
                    .forEach(System.out::println);
            userRepository.findByLikeLogin("e")
                    .forEach(System.out::println);
            userRepository.findById(user.getId())
                    .ifPresent(System.out::println);
            userRepository.findByLogin("admin")
                    .ifPresent(System.out::println);
            user.setPassword("password");
            userRepository.update(user);
            userRepository.findById(user.getId())
                    .ifPresent(System.out::println);
            userRepository.delete(user.getId());
            userRepository.findAllOrderById()
                    .forEach(System.out::println);
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}