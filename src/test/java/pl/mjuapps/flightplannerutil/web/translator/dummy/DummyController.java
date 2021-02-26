package pl.mjuapps.flightplannerutil.web.translator.dummy;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static pl.mjuapps.flightplannerutil.web.translator.ExceptionTranslatorTest.DUMMY_ROOT;
import static pl.mjuapps.flightplannerutil.web.translator.ExceptionTranslatorTest.ILLEGAL_ARG_PATH;

@RestController
@RequestMapping(DUMMY_ROOT)
public class DummyController {

    @GetMapping(ILLEGAL_ARG_PATH)
    public void throwsIllegalArgumentException() {
        throw new IllegalArgumentException("testing " + ILLEGAL_ARG_PATH);
    }

}