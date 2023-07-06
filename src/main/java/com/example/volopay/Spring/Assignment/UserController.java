package com.example.volopay.Spring.Assignment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    UserService userService;

    //adding data into database
    @PostMapping("/add-data")
    public String addData(@RequestBody User user)
    {
        userService.addData(user);
        return "User added successfully";
    }

    //API 1
    @GetMapping("/total_items")
    public Integer getTotalItems(@RequestParam Date start_date,@RequestParam Date end_date, @RequestParam String department)
    {
        return userService.getTotalItem(start_date,end_date,department);
    }

    //API 2

    @GetMapping("//nth_most_total_item")
    public String getNthMostSoldItem(@RequestParam String itemBY,@RequestParam Date start_date,@RequestParam Date end_date)
    {
        return userService.getNthMostSoldItem(itemBY,start_date,end_date);
    }

    //API 3
    @GetMapping("/percentage_of_department_wise_sold_items")
    public HashMap<String,Double> getDepatmentwiseSolditemPerc(@RequestParam Date start_date, @RequestParam Date end_date)
    {
        return userService.getDepatmentwiseSoldItemPerc(start_date,end_date);
    }


}
