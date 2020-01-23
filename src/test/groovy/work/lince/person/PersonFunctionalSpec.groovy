package work.lince.person

import groovyx.net.http.ContentType
import groovyx.net.http.RESTClient
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.web.server.LocalServerPort
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Unroll
import work.lince.person.model.Person
import work.lince.person.model.PersonStatus
import work.lince.person.repository.PersonRepository
/**
 * @author pzatta
 */

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PersonFunctionalSpec extends Specification {

    @Shared
    RESTClient client

    @LocalServerPort
    int port;

    @Autowired
    PersonRepository projectRepository

    def setup() {
        client = new RESTClient("http://localhost:${port}/")
        client.contentType = ContentType.JSON
    }

    @Unroll
    def "get Success"() {
        given:
            projectRepository.save(new Person(title: title))

        when:
            def result = client.get(path: "projects")

        then:
            result != null


        where:
            title            | _
            "Projet Title 1" | _


    }


    @Unroll
    def "Create Projetc #title"() {
        given:
            def body = [
                title: title,
                status : status
            ]


        when:
            def result = client.post(path: "projects", body: body, headers: ["lince.user.name": userName])

        then:
            result != null
            result.data.id != null
            result.data.title == title
            result.data.status == PersonStatus.CREATED.toString()
            result.data.owner == expectedOwner

        where:
            title             | userName   | status                || expectedOwner
            "Project Title 1" | null       | null                  || 'anonymous'
            "Project Title 2" | 'x1324'    | PersonStatus.CREATED || 'x1324'
            "Project Title 3" | 'zxcvasdf' | PersonStatus.CLOSED  || 'zxcvasdf'


    }

}