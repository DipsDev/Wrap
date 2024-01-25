package test.server.commands;

import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.Assert;
import test.server.TestServer;

import java.io.*;


public class TestSetCommand {



    public TestSetCommand() throws IOException {
    }

    @Test
    public void shouldBeAbleToSetAndGet() throws IOException {
        Assert.assertEquals(TestServer.exec("set version \"1.24-alpha\""), "OK");
        Assert.assertEquals(TestServer.exec("get version"), "1.24-alpha");
    }

    @Test
    public void shouldWorkWithSpaces() throws IOException {
        Assert.assertEquals(TestServer.exec("set name \"Ido Geva\""), "OK");
        Assert.assertEquals(TestServer.exec("get name"), "Ido Geva");
    }

    @Test
    public void shouldWorkWithNull() throws IOException {
        Assert.assertEquals(TestServer.exec("get thisdoesnotexist"), "null");
    }

}
