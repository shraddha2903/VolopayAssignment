package com.example.volopay.Spring.Assignment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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

    public String getNthMostSoldItem(String itemBY, Date start_date, Date end_date)
    {
        HashMap<String,Integer> itemSoldFreq = new HashMap<>();

        List<User> userList = userRepository.findAll();

        if(itemBY.equals("quantity"))
        {
            for (User user1 : userList)
            {
                if (user1.getDate().compareTo(start_date) > 0 && user1.getDate().compareTo(end_date) < 0) {
                    String softwareName = user1.getSoftware();
                    int seats = user1.getSeats();
                    itemSoldFreq.put(softwareName, itemSoldFreq.getOrDefault(softwareName, 0) + seats);
                }
            }

            //sorting in descending order
            List<Map.Entry<String, Integer>> sortedItems = new ArrayList<>(itemSoldFreq.entrySet());
            sortedItems.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));

            // 2nd most sold item in terms of quantity
            return sortedItems.get(1).getKey();
        }

        //else

        for (User user1 : userList)
        {
            if (user1.getDate().compareTo(start_date) > 0 && user1.getDate().compareTo(end_date) < 0) {
                String softwareName = user1.getSoftware();
                int amount = user1.getAmount();
                itemSoldFreq.put(softwareName, itemSoldFreq.getOrDefault(softwareName, 0) + amount);
            }
        }

        //sorting in descending order
        List<Map.Entry<String, Integer>> sortedItems = new ArrayList<>(itemSoldFreq.entrySet());
        sortedItems.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));

        // e fourth most sold item in terms of Total price
        return sortedItems.get(3).getKey();
    }

    public HashMap<String,Double> getDepatmentwiseSoldItemPerc(Date start_date, Date end_date)
    {
        List<User> user = userRepository.findAll();

        HashMap<String,Integer> depMap = new HashMap<>();
        int totalSeats=0;

        for (User u : user)
        {
            if(u.getDate().compareTo(start_date) > 0 && u.getDate().compareTo(end_date) < 0)
            {
                depMap.put(u.getDepartment(),depMap.getOrDefault(u.getDepartment(),0)+u.getSeats());
                totalSeats+=u.getSeats();
            }
        }

        HashMap<String,Double> ans = new HashMap<>();

        for (Map.Entry<String,Integer> hm : depMap.entrySet())
        {
            Double perc = (hm.getValue()/totalSeats)*100.0;
            ans.put(hm.getKey(),perc);
        }
        return ans;
    }

    public HashMap<Integer,Double> getMonthlySale(String year)
    {
        HashMap<Integer,Double> sale = new HashMap<>();
        List<User> user = userRepository.findAll();
        for(User u : user)
        {
            String u_year = String.valueOf(u.getDate().getYear());
            if(u_year.equals(year))
            {
                int month = u.getDate().getMonth();
                sale.put(month,sale.getOrDefault(month,0.0)+u.getAmount());
            }
        }
        return sale;
    }
}
