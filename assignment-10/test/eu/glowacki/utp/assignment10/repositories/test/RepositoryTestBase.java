package eu.glowacki.utp.assignment10.repositories.test;

import eu.glowacki.utp.assignment10.dtos.DTOBase;
import eu.glowacki.utp.assignment10.repositories.IRepository;
import org.junit.After;
import org.junit.Before;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class RepositoryTestBase<TDTO extends DTOBase, TRepository extends IRepository<TDTO>> {
    private static final String _URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String _USER = "mark";
    private static final String _PASSWORD = "\\\\\\\\";
    public static Connection connect;

    public TRepository _repository;

    static <TType> Method method(Class<TType> cls, String name, Class<?>... parameters){
        try {
            var method = cls.getDeclaredMethod(name, parameters);
            method.setAccessible(true);
            return method;
        } catch (Throwable exception) {
            exception.printStackTrace();
        }
        return null;
    }

    @Before
    public void before() {
        try {
            connect = DriverManager.getConnection(_URL, _USER, _PASSWORD);
            connect.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        _repository = Create();
        if (_repository != null) {
            _repository.beginTransaction();
        }
    }

    @After
    public void after() {

        if (_repository != null) {
            _repository.rollbackTransaction();
            _repository.closeConnection();
        }
    }

    protected abstract TRepository Create();
}