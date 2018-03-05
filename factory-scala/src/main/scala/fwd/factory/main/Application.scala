package fwd.factory.main

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class FactoryApplication

object FactoryApplication {
    def main(args: Array[String]): Unit = SpringApplication.run(classOf[FactoryApplication], args: _*)
}
