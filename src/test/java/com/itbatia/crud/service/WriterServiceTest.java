package com.itbatia.crud.service;

import com.itbatia.crud.molel.*;
import com.itbatia.crud.repository.WriterRepository;
import com.itbatia.crud.repository.database.DatabaseWriterRepositoryImpl;
import org.junit.*;
import org.mockito.Mock;

import java.util.*;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class WriterServiceTest {

    @Mock
    private final WriterRepository mock = mock(DatabaseWriterRepositoryImpl.class);

    private WriterService writerService;
    private String name;
    private List<Post> posts;

    @Before
    public void setUp() {
        name = "Anton";
        writerService = new WriterService(mock);
        posts = new ArrayList<>();
    }

    @Test
    public void createWriter() {
        Writer writer = new Writer(null, name, posts);
        Writer writerExpected = new Writer(1, name, posts);

        when(mock.save(writer)).thenReturn(writerExpected);
        Writer writerActual = writerService.createWriter(name, posts);

        verify(mock, times(1)).save(writer);
        assertEquals(writerExpected, writerActual);
    }

    @Test
    public void getWriter() {
        Writer writerExpected = new Writer(1, name, posts);

        when(mock.getById(1)).thenReturn(writerExpected);
        Writer writerActual = writerService.getWriter(1);

        verify(mock, atLeast(1)).getById(1);
        assertEquals(writerExpected, writerActual);
    }

    @Test
    public void getAllWriters() {
        List<Writer> writersExpected = new ArrayList<>();

        when(mock.getAll()).thenReturn(writersExpected);
        List<Writer> writersActual = writerService.getAllWriters();

        verify(mock, times(1)).getAll();
        assertEquals(writersExpected, writersActual);

    }

    @Test
    public void updateWriter() {
        Writer writerExpected = new Writer(1, name, posts);

        when(mock.update(writerExpected)).thenReturn(writerExpected);
        Writer writerActual = writerService.updateWriter(writerExpected);

        verify(mock, times(1)).update(writerExpected);
        assertEquals(writerExpected, writerActual);
    }

    @Test
    public void deleteWriter() {

        writerService.deleteWriter(1);
        verify(mock).deleteById(1);
    }
}