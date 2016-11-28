package com.hzyanglili1.greendaodemo.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Transient;
import org.greenrobot.greendao.annotation.Generated;

import static com.hzyanglili1.greendaodemo.gen.UserDao.Properties.Id;

/**
 * 实体类中使用greendao的注解可以把对应的实体类生成同名的表，同时生成相关操作类和getter setter方法（因此定义类时不需要写）
 * Created by hzyanglili1 on 2016/11/24.
 */
@Entity
public class User {
    //@Id主键id   可设置是否自增（注意这里的主键id一定是Long类型）
    @Id(autoincrement = true)
    private Long id;

    //@Property中可设置数据库中列的名称  否则为默认
    //@Index设置为索引列（当有大量查询操作时设置索引列加速查询，但同时也会有额外的空间消耗）
    @Index(unique = false)
    @Property(nameInDb = "USERNAME")
    private String name;

    //设置此属性非空

    @NotNull
    private int age;

    //@Transient 表示数据库中不生成此列
    @Transient
    private String info;

    @Generated(hash = 1309193360)
    public User(Long id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    @Generated(hash = 586692638)
    public User() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Id = "+getId()+" Name = "+getName()+" Age = "+getAge();
    }
}
