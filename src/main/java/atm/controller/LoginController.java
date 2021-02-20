package atm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import atm.model.Customer;
import atm.service.CustomerService;

@Controller
@RequestMapping("/login")
public class LoginController {

    private CustomerService customerService;

    public LoginController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public String getLoginPage() {
        return "login";   // * return login.html
    }

    @PostMapping
    public String login(@ModelAttribute Customer customer, Model model) {
        // * step 1 เอา id กับ pin ไปเช็คกับข้อมูล customer ที่มีอยู่ ว่าตรงกันบ้างไหม
        Customer matchingCustomer = customerService.checkPin(customer);

        // * step 2 ถ้าตรง ส่งข้อมูล customer กลับไปแสดงผล
        if (matchingCustomer != null) {
            model.addAttribute("greeting",
                    "Welcome, " + matchingCustomer.getName());
        } else {
            // * step 3 ถ้าไม่ตรง แจ้งว่าไม่มีข้อมูล customer นี้
            model.addAttribute("greeting", "Cannot find customer");
        }
        return "home";
    }

}
