/**
 * パッケージ名：org.authjdbc.entity
 * ファイル名  ：Employee.java
 * 
 * @author mbasa
 * @since Nov 9, 2020
 */
package org.authjdbc.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * 説明：
 *
 */
@Entity
public class Employee {
    @Id
    private int id;    
    private String name;
    private String address;
    private String email;

    public Employee() {
    }

    @Override
    public String toString() {
        return String.format("id: %d,name: %s,address: %s,email:%s", 
                this.id,this.name,
                this.address,this.email);
    }

    /**
     * @return id を取得する
     */
    public int getId() {
        return id;
    }

    /**
     * @param id id を設定する
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return name を取得する
     */
    public String getName() {
        return name;
    }

    /**
     * @param name name を設定する
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return address を取得する
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address address を設定する
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return email を取得する
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email email を設定する
     */
    public void setEmail(String email) {
        this.email = email;
    }

}
