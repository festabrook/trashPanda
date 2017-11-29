package com.ford.rocket;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.logging.Logger;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TrashPandaTest {

    private TrashRequest trashRequest;
    private FoxResponse foxResponse;
    private BearRequest bearRequest;


    @Mock
    private Fox fox;
    @Mock
    private Bear bear;
    @Mock
    private Logger logger;
    @Mock
    private Ranger rangerJoe;

    @InjectMocks
    private TrashPanda trashPanda;

    @Before
    public void setUp() {
        trashRequest = new TrashRequest();
        foxResponse = new FoxResponse();
        bearRequest = new BearRequest();
        when(fox.process(trashRequest)).thenReturn(foxResponse);
    }

    @Test
    public void passesTrashRequestToFox() throws Exception {

        trashPanda.accept(trashRequest);

        verify(fox).process(trashRequest);
    }

    @Test
    public void logsErrorFromFoxResponse() throws Exception {
        foxResponse.setStatus(403);

        trashPanda.accept(trashRequest);

        verify(logger).severe("Oops! The Fox couldn't handle the Trash.");
    }

    @Test
    public void logsSuccessFromFoxResponse() throws Exception {
        foxResponse.setStatus(201);
        foxResponse.setBody(42L);

        trashPanda.accept(trashRequest);

        verify(logger).info("Mr Fox hooked it up! We got: 42");
    }

    @Test
    public void returnsErrorStatusCodeFromFoxResponse() throws Exception {
        foxResponse.setStatus(503);

        assertThat(trashPanda.accept(trashRequest)).isEqualTo(503);
    }

    @Test
    public void buildBearRequestFromFoxResponseAndTrashRequest() throws Exception {
        foxResponse.setStatus(201);

        trashPanda.accept(trashRequest);

        verify(rangerJoe).prepareForBear(trashRequest, foxResponse);
    }

    @Test
    public void sendsBearRequestFromRangerJoeToBear() throws Exception {
        foxResponse.setStatus(201);
        when(rangerJoe.prepareForBear(trashRequest, foxResponse)).thenReturn(bearRequest);

        trashPanda.accept(trashRequest);

        verify(bear).consume(bearRequest);
    }

    @Test
    public void returnsStatusCodeFromBear() throws Exception {
        foxResponse.setStatus(201);
        when(rangerJoe.prepareForBear(trashRequest, foxResponse)).thenReturn(bearRequest);
        when(bear.consume(bearRequest)).thenReturn(123);

        assertThat(trashPanda.accept(trashRequest)).isEqualTo(123);
    }

}