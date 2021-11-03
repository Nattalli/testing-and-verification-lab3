package com.company;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashSet;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class MaxLengthTest {

    private FileSystemDataService service;
    private MaxLength world;


    @Mock
    FileSystemDataService mockDataService;

    @Spy
    private HashSet<String> oneworld = new HashSet<String>();;

//    @InjectMocks


    @Before
    public void Beggining() throws IOException {
        this.world = new MaxLength();
        this.service = Mockito.mock(FileSystemDataService.class);
        world.setService(service);
        Mockito.when(service.getData("1")).thenReturn("hello");
        Mockito.when(service.getData("2")).thenReturn("cats");
        Mockito.when(service.getData("incorrect_path")).thenThrow(new FileNotFoundException());
    }

    @Test
    public void FirstTest() throws IOException {
        var t = new HashSet<String>();
        t.add("hello");

        Assert.assertEquals(world.FileReading("1"), t);
    }

    @Test(expected = FileNotFoundException.class)
    public void ExceptionTest() throws IOException {
        var t = new HashSet<String>();
        t.add("test");
        Assert.assertEquals(world.FileReading("incorrect_path"), t);
    }

    @Test
    public void CallsNumber() throws IOException {
        world.FileReading("1");
        world.FileReading("1");
        verify(service, times(2)).getData("1");
    }

    @Test
    public void MockTest() throws IOException {
        world.setService(mockDataService);
        Mockito.when(mockDataService.getData("3")).thenReturn("1");
        var World = new HashSet<String>();
        World = world.FileReading("3");
        var expected = new HashSet<String>();
        expected.add("1");


        Assert.assertEquals(expected, World);
    }

    @Test
    public void SpyTest() throws IOException {
        oneworld.add("cats");

        Assert.assertEquals(world.FileReading("2"), oneworld);
    }
}