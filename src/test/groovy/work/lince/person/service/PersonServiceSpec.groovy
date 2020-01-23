package work.lince.person.service

import spock.lang.Specification
import spock.lang.Unroll
import work.lince.commons.authentication.AuthenticationService
import work.lince.person.model.Person
import work.lince.person.model.PersonStatus
import work.lince.person.repository.PersonRepository
/**
 * @author pzatta
 */

class PersonServiceSpec extends Specification {

    PersonService service;

    def setup() {
        service = Spy(PersonService)
        service.repository = Mock(PersonRepository)
        service.authenticationService = Mock(AuthenticationService)

    }

    @Unroll
    def "verify with #title"() {
        given:
            1 * service.repository.save(_) >> { Person value ->
                value.id = id
                return value
            }
            1 * service.authenticationService.getAuthenticatedUser() >> { user }
            def project = new Person(
                title: title,
                status: status,
                owner: owner
            )
        when:
            def result = service.create(project)

        then:
            result != null
            result.id == id
            result.title == title
            result.owner == user
            result.status == PersonStatus.CREATED

        where:
            title             | status               | owner      | user   | id
            "Project Title 1" | null                 | "asdfasdf" | "asdf" | 1L
            "Project Title 2" | PersonStatus.CLOSED | null       | "qwer" | 2L
            "Project Title 3" | null                 | null       | "asdf" | 3L
            "Project Title 4" | PersonStatus.CLOSED | "asdfasdf" | "qwer" | 4L


    }

}