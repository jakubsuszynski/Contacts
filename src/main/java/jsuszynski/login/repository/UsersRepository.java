package jsuszynski.login.repository;

import jsuszynski.login.domain.User;

public interface UsersRepository {
    User findUserByLogin(String login);

    boolean doesExist(String login);

    void register(User user);
}
