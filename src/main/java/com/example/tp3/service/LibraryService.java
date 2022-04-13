package com.example.tp3.service;

import com.example.tp3.models.library.*;
import com.example.tp3.models.users.Client;
import com.example.tp3.repository.*;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class LibraryService {

    //Repositories
    private final AdminRepository adminRepository;
    private final ClientRepository clientRepository;
    private final DetteRepository detteRepository;
    private final DocumentRepository documentRepository;
    private final EmpruntRepository empruntRepository;
    private final LivreRepository livreRepository;
    private final MediaRepository mediaRepository;

    public LibraryService(AdminRepository adminRepository, ClientRepository clientRepository, DetteRepository detteRepository, DocumentRepository documentRepository, EmpruntRepository empruntRepository, LivreRepository livreRepository, MediaRepository mediaRepository) {
        this.adminRepository = adminRepository;
        this.clientRepository = clientRepository;
        this.detteRepository = detteRepository;
        this.documentRepository = documentRepository;
        this.empruntRepository = empruntRepository;
        this.livreRepository = livreRepository;
        this.mediaRepository = mediaRepository;
    }
    //Library
    public Livre saveLivre(String title, String author, String editor, long exemplaires, int releaseYear, int nbPages, String genre){
        return livreRepository.save(Livre.builder().title(title).author(author).editor(editor).exemplaires(exemplaires).releaseYear(releaseYear).nbPages(nbPages).genre(genre).build());
    }
    public Media saveMedia(String title, String author, String editor, long exemplaires, int releaseYear, String length, MediaType type){
        return mediaRepository.save(Media.builder().title(title).author(author).editor(editor).exemplaires(exemplaires).releaseYear(releaseYear).length(length).type(type).build());
    }
    //TODO FindWithTitle
    public List<Document> findDocumentWithTitle(String title){
        return documentRepository.findDocumentWithTitle(title);
    }

    //TODO FindWithAuthor
    public List<Document> findDocumentWithAuthor(String author){
        return documentRepository.findDocumentWithAuthor(author);
    }

    //TODO FindWithYear
    public List<Document> findDocumentWithYear(int year){
        return documentRepository.findDocumentWithYear(year);
    }

    //TODO FindWithCategory
    public List<Document> findDocumentWithCategory(String category){
        return documentRepository.findDocumentWithCategory(category);
    }








    //Client
    public Client saveClient(String name, String address){
        return clientRepository.save(Client.builder().clientName(name).clientAddress(address).build());
    }

    public List<Client> getClients(){
        return clientRepository.findAll();
    }

    //TODO PayDebt



    //TODO Manage Exception
    @Transactional
    public void borrowDocument(long clientId, long documentId) throws  Exception{
        Document document = documentRepository.findById(documentId).get();
        Client client = clientRepository.findByIdWithEmprunts(clientId);
        client.setDettes(clientRepository.findByIdWithFines(clientId).getDettes());

        if(client.getDettes() != null){
            throw new Exception("Error");
        }

        Emprunt emprunt = Emprunt.builder().doc(document).client(client).dateDeRetour(LocalDate.now().plusDays(21)).build();

        client.getEmprunts().add(emprunt);
        clientRepository.save(client);
        empruntRepository.save(emprunt);
    }

    //TODO Return
    public void returnBook(long bookId, long clientId) {
        final Client client = clientRepository.findByIdWithFines(clientId);
        final Document document = livreRepository.getById(bookId);
        final Dette dette = client.returnDocument(document);
        if(dette != null){
            dette.getEmpruntsEndettes().add(empruntRepository.getWithClientIdAndBookId(bookId,clientId));
            detteRepository.save(dette);
        }

    }

    //TODO GetEmprunts
    public ArrayList<Emprunt> getEmprunts(long clientId){
        return empruntRepository.findEmprunts(clientId);
    }

    public Client saveClient(Client toClient) {
        return clientRepository.save(toClient);
    }

    public void saveLivre(Livre livre) {
        livreRepository.save(livre);
    }

    public List<Livre> getLivres() {
        return livreRepository.findAll();
    }

    public List<Media> getMedias() {
        return mediaRepository.findAll();
    }

    public void saveMedia(Media media) {
        mediaRepository.save(media);
    }
}
