package app;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

class User {
    private int id;
    private String name;
    private String email;

    public User(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}

class UserRepository {
    private List<User> users;

    public UserRepository() {
        this.users = new ArrayList<>();
    }

    public void addUser(User user) {
        users.add(user);
    }

    public Optional<User> findUserById(int id) {
        return users.stream()
                .filter(user -> user.getId() == id)
                .findFirst();
    }

    public Optional<User> findUserByEmail(String email) {
        return users.stream()
                .filter(user -> user.getEmail().equalsIgnoreCase(email))
                .findFirst();
    }

    public Optional<List<User>> findAllUsers() {
        return users.isEmpty() ? Optional.empty() : Optional.of(users);
    }
}

public class Main {
    public static void main(String[] args) {
        UserRepository userRepository = new UserRepository();
        userRepository.addUser(new User(1, "Maria", "maria@example.com"));
        userRepository.addUser(new User(2, "Ivan", "ivan@example.com"));
        userRepository.addUser(new User(3, "Olena", "olena@example.com"));

        int searchId = 1;
        Optional<User> userById = userRepository.findUserById(searchId);
        if (userById.isPresent()) {
            System.out.println("User found: " + userById.get());
        } else {
            System.out.println("User with ID " + searchId + " not found.");
        }

        String searchEmail = "ivan@example.com";
        Optional<User> userByEmail = userRepository.findUserByEmail(searchEmail);
        userByEmail.ifPresent(user -> System.out.println("User found: " + user));
        if (!userByEmail.isPresent()) {
            System.out.println("User with email " + searchEmail + " not found.");
        }

        Optional<List<User>> allUsers = userRepository.findAllUsers();
        if (allUsers.isPresent()) {
            System.out.println("Total users: " + allUsers.get().size());
            allUsers.get().forEach(System.out::println);
        } else {
            System.out.println("No users found.");
        }
    }
}
