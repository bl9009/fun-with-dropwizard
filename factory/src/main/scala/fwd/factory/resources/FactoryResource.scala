package fwd.factory.resources

import org.springframework.web.bind.annotation.{GetMapping, RequestMapping, RestController}

@RestController
@RequestMapping(path = Array("/factory"))
class FactoryResource {
    @GetMapping(path = Array("/fries"))
    def getFries(): Int = {
        return 123
    }
}
