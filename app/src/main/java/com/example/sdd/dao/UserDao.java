package com.example.sdd.dao;

import com.example.sdd.dto.User;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class UserDao {

    private static DatabaseReference reference;
    private static UserDao userDao;
    private static User user = null;

    private UserDao() {
    }

    public static UserDao getInstance() {
        if (userDao == null) {
            userDao = new UserDao();
            FirebaseDatabase db = FirebaseDatabase.getInstance();
            reference = db.getReference("Users");
        }
        return userDao;
    }

    public Task<Void> add(User user) {
        return reference.child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .setValue(user);
    }

    public Task<Void> update(String key, HashMap<String, Object> values) {
        if (values.get("category") != null || !values.get("category").equals("")) {
            user.setCategory(values.get("category").toString());
        }
        if (values.get("name") != null) {
            user.setName(values.get("name").toString());
        }
        if(values.get("email") != null){
            user.setEmail(values.get("email").toString());
        }
        if(values.get("phoneNumber") != null){
            user.setPhoneNumber(values.get("phoneNumber").toString());
        }
        return reference.child(key).updateChildren(values);
    }

    public void setUser(User user) {
        UserDao.user = user;
    }

    public User getUser() {
        return user;
    }

}
