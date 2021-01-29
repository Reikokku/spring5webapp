package rkku.springframework.spring5webapp.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import rkku.springframework.spring5webapp.domain.Author;
import rkku.springframework.spring5webapp.domain.Book;
import rkku.springframework.spring5webapp.domain.Publisher;
import rkku.springframework.spring5webapp.repositories.AuthorRepository;
import rkku.springframework.spring5webapp.repositories.BookRepository;
import rkku.springframework.spring5webapp.repositories.PublisherRepository;

@Component
public class BootStrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Author eric = new Author("Eric", "Evans");
        Book ddd = new Book("Domain Driven Design", "12344321");
        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);

        authorRepository.save(eric);
        bookRepository.save(ddd);

        Author rod = new Author("Rod", "Johnson");
        Book noEJB = new Book("J2EE Development without EJB", "16346179873120");

        rod.getBooks().add(noEJB);
        noEJB.getAuthors().add(rod);

        authorRepository.save(rod);
        bookRepository.save(noEJB);

        Publisher literal = new Publisher("Literal", "Some address", "Some City", "Some state", "Some zip" );

        publisherRepository.save(literal);

        ddd.setPublisher(literal);
        literal.getBooks().add(ddd);
        publisherRepository.save(literal);

        noEJB.setPublisher(literal);
        literal.getBooks().add(noEJB);
        publisherRepository.save(literal);


        System.out.println("Starting a Bootstrap");
        System.out.println("Number of books: " + bookRepository.count());
        System.out.println(literal);
        System.out.println("Number of publishers: " + publisherRepository.count());
        System.out.println("Publisher number of books: " + literal.getBooks().size());
    }
}
