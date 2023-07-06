package com.example.volopay.Spring.Assignment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;
    public void addData(User user)
    {
        User user1 = new User();

        user1.setDate(user.getDate());
        user1.setAmount(user.getAmount());
        user1.setSoftware(user.getSoftware());
        user1.setDepartment(user.getDepartment());
        user1.setSeats(user.getSeats());

        userRepository.save(user1);
    }

    public Integer getTotalItem(Date start_date, Date end_date, String department)
    {
        List<User> user = userRepository.findAll();

        int totalItem=0;
        for (User user1 : user)
        {
            if(user1.getDepartment().equals(department)  &&  user1.getDate().compareTo(start_date)>0 && user1.getDate().compareTo(end_date)<0)
            {
                totalItem++;
            }
        }
        return totalItem;

    }
}
