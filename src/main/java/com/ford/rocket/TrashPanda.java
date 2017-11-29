package com.ford.rocket;

import javax.inject.Inject;
import java.util.logging.Logger;

public class TrashPanda {

    @Inject
    private Fox fox;
    @Inject
    private Logger logger;
    @Inject
    private Bear bear;
    @Inject
    private Ranger joe;

    public int accept(TrashRequest trashRequest) {
        FoxResponse foxResponse = fox.process(trashRequest);
        if (responseIsInvalid(foxResponse)) {
            logger.severe("Oops! The Fox couldn't handle the Trash.");
            return foxResponse.getStatus();
        }
        logger.info(String.format("Mr Fox hooked it up! We got: %d", foxResponse.getBody()));
        return bear.consume(joe.prepareForBear(trashRequest, foxResponse));
    }

    private boolean responseIsInvalid(FoxResponse foxResponse) {
        return foxResponse.getStatus() != 201;
    }
}
