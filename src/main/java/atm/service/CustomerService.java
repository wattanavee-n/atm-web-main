package atm.service;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import atm.model.Customer;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {

    private final ArrayList<Customer> customerList = new ArrayList<>();

    public void createCustomer(Customer customer) {
        String hashPin = hash(customer.getPin());
        customer.setPin(hashPin);
        customerList.add(customer);
    }

    public List<Customer> getCustomers() {
        return new ArrayList<>(customerList);
    }

    public Customer findCustomer(int id) {
        for (Customer customer : customerList) {
            if (customer.getId() == id)
                return customer;
        }
        return null;
    }

    public Customer checkPin(Customer inputCustomer) {
        // * step 1 check customer contain InputParameter
        Customer storedCustomer = findCustomer(inputCustomer.getId());

        // * step 2 if any check pin โดยใช้ฟังก์ชันเกี่ยวกับ hash
        if (storedCustomer != null) {
            String hashPin = storedCustomer.getPin();

            if (BCrypt.checkpw(inputCustomer.getPin(), hashPin))
                return storedCustomer;
        }
        // * step 3 ถ้าไม่ตรง ต้องคืนค่า null
        return null;
    }

    private String hash(String pin) {
        String salt = BCrypt.gensalt(12);
        return BCrypt.hashpw(pin, salt);
    }
}

