package dhslrl321.controller;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ByeController {

    public String bye(String name) {
        log.info("hello");
        return name + ", bye ! farewell to spring";
    }
}
