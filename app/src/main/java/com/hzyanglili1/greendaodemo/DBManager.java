package com.hzyanglili1.greendaodemo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.hzyanglili1.greendaodemo.entity.User;
import com.hzyanglili1.greendaodemo.gen.DaoMaster;
import com.hzyanglili1.greendaodemo.gen.DaoSession;
import com.hzyanglili1.greendaodemo.gen.UserDao;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

/**
 * Created by hzyanglili1 on 2016/11/24.
 */

public class DBManager {

    private Context context;
    private DaoMaster.DevOpenHelper openHelper;
    private static DBManager instance;

    private final static String dbName = "test_db";

    private DBManager(Context context) {
        this.context = context;
        openHelper = new DaoMaster.DevOpenHelper(context,dbName);
    }

    /**
     * 获取单例引用
     * @param context
     * @return
     */
    public static DBManager getInstance(Context context){
        if (instance == null){
            synchronized (DBManager.class){
                if (instance == null){
                    instance = new DBManager(context);
                }
            }
        }

        return instance;
    }

    /**
     * 获取可读数据库
     * @return
     */
    private SQLiteDatabase getReadableDatabase(){
        if (openHelper == null){
            openHelper = new DaoMaster.DevOpenHelper(context,dbName);
        }

        SQLiteDatabase db = openHelper.getReadableDatabase();
        return db;
    }

    /**
     * 获取可写数据库
     * @return
     */
    private SQLiteDatabase getWritableDatabase(){
        if (openHelper == null){
            openHelper = new DaoMaster.DevOpenHelper(context,dbName);
        }

        SQLiteDatabase db = openHelper.getWritableDatabase();
        return db;
    }

    /**
     * 插入一条记录
     * @param user
     */

    public void insertUser(User user){
        DaoMaster daoMaster = new DaoMaster(getWritableDatabase());
        UserDao userDao = daoMaster.newSession().getUserDao();
        userDao.insert(user);
    }

    /**
     * 插入用户集合
     * @param users
     */
    public void insertUserList(List<User> users){
        if (users == null || users.isEmpty())  return;

        DaoMaster daoMaster = new DaoMaster(getWritableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        UserDao userDao = daoSession.getUserDao();
        userDao.insertInTx(users);
    }

    /**
     * 删除一条记录
     * @param user
     */
    public void deleteUser(User user){
        DaoMaster daoMaster = new DaoMaster(getWritableDatabase());
        UserDao userDao = daoMaster.newSession().getUserDao();
        userDao.delete(user);
    }

    /**
     * 更新一条数据
     * @param user
     */
    public void updateUser(User user){
        DaoMaster daoMaster = new DaoMaster(getWritableDatabase());
        daoMaster.newSession().getUserDao().update(user);
    }

    /**
     * 查询用户列表
     * @return
     */
    public List<User> queryUserlist(){

        DaoMaster daoMaster = new DaoMaster(getReadableDatabase());

        UserDao userDao = daoMaster.newSession().getUserDao();

        QueryBuilder<User> queryBuilder = userDao.queryBuilder();
        return queryBuilder.list();
    }


    public List<User> queryUserList(int age){
        DaoMaster daoMaster = new DaoMaster(getReadableDatabase());

        UserDao userDao = daoMaster.newSession().getUserDao();

        QueryBuilder<User> queryBuilder = userDao.queryBuilder();
        queryBuilder.where(UserDao.Properties.Age.gt(age)).orderAsc(UserDao.Properties.Age);

        List<User> list = queryBuilder.list();

        return list;
    }

    public User queryUserById(long id){

        DaoMaster daoMaster = new DaoMaster(getReadableDatabase());

        UserDao userDao = daoMaster.newSession().getUserDao();

        QueryBuilder<User> queryBuilder = userDao.queryBuilder();
        queryBuilder.where(UserDao.Properties.Id.eq(id));
        return queryBuilder.unique();
    }
}
