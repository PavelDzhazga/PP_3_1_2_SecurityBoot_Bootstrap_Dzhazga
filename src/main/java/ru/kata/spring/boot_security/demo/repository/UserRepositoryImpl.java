package ru.kata.spring.boot_security.demo.repository;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.User;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepository{

    private final EntityManager entityManager;


    public UserRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void createUser(User user) {
        entityManager.merge(user);
    }


    @Override
    public User getUser(Long id) {
        TypedQuery<User> query = entityManager.createQuery("from User where id=:id", User.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    public List<User> getList() {
        TypedQuery<User> query = entityManager.createQuery("from User", User.class);
        return query.getResultList();
    }

    @Override
    public void updateUser(Long id, User user) {
        entityManager.joinTransaction();
        User u = getUser(id);
        u.setUsername(user.getUsername());
        u.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        u.setLastName(user.getLastName());
        u.setEmail(user.getEmail());
        entityManager.merge(u);
    }

    @Override
    public void deleteUser(Long id) {
        entityManager.createQuery("DELETE User WHERE id =: id")
                .setParameter("id", id)
                .executeUpdate();

    }

    @Override
    public void deleteUser(User user) {
        entityManager.joinTransaction();
        findUser(user).forEach(u -> entityManager.remove(u.getId()));
    }

    @Override
    public List<User> findUser(User user) {
        TypedQuery<User> query = entityManager.createQuery("from User where email=:eml", User.class);
        query.setParameter("eml", user.getEmail());
        return query.getResultList();
    }

    @Override
    public User getUserByUsername(String username) {
        return entityManager.createQuery("from User where email=:username", User.class)
                .setParameter("username", username).getSingleResult();
    }
}
