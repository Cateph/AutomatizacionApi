package com.tsoft.bot.backend.runner;
import com.tsoft.bot.backend.pages.pages.api.pages.GetResources.GetResourceTest;
import com.tsoft.bot.backend.pages.pages.api.pages.CreateUser.CreateUserTest;
import com.tsoft.bot.backend.pages.pages.api.pages.RegisterUser.RegisterUserTest;
import com.tsoft.bot.backend.pages.pages.api.pages.UpdateUser.UpdateUserTest;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

public class RunPrueba {

    @Test
    @Factory
    public Object[] RunTest(){

        return new Object[]{
                new GetResourceTest(), new CreateUserTest(), new RegisterUserTest(), new UpdateUserTest()
        };
    }

}
