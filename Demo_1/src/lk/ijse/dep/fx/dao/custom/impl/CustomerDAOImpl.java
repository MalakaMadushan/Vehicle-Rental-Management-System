package lk.ijse.dep.fx.dao.custom.impl;

import lk.ijse.dep.fx.dao.CrudUtill;
import lk.ijse.dep.fx.dao.custom.CustomerDAO;
import lk.ijse.dep.fx.entity.Customer;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CustomerDAOImpl implements CustomerDAO {
    @Override
    public Optional<Customer> find(String nic) throws Exception {
        ResultSet rst = CrudUtill.execute("SELECT * FROM Customer WHERE nic=?", nic);
        Customer c = null;
        while (rst.next()){
             c = new Customer(rst.getString(1), rst.getString(2),
                    rst.getString(3), rst.getString(4));
        }
        return Optional.ofNullable(c);
    }

    @Override
    public Optional<List<Customer>> findAll() throws Exception {
        ArrayList<Customer> alCustomer = new ArrayList<>();
        ResultSet rst = CrudUtill.execute("SELECT * FROM Customer");
        while (rst.next()){
            String nic = rst.getString(1);
            String name = rst.getString(2);
            String address = rst.getString(3);
            String telephone = rst.getString(4);
            Customer customer = new Customer(nic,name,address,telephone);
            alCustomer.add(customer);
        }
        return Optional.ofNullable(alCustomer);
    }

    @Override
    public boolean save(Customer customer) throws Exception {
       return CrudUtill.<Integer>execute("INSERT INTO Customer VALUES (?,?,?,?)",
               customer.getNic(),customer.getName(),customer.getAddress(),customer.getTelephone())>0;

    }

    @Override
    public boolean update(Customer customer) throws Exception {
        return CrudUtill.<Integer>execute("UPDATE Customer SET name=?,address=?,telephone=? WHERE nic=?",
                customer.getName(),customer.getAddress(),customer.getTelephone(),customer.getNic())>0;
    }

    @Override
    public boolean delete(String nic) throws Exception {
        return CrudUtill.<Integer>execute("DELETE FROM Customer WHERE nic=?",nic)>0;
    }
}
