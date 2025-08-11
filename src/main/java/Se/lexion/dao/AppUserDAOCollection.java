package Se.lexion.dao;

import Se.lexion.AppUser;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class AppUserDAOCollection implements AppUserDAO {
    private List<AppUser> appUsers;

    public AppUserDAOCollection() {
        this.appUsers = new ArrayList<>();
    }

    @Override
    public AppUser persist(AppUser appUser) {
        if (appUser == null) {
            throw new IllegalArgumentException("AppUser cannot be null");
        }
        // Check if username already exists
        if (findByUsername(appUser.getUsername()) != null) {
            throw new IllegalArgumentException("Username already exists");
        }
        appUsers.add(appUser);
        return appUser;
    }

    @Override
    public AppUser findByUsername(String username) {
        if (username == null) {
            return null;
        }
        return appUsers.stream()
                .filter(user -> username.equals(user.getUsername()))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Collection<AppUser> findAll() {
        return new ArrayList<>(appUsers);
    }

    @Override
    public void remove(String username) {
        if (username == null) {
            return;
        }
        appUsers.removeIf(user -> username.equals(user.getUsername()));
    }
}
