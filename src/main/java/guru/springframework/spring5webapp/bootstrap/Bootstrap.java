package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.model.Author;
import guru.springframework.spring5webapp.model.Book;
import guru.springframework.spring5webapp.model.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class Bootstrap implements ApplicationListener<ContextRefreshedEvent> {
    private AuthorRepository authorRepository;
    private BookRepository bookRepository;
    private PublisherRepository publisherRepository;

    public Bootstrap(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        initData();
    }

    private void initData() {
        //Eric
        Author eric=new Author("Eric","Evans");
        Publisher publisher=new Publisher();
        publisher.setName("Harper Collins");
        publisher.setAddress("USA");
        publisherRepository.save(publisher);

        Book ddd=new Book("Domain Driven Design","1234",publisher);
        eric.getBooks().add(ddd);
        authorRepository.save(eric);
        bookRepository.save(ddd);


        //Rod
        Author rod=new Author("Rod","Johnsan");
        Publisher publisher2=new Publisher();
        publisher2.setName("TMH");
        publisher2.setAddress("UK");
        publisherRepository.save(publisher2);
        Book noEjb=new Book("J2EE Development without EJB","1235",publisher2);
        rod.getBooks().add(ddd);

        authorRepository.save(rod);
        bookRepository.save(noEjb);

    }
}
