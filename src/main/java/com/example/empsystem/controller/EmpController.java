package com.example.empsystem.controller;

import com.example.empsystem.entity.Employee;
import com.example.empsystem.service.EmpService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@Controller
public class EmpController {

    @Autowired
    private EmpService service;

    @GetMapping("/")
    public String home( Model model) {
        // Remove 'msg' attribute from session when the home page is accessed
//        session.removeAttribute("msg");

        // Add any logic you need for the home page that requires the Model
        // For example, you can fetch data and add it to the model
        List<Employee> emp = service.getAllEmp();
        model.addAttribute("emp", emp);

        return "index";
    }

    //    @GetMapping("/")
//    public String home(Model m){
//
//
//
//        List<Employee> emp=service.getAllEmp();
//        m.addAttribute("emp",emp);
//        return "index";
//
//    }
    @GetMapping("/addemp")
    public String addEmpForm() {
        return "add_emp";
    }

    @PostMapping("/register")
    public String empRegister(@ModelAttribute Employee e, HttpSession session, Model model) {
        System.out.println(e);

        service.addEmp(e);
        session.setAttribute("msg", "Employee Added Successfully..");

        // Add the 'emp' attribute to the model for displaying employee data
        List<Employee> emp = service.getAllEmp();
        model.addAttribute("emp", emp);

        // Remove the 'msg' attribute from the session after displaying the message
        session.removeAttribute("msg");

        return "index";
    }


    @GetMapping("/edit/{id}")
    public String edit(@PathVariable int id, Model m) {
        Employee e = service.getEmpById(id);
        m.addAttribute("emp", e);
        return "edit";
    }

@PostMapping("/update")
    public String updateEmp(@ModelAttribute Employee e,HttpSession session) {
service.addEmp(e);
//session.setAttribute("msg","Emp Data Update Successfully..");
return"redirect:/";
    }
    @GetMapping("/delete/{id}")
    public String deleteEmp(@PathVariable int id, HttpSession session){
        service.deleteEmp(id);
        //session.setAttribute("msg","Emp Data Deleted Successfully..");
        return "redirect:/";

    }
}