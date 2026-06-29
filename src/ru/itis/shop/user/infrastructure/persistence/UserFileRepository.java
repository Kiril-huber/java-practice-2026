package ru.itis.shop.user.infrastructure.persistence;

import ru.itis.shop.user.domain.User;
import ru.itis.shop.user.repository.UserRepository;

import java.io.*;
import java.util.UUID;

public class UserFileRepository implements UserRepository {

    private final String fileName;

    public UserFileRepository(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void save(User user) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            String id = UUID.randomUUID().toString();
            user.setId(id);
            writer.write(user.getId() + "|" +
                    user.getEmail() + "|" +
                    user.getPassword() + "|" +
                    user.getProfileDescription());
            writer.newLine();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public User findById(String id) {
        try(BufferedReader reader = new BufferedReader(new FileReader(fileName))){
            String userInfo;
            while((userInfo = reader.readLine()) != null){
                String [] arrayUserInfo = userInfo.split("\\|");
                String userId = arrayUserInfo[0];
                if(id.equals(userId)){
                    return new User(userId,arrayUserInfo[1],arrayUserInfo[2],arrayUserInfo[3]);
                }
            }
            return null;
        }catch (IOException e){
            throw new IllegalStateException(e);
        }
    }
}
