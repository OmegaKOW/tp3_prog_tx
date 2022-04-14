package com.example.tp3.service;

import com.example.tp3.models.library.*;
import com.example.tp3.models.users.Client;
import com.example.tp3.repository.*;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

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

    public List<Document> findDocumentWithTitle(String title){
        return documentRepository.findDocumentWithTitle(title);
    }


    public List<Document> findDocumentWithAuthor(String author){
        return documentRepository.findDocumentWithAuthor(author);
    }


    public List<Document> findDocumentWithYear(int year){
        return documentRepository.findDocumentWithYear(year);
    }


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


    public void payDebts(long clientId){
        Client c = clientRepository.findByIdWithFines(clientId);
        c.getDettes().clear();
        clientRepository.save(c);
        System.out.println("Dettes payées");
    }


    @Transactional
    public void borrowDocument(long clientId, long documentId) throws  Exception{
        Document document = documentRepository.findById(documentId).get();
        Client client = clientRepository.findById(clientId).get();
        client.setDettes(clientRepository.findByIdWithFines(clientId).getDettes());

        if(document.getExemplaires() < 1){throw new Exception("Erreur");}
        if(client.isHasDebt()){
            throw new Exception("Error");
        }



        document.setExemplaires(document.getExemplaires() - 1);
        documentRepository.save(document);
        Emprunt emprunt = Emprunt.builder().doc(document).client(client).dateDeRetour(LocalDate.now().minusDays(1)).build();
        client.getEmprunts().add(emprunt);
        clientRepository.save(client);
    }

    //TODO Return
    public void returnDocument(long bookId, long clientId) {
        final Emprunt e = empruntRepository.getWithClientIdAndBookId(bookId,clientId);
        final Client c = clientRepository.findByIdWithFines(clientId);
        final Dette dette = checkDettes(e);

        if(dette != null){
            c.getDettes().add(dette);
            clientRepository.save(c);
        }
    }

    public Dette checkDettes(Emprunt e){
        if(e.getDateDeRetour().isBefore(LocalDate.now())){
            double cout = e.getDateDeRetour().until(LocalDate.now(), ChronoUnit.DAYS);
            Dette dette = Dette.builder().dette(cout * 0.25).client(e.getClient()).build();
            dette.setEmpruntEndette(e);
            return dette;
        }
        return null;
    }

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

    public Optional<Client> findClientWithId(long id) {
        return clientRepository.findById(id);
    }

    public Optional<Document> findDocumentWithId(long id) {
        return documentRepository.findById(id);
    }

    public Client findByIdWithAll(long id) {
        return clientRepository.findByIdWithFines(id);
    }
}
