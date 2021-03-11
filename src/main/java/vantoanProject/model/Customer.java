package vantoanProject.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty
    @Size(min = 2, max = 30, message = "nam is invalid")
    private String name;
    @NotEmpty
    @Pattern(regexp="(^$|[0-9]{10})", message = "phone is invalid")
    @Column(unique = true)
    private String phone;
    @ManyToOne
    private Province province;

    public Customer() {
    }

    public Customer(String name, String phone, Province province) {
        this.name = name;
        this.phone = phone;
        this.province = province;
    }

    public Customer(Long id, String name, String phone, Province province) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.province = province;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Province getProvince() {
        return province;
    }

    public void setProvince(Province province) {
        this.province = province;
    }
}
