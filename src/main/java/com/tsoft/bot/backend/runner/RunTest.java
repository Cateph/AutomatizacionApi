package com.tsoft.bot.backend.runner;
import com.tsoft.bot.backend.test.GetResourceTest;
import com.tsoft.bot.backend.test.CreateUserTest;
import com.tsoft.bot.backend.test.RegisterUserTest;
import com.tsoft.bot.backend.test.UpdateUserTest;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

public class RunTest {

    @Test
    @Factory
    public Object[] RunTest(){

        return new Object[]{
                new GetResourceTest(), new CreateUserTest(), new RegisterUserTest(), new UpdateUserTest()
        };
    }

}
